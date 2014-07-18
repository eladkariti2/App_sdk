package com.application.asynctask;

import java.util.List;

import android.os.AsyncTask;

import com.application.helper.StaticObjectHalper;
import com.application.listener.AsyncTaskListener;
import com.application.models.GenericModel;
import com.application.utils.JsonUtil;
import com.application.utils.ServerUtil;
import com.application.utils.StringUtil;

public class GenericModelJsonAsyncTask extends AsyncTask<String, Void, GenericModel> {

	Class<GenericModel> mLoadedClass;
	Exception mException = null;
	AsyncTaskListener mListener;
	
	
	public GenericModelJsonAsyncTask(AsyncTaskListener listener,Class loadedClass)
	{
		mListener = listener;
		mLoadedClass = loadedClass;
	}
	
	@Override
	protected GenericModel doInBackground(String... params) {
		String url = params[0];
		String json = null;
		
		
		try {
			 json = ServerUtil.doGet(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//For now i dont have server so create static models.
		if(StringUtil.isEmpty(json)){
			List<GenericModel> models = StaticObjectHalper.createStaticBeachList();
			json = JsonUtil.deserialize(models,GenericModel.class);
		}
		
		return null;
	}
	
	protected void onPostExecute(GenericModel model) 
	{	
		if(mException != null){
			mListener.handleException(mException);				
		}
		else{
			mListener.onTaskComplete(model);
		}
	}

}
