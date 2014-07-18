package com.application.base;

import com.application.base.behaviour.BaseBehaviour;
import com.application.facebook.util.FacebookUtil;
import com.application.interfaces.BaseActivityFacebookAuthoriziationI;
import com.application.utils.AppData;
import com.application.utils.StringUtil;
import com.facebook.Session;
import com.facebook.UiLifecycleHelper;
import com.facebook.Session.StatusCallback;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class BaseFragmentActivity extends FragmentActivity implements BaseActivityFacebookAuthoriziationI{
	
	
	
	private static final String TAG = "BaseActivity";
	protected UiLifecycleHelper facebookSessionLifeCycleHelper;
	protected Session.StatusCallback facebookSessionCallback;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		BaseBehaviour.onCreate(this, savedInstanceState);
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
		BaseBehaviour.onStop(this);
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
	public UiLifecycleHelper getFacebookSessionLifecycleHelper() {
		String fbID = AppData.getFacebookAppId(this);
		if(!StringUtil.isEmpty(fbID)){
			if(facebookSessionLifeCycleHelper == null){
				facebookSessionLifeCycleHelper = new UiLifecycleHelper(this, new FacebookUtil.SessionStatusCallback(this,  initFacebookSessionCallback()));
			}
		}else{
			Log.e(TAG,"Facebook app ID can't be null, check if 'fb_app_id' decleared on strings");
		}
		return facebookSessionLifeCycleHelper;
	}

	@Override
	public StatusCallback initFacebookSessionCallback() {
		// TODO Auto-generated method stub
		return null;
	}
}