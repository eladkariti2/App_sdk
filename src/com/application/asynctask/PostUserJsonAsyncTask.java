package com.application.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.application.listener.AsyncTaskListener;
import com.application.models.AccountModel;
import com.application.utils.ServerUtil;

public class PostUserJsonAsyncTask extends AsyncTask<String, Void, Void> {

	private static final String TAG = "PostUserJsonAsyncTask";
	Class<AccountModel> mLoadedClass;
	Exception mException = null;
	AsyncTaskListener mListener;
	
	
	public PostUserJsonAsyncTask(AsyncTaskListener listener,Class loadedClass)
	{
		mListener = listener;
		mLoadedClass = loadedClass;
	}
	
	@Override
	protected Void doInBackground(String... params) {
		mListener.onTaskStart();
		String url = params[0];
		String json = null;
		try {
			Log.d(TAG, "url");
			 json = ServerUtil.doGet(url);
		} catch (Exception e) {
			Log.d(TAG, "Exeption: can't load account and create user, message: " + e.getMessage() );
			mException = e;
		}

		return null;
	}
	
	protected void onPostExecute()
	{	
		if(mException != null){
			mListener.handleException(mException);				
		}
		else{
			mListener.onTaskComplete(null);
		}
	}

}
