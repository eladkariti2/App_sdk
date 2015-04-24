package com.application;

import java.util.Locale;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.sax.StartElementListener;

import com.application.bg.LocationUpdaterService;
import com.application.utils.AppData;

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
		AppData.loadProperties(context);
	   // context.startService(new Intent(context, LocationUpdaterService.class));
//		FlurryAgent .init
		
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	} 
	
	public static Context getAppContext(){
		return context;
	}
	
	public static Locale getApplicationLocale(){
		Locale result = context.getResources().getConfiguration().locale;
		if(result == null){
			result = getDefaultDeviceLocale();
		}
		return result;
	}
	
	public static Locale getDefaultDeviceLocale(){
		return Locale.getDefault();
	}
	
	public static void setApplicationLocale(Locale locale) {
		setApplicationLocale(locale,false);
		
	}

	public static void setApplicationLocale(Locale locale, boolean isRecovered){
		if(!isRecovered){
			AppData.setLocale(locale);
		}
		Configuration config = context.getResources().getConfiguration();
		config.locale = locale;
		context.getResources().updateConfiguration(config, null);
	}
}
