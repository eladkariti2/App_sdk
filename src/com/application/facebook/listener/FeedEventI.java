package com.application.facebook.listener;

import com.application.facebook.model.FBModel;

public interface FeedEventI {

	public void onLoaded(FBModel model);
	public void onEror();
}
