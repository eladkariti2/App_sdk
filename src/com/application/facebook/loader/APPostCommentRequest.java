package com.application.facebook.loader;

import android.os.Bundle;
import android.util.Log;

import com.application.facebook.model.FBModel;
import com.application.listener.AsyncTaskListener;
import com.facebook.AccessToken;
import com.facebook.FacebookRequestError;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

public class APPostCommentRequest {

	private static final String TAG = APPostToFeedRequest.class.getSimpleName();
	
	String mMessage;
	AsyncTaskListener<FBModel> mListener;
	String mIdentifier;
	
	public APPostCommentRequest(String identifier,String message,AsyncTaskListener<FBModel> listener) {
	
		this.mListener = listener;
		this.mIdentifier = identifier;
		this.mMessage= message;
	}
	
	public void doQuery(){
		mListener.onTaskStart();
		Bundle postParams = new Bundle();
		postParams.putString("message", mMessage);

		String graphPath = mIdentifier + "/comments";
		AccessToken token = AccessToken.getCurrentAccessToken();

		GraphRequest request  = new GraphRequest(token, graphPath, postParams,HttpMethod.POST, new GraphRequest.Callback() {

			@Override
			public void onCompleted(GraphResponse response) {
				FacebookRequestError error = response.getError();

				// request succeeded
				if(error == null){

					String graphResponse = response.getJSONObject().toString();
					try {
						mListener.onTaskComplete(null);
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

		request.executeAsync();
	}
}