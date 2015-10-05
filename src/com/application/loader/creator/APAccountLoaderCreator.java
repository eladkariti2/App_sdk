package com.application.loader.creator;

import com.application.models.AreaType;

import java.util.Map;

public class APAccountLoaderCreator extends APLoaderCreator {

	public static final String ACCOUNT_ID = "ID";
	public static final String AREA = "UserArea";
	public static final String ACCOUNT_URL = "Account/";
	String mAccountID;
	AreaType mAreaType;
	
	public APAccountLoaderCreator(String accountId,AreaType type){
		mAccountID = accountId;
		mAreaType = type;
	}
	
	public String getURL(String accountId){
		String url = super.getBaseURL();
		url += ACCOUNT_URL;
		Map<String,String> baseParams =  getBaseParams();
		url += prepareUrlParams(baseParams,true);

		return url;
	}
	
	public  Map<String,String>  getBaseParams(){
		 Map<String,String> params = super.getBaseParams();
		
		 params.put(AREA, "" + mAreaType.toString());
		 params.put(ACCOUNT_ID,mAccountID);
		 //create sign
		 String signParam = "signParam";
		 params.put(SIGNATURE_KEY,signParam );	
		 
		 return params;
	}
}
