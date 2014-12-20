package com.application.facebook.model;

import java.util.ArrayList;
import java.util.List;



public class FBPost extends FBModel{

	private String message ;
	private String created_time;
	private String caption;
	private String picture;
	private String link;
	private String object_id;
	private String source;
	private MediaType type;
	
	private FBLikes likes;
	
	
	private FBCommentsContainer	comments;
	
	private FBProfilePic from;
	
	public void setUserProfile(FBProfilePic from){
		this.from = from;
	}
	
	public void setLink(String link){
		this.link = link;
	}
	
	public void setSource(String source){
		this.source = source;
	}
	
	public String getSource(){
		return source;
	}
	
	public String getMessage(){
		return message;
	}
	
	public void setMessage(String msg){
		message = msg;
	}
	
	public String getCreatedTime(){
		return created_time;
	}
	
	public void setCreatedTime(String time){
		created_time = time;
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
	
	public String getObject_id() {
		return object_id;
	}
	
	public MediaType getType() {
		return type;
	}

	public void setType(MediaType type) {
		this.type = type;
	}


	public void setObject_id(String object_id) {
		this.object_id = object_id;
	}
	
	public void setLikeNumber(int likeNumber){
		if (likes != null){
			 likes.setLikesNumber(likeNumber);
		}
	}
	
	public List<FBModel> getLikes(){
		if(likes != null){
			return likes.getLikes();
		}
		return null;
	}
	
	
	public boolean isUserLike(){
		boolean isUserLike = false;
		if(likes!=null){
			isUserLike = likes.getUserLike();
		}
		return isUserLike;
	}
	
	public void setIsUserLike(boolean isLikes){
		if (likes != null){
			 likes.setUserLike(isLikes);
		}
	}
	
	public List<FBPost> getComments(){
		List<FBPost> commentsList = new ArrayList<FBPost>();
		if(comments!= null && comments.data != null){
			commentsList =  comments.data;
		}
		return commentsList;
	}
	
	
	public String getLimk(){
		return link;
	}
	
	protected  class FBCommentsContainer {
		protected List<FBPost> data;
	}
	
	
	
	
	
	protected  class FBLikes {
		protected List<FBModel> data;
		protected FBLikesSummery  summary;
		protected boolean  isUserLike;
		
		protected List<FBModel> getLikes() {
			return data;
		}
		
		protected int  getLikesNumber() {
			return summary.total_count;
		}
		
		protected void  setLikesNumber(int likeNumber) {
			 summary.total_count = likeNumber;
		}
		
		public void setUserLike(boolean userLike){
			isUserLike = userLike;
		}
		
		public boolean getUserLike(){
			return isUserLike;
		}
		
	}
	
	protected  class FBLikesSummery {
		protected int  total_count;
	}
	

}
