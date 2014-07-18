package com.application.loader;

import com.application.asynctask.AccountJsonAsyncTask;
import com.application.asynctask.GenericModelJsonAsyncTask;
import com.application.listener.AccountLoaderListener;
import com.application.listener.GeneralModelListener;
import com.application.models.AccountModel;
import com.application.models.GenericModel;

public class ModelLoader {

	public static void GeneralModelLoader(GeneralModelListener listener){
		String generalModelUrl = getGenericModelURL();
	    GenericModelJsonAsyncTask loader = new GenericModelJsonAsyncTask(listener, GenericModel.class);
		loader.execute(generalModelUrl);
	}

	private static String getGenericModelURL() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void AccountModelLoader(AccountLoaderListener accountLoaderListener) {
		String accountModelUrl = getGenericModelURL();
		AccountJsonAsyncTask loader = new AccountJsonAsyncTask(accountLoaderListener, AccountModel.class);
		loader.execute(accountModelUrl);
		
	}
}
