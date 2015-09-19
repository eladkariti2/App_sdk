package com.application.imageholders;

import java.util.HashMap;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class ImageHolder {

	private String mImageUrl;
	private String mTitle;
	private String mDescription;
	transient Drawable mDrawable;
	private String mID;
	HashMap<String, String> mExtension;

	public ImageHolder(String url){
		this("","",url);
	}

	public ImageHolder(String id,String title,String url){
		this(id,title,"",url);
	}
	
	public ImageHolder(String id,String title,String description,String url){
		mImageUrl = url;
		mTitle = title;
		mID = id;
		mDescription = description;
		mExtension = new HashMap<String, String>();
	}
	
	
	public String getID() {
		return mID;
	}
	
	public String getImageUrl() {
		return mImageUrl;
	}

	public String getImageTitle() {
		return mTitle;
	}
	
	public String getDescription() {
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

	public void setDrawable(Drawable bitmapDrawable) {
		// TODO Auto-generated method stub
		mDrawable = bitmapDrawable;
	}
	
	public Drawable getDrawable() {
		// TODO Auto-generated method stub
		return mDrawable ;
	}
}
