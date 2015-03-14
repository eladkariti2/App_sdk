package com.application.loader;

import android.os.AsyncTask;

import com.application.asynctask.AccountJsonAsyncTask;
import com.application.asynctask.GenericModelJsonAsyncTask;
import com.application.facebook.model.FBProfilePic;
import com.application.facebook.util.FacebookUtil;
import com.application.listener.AccountLoaderListener;
import com.application.listener.GeneralModelListener;
import com.application.loader.creator.APAccountLoaderCreator;
import com.application.loader.creator.APUpdateUSerCreator;
import com.application.models.AccountModel;
import com.application.models.GenericModel;

public class ModelLoader {


	public static void AccountModelLoader(AccountLoaderListener accountLoaderListener) {
		APAccountLoaderCreator  modelCreator = new APAccountLoaderCreator("", "", 100);
		AccountJsonAsyncTask loader = new AccountJsonAsyncTask(accountLoaderListener, AccountModel.class);
		loader.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, modelCreator.getURL(""));
		
	}
	
	public static void updateOrCreateUser(AccountLoaderListener accountLoaderListener) {
		FBProfilePic userProfile = FacebookUtil.getUserProfile();
		APUpdateUSerCreator  modelCreator = new APUpdateUSerCreator(userProfile.getName(),userProfile.getUrl(),
				"birthday","","",100);
		AccountJsonAsyncTask loader = new AccountJsonAsyncTask(accountLoaderListener, AccountModel.class);
		loader.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, modelCreator.getURL(""));
		
	}
}
