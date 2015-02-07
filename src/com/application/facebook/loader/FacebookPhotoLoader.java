package com.application.facebook.loader;


import org.json.JSONObject;

import com.application.facebook.model.FBModel;
import com.application.facebook.model.FBPhoto;
import com.application.listener.AsyncTaskListener;
import com.facebook.FacebookRequestError;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.google.gson.Gson;

public class FacebookPhotoLoader {
	
	protected String mPhotoId;
	protected AsyncTaskListener mListener;
	
	public FacebookPhotoLoader(String photoId, AsyncTaskListener<FBPhoto> listener){
		mPhotoId = photoId;
		mListener = listener;
	}
	
	public void load(){
		new Request(
			    Session.getActiveSession(),mPhotoId, null, HttpMethod.GET,
			    new Request.Callback() {
			        public void onCompleted(Response response) {
			        	FacebookRequestError error = response.getError();
						
						// request succeeded
						if(error == null){
							FBModel model = new FBModel();
							JSONObject graphResponse = response.getGraphObject().getInnerJSONObject();
							FBPhoto photo = new Gson().fromJson(graphResponse.toString(), FBPhoto.class);
							
							try {
								mListener.onTaskComplete(photo);
							} catch (Exception e) {
								//error parsing the response
								mListener.handleException(e);
							}
							
						}
						
						// error
						else{
							mListener.handleException(error.getException());
						}
			        }
			    }
			).executeAsync();
	}

}