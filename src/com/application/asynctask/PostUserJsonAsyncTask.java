package com.application.asynctask;

import java.util.List;

import android.nfc.Tag;
import android.os.AsyncTask;
import android.util.Log;

import com.application.helper.StaticObjectHalper;
import com.application.listener.AsyncTaskListener;
import com.application.models.AccountModel;
import com.application.models.BeachModel;
import com.application.models.GenericModel;
import com.application.utils.JsonUtil;
import com.application.utils.ServerUtil;
import com.application.utils.StringUtil;

public class PostUserJsonAsyncTask extends AsyncTask<String, Void, AccountModel> {

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
	protected AccountModel doInBackground(String... params) {
		String url = params[0];
		String json = null;
		AccountModel account = new AccountModel();
		
		try {
			Log.d(TAG, "url");
			 json = ServerUtil.doGet(url);
		} catch (Exception e) {
			Log.d(TAG, "Exeption: can't load account and create user, message: " + e.getMessage() );
			mException = e;
		}
		
		//For now i dont have server so create static models.
//		if(StringUtil.isEmpty(json)){
//			List<BeachModel> models = StaticObjectHalper.createStaticBeachList();
//			account.setmID("1");
//			account.setList(models);
//			json = JsonUtil.deserialize(account,mLoadedClass);
//		}
		
		account = (AccountModel)JsonUtil.serialize(json,mLoadedClass);
		
		return account;
	}
	
	protected void onPostExecute(AccountModel model) 
	{	
		if(mException != null){
			mListener.handleException(mException);				
		}
		else{
			mListener.onTaskComplete(model);
		}
	}

}
