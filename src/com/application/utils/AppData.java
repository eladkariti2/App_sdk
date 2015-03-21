package com.application.utils;

import java.io.IOException;
import java.util.Properties;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.application.models.AccountModel;
import com.application.text.APConstant;
import com.google.gson.Gson;

/* 
 * Singleton object to store application data. 
 * The data is available across the application
 */

public class AppData {

	private static AppData mInstance;
	private static Properties properties;
	
	AccountModel mAccount;
	
	public static synchronized AppData getInstace() {
		if(mInstance == null){
			mInstance = new AppData();
		}
		return mInstance;
	}
	
	
	private AppData(){
		
	}
	
	public static AccountModel getAPAccount(){
		return getInstace().getAccount();
	}
	
	public AccountModel getAccount() {
		return mAccount;
	}
	
	public void setAccount(AccountModel account) {
		this.mAccount = account;	
	}


	public static String getFacebookAppId(Context context) {
		// TODO Auto-generated method stub
		String fbID = context.getString(OSUtil.getStringResourceIdentifier("fb_app_id"));	
		return fbID;
	}
	
	public static void loadProperties(Context context) {
		try {
			Properties properties = new Properties();
			//properties.load(context.getAssets().open("applicaster.properties"));
			getInstace().properties = properties;
			//setDirty(true);
		} catch (Exception e) {
			Log.e(AppData.class.getSimpleName(),"Missing properties file");
		}
	}
	
	
	public static void setBooleanProperty(String name, boolean booleanValue){
		String value = String.valueOf(booleanValue);
		getInstace().properties.setProperty(name,value);
		//setDirty(true);
	}

	public static boolean getBooleanProperty(String name){
		String tempResult = getProperty(name);
		return !StringUtil.isEmpty(tempResult) && Boolean.parseBoolean(tempResult.trim());
	}

	public static String getProperty(String name){
		return getInstace().properties.getProperty(name);
	}

	public static String getProperties(){
		return getInstace().properties.toString();
	}

	public static void setProperty(String name,String value){
		getInstace().properties.setProperty(name,value);
		//setDirty(true);
	}
	
	public static Location getUserLocation() {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		String userLocation = AppData.getProperty(APConstant.USER_LOCATIOM);
		Location location = (Location)gson.fromJson(userLocation, Location.class);
		return location;
	}

	public static void saveUserLocation(Location location) {
		// TODO Auto-generated method stub
		String userLocation = new Gson().toJson(location);
		AppData.setProperty(APConstant.USER_LOCATIOM, userLocation);
		
	}
	
}
