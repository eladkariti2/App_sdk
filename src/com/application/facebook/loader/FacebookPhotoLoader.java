package com.application.facebook.loader;


import org.json.JSONObject;

import com.application.facebook.model.FBModel;
import com.application.facebook.model.FBPhoto;
import com.application.listener.AsyncTaskListener;

import com.facebook.AccessToken;
import com.facebook.FacebookRequestError;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.google.gson.Gson;

public class FacebookPhotoLoader {
	
	protected String mPhotoId;
	protected AsyncTaskListener mListener;
	
	public FacebookPhotoLoader(String photoId, AsyncTaskListener<FBPhoto> listener){
		mPhotoId = photoId;
		mListener = listener;
	}
	
	public void load(){
		AccessToken token = AccessToken.getCurrentAccessToken();
		new GraphRequest(
				token,mPhotoId, null, HttpMethod.GET,
				new GraphRequest.Callback() {
					public void onCompleted(GraphResponse response) {
						FacebookRequestError error = response.getError();

						// request succeeded
						if(error == null){
							FBModel model = new FBModel();
							JSONObject graphResponse = response.getJSONObject();
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