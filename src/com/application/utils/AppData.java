package com.application.utils;

import android.content.Context;
import android.util.Log;

import com.application.models.AccountModel;

/* 
 * Singleton object to store application data. 
 * The data is available across the application
 */

public class AppData {

	private static AppData mInstance;
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
	
}
