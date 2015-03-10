package com.application.loader;

import android.os.AsyncTask;

import com.application.asynctask.AccountJsonAsyncTask;
import com.application.asynctask.GenericModelJsonAsyncTask;
import com.application.listener.AccountLoaderListener;
import com.application.listener.GeneralModelListener;
import com.application.loader.creator.APAccountLoaderCreator;
import com.application.models.AccountModel;
import com.application.models.GenericModel;

public class ModelLoader {

	public static void GeneralModelLoader(GeneralModelListener listener){
//		String generalModelUrl = getGenericModelURL();
//	    GenericModelJsonAsyncTask loader = new GenericModelJsonAsyncTask(listener, GenericModel.class);
//		loader.execute(generalModelUrl);
	}


	public static void AccountModelLoader(AccountLoaderListener accountLoaderListener) {
		APAccountLoaderCreator  modelCreator = new APAccountLoaderCreator("", "", 100);
		AccountJsonAsyncTask loader = new AccountJsonAsyncTask(accountLoaderListener, AccountModel.class);
		loader.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, modelCreator.getURL(""));
		
	}
}
