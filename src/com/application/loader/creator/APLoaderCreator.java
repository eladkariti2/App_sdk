package com.application.loader.creator;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.application.CustomApplication;
import com.application.utils.OSUtil;


public class APLoaderCreator {
	
	public static String BASE_URL = "http://localhost:50423/api/";
	
	public static final String TIMESTAMP_KEY = "TimeStamp";	
	public static final String BUNDLE_KEY = "Bundle";	
	public static final String OS_VERSION_KEY = "OsVersion";
	public static final String OS_TYPE_KEY = "OsType";
	public static final String OS_TYPE_VALUE = "android";
	public static final String DEVICE_MODEL_KEY = "DeviceModel";	
	public static final String DEVICE_ID_KEY = "DeviceID";	
	public static final String SIGNATURE_KEY = "Signature";
	public static final String LANG = "Language";
	public static final String APP_VERSION = "AppVersion"; 
	public static final String LATITUDE = "Latitude";
	public static final String LONGITUDE = "Longitude";
			
	protected  String getBaseURL(){
		String url = BASE_URL;
		
		return url;
	}
	
	protected  Map<String,String>  getBaseParams(String latitue,String longitue){
		 Map<String,String> params = new HashMap<String, String>();
		 
		 int timeStamp = (int) ((new Date().getTime())/1000);
			
		 params.put(TIMESTAMP_KEY, "" + timeStamp);
		 params.put(DEVICE_ID_KEY, OSUtil.getDeviceIdentifier(CustomApplication.getAppContext()));	
		 params.put(BUNDLE_KEY, OSUtil.getBundleId());	
		 params.put(OS_TYPE_KEY,OS_TYPE_VALUE );
		 params.put(OS_VERSION_KEY,"" + OSUtil.getAPIVersion());
		 params.put(DEVICE_MODEL_KEY,OSUtil.getDeviceModel() );		
		 params.put(APP_VERSION,OSUtil.getAppVersion(CustomApplication.getAppContext()) );
		 params.put(LANG,CustomApplication.getApplicationLocale().getLanguage());
		 params.put(LATITUDE,latitue);
		 params.put(LONGITUDE,longitue);
		 	
			
		return params;
	}
	
	protected static String prepareUrlParams(Map<String,String> params,boolean encode){
		StringBuffer result = new StringBuffer();
		int count = 0;
		if(params != null){
			for(String key : params.keySet()){
				if(count > 0){
					result.append("&");
				}else{
					result.append("?");
				}
				String value= params.get(key);
				if(encode && value != null){
					value = URLEncoder.encode(value);
				}
				result.append(key).append("=").append(value);
				count++;
			}
		}
		return result.toString();
	}

}
