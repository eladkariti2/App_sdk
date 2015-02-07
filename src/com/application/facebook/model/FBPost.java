package com.application.facebook.model;


import java.util.ArrayList;
import java.util.List;


public class FBPost extends FBModel{

	protected String message ;
	protected String created_time;
	protected String caption;
	protected String picture;
	protected String link;
	protected String object_id;
	protected String source;
	protected FBMediaType type;	
	protected FBLikes likes;		
	protected FBCommentsContainer	comments;	
	protected FBProfilePic from;
	
	
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
		if (getLikes() != null){
			return getLikes().getLikesNumber();
		}
		return 0;
	}
	
	public String getObject_id() {
		return object_id;
	}
	
	public FBMediaType getType() {
		return type;
	}

	public void setType(FBMediaType type) {
		this.type = type;
	}


	public void setObject_id(String object_id) {
		this.object_id = object_id;
	}
	
	public void setLikeNumber(int likeNumber){
		if (getLikes() != null){
			 getLikes().setLikesNumber(likeNumber);
		}
	}
	
	public List<FBModel> getFBModelLikes(){
		if(getLikes() != null){
			return getLikes().getLikes();
		}
		return null;
	}
	
	
	public boolean isUserLike(){
		boolean isUserLike = false;
		if(getLikes()!=null){
			isUserLike = getLikes().getUserLike();
		}
		return isUserLike;
	}
	
	public void setIsUserLike(boolean isLikes){
		if (getLikes() != null){
			 getLikes().setUserLike(isLikes);
		}
	}
	
	public List<FBComment> getComments(){
		List<FBComment> commentsList = new ArrayList<FBComment>();
		if(comments!= null && comments.getData() != null){
			commentsList = comments.getData();
		}
		return commentsList;
	}
	
	
	public String getLimk(){
		return link;
	}

	public FBLikes getLikes() {
		return likes;
	}

	public void setLikes(FBLikes likes) {
		this.likes = likes;
	}
}