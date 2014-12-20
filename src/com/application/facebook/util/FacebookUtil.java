package com.application.facebook.util;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.application.activities.FacebookAuthenticationActivity;
import com.application.facebook.FacebookPermissions;
import com.application.facebook.interfaces.FacebookLoaderI;
import com.application.facebook.model.FBModel;
import com.application.facebook.model.FBProfilePic;
import com.application.facebook.requset.APFeedRequest;
import com.application.facebook.requset.APLikeRequest;
import com.application.facebook.requset.APPostCommentRequest;
import com.application.facebook.requset.APPostToFeedRequest;
import com.application.listener.AsyncTaskListener;
import com.application.text.APConstant;
import com.application.utils.AppData;
import com.application.utils.JsonUtil;
import com.application.utils.PreferenceUtil;
import com.facebook.Session;
import com.facebook.Session.NewPermissionsRequest;
import com.facebook.SessionState;


public class FacebookUtil {
	public static final List<String> BASIC_APP_PERMISSIONS = Arrays.asList(new String[]{"publish_actions" , "publish_stream", "manage_pages"});
	public static final List<String> PUBLISH_APP_PERMISSIONS = Arrays.asList(new String[]{"publish_actions", "publish_stream", "manage_pages" });

	public static final String TAG = "FacebookUtil";

	public static String getFBAuthToken(Context context){
		PreferenceUtil prefUtil = PreferenceUtil.getInstance();
		return prefUtil.getStringPref(APConstant.FACEBOOK_ACCESS_TOKEN_KEY, null);

	}

	public static void setFBAuthToken(Context context, String value){
		PreferenceUtil prefUtil = PreferenceUtil.getInstance();
		prefUtil.setStringPref(APConstant.FACEBOOK_ACCESS_TOKEN_KEY, value);
	}

	public static long getFBTokenExpiration(Context context){
		PreferenceUtil prefUtil = PreferenceUtil.getInstance();
		return prefUtil.getLongPref(APConstant.FACEBOOK_ACCESS_EXPIRATION_KEY, 0);

	}

	public static void setFBTokenExpiration(Context context, long value){
		PreferenceUtil prefUtil = PreferenceUtil.getInstance();
		prefUtil.setLongPref(APConstant.FACEBOOK_ACCESS_EXPIRATION_KEY, value);
	}

	// Which permissions should be requested
	public static FacebookPermissions getApplicationFBPermissions(){

		FacebookPermissions result = new FacebookPermissions();
		result.setPublishPermissions(BASIC_APP_PERMISSIONS);

		return result;
	}

	public static boolean publishPermissionRequestHasChanged(Session session, FacebookPermissions newPermissions){
		boolean result = false;
		if(newPermissions.getPublishPermissions()!= null){
			result = !isSubsetOf(newPermissions.getPublishPermissions(), session.getPermissions());
		}
		return result;

	}

	public static boolean readPermissionRequestHasChanged(Session session, FacebookPermissions newPermissions){
		boolean result = false;
		if(newPermissions.getReadPermissions()!= null){
			result = !isSubsetOf(newPermissions.getReadPermissions(), session.getPermissions());
		}
		return result;

	}
	
	private static boolean isSubsetOf(Collection<String> subset, Collection<String> superset) {
		for (String string : subset) {
			if (!superset.contains(string)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isTokenValid(){

		Session session = Session.getActiveSession();
		return session != null && session.isOpened();
	}
	
	public static boolean isTokenValid(List<String> neededPermissions){

		Session session = Session.getActiveSession();
		return session != null && session.isOpened() && isSubsetOf(neededPermissions, session.getPermissions());
	}
	
	public static void loginTofacebook(Activity activity){
		FacebookAuthenticationActivity.StartFacebookAuthenticationActivity(activity);
	}
	
	public static void postFeedTofacebook(Context context,AsyncTaskListener<FBModel> listener,String message,Bitmap image ){
		Session session = Session.getActiveSession();
		if(isSubsetOf(PUBLISH_APP_PERMISSIONS,session.getPermissions())){
			APPostToFeedRequest req  = new APPostToFeedRequest(AppData.getAPAccount().getFBPageID(),message, image, listener);
			req.doQuery();
		}
		else{
			APPostToFeedRequest req  = new APPostToFeedRequest(AppData.getAPAccount().getFBPageID(), message, image, listener);
			req.doQuery();
		}
	}
	
	public static void postFeedTofacebook(Context context,AsyncTaskListener<FBModel> listener,String message ){
		postFeedTofacebook(context,listener,message,null);
	}
	
	public static void postCommentTofeed(Context context,AsyncTaskListener<FBModel>  listener,String postID,String message ){
		Session session = Session.getActiveSession();
		if(isSubsetOf(PUBLISH_APP_PERMISSIONS,session.getPermissions())){
			APPostCommentRequest req  = new APPostCommentRequest(postID,message, listener);
			req.doQuery();
		}
		else{
			APPostCommentRequest req  = new APPostCommentRequest(postID,message, listener);
			req.doQuery();
		}
	}
	

	public static void updateLike(Context context,final AsyncTaskListener listener,String commentID,boolean isLiked){
		APLikeRequest likeReq = new APLikeRequest(commentID, listener, isLiked);
		likeReq.doQuery();
	}
	
	public static void clearFBToken(Context context){
		setFBAuthToken(context,null);
		setFBTokenExpiration(context,0);
		
		Session session = Session.getActiveSession();
		if(session!= null)	{
			session.close();
		}
	}
	
	
	public static void loadFacebookProfile(Context context,FacebookLoaderI listener){
			
	}
	
	
	public static void loadFacebookPage(Context context,final String pageID,final String date,final AsyncTaskListener<FBModel> listener){
		
		((Activity)context).runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				APFeedRequest req = new APFeedRequest(pageID,date,"","", listener);
				req.doQuery();
			}
		});
		
	}
	
	public static FBProfilePic getUserProfile(){
		String user = AppData.getProperty(APConstant.USER_FACEBOOK_PROFILE);
		FBProfilePic model = (FBProfilePic)JsonUtil.serialize(user, FBProfilePic.class);
		return model;
	}
	
	public static void setUserProfile(FBModel model){
		String user = JsonUtil.deserialize(model, FBProfilePic.class);
		AppData.setProperty(APConstant.USER_FACEBOOK_PROFILE,user);
	}
	
	
	
	public static void postLocation(Context context,String location,String description,String name,String url) {
//		String imageURL = MapRouteUtil.getLocationStaticImageURL(location);
//		FacebookFeed feed = new FacebookFeed(name,context.getString(R.string.fb_share_app_description),StringUtil.isEmpty(description) ? " "  :description,imageURL,url);
//		FacebookAction action = new FacebookAction(context, feed);
//		
//		if(isTokenValid()){
//			action.execute();
//		}else{
//			loginTofacebook(context, action);
//		}
	}
	

	//this is facebook session listener
	public  static class SessionStatusCallback implements Session.StatusCallback {

		Context context;
		Session.StatusCallback callbackDelegate;
		boolean hasPendingBasicPermissions = false;
		boolean hasPendingReadPermissions = false;
		boolean hasPendingPublishPermissions = false;
		
		public SessionStatusCallback(Context context,Session.StatusCallback callbackDelegate){
			this(context,false,callbackDelegate);
		}
		
		public SessionStatusCallback(Context context,boolean publishPermissionNeeded,Session.StatusCallback callbackDelegate){
			this.context = context;
			this.callbackDelegate = callbackDelegate;
			hasPendingPublishPermissions = publishPermissionNeeded;
			
		}
		
		public SessionStatusCallback(Context context,Session.StatusCallback callbackDelegate,String action){
			this.context = context;
			this.callbackDelegate = callbackDelegate;
		}
		

		private void onSuccefullyFinished() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void call(Session session, SessionState state,Exception exception) {

			switch (state) {
			case OPENED:
				Log.d(TAG,"session OPENED");
				Log.d(TAG,"onComplete ");
				Log.d(TAG,"permissions " + session.getPermissions());

				
				FacebookUtil.setFBAuthToken(context, session.getAccessToken());
				Log.d(TAG,"access token is " + session.getAccessToken());
				FacebookUtil.setFBTokenExpiration(context,session.getExpirationDate().getTime());
				
				if(hasPendingPublishPermissions && FacebookUtil.publishPermissionRequestHasChanged(session, FacebookUtil.getApplicationFBPermissions())){
					session.requestNewPublishPermissions(new NewPermissionsRequest((Activity) context, FacebookUtil.PUBLISH_APP_PERMISSIONS));
					hasPendingBasicPermissions = false;
				}
				else if(hasPendingReadPermissions && FacebookUtil.readPermissionRequestHasChanged(session, FacebookUtil.getApplicationFBPermissions())){
					//session.requestNewReadPermissions(new NewPermissionsRequest((Activity) context, FacebookUtil.EXTENDED_READ_PERMISSIONS));
					hasPendingReadPermissions = false;
				}
				break;

			case OPENED_TOKEN_UPDATED:
				Log.d(TAG,"session OPENED_TOKEN_UPDATED");
				if(!hasPendingBasicPermissions && !hasPendingReadPermissions){
					onSuccefullyFinished();
				}
				else if(hasPendingBasicPermissions){
					session.requestNewPublishPermissions(new NewPermissionsRequest((Activity) context, FacebookUtil.BASIC_APP_PERMISSIONS));
					hasPendingBasicPermissions = false;
				}
				else if (hasPendingReadPermissions){
					//session.requestNewReadPermissions(new NewPermissionsRequest((Activity) context, FacebookUtil.EXTENDED_READ_PERMISSIONS));
					hasPendingReadPermissions = false;
				}

				break;
			case CLOSED:
				Log.d(TAG,"session CLOSED");
				break;
			case CLOSED_LOGIN_FAILED:
				Log.d(TAG,"CLOSED_LOGIN_FAILED state " + exception.getMessage());
				
				break;
			case CREATED:
				Log.d(TAG,"session CREATED");
				break;
			case CREATED_TOKEN_LOADED:
				Log.d(TAG,"session CREATED_TOKEN_LOADED");
				break;
			case OPENING:
				Log.d(TAG,"session OPENING");
				break;

			}

		}

	}

}