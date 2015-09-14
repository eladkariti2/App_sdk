package com.application.facebook.loader;

import android.os.Bundle;
import android.util.Log;

import com.application.facebook.model.FBFriendsList;
import com.application.facebook.model.FBModel;
import com.application.listener.AsyncTaskListener;

import com.facebook.AccessToken;
import com.facebook.FacebookRequestError;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.google.gson.Gson;


public class APFriendsLoader {

	private static final String TAG = APCommentsRequest.class.getSimpleName();

	public static final String PAGE_FIELDS = "id,name,picture.type(large)";
	private AsyncTaskListener<FBModel> mListener;
	private static final String FIELDS= "fields";
	private String mQuery;
	String identifier;

	public APFriendsLoader(AsyncTaskListener<FBModel> listener) {
		identifier = "me/friends";
		mListener = listener;

		StringBuilder queryUrl = new StringBuilder();
		queryUrl.append(PAGE_FIELDS);
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
					try {

						Gson gson = new Gson();
						FBFriendsList list = (FBFriendsList)gson.fromJson(graphResponse, FBFriendsList.class);

						mListener.onTaskComplete((FBModel)list);
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
