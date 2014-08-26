package com.application.facebook.listener;

import android.content.Context;

import com.application.facebook.asyncTask.FBPostsLoaderAsyncTask;
import com.application.facebook.interfaces.FacebookLoaderI;
import com.application.facebook.model.FBFeed;
import com.application.facebook.model.FBPostContainer;
import com.application.facebook.model.FbModel;
import com.application.utils.StringUtil;

public class FacebookPageLoaderListener extends FacebookLoaderListener implements FacebookLoaderI {

	FBFeed mFeed;
	
	public FacebookPageLoaderListener(Context context, FacebookLoaderI listener) {
		super(context, listener);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onTaskComplete(FbModel result) {
		mFeed = (FBFeed) result;
		
		if(mFeed != null ){
			//check if need to load more Post
			String nextPage = mFeed.getPosts().getNext();
			nextPage = "";
			if(!StringUtil.isEmpty(nextPage)){
				loadNextComment(nextPage);
			}else{
				finishLoading();
			}
		}else{
			finishLoading();
		}
	}

	private void loadNextComment(String nextPage) {
		// TODO Auto-generated method stub
		FBPostsLoaderAsyncTask loader = new FBPostsLoaderAsyncTask(new FacebookCommentLoaderListener(mContext, this),FBPostContainer.class);
		loader.execute(nextPage);
	}

	private void finishLoading() {
		// TODO Auto-generated method stub
		mListener.onSuccess(mFeed);
	}

	@Override
	public void onSuccess(FbModel model) {
		// TODO Auto-generated method stub
		FBPostContainer comments = (FBPostContainer) model;
		if(comments.getPosts() != null && comments.getPosts().size() > 0){
			mFeed.getPosts().getPosts().addAll(comments.getPosts());
		}
		
		//check if need to load more Post
		String nextPage = comments.getNext();
		if(!StringUtil.isEmpty(nextPage)){
			loadNextComment(nextPage);
		}else{
			finishLoading();
		}
	}

	@Override
	public void onFailure(Exception e) {
		// TODO Auto-generated method stub
		finishLoading();
	}
	
	
	
	

}
