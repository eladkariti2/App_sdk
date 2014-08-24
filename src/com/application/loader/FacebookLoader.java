package com.application.loader;

import android.content.Context;

import com.application.facebook.asyncTask.FBFeedLoaderAsyncTask;
import com.application.facebook.asyncTask.FBProfilePicAsyncTask;
import com.application.facebook.listener.FacebookLoaderListener;
import com.application.facebook.model.FBFeed;
import com.application.facebook.model.FbModel;
import com.application.facebook.model.FbProfilePic;
import com.application.facebook.opengraph.FbOpenGraphUrlFactory;

public class FacebookLoader {

	
	public static void UserProfilePicLoader(Context con,FacebookLoaderListener listener){
		String url = FbOpenGraphUrlFactory.getFacebokUserBasicURL(con);
		FBProfilePicAsyncTask loader = new FBProfilePicAsyncTask(listener, FbProfilePic.class);
		loader.execute(url);
	}

	public static void FBFeedPageLoader(Context context,String pageID,FacebookLoaderListener listener) {
		String url = FbOpenGraphUrlFactory.getFacebokFeedBasicURL(context,pageID);
		FBFeedLoaderAsyncTask loader = new FBFeedLoaderAsyncTask(listener, FBFeed.class);
		loader.execute(url);
		
	}
	
	
}
