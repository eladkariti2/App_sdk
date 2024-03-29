package com.application.base.behaviour;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.application.activities.FacebookAuthenticationActivity;
import com.application.gps.listener.APLocationListenr;
import com.application.interfaces.BaseActivityFacebookAuthoriziationI;
import com.application.messagebroker.APBrokerNotificationTypes;
import com.application.messagebroker.APMessageBroker;
import com.application.messagebroker.IAPBrokerListener;
import com.application.text.APConstant;
import com.application.utils.AppData;
import com.facebook.CallbackManager;
import com.google.gson.Gson;

public class BaseBehaviour {

	public static void  onCreate(Activity activity, Bundle savedInstanceState){

		registerTolistener(activity);

	}

	private static void registerTolistener(Activity activity) {
		APMessageBroker.getInstance().addListener(APBrokerNotificationTypes.AP_BROKER_UPDATE_LOCATION,(IAPBrokerListener) activity);
	}

	public static void onResume(Activity activity){


	}


	public static void onPause(Activity activity){

	}

	public static void onActivityResult(Activity activity,int requestCode, int resultCode, Intent data) {
		if(activity instanceof BaseActivityFacebookAuthoriziationI){
			//this call is to init the callback manager if needed

			CallbackManager manager = getFBCallbackManager((BaseActivityFacebookAuthoriziationI) activity);
			manager.onActivityResult(requestCode,resultCode,data);
		}
	}
	private static CallbackManager getFBCallbackManager(BaseActivityFacebookAuthoriziationI activity) {
		return activity.getFBCallBackManager();
	}
	public static void onSaveInstanceState(Activity activity,Bundle outState) {

	}

	public static void onStart(Activity activity,LocationManager locationManager,APLocationListenr locationListenr){
		//locationListenr = new APLocationListenr();
		startLocationListener(activity,locationManager,locationListenr);

	}

	private static void startLocationListener(Activity activity,LocationManager locationManager,APLocationListenr locationListenr) {
		// TODO Auto-generated method stub
		if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ){
			buildAlertMessageNoGps(activity);
		}
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0,60*  1000,locationListenr);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 60* 1000,locationListenr);
	}

	public static void stopLocationListener(LocationManager locationManager,APLocationListenr locationListenr) {
		locationManager.removeUpdates(locationListenr);
	}

	private static void buildAlertMessageNoGps(final Context context) {
		// TODO Auto-generated method stub
		String message = "GPS DISABLED";
		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		builder.setPositiveButton("YES", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				context.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));

			}
		});

		builder.setNegativeButton("NO",  new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();		
			}
		});

		builder.setMessage(message);

		AlertDialog alert = builder.create();
		alert.show();
	}

	public static void onStop(Activity activity,LocationManager locationManager,APLocationListenr locationListenr){
		stopLocationListener(locationManager, locationListenr);

	}

	public static void onDestroy(Activity activity){

	}


	public static void onBrokerEventOccurred(Integer eventType,Object eventParams) {
		// TODO Auto-generated method stub
		switch (eventType) {
		case APBrokerNotificationTypes.AP_BROKER_UPDATE_LOCATION:
			Location location = (Location)eventParams;
			Log.d("BaseBehaviur","User location update - longitude = " + location.getLongitude()  +", Latitude = " + location.getLatitude());
			AppData.saveUserLocation(location);
			break;

		default:
			break;
		}
	}



}
