package com.application.facebook.interfaces;

import com.application.facebook.model.FBModel;

public interface FacebookLoaderI {

	public void onSuccess(FBModel model);
	public void onFailure(Exception e);	
}
