package com.application.datahandler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import android.util.Log;

import com.application.facebook.listener.FeedEventI;
import com.application.facebook.loader.APFeedRequest;
import com.application.facebook.model.FBFeed;
import com.application.facebook.model.FBModel;
import com.application.facebook.model.FBPost;
import com.application.listener.AsyncTaskListener;
import com.application.messagebroker.APBrokerNotificationTypes;
import com.application.messagebroker.APMessageBroker;
import com.application.utils.AppData;
import com.application.utils.StringUtil;
import com.google.android.gms.internal.da;

public class FBEventDataHandler {

	List<FBPost> mPosts;
	FeedEventI mListener;
	boolean mIsfirstLoad ;
	long mLastUpdateTime;
	
	public FBEventDataHandler(FeedEventI listener){
		mListener = listener;
		mIsfirstLoad = true;
	}

	public void loadFeed() {

		APFeedRequest req = new APFeedRequest(AppData.getAPAccount().getFBPageID(), getStartTime(), null, new AsyncTaskListener<FBModel>() {

			@Override
			public void onTaskStart() {
				// TODO Auto-generated method stub
			}


			@Override
			public void onTaskComplete(FBModel result) {
				// TODO Auto-generated method stub
				FBFeed feed = (FBFeed)result;
				if(feed != null && feed.getPosts() != null && !feed.getPosts().isEmpty()){
					long createdTime = getFBTime(feed.getPosts().get(0).getCreatedTime());
					setmSinceId(createdTime); 			
					mListener.onLoaded(feed);
				}
				
			}

			@Override
			public void handleException(Exception e) {
				// TODO Auto-generated method stub
				mListener.onEror();
			}
		});

		req.doQuery();
	}

	protected void setmSinceId(long createdTime) {
		// TODO Auto-generated method stub
		mLastUpdateTime = createdTime;
	}

	private String getStartTime() {
		// TODO Auto-generated method stub
		String result ="";
		if(mIsfirstLoad){
			result = getStartDayDate();
		}else{
			result = "" + mLastUpdateTime;
		}
		return result;
	}

	private String getStartDayDate() {
		mIsfirstLoad = false;
		String time = "";
		Calendar c = Calendar.getInstance();
		Date date = c.getTime();
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		time = date.getTime() + "";
		mLastUpdateTime = date.getTime();
		return time;
	}
	
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

}
