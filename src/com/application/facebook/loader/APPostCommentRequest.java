package com.application.facebook.loader;

import android.os.Bundle;
import android.util.Log;

import com.application.facebook.model.FBModel;
import com.application.listener.AsyncTaskListener;
import com.facebook.FacebookRequestError;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Request.Callback;
import com.facebook.RequestAsyncTask;
import com.facebook.Response;
import com.facebook.Session;

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
		
		Request request = new Request(Session.getActiveSession(), graphPath, postParams,HttpMethod.POST, new Callback() {

			@Override
			public void onCompleted(Response response) {
				FacebookRequestError error = response.getError();
				
				// request succeeded
				if(error == null){
					
					String graphResponse = response.getGraphObject().getInnerJSONObject().toString();
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

		RequestAsyncTask task = new RequestAsyncTask(request);
		task.execute();

		
		
		
	}
}