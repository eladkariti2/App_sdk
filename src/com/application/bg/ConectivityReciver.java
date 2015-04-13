package com.application.bg;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.SystemClock;
import android.util.Log;

public class ConectivityReciver extends BroadcastReceiver {

	private static final String TAG = "ConectivityReciver";

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		 Log.d(TAG, "action: " + intent.getAction());
		 final AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
	     final PendingIntent wakeupIntent = PendingIntent.getService(context, 0,new Intent(context, LocationUpdaterService.class), PendingIntent.FLAG_UPDATE_CURRENT);
		 boolean isNetworkAvailable = haveNetworkConnection(context);
		 Log.d(TAG, "isNetworkAvailable: " + "" + isNetworkAvailable );
		 if (isNetworkAvailable) {
	            // start service now for doing once
	            context.startService(new Intent(context, LocationUpdaterService.class));

	            // schedule service for every 15 minutes
	            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
	                    SystemClock.elapsedRealtime() + 10000,
	                    10000, wakeupIntent);
	        } else {
	            alarmManager.cancel(wakeupIntent);
	        }
	
	}

	  private boolean haveNetworkConnection(Context context) {
		    boolean haveConnectedWifi = false;
		    boolean haveConnectedMobile = false;

		    ConnectivityManager cm = (ConnectivityManager)   context.getSystemService(Context.CONNECTIVITY_SERVICE);
		    NetworkInfo[] netInfo = cm.getAllNetworkInfo();
		    for (NetworkInfo ni : netInfo) {
		        if (ni.getTypeName().equalsIgnoreCase("WIFI"))
		            if (ni.isConnected())
		                haveConnectedWifi = true;
		        if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
		            if (ni.isConnected())
		                haveConnectedMobile = true;
		    }
		    return haveConnectedWifi || haveConnectedMobile;
		}
}
