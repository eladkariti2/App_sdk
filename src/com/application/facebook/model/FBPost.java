package com.application.facebook.model;

import java.util.List;

import com.application.facebook.listener.LikeClickListener;


public class FBPost extends FbModel {

	private String message ;
	private String created_time;
	private String caption;
	private String picture;
	private FBLikes likes;
	
	private FBCommentsContainer	comments;
	private int like_count; //it only used in comment class
	
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
	
	public int getLikeNumber(){
		if (likes != null){
			return likes.getLikesNumber();
		}
		return 0;
	}
	
	public List<FbModel> getLike(){
		if(likes != null){
			return likes.getLikes();
		}
		return null;
	}
	
	public List<FBPost> getComments(){
		if(comments!= null){
			return comments.data;
		}
		return null;
	}
	
	protected  class FBCommentsContainer {
		protected List<FBPost> data;
	}
	
	
	
	
	protected  class FBLikes {
		protected List<FbModel> data;
		protected FBLikesSummery  summary;
		
		
		protected List<FbModel> getLikes() {
			return data;
		}
		
		protected int  getLikesNumber() {
			return summary.total_count;
		}
	}
	
	protected  class FBLikesSummery {
		protected int  total_count;
	}
	
	
}
