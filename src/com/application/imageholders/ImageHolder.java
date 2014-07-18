package com.application.imageholders;

import java.util.HashMap;

public class ImageHolder {

	private String mImageUrl;
	String mTitle;
	String mDescription;
	
	HashMap<String, String> mExtension;

	public ImageHolder(String title,String url){
		this(title,"",url);
	}
	
	public ImageHolder(String title,String description,String url){
		mImageUrl = url;
		mTitle = title;
		mExtension = new HashMap<String, String>();
	}
	
	
	
	
	public String getImageUrl() {
		return mImageUrl;
	}

	public String getImageTitle() {
		return mTitle;
	}
	
	public String getDescriptionUrl() {
		return mDescription;
	}
	
	public String getExtension(String key){
		String value = "";
		
		if(mExtension.containsKey(key)){
			value = mExtension.get(key);
		}
		
		return value;
	}
	
	public void addExtension(String key,String value){
		mExtension.put(key, value);
	}
	
}
