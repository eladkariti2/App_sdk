package com.application.loader.creator;

import com.application.models.AreaType;

import java.util.Map;

public class APUpdateUSerCreator extends APLoaderCreator {

	public static final String USERS_URL_PREFIX = "User";
	
	public static final String USER_NAME = "UserName";
	public static final String USER_BIRTHDAY = "UserBirthday";
	public static final String USER_PHOTO = "UserPhoto";
	public static final String AREA = "UserArea";
	public static final String USER_ID = "UserID";
	
	String mLatitue;
	String mLongitue;
	AreaType mArea;
	
	String mUserName;
	String mUserPhoto;
	String mUserBirthday;
	String mUserID;
	public APUpdateUSerCreator(String userId,String name,String photo,String birthday,String latitue,String longitue,AreaType area){
		mUserBirthday = birthday;
		mUserName = name;
		mUserPhoto = photo;
		mArea = area;
		mLatitue = latitue;
		mLongitue = longitue;
		mUserID = userId;
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
		params.put(USER_ID, "" + mUserID);
		 params.put(AREA, "" + mArea.toString());
		 params.put(USER_BIRTHDAY, "" + mUserBirthday);
		 params.put(USER_NAME, "" + mUserName);
		 params.put(USER_PHOTO, "" + mUserPhoto);
		 
		 //create sign
		 String signParam = "signParam";
		 params.put(SIGNATURE_KEY,signParam );	
		 
		 return params;
	}
}
