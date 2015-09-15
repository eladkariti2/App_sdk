package com.application.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.base.BaseActivity;
import com.application.facebook.listener.FBAuthoriziationListener;
import com.application.facebook.permissions.APPermissionsType;
import com.application.facebook.util.FacebookUtil;
import com.application.utils.OSUtil;

public class FacebookLoginActivity extends BaseActivity {
	ImageView mUserImg;
	TextView mUserText;
	TextView mLoginText;
	View mTextContainer;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(OSUtil.getLayoutResourceIdentifier("facebook_login"));

		View view = findViewById(OSUtil.getResourceId("facebook_connect_container"));

		view.setOnClickListener(loginInClickListener);
	}

	OnClickListener loginInClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			FacebookUtil.clearToken();
			FacebookUtil.loginTofacebook(FacebookLoginActivity.this, new FBAuthoriziationListener() {
				@Override
				public void onError(Exception error) {

				}

				@Override
				public void onSuccess() {
					setResult(FacebookAuthenticationActivity.FACEBOOK_AUTH_RESULT);
					finish();
				}

				@Override
				public void onCancel() {

				}
			}, APPermissionsType.Basic);
		}
	};
	
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == FacebookAuthenticationActivity.FACEBOOK_AUTH_RESULT){
			setResult(FacebookAuthenticationActivity.FACEBOOK_AUTH_RESULT);
			finish();
		}
		
	}
}
