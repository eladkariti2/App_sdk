package com.application.loader.creator;

import java.util.Map;

public class APUpdateUSerCreator extends APLoaderCreator {

	public static final String USERS_URL_PREFIX = "User/";
	
	public static final String USER_NAME = "UserName";
	public static final String USER_BIRTHDAY = "birthday";
	public static final String USER_PHOTO = "Photo";
	public static final String RADIUS = "Radius";
	
	String mLatitue;
	String mLongitue;
	int mRaduis;
	
	String mUserName;
	String mUserPhoto;
	String mUserBirthday;
	
	public APUpdateUSerCreator(String name,String photo,String birthday,String latitue,String longitue,int raduis){
		mUserBirthday = birthday;
		mUserName = name;
		mUserPhoto = photo;
		mRaduis = raduis;
		mLatitue = latitue;
		mLongitue = longitue;
	}
	
	public String getURL(String accountId){
		String url = super.getBaseURL();
		url += USERS_URL_PREFIX;
		Map<String,String> baseParams =  getBaseParams();
		url += prepareUrlParams(baseParams,true);
		
		return url;
	}
	
	public  Map<String,String>  getBaseParams(){
		 Map<String,String> params = super.getBaseParams(mLatitue,mLongitue);
		
		 params.put(RADIUS, "" + mRaduis);
		 params.put(USER_BIRTHDAY, "" + mUserBirthday);
		 params.put(USER_NAME, "" + mUserName);
		 params.put(USER_PHOTO, "" + mUserPhoto);
		 
		 //create sign
		 String signParam = "signParam";
		 params.put(SIGNATURE_KEY,signParam );	
		 
		 return params;
	}
}
