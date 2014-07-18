package com.application.base.behaviour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.application.activities.FacebookAuthenticationActivity;
import com.application.interfaces.BaseActivityFacebookAuthoriziationI;
import com.facebook.UiLifecycleHelper;

public class BaseBehaviour {

	public static void  onCreate(Activity activity, Bundle savedInstanceState){
		
		if(activity instanceof BaseActivityFacebookAuthoriziationI){
			UiLifecycleHelper facebookLifecycleHelper = getFacebookUiHelper((BaseActivityFacebookAuthoriziationI) activity);
			if(facebookLifecycleHelper != null){
				facebookLifecycleHelper.onCreate(savedInstanceState);

			}
		}
	}
	
	public static void onResume(Activity activity){
		if(activity instanceof BaseActivityFacebookAuthoriziationI){
			UiLifecycleHelper facebookLifecycleHelper =  getFacebookUiHelper((BaseActivityFacebookAuthoriziationI) activity);
			if(facebookLifecycleHelper != null){
				facebookLifecycleHelper.onResume();
			}
		}
		
	}

	
	public static void onPause(Activity activity){
		if(activity instanceof BaseActivityFacebookAuthoriziationI){
			UiLifecycleHelper facebookLifecycleHelper =  getFacebookUiHelper((BaseActivityFacebookAuthoriziationI) activity);
			if(facebookLifecycleHelper != null){
				facebookLifecycleHelper.onPause();
			}
		}
	}
	
	public static void onActivityResult(Activity activity,int requestCode, int resultCode, Intent data) {
		if(activity instanceof BaseActivityFacebookAuthoriziationI){
			UiLifecycleHelper facebookLifecycleHelper =  getFacebookUiHelper((BaseActivityFacebookAuthoriziationI) activity);
			if(facebookLifecycleHelper != null){
				facebookLifecycleHelper.onActivityResult(requestCode, resultCode, data);
			}
		}
	}
	
	public static void onSaveInstanceState(Activity activity,Bundle outState) {
	
		if(activity instanceof BaseActivityFacebookAuthoriziationI){
			UiLifecycleHelper facebookLifecycleHelper =  getFacebookUiHelper((BaseActivityFacebookAuthoriziationI) activity);
			if(facebookLifecycleHelper != null){
				facebookLifecycleHelper.onSaveInstanceState(outState);

			}
		}
	}

	public static void onStop(Activity activity){
	
		
		if(activity instanceof BaseActivityFacebookAuthoriziationI){
			UiLifecycleHelper facebookLifecycleHelper =  getFacebookUiHelper((BaseActivityFacebookAuthoriziationI) activity);
			if(facebookLifecycleHelper != null){
				facebookLifecycleHelper.onStop();

			}
		}
	}

	public static void onDestroy(Activity activity){
		if(activity instanceof BaseActivityFacebookAuthoriziationI){
			UiLifecycleHelper facebookLifecycleHelper =  getFacebookUiHelper((BaseActivityFacebookAuthoriziationI) activity);
			if(facebookLifecycleHelper != null){
				facebookLifecycleHelper.onDestroy();
			}
		}
	}
	
	
	
	
	private static UiLifecycleHelper getFacebookUiHelper(BaseActivityFacebookAuthoriziationI baseActivity){
		return baseActivity instanceof FacebookAuthenticationActivity ? null : baseActivity.getFacebookSessionLifecycleHelper();
	}
	
}
