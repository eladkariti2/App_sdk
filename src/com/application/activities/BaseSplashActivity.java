package com.application.activities;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.location.Location;
import android.os.Bundle;
import android.widget.ImageView;

import com.application.base.BaseActivity;
import com.application.base.behaviour.BaseBehaviour;
import com.application.facebook.loader.APUserProfileRequest;
import com.application.facebook.model.FBModel;
import com.application.facebook.util.FacebookUtil;
import com.application.interfaces.AccountLoadI;
import com.application.listener.AsyncTaskListener;
import com.application.messagebroker.APBrokerNotificationTypes;
import com.application.messagebroker.APMessageBroker;
import com.application.models.AccountModel;
import com.application.ui.SplashLoader;
import com.application.utils.AppData;
import com.application.utils.OSUtil;

public abstract class BaseSplashActivity extends BaseActivity implements AccountLoadI{

	private SplashLoader mSplashLoader; 
	protected AccountModel mAccount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(OSUtil.getLayoutResourceIdentifier("splash_layout"));
		mSplashLoader = (SplashLoader) findViewById(OSUtil.getResourceId("splash_loader"));
		ImageView loaderAnimation = (ImageView) findViewById(OSUtil.getResourceId("progress_indicator"));
		AnimationDrawable progressAnimation = (AnimationDrawable) loaderAnimation.getDrawable();
		progressAnimation.start();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		timer = new Timer();
//		timer.schedule(new TimerTask() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				APMessageBroker.getInstance().fireNotificationsByType(APBrokerNotificationTypes.AP_BROKER_UPDATE_LOCATION,new Location(""));
//			}
//		}, 2000);
		
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	Timer timer;

	@Override
	public abstract void onAccountLoaded(AccountModel account);

	protected abstract void onIntroLoaded();

	@Override
	public void onBrokerEventOccurred(Integer eventType, Object eventParams) {
		// TODO Auto-generated method stub
		switch (eventType) {
		case APBrokerNotificationTypes.AP_BROKER_UPDATE_LOCATION:
			Location location = (Location)eventParams;
			AppData.saveUserLocation(location);
			//Stop listen to event because the instance still live on the APMessageBroker
			//and this activity still get called when the event raised.
			APMessageBroker.getInstance().removeListener(APBrokerNotificationTypes.AP_BROKER_UPDATE_LOCATION, BaseSplashActivity.this);
			BaseBehaviour.stopLocationListener(mLocationManager, mLocationListenr);
			boolean isConnected =  FacebookUtil.isTokenValid();
			if(isConnected){
				continueFlowUserConnected() ;
			}
			else{
				connectToFacebook();
			}
			break;

		default:
			break;
		}
	}

	private void connectToFacebook() {
		// TODO Auto-generated method stub
		Intent i = new Intent(this,FacebookLoginActivity.class);
		startActivityForResult(i, 0);
	}

	private void continueFlowUserConnected() {
		// TODO Auto-generated method stub
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				loadUserProfile();
			}
		});
	
	}

	private void loadUserProfile() {

		APUserProfileRequest req = new APUserProfileRequest(new AsyncTaskListener<FBModel>() {

			@Override
			public void onTaskStart() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTaskComplete(FBModel result) {
				FacebookUtil.setUserProfile(result);
				onIntroLoaded();
			}

			@Override
			public void handleException(Exception e) {
				// TODO Auto-generated method stub

			}
		});

		req.doQuery();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == FacebookAuthenticationActivity.FACEBOOK_AUTH_RESULT){
			continueFlowUserConnected();
		}
	}

}
