package com.application.asynctask;

import java.util.List;

import android.os.AsyncTask;

import com.application.helper.StaticObjectHalper;
import com.application.listener.AsyncTaskListener;
import com.application.models.AccountModel;
import com.application.models.BeachModel;
import com.application.models.GenericModel;
import com.application.utils.JsonUtil;
import com.application.utils.ServerUtil;
import com.application.utils.StringUtil;

public class AccountJsonAsyncTask extends AsyncTask<String, Void, AccountModel> {

	Class<AccountModel> mLoadedClass;
	Exception mException = null;
	AsyncTaskListener mListener;
	
	
	public AccountJsonAsyncTask(AsyncTaskListener listener,Class loadedClass)
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
			 json = ServerUtil.doGet(url);
		} catch (Exception e) {
			e.printStackTrace();
			//mException = e;
		}
		
		//For now i dont have server so create static models.
		if(StringUtil.isEmpty(json)){
			List<BeachModel> models = StaticObjectHalper.createStaticBeachList();
			account.setmID("1");
			account.setList(models);
			json = JsonUtil.deserialize(account,mLoadedClass);
		}
		
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
