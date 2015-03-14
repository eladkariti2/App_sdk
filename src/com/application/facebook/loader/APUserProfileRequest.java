package com.application.facebook.loader;


import android.os.Bundle;
import android.util.Log;

import com.application.facebook.model.FBModel;
import com.application.facebook.model.FBProfilePic;
import com.application.listener.AsyncTaskListener;
import com.application.utils.StringUtil;
import com.facebook.FacebookRequestError;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Request.Callback;
import com.facebook.RequestAsyncTask;
import com.facebook.Response;
import com.facebook.Session;
import com.google.gson.Gson;

public class APUserProfileRequest {

	private static final String TAG = APUserProfileRequest.class.getSimpleName();
	private static final String FIELDS= "fields";
	AsyncTaskListener<FBModel> mListener;

	String mIdentifier = "me";
	private String mQuery;
	
	public APUserProfileRequest(AsyncTaskListener<FBModel> listener) {
		this("me",UserPictureSize.normal,listener);
	}

	public APUserProfileRequest(UserPictureSize size, AsyncTaskListener<FBModel> listener) {
		this("me", size,listener);
	}
	
	public APUserProfileRequest(String identifier, UserPictureSize size,AsyncTaskListener<FBModel> listener) {
		this.mListener = listener;
		
		if ( !StringUtil.isEmpty(identifier) ){
			mIdentifier = identifier;
		}
		
		
		String picture = "picture.type({{size}})";
		switch(size){
		case small:
			picture = picture.replace("{{size}}", "small");
			break;
		case square:
			picture = picture.replace("{{size}}", "square");
			break;
		case large:
			picture = picture.replace("{{size}}", "large");
			break;
		default:
			picture = picture.replace("{{size}}", "normal");
				
		}
		mQuery = "id,name,birthday," + picture ;
			
	}

	public void doQuery(){

		Bundle params = new Bundle();
	    params.putString(FIELDS, mQuery);
	    
		Request request = new Request(Session.getActiveSession(), mIdentifier, params,HttpMethod.GET, new Callback() {

			@Override
			public void onCompleted(Response response) {
				FacebookRequestError error = response.getError();

				// request succeeded
				if(error == null){
					String graphResponse = response.getGraphObject().getInnerJSONObject().toString();				
					try {

						Gson gson = new Gson();
						FBModel user = (FBModel)gson.fromJson(graphResponse, FBProfilePic.class);	
						 
						Log.d(TAG, "graphResponse= "+graphResponse);
						
						mListener.onTaskComplete(user);
					} catch (Exception e) {
						//error parsing the response
						Log.i(TAG,"JSON error "+ e.getMessage());
						mListener.handleException(e);
					}
				}
				// error
				else{
					mListener.handleException(error.getException());
				}
			}
		});

		RequestAsyncTask task = new RequestAsyncTask(request);
		task.execute();
	}

	public enum UserPictureSize{
		small,
		normal,
		square,
		large
	}


}