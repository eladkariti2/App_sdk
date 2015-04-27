package com.application.facebook.loader;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.application.facebook.model.FBModel;
import com.application.facebook.model.FBPost;
import com.application.listener.AsyncTaskListener;
import com.facebook.FacebookRequestError;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Request.Callback;
import com.facebook.RequestAsyncTask;
import com.facebook.Response;
import com.facebook.Session;
import com.google.gson.Gson;

public class APPostToFeedRequest {

	private static final String TAG = APPostToFeedRequest.class.getSimpleName();


	String mIdentifier;
	AsyncTaskListener<FBModel> mListener;
	String mName;
	String mMessage;
	String mCaption;
	String mDescription;
	String mLink;
	String mPicture;
	Bitmap mImage;
	Context mContext;

	public APPostToFeedRequest(Context context,String identifier,String postText,Bitmap image, AsyncTaskListener<FBModel> listener){
		this(context,identifier, postText,image,null,null,null,null,null ,listener);	
	}

	public APPostToFeedRequest(Context context,String identifier,String postText,Bitmap image,String name,String caption, String description, String link, String picture,AsyncTaskListener<FBModel> listener){
		mContext = context;
		this.mIdentifier = identifier;
		this.mMessage = postText;
		this.mName= name;
		this.mCaption= caption;
		this.mDescription= description;
		this.mLink= link;
		this.mPicture= picture;
		this.mImage = image;
		this.mListener = listener;
	}

	public void doQuery(){
		mListener.onTaskStart();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				post();
			}
		}).start();;
		

	}

	protected void post() {
		// TODO Auto-generated method stub
		Bundle postParams = new Bundle();
		if(mImage!= null){
			//postParams.putString("name", mName);
		//	postParams.putString("caption", mCaption);
			postParams.putString("message", mMessage);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			mImage.compress(Bitmap.CompressFormat.JPEG, 80, stream);
			byte[] byteArray = stream.toByteArray();
			postParams.putByteArray("picture",byteArray);
		}
		else{
			postParams.putString("name", mName);
			postParams.putString("caption", mCaption);
			postParams.putString("description", mDescription);
			postParams.putString("link", mLink);
			postParams.putString("message", mMessage);
			postParams.putString("picture", mPicture);
		}

		String path = (mImage == null) ? mIdentifier + "/feed" : mIdentifier + "/photos";

		final Request request = new Request(Session.getActiveSession(), path, postParams,HttpMethod.POST, new Callback() {

			@Override
			public void onCompleted(Response response) {
				FacebookRequestError error = response.getError();

				// request succeeded
				if(error == null){
					String graphResponse = response.getGraphObject().getInnerJSONObject().toString();				
					try {

						Gson gson = new Gson();
						FBPost post = (FBPost)gson.fromJson(graphResponse, FBPost.class);	

						mListener.onTaskComplete((FBModel)post);
					} catch (Exception e) {
						//error parsing the response
						Log.e(TAG,"JSON error "+ e.getMessage());
						mListener.handleException(e);
					}

				}

				// error
				else{
					Log.e(TAG,"JSON error "+ error.getErrorMessage());
					mListener.handleException(error.getException());
				}
			}
		});

		((Activity)mContext).runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				RequestAsyncTask task = new RequestAsyncTask(request);
				task.execute();
			}
		});
		
	}
}