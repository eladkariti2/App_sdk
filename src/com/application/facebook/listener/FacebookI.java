package com.application.facebook.listener;

import com.application.facebook.model.FBModel;

public interface FacebookI {

	public void onLoaded(FBModel model);
	public void onEror();
}
