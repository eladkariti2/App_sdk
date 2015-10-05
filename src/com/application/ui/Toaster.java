package com.application.ui;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * Created by elad on 10/5/2015.
 */

public class Toaster {

    public static void toast(Context context, View defaultMessage){
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(defaultMessage);
        toast.show();
    }


    public static void toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }


    public static void shortToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void makeToast(Context context, String message) {
        toast(context, message);
    }

}