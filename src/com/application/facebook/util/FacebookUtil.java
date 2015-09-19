package com.application.facebook.util;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;

import com.application.CustomApplication;
import com.application.activities.FacebookAuthenticationActivity;
import com.application.facebook.FacebookPermissions;
import com.application.facebook.listener.FBAuthoriziationListener;
import com.application.facebook.loader.APCommentsRequest;
import com.application.facebook.loader.APFeedRequest;
import com.application.facebook.loader.APFriendsLoader;
import com.application.facebook.loader.APLikeRequest;
import com.application.facebook.loader.APPostCommentRequest;
import com.application.facebook.loader.APPostToFeedRequest;
import com.application.facebook.loader.APUserProfileRequest;
import com.application.facebook.loader.FacebookPhotoLoader;
import com.application.facebook.model.FBModel;
import com.application.facebook.model.FBPhoto;
import com.application.facebook.model.FBProfilePic;
import com.application.facebook.permissions.APFBPermissions;
import com.application.facebook.permissions.APPermissionsType;
import com.application.facebook.permissions.BasicFBPermissions;
import com.application.facebook.permissions.FeedFBPermissions;
import com.application.listener.AsyncTaskListener;
import com.application.text.APConstant;
import com.application.utils.AppData;
import com.application.utils.JsonUtil;
import com.application.utils.OSUtil;
import com.application.utils.PreferenceUtil;

import com.facebook.AccessToken;
import com.facebook.FacebookRequestError;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.google.gson.Gson;




public class FacebookUtil {
	public static final String FACEBOOK_USER_KEY = "facebook_user";
	public static final String ACTION_ID_KEY = "actionId";

	public static String getCurrentFBAppId(){
		return CustomApplication.getAppContext().getString(OSUtil.getStringResourceIdentifier("fb_app_id"));
	}

	/**
	 * Check if need to upgrade the publish permission
	 * @param newPermissions the permissions
	 * @return true - if the permission is subset of the token permission.
	 */
	public static boolean publishPermissionRequestHasChanged( APFBPermissions newPermissions){
		boolean result = false;
		AccessToken token = AccessToken.getCurrentAccessToken();
		if(token != null && newPermissions.getPublishPermissions()!= null){
			result = !isSubsetOf(newPermissions.getPublishPermissions(),(Collection) token.getPermissions());
		}
		return result;

	}

	/**
	 * Check if need to upgrade the read permission
	 * @param newPermissions the permissions
	 * @return true - if the permission is subset of the token permission.
	 */
	public static boolean readPermissionRequestHasChanged( APFBPermissions newPermissions){
		boolean result = false;
		AccessToken token = AccessToken.getCurrentAccessToken();

		if(token != null && newPermissions.getReadPermissions()!= null ){
			result = !isSubsetOf(newPermissions.getReadPermissions(), (Collection) token.getPermissions());
		}
		return result;

	}

	/**
	 * Check if the token valid with basic permission
	 * @return
	 */
	public static boolean isTokenValid(){
		return isTokenValid(BasicFBPermissions.getInstance());
	}

	/**
	 *  Check if the token valid
	 * @param neededPermissions the permission that need to be validate
	 * @return true - if the token valid
	 */
	public static boolean isTokenValid(APFBPermissions neededPermissions){
		boolean result = false;
		AccessToken token = AccessToken.getCurrentAccessToken();
		if(token != null && !token.isExpired()){
			result = isSubsetOf(neededPermissions.getAllPermissions(), token.getPermissions());
		}
		return result;
	}

	/**
	 * Check if the token valid and login to facebook with additional permission if needed.
	 * @param context the activity
	 * @param type permission type
	 * @param listener listener to call after token validated or created.
	 */
	public static void updateTokenIfNeeded(Activity context,APPermissionsType type,FBAuthoriziationListener listener){
		APFBPermissions permissions = getPermissionsByType(type) ;
		if(isTokenValid(permissions)){
			listener.onSuccess();
		}else{
			loginToFacebook(context,type,listener);
		}
	}

	/**
	 * Clear token and logout from facebook.
	 */
	public static void clearToken() {
		LoginManager.getInstance().logOut();
	}

	/**
	 *
	 * @param subset
	 * @param superset
	 * @return
	 */
	private static boolean isSubsetOf(Collection<String> subset, Collection<String> superset) {
		for (String string : subset) {
			if (!superset.contains(string)) {
				return false;
			}
		}
		return true;
	}

	public static void loginTofacebook(Activity activity,FBAuthoriziationListener listener,APPermissionsType type){
		FacebookAuthenticationActivity.launchAPFacebookAutherization(activity, listener,type);
	}

	public static boolean isFacebookAppInstalled(){
		return OSUtil.isApplicationInstalled("com.facebook.katana");
	}

	/**
	 * Get permission by type
	 * @param type permission type
	 * @return return the permission object.
	 */
	public static APFBPermissions getPermissionsByType(APPermissionsType type) {
		APFBPermissions permiisions = BasicFBPermissions.getInstance();
		switch (type){
			case Feed:{
				permiisions = FeedFBPermissions.getInstance();
				break;
			}

		}
		return permiisions;

	}

	/**
	 * Share post to the user profile
	 * @param context Activity
	 * @param post the post that need to be shared
	 * @param type the permission type
	 * @param listener listener to call when action finished
	 */
//	public static void share(final Activity context, final FBShare post, final APPermissionsType type, final FBActionListener listener) {
//		if(isTokenValid(getPermissionsByType(type))){
//			handleSharePost(context,post,listener);
//		}else{
//			loginToFacebook(context, type,new FBAuthoriziationListener() {
//				@Override
//				public void onError(Exception error) {
//					listener.onError(error);
//				}
//
//				@Override
//				public void onSuccess() {
//					handleSharePost(context,post,listener);
//				}
//
//				@Override
//				public void onCancel() {
//					listener.onCancel();
//				}
//			});
//
//		}
//	}

	/**
	 *
	 * @param context
	 * @param type
	 * @param listener
	 */
	private static void loginToFacebook( Activity context, APPermissionsType type, FBAuthoriziationListener listener) {
		FacebookAuthenticationActivity.launchAPFacebookAutherization(context, listener, type);
	}

	/**
	 *
	 * @param context
	 * @param post
	 * @param listener
	 */
//	private static void handleSharePost(Activity context,FBShare post, final FBActionListener listener) {
//
//		APFBShareDialog dialog = new APFBShareDialog(context,listener);
//		dialog.displayDialog(post);
//	}

	/**
	 * Post open graph actoin to user profile
	 * @param context the activity
	 * @param action the open graph action
	 * @param type the permission type
	 * @param fbActionListener listener to call when action finished
	 */
//	public static void doPostAction(final Activity context,final  FBShare action, APPermissionsType type,final FBActionListener fbActionListener) {
//		if(isTokenValid(getPermissionsByType(type))){
//			postAction(context,action,fbActionListener);
//
//		}else{
//			loginToFacebook(context, type, new FBAuthoriziationListener() {
//				@Override
//				public void onError(Exception error) {
//					fbActionListener.onError(error);
//				}
//
//				@Override
//				public void onSuccess() {
//					postAction(context,action,fbActionListener);
//				}
//
//				@Override
//				public void onCancel() {
//					fbActionListener.onCancel();
//				}
//			});
//		}
//	}

//	private static void postAction(Activity context, FBShare action, FBActionListener fbActionListener) {
//		APFBActionDialog dialog = new APFBActionDialog(context,fbActionListener);
//		dialog.displayDialog(action);
//	}


	/**
	 * Get comment of fb post
	 * @param postId the post id
	 * @param listener listener to call when request finished
	 */
	public static void getPostComments(String postId,AsyncTaskListener<FBModel> listener){
		APCommentsRequest request = new APCommentsRequest(postId,listener);
		request.doQuery();
	}

	/**
	 * Get posts of feed (facebook page/user id ...)
	 * @param identifier the feed id
	 * @param listener listener to call when request finished
	 */
	public static void getFeedPosts(String identifier,AsyncTaskListener<FBModel> listener) {
		getFeedPosts(identifier, null, null, listener);
	}

	/**
	 * Get posts of feed (facebook page/user id ...)
	 * @param identifier the feed id
	 * @param since start time of the posts
	 * @param until end time of posts
	 * @param listener listener to call when request finished
	 */
	public static void getFeedPosts(String identifier,String since, String until, AsyncTaskListener<FBModel> listener) {
		getFeedPosts(identifier, since, until, null, listener);
	}

	/**
	 * Get posts of feed (facebook page/user id ...)
	 * @param identifier the feed id
	 * @param since start time of the posts
	 * @param until end time of posts
	 * @param limit max number of posts
	 * @param listener listener to call when request finished
	 */
	public static void getFeedPosts(String identifier,String since, String until,String limit, AsyncTaskListener<FBModel> listener) {
		APFeedRequest request = new APFeedRequest(identifier,since,until,limit,listener);
		request.doQuery();
	}

	/**
	 * Get the user fb friends
	 * @param listener listener to call when request finished
	 */
	public static void getFBFriends( AsyncTaskListener<FBModel> listener) {
		APFriendsLoader request = new APFriendsLoader(listener);
		request.doQuery();
	}

	/**
	 * Update like status
	 * @param identifier post id
	 * @param listener listener to call when request finished
	 * @param isLiked if the user liked the post
	 */
	public static void  createLike(Activity activity,final  String identifier,APPermissionsType type, final AsyncTaskListener listener,final boolean isLiked){
		loginIfNeeded(activity,type, new FBAuthoriziationListener() {
			@Override
			public void onError(Exception error) {
				listener.handleException(error);
			}

			@Override
			public void onSuccess() {
				APLikeRequest request = new APLikeRequest(identifier, listener, isLiked);
				request.doQuery();
			}

			@Override
			public void onCancel() {

			}
		});

	}

	private static void loginIfNeeded(Activity activity,APPermissionsType type, FBAuthoriziationListener fbAuthoriziationListener) {
		if(isTokenValid(getPermissionsByType(type))){
			fbAuthoriziationListener.onSuccess();
		}else{
			loginTofacebook(activity,fbAuthoriziationListener,type);
		}
	}

	/**
	 *  Create comment to post
	 * @param identifier post id
	 * @param message the comment message
	 * @param listener  listener to call when request finished
	 */
	public static void createComment(Activity activity,final String identifier,final String message,APPermissionsType type,final AsyncTaskListener<FBModel> listener) {
			loginIfNeeded(activity,type, new FBAuthoriziationListener() {
			@Override
			public void onError(Exception error) {
				listener.handleException(error);
			}

			@Override
			public void onSuccess() {
				APPostCommentRequest request = new APPostCommentRequest(identifier,message,listener);
				request.doQuery();
			}

			@Override
			public void onCancel() {

			}
		});
	}


	/**
	 * Create post to feed (facebook page/user id ...)
	 * @param identifier feed id
	 * @param postText the meesage
	 * @param image image
	 * @param listener listener to call when request finished
	 */
	public static void  createPostToFeed(Activity activity,final String identifier,final String postText,final Bitmap image,APPermissionsType type,final AsyncTaskListener<FBModel> listener) {

		loginIfNeeded(activity,type, new FBAuthoriziationListener() {
			@Override
			public void onError(Exception error) {
				listener.handleException(error);
			}

			@Override
			public void onSuccess() {
				APPostToFeedRequest request = new APPostToFeedRequest(identifier,postText,image,listener);
				request.doQuery();
			}

			@Override
			public void onCancel() {
				listener.handleException(null);
			}
		});
	}

//	/**
//	 * Get user profile
//	 * @param listener listener to call when request finished
//	 */
	public static void loadUserInfo( AsyncTaskListener<FBModel> listener){
		APUserProfileRequest req = new APUserProfileRequest(listener);
		req.doQuery();

	}

	/**
	 * Load fb image from fb
	 * @param photoId fb photo id
	 * @param listener listener to call when request finished
	 */
	public static void  getFBPhoto(String photoId, AsyncTaskListener<FBPhoto> listener){
		FacebookPhotoLoader request = new FacebookPhotoLoader(photoId,listener);
		request.load();
	}


	public static void deleteAction(Activity mContext, String actionID, final AsyncTaskListener<Boolean> asyncTaskListener) {
		AccessToken token = AccessToken.getCurrentAccessToken();
		GraphRequest request = new GraphRequest(token, actionID, null, HttpMethod.DELETE, new GraphRequest.Callback() {
			@Override
			public void onCompleted(GraphResponse graphResponse) {
				FacebookRequestError error = graphResponse.getError();

				// request succeeded
				if(error == null){
					asyncTaskListener.onTaskComplete(true);
				}else{
					asyncTaskListener.handleException(new Exception(error.getErrorMessage()));
				}
			}
		});
		request.executeAsync();
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
}
