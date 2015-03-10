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

import com.application.facebook.listener.FacebookI;
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

public class FeedLoadingManger {

	List<FBPost> mPosts;
	Date currentTime;
	
	FacebookI mListener;
	
	public FeedLoadingManger(FacebookI listener){
		mListener = listener;
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
				mListener.onLoaded(feed);
			}

			@Override
			public void handleException(Exception e) {
				// TODO Auto-generated method stub
				mListener.onEror();
			}
		});

		req.doQuery();
	}



	private String getStartTime() {
		// TODO Auto-generated method stub
		String result ="";
		Calendar c = Calendar.getInstance();
		StringUtil.internetDF.setTimeZone(TimeZone.getTimeZone("UTC")); 
		Date date =  c.getTime();
		if(currentTime != null){
			result = StringUtil.internetDF.format(currentTime);
			Log.e("ELAD", currentTime.getTime() +"");
			currentTime = date;
		}else{

			currentTime = new Date(date.getTime());
			c.set(2014, 7, 1);
			date = c.getTime();
			date.setHours(0);
			date.setMinutes(0);
			date.setSeconds(0);
			date.setMonth(1);
			
			
			
		
			result = StringUtil.internetDF.format(date);
		}

		return result;

	}

}
