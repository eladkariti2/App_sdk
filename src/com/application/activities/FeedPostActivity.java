package com.application.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.application.base.BaseActivity;
import com.application.facebook.interfaces.FacebookLoaderI;
import com.application.facebook.listener.FacebookLoaderListener;
import com.application.facebook.model.FbModel;
import com.application.facebook.util.FacebookUtil;
import com.application.utils.OSUtil;

public class FeedPostActivity extends BaseActivity {
	
	protected static final String TAG = "FeedPostActivity";

	private static String ADD_PHOTO = "add_photo";
	
	View postButton;
	EditText postText;
	View postActionView;
	
	FacebookLoaderListener listener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(OSUtil.getLayoutResourceIdentifier("feed_post_layout"));
		postText = (EditText)findViewById(OSUtil.getResourceId("post_text"));
		postButton = findViewById(OSUtil.getResourceId("post_button"));
		postActionView = findViewById(OSUtil.getResourceId("post_layout_container"));
		
		
		postButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				postToFacebook();
			}

			
		});
		listener = new FacebookLoaderListener(this,new FacebookLoaderI() {
			
			@Override
			public void onSuccess(FbModel model) {
				// TODO Auto-generated method stub
				FeedPostActivity.this.finish();
			}
			
			@Override
			public void onFailure(Exception e) {
				// TODO Auto-generated method stub
				Log.e(TAG, e.getMessage());
			}
		});
	}
	
	private void postToFacebook() {
		
		FacebookUtil.postFeedTofacebook(this,listener);
	}
	
	
	
	
	public static void startFeedPostActivity(Context context,boolean withPhoto){
		Intent i = new Intent (context,FeedPostActivity.class);
		i.putExtra(ADD_PHOTO, withPhoto);
		
		context.startActivity(i);
	}

}
