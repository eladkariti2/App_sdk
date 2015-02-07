package com.application.facebook.loader;

import java.util.Date;

import android.os.Bundle;
import android.util.Log;

import com.application.facebook.model.FBFeed;
import com.application.facebook.model.FBModel;
import com.application.facebook.model.FBPost;
import com.application.listener.AsyncTaskListener;
import com.application.utils.StringUtil;
import com.facebook.FacebookRequestError;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.RequestAsyncTask;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.Request.Callback;
import com.google.gson.Gson;


public class APFeedRequest {	

	private static final String TAG = APFeedRequest.class.getSimpleName();

	public static final String PAGE_FIELDS = "id,name,feed";
	public static final String SINCE_CREATED_TIME_FILTER = ".since({{since}})";
	public static final String LIMIT_FILTER = ".limit({{limit}})";
	public static final String UNTIL_CREATED_TIME_FILTER = ".until({{until}})";
	public static final String POSTS_FIELDS = ",id,caption,message,link,object_id,type,source,picture,likes.summary(true),comments.summary(true)";
	public static final String USERS_FIELDS = "{id,name,picture.type(large)}";
	public static final String COMMENTS_FIELDS = "{attachment,created_time,like_count,message,id,from" + USERS_FIELDS +"}";

	//	private static final String QUERY = "{{identifier}}?fields=id,name,feed.limit(25){from{id,name,picture.type(large)},id,caption,message,picture,likes.limit(1).summary(true),comments.summary(true){created_time,like_count,message,id,from{id,name,picture.type(large)}}}";
	private static final String FIELDS= "fields";
	private String mQuery;
	private AsyncTaskListener<FBModel> mListener;
	String identifier;

	public APFeedRequest(String identifier,AsyncTaskListener<FBModel> listener) {
		this(identifier, null,null, listener);
	}
	

	public APFeedRequest(String identifier,String since, String until, AsyncTaskListener<FBModel> listener) {
		this(identifier,since,until,null,listener);
	}

	public APFeedRequest(String identifier,String since, String until,String limit, AsyncTaskListener<FBModel> listener) {
		mListener = listener;
		this.identifier = identifier;
		StringBuilder queryUrl = new StringBuilder();
		queryUrl.append(PAGE_FIELDS);
		if(!StringUtil.isEmpty(since)){
			
			String dateSince = StringUtil.internetDF.format(new Date(Long.parseLong(since)));
			
			queryUrl.append(SINCE_CREATED_TIME_FILTER.replace("{{since}}", dateSince));
		}
		if(!StringUtil.isEmpty(until)){
			String dateuntil  = StringUtil.internetDF.format(new Date(Long.parseLong(since)));
			queryUrl.append(UNTIL_CREATED_TIME_FILTER.replace("{{until}}", dateuntil));
		}
		if(!StringUtil.isEmpty(limit)){
			queryUrl.append(LIMIT_FILTER.replace("{{limit}}", limit));
		}
		queryUrl.append("{from").append(USERS_FIELDS).append(POSTS_FIELDS);
		queryUrl.append(COMMENTS_FIELDS).append("}");
		mQuery = queryUrl.toString();
		Log.d(TAG, queryUrl.toString());
	}

	
	public void doQuery(){
	
		Bundle params = new Bundle();
	    params.putString(FIELDS, mQuery);
	    
		
		Request request = new Request(Session.getActiveSession(), identifier, params,HttpMethod.GET, new Callback() {

			@Override
			public void onCompleted(Response response) {
				FacebookRequestError error = response.getError();

				// request succeeded
				if(error == null){
					String graphResponse = response.getGraphObject().getInnerJSONObject().toString();				
					try {
						Gson gson = new Gson();
						FBFeed feed = (FBFeed)gson.fromJson(graphResponse, FBFeed.class);						
						mListener.onTaskComplete((FBModel)feed);
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