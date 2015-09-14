package com.application.facebook.loader;


import android.util.Log;

import com.application.listener.AsyncTaskListener;
import com.facebook.AccessToken;
import com.facebook.FacebookRequestError;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;


public class APLikeRequest {

	private static final String TAG = APLikeRequest.class.getSimpleName();

	String mIdentifier;
	AsyncTaskListener<Boolean> mListener;
	boolean mIsLiked;
	String mQuery = "";

	public APLikeRequest(String identifier,AsyncTaskListener listener,boolean isLiked){
		mIsLiked = isLiked;
		mListener = listener;
		mIdentifier = identifier;

		mQuery = mIdentifier + "/likes";
	}



	public void doQuery(){
		mListener.onTaskStart();
		AccessToken token = AccessToken.getCurrentAccessToken();

		GraphRequest request  = new GraphRequest(token, mQuery, null,mIsLiked ? HttpMethod.DELETE : HttpMethod.POST, new GraphRequest.Callback() {

			@Override
			public void onCompleted(GraphResponse response) {
				// TODO Auto-generated method stub
				FacebookRequestError error = response.getError();

				// request succeeded
				if(error == null){
					try {

						mListener.onTaskComplete(true);
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