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
import com.facebook.Request.Callback;
import com.facebook.RequestAsyncTask;
import com.facebook.Response;
import com.facebook.Session;

public class FbPostToFeedRequest  {

	AsyncTaskListener<FbModel> listener;
	String identifier;
	String name;
	String message;
	String caption;
	String description;
	String link;
	String picture;
	Bitmap image;

	public FbPostToFeedRequest(String identifier,String message,AsyncTaskListener<FbModel> listener) {
		this(identifier, message,null,listener);	
	}
	
	public FbPostToFeedRequest(String identifier,String message,Bitmap image, AsyncTaskListener<FbModel> listener) {
		this(identifier, message,image,null,null,null,null,null ,listener);	
	}

	public FbPostToFeedRequest(String identifier,String message,Bitmap image,String name,String caption, String description, String link, String picture,AsyncTaskListener<FbModel> listener) {
		
		this.identifier = identifier;
		this.message = message;
		this.name= name;
		this.caption= caption;
		this.description= description;
		this.link= link;
		this.picture= picture;
		this.image = image;
		this.listener = listener;
	}


	public void execute(){
		listener.onTaskStart();
		Bundle postParams = new Bundle();
		if(image!= null){
			postParams.putString("message", message);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			image.compress(Bitmap.CompressFormat.PNG, 100, stream);
			byte[] byteArray = stream.toByteArray();
			postParams.putByteArray("picture",byteArray);
		}
		else{
			postParams.putString("name", name);
			postParams.putString("message", message);
			postParams.putString("caption", caption);
			postParams.putString("description", description);
			postParams.putString("link", link);
			postParams.putString("picture", picture);
		}
		
		
		String graphPath = image == null ? identifier + "/feed" : identifier + "/photos";
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
