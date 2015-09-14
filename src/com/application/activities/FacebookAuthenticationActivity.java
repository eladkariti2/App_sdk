package com.application.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.application.base.BaseActivity;
import com.application.facebook.listener.FBAuthoriziationListener;
import com.application.facebook.permissions.APFBPermissions;
import com.application.facebook.permissions.APPermissionsType;
import com.application.facebook.permissions.BasicFBPermissions;
import com.application.facebook.util.FacebookUtil;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;


public class FacebookAuthenticationActivity extends BaseActivity {
	
	
	public static final String TAG = "FacebookAuthenticationActivity";
	
	public static final int FACEBOOK_AUTH_RESULT = 110;
	private static final String AUTH_LISTENER = "APFacebookAuthoriziationListener";
	private static final String AUTH_PERMISSIONS = "AuthPermissions";

	CallbackManager callbackManager;
	static FBAuthoriziationListener mListener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "Start Facebook Authoriziation");

        FBAuthoriziationListener listener = (FBAuthoriziationListener)getIntent().getSerializableExtra(AUTH_LISTENER);
		APPermissionsType type = (APPermissionsType)getIntent().getSerializableExtra(AUTH_PERMISSIONS);
		callbackManager = CallbackManager.Factory.create();
		LoginManager.getInstance().registerCallback(callbackManager,new APFacebookCallback(this,mListener,type));

		continueFacebookLogin();
	}


	//start to
	private void continueFacebookLogin() {
		Log.d(TAG, "Start Facebook login,Basic permissions : " + BasicFBPermissions.getInstance().getBasicPermissions());
		LoginManager.getInstance().logInWithReadPermissions(this, BasicFBPermissions.getInstance().getBasicPermissions());
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		callbackManager.onActivityResult(requestCode, resultCode, data);
	}


	public static void launchAPFacebookAutherization(Activity context, FBAuthoriziationListener listener,APPermissionsType type){
		Intent intent = new Intent(context,FacebookAuthenticationActivity.class);
		mListener = listener;

		intent.putExtra(AUTH_PERMISSIONS,type);

		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		context.startActivity(intent);
	}



	private class APFacebookCallback implements FacebookCallback<LoginResult> {

		Context context;
		FBAuthoriziationListener listener;

		boolean hasPendingPublishPermissions;
		boolean hasPendingReadPermissions;
		APFBPermissions permissions;

		public APFacebookCallback(Context context, FBAuthoriziationListener listener,APPermissionsType type){
			this.context = context;
			this.listener = listener;
			permissions =  FacebookUtil.getPermissionsByType(type);
			hasPendingReadPermissions = permissions.getReadPermissions() != null;
			hasPendingPublishPermissions = permissions.getPublishPermissions() != null;
		}


		@Override
		public void onSuccess(LoginResult result) {
			AccessToken token =  result.getAccessToken();
			Log.d(TAG, "Facebook Authoriziation success, permissions: " + token.getPermissions().toString());

			if(hasPendingPublishPermissions && FacebookUtil.publishPermissionRequestHasChanged(permissions) ){
				Log.d(TAG, "Update Facebook Token ,with permissions : " + permissions.getPublishPermissions());
				LoginManager.getInstance().logInWithPublishPermissions(FacebookAuthenticationActivity.this, permissions.getPublishPermissions());
				hasPendingPublishPermissions = false;
			}
			else if(hasPendingReadPermissions && FacebookUtil.readPermissionRequestHasChanged( permissions) ){
				Log.d(TAG, "Update Facebook Token ,with permissions : " + permissions.getReadPermissions());
				LoginManager.getInstance().logInWithReadPermissions(FacebookAuthenticationActivity.this, permissions.getReadPermissions());
				hasPendingReadPermissions = false;
			}
			else{
				onFinishedSuccessfully();
			}



		}

		@Override
		public void onCancel() {
			Log.d(TAG, "Facebook Authoriziation canceled ");

			if(listener != null){
				listener.onCancel();
			}
			closeActivity();
		}

		@Override
		public void onError(FacebookException e) {
			String errorMessage  = e.getMessage();
			Log.d(TAG, "Facebook Authoriziation error: " + errorMessage);

			if(listener != null){
				listener.onError(e);
			}
			closeActivity();

		}


		private void onFinishedSuccessfully() {

			if(listener != null){
				listener.onSuccess();
			}

			closeActivity();
		}





		private void closeActivity() {
			finish();
		}
	}
}
