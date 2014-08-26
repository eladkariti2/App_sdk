package com.application.facebook.model;


public class FBPost extends FbModel {

	private String message ;
	private String created_time;
	private String caption;
	private String picture;
	
	private FbProfilePic from;
	
	public String getMessage(){
		return message;
	}
	
	public String getCreatedTime(){
		return created_time;
	}
	
	public String getCaption(){
		return caption;
	}
	
	public String getPostPicture(){
		return picture;
	}
	
	public String getUserName() {
		return from.getName();
	}
	
	public String getUserPicture(){
		return from.getUrl();
	}
	
	
	protected  class FBLikes {
		protected FBLikesSummery  summary;
	}
	
	protected  class FBLikesSummery {
		protected int  total_count;
	}
}
