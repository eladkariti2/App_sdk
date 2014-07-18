package com.application.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.application.base.BaseActivity;
import com.application.facebook.util.FacebookUtil;
import com.facebook.Session;
import com.facebook.Session.NewPermissionsRequest;
import com.facebook.SessionState;

public class FacebookAuthenticationActivity extends BaseActivity {
	
	
	public static final String TAG = "FacebookAuthenticationActivity";
	
	public static final int FACEBOOK_AUTH_RESULT = 110;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		loginToFacebook();
	}
	
	private void loginToFacebook() {
		Session session = Session.getActiveSession();
		if (session != null && !session.isOpened() && !session.isClosed()) {
			session.openForRead(new Session.OpenRequest(this).setCallback(new SessionStatusCallback()));
		} else {
			 Session.openActiveSession(this, true, new SessionStatusCallback());
		}
	}

	private void onSuccefullyFinished(){
		setResult(FACEBOOK_AUTH_RESULT);
	    finish();
	}
	

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Session.getActiveSession().onActivityResult(this, requestCode, resultCode,  data);
	}
	

	
	public  static void StartFacebookAuthenticationActivity(Activity activity){
		Intent intent = new Intent(activity,FacebookAuthenticationActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		//context.startActivity(intent);
		activity.startActivityForResult(intent, 0);
	}

	//this is facebook session listener
		private  class SessionStatusCallback implements Session.StatusCallback {

			
			boolean hasPendingBasicPermissions = false;//it's false because  the app don't need more permission if we need to ask for more permission change it's to true.
			boolean hasPendingReadPermissions = false;
		
			
			@Override
			public void call(Session session, SessionState state,Exception exception) {

				switch (state) {
				case OPENED:
					Log.d(TAG,"session OPENED");
					Log.d(TAG,"onComplete ");
					Log.d(TAG,"permissions " + session.getPermissions());

					
					FacebookUtil.setFBAuthToken(FacebookAuthenticationActivity.this, session.getAccessToken());
					Log.d(TAG,"access token is " + session.getAccessToken());
					FacebookUtil.setFBTokenExpiration(FacebookAuthenticationActivity.this,session.getExpirationDate().getTime());
					
					if(!hasPendingBasicPermissions && FacebookUtil.publishPermissionRequestHasChanged(session, FacebookUtil.getApplicationFBPermissions())){
						session.requestNewPublishPermissions(new NewPermissionsRequest( FacebookAuthenticationActivity.this, FacebookUtil.BASIC_APP_PERMISSIONS));
						hasPendingBasicPermissions = true;
					}
					else if(hasPendingReadPermissions && FacebookUtil.readPermissionRequestHasChanged(session, FacebookUtil.getApplicationFBPermissions())){
						//session.requestNewReadPermissions(new NewPermissionsRequest(FacebookAuthenticationActivity.this, FacebookUtil.EXTENDED_READ_PERMISSIONS));
						hasPendingReadPermissions = false;
					}else{
						onSuccefullyFinished();
					}
					break;

				case OPENED_TOKEN_UPDATED:
					Log.d(TAG,"session OPENED_TOKEN_UPDATED");
					if(!hasPendingBasicPermissions && !hasPendingReadPermissions){
						onSuccefullyFinished();
					}
					else if(hasPendingBasicPermissions){
						session.requestNewPublishPermissions(new NewPermissionsRequest(FacebookAuthenticationActivity.this, FacebookUtil.BASIC_APP_PERMISSIONS));
						hasPendingBasicPermissions = false;
					}
					else if (hasPendingReadPermissions){
						//session.requestNewReadPermissions(new NewPermissionsRequest(FacebookAuthenticationActivity.this, FacebookUtil.EXTENDED_READ_PERMISSIONS));
						hasPendingReadPermissions = false;
					}

					break;
				case CLOSED:
					Log.d(TAG,"session CLOSED");
					break;
				case CLOSED_LOGIN_FAILED:
					Log.d(TAG,"CLOSED_LOGIN_FAILED state " + exception.getMessage());
					
					break;
				case CREATED:
					Log.d(TAG,"session CREATED");
					break;
				case CREATED_TOKEN_LOADED:
					Log.d(TAG,"session CREATED_TOKEN_LOADED");
					break;
				case OPENING:
					Log.d(TAG,"session OPENING");
					break;

				}

			}
		}
}
