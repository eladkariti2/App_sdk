package com.application.loader.creator;

import java.util.Map;

public class APAccountLoaderCreator extends APLoaderCreator {
	
	public static final String RADIUS = "Radius";
	public static final String ACCOUNT_URL = "Account/";
	String mLatitue;
	String mLongitue;
	int mRaduis;
	
	public APAccountLoaderCreator(String latitue,String longitue,int raduis){
		mRaduis = raduis;
		mLatitue = latitue;
		mLongitue = longitue;
	}
	
	public String getURL(String accountId){
		String url = super.getBaseURL();
		url += ACCOUNT_URL;
		Map<String,String> baseParams =  getBaseParams();
		url += prepareUrlParams(baseParams,true);
		
		return url;
	}
	
	public  Map<String,String>  getBaseParams(){
		 Map<String,String> params = super.getBaseParams(mLatitue,mLongitue);
		
		 params.put(RADIUS, "" + mRaduis);
		 
		 //create sign
		 String signParam = "signParam";
		 params.put(SIGNATURE_KEY,signParam );	
		 
		 return params;
	}
}
