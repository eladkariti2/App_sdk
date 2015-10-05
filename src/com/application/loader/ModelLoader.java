package com.application.loader;

import android.location.Location;
import android.os.AsyncTask;

import com.application.asynctask.GetAccountAsyncTask;
import com.application.asynctask.PostUserJsonAsyncTask;
import com.application.facebook.model.FBProfilePic;
import com.application.facebook.util.FacebookUtil;
import com.application.listener.AccountLoaderListener;
import com.application.loader.creator.APAccountLoaderCreator;
import com.application.loader.creator.APUpdateUSerCreator;
import com.application.models.AccountModel;
import com.application.models.AreaType;
import com.application.utils.AppData;

public class ModelLoader {


	public static void accountModelLoader(AccountLoaderListener accountLoaderListener) {
		APAccountLoaderCreator  modelCreator = new APAccountLoaderCreator("1",AreaType.Center);
		GetAccountAsyncTask loader = new GetAccountAsyncTask(accountLoaderListener, AccountModel.class);
		loader.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, modelCreator.getURL(""));
	}
	
	public static void updateOrCreateUser(AccountLoaderListener accountLoaderListener) {
		FBProfilePic userProfile = FacebookUtil.getUserProfile();
		Location location = AppData.getUserLocation();
		APUpdateUSerCreator  modelCreator = new APUpdateUSerCreator(userProfile.getId(),userProfile.getName(),userProfile.getUrl(),
				userProfile.gebirthday(),location.getLatitude() + "",location.getLongitude() + "", AreaType.Center);
		PostUserJsonAsyncTask loader = new PostUserJsonAsyncTask(accountLoaderListener, AccountModel.class);
		loader.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, modelCreator.getURL(""));
	}
}
