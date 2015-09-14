package com.application.facebook.loader;

import android.os.Bundle;
import android.util.Log;

import com.application.facebook.model.FBModel;
import com.application.facebook.model.FBPost;
import com.application.listener.AsyncTaskListener;

import com.facebook.AccessToken;
import com.facebook.FacebookRequestError;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.google.gson.Gson;

public class APCommentsRequest {

	private static final String TAG = APCommentsRequest.class.getSimpleName();


	private AsyncTaskListener<FBModel> mListener;
	private static final String FIELDS= "fields";
	private String mQuery;
	String identifier;


	public APCommentsRequest(String postId,AsyncTaskListener<FBModel> listener) {
		this(postId,false,listener);
	}

	public APCommentsRequest(String postId, boolean friendsOnly,AsyncTaskListener<FBModel> listener) {
		identifier = postId;
		mListener = listener;

		StringBuilder queryUrl = new StringBuilder();
		queryUrl.append("from").append(APFeedRequest.USERS_FIELDS).append(APFeedRequest.POSTS_FIELDS);
		queryUrl.append(APFeedRequest.COMMENTS_FIELDS);
		mQuery = queryUrl.toString();
		Log.d(TAG, queryUrl.toString());


	}



	public void doQuery(){

		Bundle params = new Bundle();
		params.putString(FIELDS, mQuery);

		AccessToken token = AccessToken.getCurrentAccessToken();

		GraphRequest request  = new GraphRequest(token, identifier, params, HttpMethod.GET, new GraphRequest.Callback() {

			@Override
			public void onCompleted(GraphResponse response) {
				FacebookRequestError error = response.getError();
				// request succeeded
				if(error == null){
					String graphResponse = response.getJSONObject().toString();
					System.out.println("comment Request - graphResponse : "  + graphResponse);
					try {

						Gson gson = new Gson();
						FBPost post = (FBPost)gson.fromJson(graphResponse, FBPost.class);

						mListener.onTaskComplete((FBModel)post);
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