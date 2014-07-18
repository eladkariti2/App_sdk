package com.application;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

public class CustomApplication  extends Application{
	
	protected static Context context;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		onCreateBehaviour(this);
	}

	public static void onCreateBehaviour(Application context) {
		CustomApplication.context = context.getApplicationContext();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	} 
	
	public static Context getAppContext(){
		return context;
	}
}
