package com.application.facebook.asyncTask;

import android.os.AsyncTask;
import android.util.Log;

import com.application.facebook.model.FbModel;
import com.application.facebook.model.FbProfilePic;
import com.application.listener.AsyncTaskListener;
import com.application.utils.JsonUtil;
import com.application.utils.ServerUtil;

public class FBModelLoaderAsynTask extends AsyncTask<String, Void, FbModel> {

	Class mLoadedClass;
	Exception mException = null;
	AsyncTaskListener mListener;
	
	public FBModelLoaderAsynTask(AsyncTaskListener listener,Class modelClass)
	{
		mLoadedClass = modelClass;
		mListener  = listener;
	}
	
	@Override
	protected FbModel doInBackground(String... params) {
		String url = params[0];
		String json = null;
		
		try {
			 json = ServerUtil.doGet(url);
		} catch (Exception e) {
			Log.e("FBModelLoaderAsynTask", e.getMessage());
			mException = e;
		}
		
		
		
		FbModel model = (FbModel)JsonUtil.serialize(json,mLoadedClass);
		
		return model;
	} 

	protected void onPostExecute(FbModel model) 
	{	
		if(mException != null){
			mListener.handleException(mException);				
		}
		else{
			mListener.onTaskComplete(model);
		}
	}
	
	
}
