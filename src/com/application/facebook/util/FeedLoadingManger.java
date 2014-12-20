package com.application.facebook.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.util.Log;

import com.application.CustomApplication;
import com.application.facebook.interfaces.FacebookLoaderI;
import com.application.facebook.model.FBFeed;
import com.application.facebook.model.FBModel;
import com.application.facebook.model.FBPost;
import com.application.listener.AsyncTaskListener;
import com.application.messagebroker.APBrokerNotificationTypes;
import com.application.messagebroker.APMessageBroker;
import com.application.utils.AppData;
import com.application.utils.StringUtil;

public class FeedLoadingManger {

	static FeedLoadingManger _instance; 

	HashMap<String , List<FBPost>> mPostToComments;
	List<FBPost> mPosts;
	long mSinceId;
	Timer mFeedLoader;
	Context mContext;
	private FeedLoadingManger(){
		mPosts = new ArrayList<FBPost>();
		mPostToComments = new HashMap<String, List<FBPost>>();
	}

	public static synchronized FeedLoadingManger getInstance(Context context){
		if(_instance == null){
			_instance = new FeedLoadingManger();
			Date date = new Date(System.currentTimeMillis());
			date.setHours(0);
			date.setMinutes(0);
			date.setSeconds(0);
			//date.setMonth(0);
			_instance.setSinceId(date.getTime());
		}
		_instance.mContext = context;
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
		}, 0,3 * 1000);//every 3 seconds check if there is new posts.
	}

	public void stopTimmer(){
		if(mFeedLoader != null){
			mFeedLoader.cancel();
			mFeedLoader = null;
		}
	}

	public void setSinceId(long sinceId) {
		this.mSinceId = sinceId;
	}
	
	protected void loadFeed() {
		// TODO Auto-generated method stub
		FacebookUtil.loadFacebookPage(mContext,AppData.getAPAccount().getFBPageID(), String.valueOf(mSinceId), new AsyncTaskListener<FBModel>() {
			
			@Override
			public void onTaskStart() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onTaskComplete(FBModel result) {
				// TODO Auto-generated method stub
				FBFeed feed = (FBFeed)result;
				if(feed.hasPost()){
					long createdTime = getFBTime(feed.getPosts().get(0).getCreatedTime());
					setSinceId(createdTime);
					mPosts.addAll(feed.getPosts());
					updateCommentToPost(feed.getPosts());
					APMessageBroker.getInstance().fireNotificationsByType(APBrokerNotificationTypes.AP_BROKER_FEED_LOADED, null);
				}else{
					APMessageBroker.getInstance().fireNotificationsByType(APBrokerNotificationTypes.AP_BROKER_FEED_LOADED_EMPTY, null);
				}
				
				
			}
			
			@Override
			public void handleException(Exception e) {
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


//	private String getStartTime() {
//		// TODO Auto-generated method stub
//		String result ="";
//		Calendar c = Calendar.getInstance();
//		Date date =  c.getTime();
//		if(currentTime != null){
//			result = StringUtil.internetDF.format(currentTime);
//			Log.e("ELAD", currentTime.getTime() +"");
//			currentTime = date;
//		}else{
//			
//			currentTime = new Date(date.getTime());
//			date.setHours(0);
//			date.setMinutes(0);
//			date.setSeconds(0);
//			date.setMonth(8);
//			result = StringUtil.internetDF.(date);
//		}
//
//		return result;
//
//	}
	
	protected long getFBTime(String createdTime) {
		// TODO Auto-generated method stub
		long result = 0;
		try {
			result = StringUtil.internetDF.parse(createdTime).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	

	public List<FBPost> getFeed(int from){
		return mPosts.subList(from, mPosts.size());
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
