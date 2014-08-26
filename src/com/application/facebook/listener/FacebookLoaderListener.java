package com.application.facebook.listener;

import android.content.Context;

import com.application.facebook.interfaces.FacebookLoaderI;
import com.application.facebook.model.FbModel;
import com.application.listener.AsyncTaskListener;

public class FacebookLoaderListener implements AsyncTaskListener<FbModel> {
	protected Context mContext;
	protected FacebookLoaderI mListener;
	
	public FacebookLoaderListener(Context context,FacebookLoaderI listener){
		mContext = context;
		mListener = listener;
		
	}
	
	@Override
	public void handleException(Exception e) {
		// TODO Auto-generated method stub
		mListener.onFailure(e);
	}

	@Override
	public void onTaskComplete(FbModel result) {
		// TODO Auto-generated method stub
		mListener.onSuccess(result);
	}

	@Override
	public void onTaskStart() {
		// TODO Auto-generated method stub
		
	}

}
