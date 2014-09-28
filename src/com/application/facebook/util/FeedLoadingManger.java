package com.application.facebook.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import android.util.Log;

import com.application.CustomApplication;
import com.application.facebook.interfaces.FacebookLoaderI;
import com.application.facebook.model.FBFeed;
import com.application.facebook.model.FBPost;
import com.application.facebook.model.FbModel;
import com.application.imageholders.ImageHolder;
import com.application.imageholders.ImageHolderBuilder;
import com.application.messagebroker.APBrokerNotificationTypes;
import com.application.messagebroker.APMessageBroker;
import com.application.utils.AppData;
import com.application.utils.StringUtil;

public class FeedLoadingManger {

	static FeedLoadingManger _instance; 

	HashMap<String , List<FBPost>> mPostToComments;
	List<FBPost> mPosts;
	Date currentTime;
	Timer mFeedLoader;

	private FeedLoadingManger(){
		mPosts = new ArrayList<FBPost>();
		mPostToComments = new HashMap<String, List<FBPost>>();
	}

	public static synchronized FeedLoadingManger getInstance(){
		if(_instance == null){
			_instance = new FeedLoadingManger();
		}
		return _instance;
	}

	public void startTimmer(){
		if(mFeedLoader == null){
			mFeedLoader = new Timer();
		}
		mFeedLoader.schedule(new TimerTask() {

			@Override
			public void run() {		
				loadFeed();
			}
		}, 0,30 * 1000);//every 30 second check if there is new posts.
	}

	public void stopTimmer(){
		if(mFeedLoader != null){
			mFeedLoader.cancel();
			mFeedLoader = null;
		}
	}

	protected void loadFeed() {
		// TODO Auto-generated method stub
		FacebookUtil.loadFacebookPage(CustomApplication.getAppContext(),AppData.getAPAccount().getFBPageID(),getStartTime(), new FacebookLoaderI() {

			@Override
			public void onSuccess(FbModel model) {
				// TODO Auto-generated method stub
				FBFeed feed = (FBFeed)model;
				if(feed.hasPost()){
					mPosts.addAll(feed.getPosts().getPosts());
					updateCommentToPost(feed.getPosts().getPosts());
					APMessageBroker.getInstance().fireNotificationsByType(APBrokerNotificationTypes.AP_BROKER_FEED_LOADED, null);
				}
			}

			@Override
			public void onFailure(Exception e) {
				// TODO Auto-generated method stub

			}
		});
	}


	protected void updateCommentToPost(List<FBPost> posts) {
		// TODO Auto-generated method stub
		for(FBPost p : posts){
			if(p.getComments() != null){
				mPostToComments.put(p.getId(),p.getComments());
			}
		}
	}


	private String getStartTime() {
		// TODO Auto-generated method stub
		String result ="";
		Calendar c = Calendar.getInstance(); 
		Date date =  c.getTime();
		if(currentTime != null){
			result = StringUtil.fbDF.format(currentTime);
			currentTime = date;
		}else{
			
			currentTime = new Date(date.getTime());
			date.setHours(0);
			date.setMinutes(0);
			date.setSeconds(0);
			
			result = StringUtil.fbDF.format(date);
		}

		return result;

	}

	public List<FBPost> getFeed(){
		return mPosts;
	}

	public List<FBPost> getPostComments(String postID){
		List<FBPost> comments = null;
		if(mPostToComments.containsKey(postID)){
			comments = mPostToComments.get(postID);
		}
		return comments;
	}

	public void addCommentToPost(String postID,FBPost comment){
		if(mPostToComments.containsKey(postID)){
			mPostToComments.get(postID).add(0,comment);
		}else{
			ArrayList<FBPost> commentList = new ArrayList<FBPost>();
			commentList.add(comment);
			mPostToComments.put(postID,commentList );
		}
		
	}


}
