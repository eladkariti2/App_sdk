package com.application.bg;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.application.app.CustomApplication;
import com.application.facebook.model.FBProfilePic;
import com.application.facebook.util.FacebookUtil;
import com.application.listener.GeneralModelListener;
import com.application.loader.ModelLoader;
import com.application.models.GenericModel;
import com.application.utils.PreferenceUtil;


public class LocationUpdaterService extends Service implements LocationListener {

	private static final String TAG = "LocationUpdaterService";
	private static final String LAST_UPDATE_TIME = "LocationUpdaterService";

	protected LocationManager mLocationManager ;
	static int  mCounter =0;


	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.d(TAG,"onCreate");
		if(CustomApplication.isActivityVisible()){
			Log.d(TAG, "isActivityVisible");
			handleAppVisible();
		}else{
			init();
		}
	}

	private void handleAppVisible() {
		updateLastUpdateTime(-1);
		stopSelf();
	}

	private void init() {
		Log.d(TAG, "init LocationUpdaterService");
		updateLastUpdateTime(getLastUpdateTime()+1);
		mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		if(!mLocationManager .isProviderEnabled(LocationManager.GPS_PROVIDER) ){
			return;
		}
		mLocationManager .requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 600 * 1000, this);
		mLocationManager .requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 600 * 1000, this);

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return START_NOT_STICKY;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d(TAG, "onDestroy LocationUpdaterService");
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onBind LocationUpdaterService");
		return null;
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onLocationChanged LocationUpdaterService");
		FBProfilePic userProfile = FacebookUtil.getUserProfile();
		if(userProfile != null ) {

			ModelLoader.updateOrCreateUser(new UpdateServiceLocationListener(CustomApplication.getAppContext(), location.getLatitude() + "", location.getLongitude() + "")
					, location, userProfile);
		}else{
			Log.d(TAG, "User FB profile is null");

		}

		if( CustomApplication.getAppContext() == null){
			Log.d(TAG, "CustomApplication is null");

		}
		if( CustomApplication.isActivityVisible()){
			Log.d(TAG, "isActivityVisible =  true");

		}else{
			Log.d(TAG, "isActivityVisible =  false");
		}
		stopSelf();
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onStatusChanged LocationUpdaterService");
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onProviderEnabled LocationUpdaterService");

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onProviderDisabled LocationUpdaterService");

	}

	public void updateLastUpdateTime(int lastUpdateTime) {
		PreferenceUtil.getInstance().setIntPref(LAST_UPDATE_TIME, lastUpdateTime);
	}

	public int getLastUpdateTime() {
		int counter = PreferenceUtil.getInstance().getIntPref(LAST_UPDATE_TIME,-1);
		if(counter > 2){
			stopBroadcastReciver();
		}
		return counter;
	}

	private void stopBroadcastReciver() {
		Log.d(TAG, "stopBroadcastReciver");
		Intent i = new Intent();
		i.setAction("com.application.app.STOP_LOCATION_RECIVER");
		sendBroadcast(i);

	}

	public class UpdateServiceLocationListener extends GeneralModelListener {
		String mLatitude;
		String mLongitude;

		public  UpdateServiceLocationListener(Context context,String latitude,String longitude){
			super(context);
			mLongitude = longitude;
			mLatitude = latitude;
		}

		@Override
		public void onTaskStart() {
			Log.i(TAG, "start update location: latitude : " + mLatitude + ", longitude : " + mLongitude);
		}

		@Override
		public void onTaskComplete(GenericModel result) {
			Log.i(TAG, "finsh successfully to update location: latitude : " + mLatitude + ", longitude : " + mLongitude);
			stopSelf();
		}

		@Override
		public void handleException(Exception e) {
			Log.i(TAG, "Error update location: latitude : " + mLatitude + ", longitude : " + mLongitude);
			stopSelf();
			super.handleException(e);

		}
	}

}
