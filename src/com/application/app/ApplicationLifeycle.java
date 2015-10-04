package com.application.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;

import com.application.models.AccountModel;
import com.application.utils.OSUtil;
import com.application.utils.StringUtil;

/**
 * Created by elad on 10/4/2015.
 */
public class ApplicationLifeycle {

    public static final String DEPRECATION_ERROR_TITLE_KEY = "deprecation_error_message_title";
    public static final String DEPRECATION_ERROR_MESSAGE_KEY = "deprecation_error_message";
    public static final String DEPRECATION_ERROR_LINK_KEY = "deprecation_button_link";

    private  AccountModel account;
    private  Activity context;
    private boolean isDepracated = false;
    String depracationTitle;
    String deprecatonMessage;
    String deprecationLink;

    public ApplicationLifeycle(Activity context,AccountModel account){
        this.context = context;
        this.account = account;
        checkIsVersionDeprecated();
    }

    private void checkIsVersionDeprecated() {
        String appCurrentVersion = OSUtil.getAppVersion(context);
        String minVersionCodeString = account.getVersionNumber();
        String [] minVersionArray = minVersionCodeString.split("\\.");
        String [] currentVersionArray = appCurrentVersion.split("\\.");

        for(int i =0 ; i < Math.min(minVersionArray.length,currentVersionArray.length); i ++){
            if(Integer.parseInt(minVersionArray[i]) > Integer.parseInt(currentVersionArray[0])){
                isDepracated = true;
                break;
            }
        }

        depracationTitle = "";
        deprecatonMessage = account.getDepracatedText();

        depracationTitle = StringUtil.isEmpty(depracationTitle) ? context.getString(OSUtil.getStringResourceIdentifier(DEPRECATION_ERROR_TITLE_KEY)) : depracationTitle;
        deprecatonMessage = StringUtil.isEmpty(deprecatonMessage) ? context.getString(OSUtil.getStringResourceIdentifier(DEPRECATION_ERROR_MESSAGE_KEY))  : deprecatonMessage;
    }

    public boolean isDeprecated() {
        return isDepracated;
    }


    public Dialog getDeprecationDialog(){
        AlertDialog.Builder builderN = new AlertDialog.Builder(context);
        builderN.setTitle(depracationTitle).setMessage(deprecatonMessage)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        OSUtil.launchBrowswer(context, deprecationLink);
                        context.finish();
                    }
                });
        return builderN.create();
    }
}
