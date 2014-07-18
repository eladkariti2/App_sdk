package com.application.interfaces;

import com.facebook.Session;
import com.facebook.UiLifecycleHelper;

public interface BaseActivityFacebookAuthoriziationI {

	public UiLifecycleHelper getFacebookSessionLifecycleHelper();
	public Session.StatusCallback initFacebookSessionCallback();
	
}
