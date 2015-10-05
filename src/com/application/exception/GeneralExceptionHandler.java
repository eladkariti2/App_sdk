package com.application.exception;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.ui.Toaster;
import com.application.utils.OSUtil;

public  class GeneralExceptionHandler {

	public static void handleException(Context context, Exception e) {
		boolean connectedToNetwork = OSUtil.hasNetworkConnection(context);
		if(connectedToNetwork){
			showToast(context, context.getString(OSUtil.getStringResourceIdentifier("general_error")));
		}
		else{
			showToast(context, context.getString(OSUtil.getStringResourceIdentifier("no_connection")));
		}
	}

	// This is to center the no connection toast. If toast implementation changes in the future and it throws and classcast exception,
	// then we'll show it the regular way.
	private static void showToast(Context context, String msg){
		try{
			Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
			((TextView)((LinearLayout)toast.getView()).getChildAt(0)).setGravity(Gravity.CENTER_HORIZONTAL);
			toast.show();
		}
		catch (Exception ex){
			Toaster.toast(context, msg);
		}
	}

	
}
