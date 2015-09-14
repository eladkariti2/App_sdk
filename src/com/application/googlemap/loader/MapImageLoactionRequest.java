package com.application.googlemap.loader;

import com.application.listener.AsyncTaskListener;
import com.application.utils.ServerUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class MapImageLoactionRequest {

	private final static String IMAGE_URL = "http://maps.google.com/maps/api/staticmap?center={{x}},{{y}}&zoom=15&size=200x200&sensor=false";
	private final static String TAG = "MapImageLoactionRequest";
	
	Context mContext;
	String mQuery = "";
	AsyncTaskListener<Drawable> mListner;
	
	public MapImageLoactionRequest(Context context,double latitude ,double longitude,AsyncTaskListener<Drawable> listner){
		mContext = context;
		mListner = listner;
		mQuery = IMAGE_URL;
		mQuery =  mQuery.replace("{{x}}", latitude+ "").replace("{{y}}",  longitude + "");
	}
	
	
	

	public  void doQuery(){
		Drawable drawble = null;
		
		try {
			 drawble = ServerUtil.loadImage(mQuery,true);
			 mListner.onTaskComplete(drawble);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			mListner.handleException(e);
			e.printStackTrace();
		}
	}
}
