package com.application.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.application.app.CustomApplication;
import com.application.base.behaviour.BaseBehaviour;
import com.application.facebook.util.FacebookUtil;
import com.application.gps.listener.APLocationListenr;
import com.application.interfaces.BaseActivityFacebookAuthoriziationI;
import com.application.messagebroker.IAPBrokerListener;
import com.application.utils.AppData;
import com.application.utils.StringUtil;
import com.facebook.CallbackManager;


public class BaseActivity extends Activity implements BaseActivityFacebookAuthoriziationI,IAPBrokerListener{
	
	
	
	private static final String TAG = "BaseActivity";
	protected APLocationListenr mLocationListenr = new APLocationListenr();
	protected LocationManager mLocationManager ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// Acquire a reference to the system Location Manager
		mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		BaseBehaviour.onCreate(this, savedInstanceState);
		
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		BaseBehaviour.onStart(this, mLocationManager, mLocationListenr);

	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		BaseBehaviour.onResume(this);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		BaseBehaviour.onPause(this);
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		BaseBehaviour.onStop(this,mLocationManager,mLocationListenr);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		BaseBehaviour.onDestroy(this);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		
		BaseBehaviour.onSaveInstanceState(this, outState);
	}
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		BaseBehaviour.onActivityResult(this, requestCode, resultCode, data);
	}
	



	@Override
	public void onBrokerEventOccurred(Integer eventType, Object eventParams) {
		// TODO Auto-generated method stub
		BaseBehaviour.onBrokerEventOccurred(eventType, eventParams);
	}

	protected CallbackManager mCallbackManager;

	@Override
	public CallbackManager getFBCallBackManager() {
		if(mCallbackManager == null){
			mCallbackManager = CallbackManager.Factory.create();
		}
		return mCallbackManager  ;
	}
}
