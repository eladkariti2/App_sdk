package com.application.facebook;

import java.io.ByteArrayOutputStream;

import org.json.JSONObject;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;

import com.application.facebook.model.FbModel;
import com.application.listener.AsyncTaskListener;
import com.facebook.FacebookRequestError;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.RequestAsyncTask;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.Request.Callback;

public class FbPostComment {
	
	AsyncTaskListener<FbModel> listener;
	String identifier;
	String message;
	
	public FbPostComment(String identifier,String message,AsyncTaskListener<FbModel> listener) {
		this.listener = listener;
		this.identifier = identifier;
		this.message = message;
	}
	
	public void execute(){
		listener.onTaskStart();
		Bundle postParams = new Bundle();
		postParams.putString("message", message);
		
		
		
		String graphPath = identifier + "/comments";
		Request request = new Request(Session.getActiveSession(), graphPath, postParams,HttpMethod.POST, new Callback() {

			@Override
			public void onCompleted(Response response) {
				FacebookRequestError error = response.getError();
				
				// request succeeded
				if(error == null){
					FbModel model = new FbModel();
					JSONObject graphResponse = response.getGraphObject().getInnerJSONObject();
					try {
						model.setId(graphResponse.getString("id"));
						listener.onTaskComplete(model);
					} catch (Exception e) {
						//error parsing the response
						Log.i("ApFbPostToFeedRequest","JSON error "+ e.getMessage());
						listener.handleException(e);
					}
					
				}
				
				// error
				else{
					listener.handleException(error.getException());
				}
			}
		});

		RequestAsyncTask task = new RequestAsyncTask(request);
		task.execute();
	}
	
}
