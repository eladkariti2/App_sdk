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

import com.application.utils.OSUtil;

public class ConectivityReciver extends BroadcastReceiver {

	private static final String TAG = "ConectivityReciver";

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		 Log.d(TAG, "action: " + intent.getAction());
		 final AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
	     final PendingIntent wakeupIntent = PendingIntent.getService(context, 0,new Intent(context, LocationUpdaterService.class), PendingIntent.FLAG_UPDATE_CURRENT);
		 boolean isNetworkAvailable = OSUtil.hasNetworkConnection(context);
		 Log.d(TAG, "isNetworkAvailable: " + "" + isNetworkAvailable );
		 if (isNetworkAvailable && checkAction(intent)) {
	            // start service now for doing once
	            context.startService(new Intent(context, LocationUpdaterService.class));

	            // schedule service for every 15 minutes
	            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
	                    SystemClock.elapsedRealtime() + 500,
	                    500, wakeupIntent);
	        } else {
	            alarmManager.cancel(wakeupIntent);
	        }
	
	}

	private boolean checkAction(Intent intent) {
		if("com.application.app.LOCATION_RECIVER".equals(intent.getAction())){
			return true;
		}
		return false;
	}


}
