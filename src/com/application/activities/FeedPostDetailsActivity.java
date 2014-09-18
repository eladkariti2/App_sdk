package com.application.activities;

import android.content.Context;
import android.content.Intent;

import com.application.base.BaseActivity;
import com.application.imageholders.FBPostHolder;

public class FeedPostDetailsActivity extends BaseActivity {

	
	
	
	
	
	public static void startFeedPostDetailsActivity(Context context,FBPostHolder holder){
		
		Intent intent = new Intent(context,FeedPostDetailsActivity.class);
		context.startActivity(intent);
	}
}
