package com.application.imageholders;


public class FBPostHolder  extends ImageHolder {

	String postId;
	String userImage;
	String postDate;
	String message;
	String pictureUrl;
	String caption;
	String userName;
	int likesNumber;
	int commentNumber;
	int width = 0;
	int height = 0;
	
	
	public FBPostHolder(String postId,String userName,String date,String userPic,String message,String pic,String caption,int likes,int comments){
		super(postId, "", userPic);
		this.postDate = date;
		this.postId = postId;
		this.userImage = userPic;
		this.userName = userName;
		this.pictureUrl = pic;
		this.caption = caption;
		this.likesNumber = likes;
		this.message = message;
		this.commentNumber = comments;
	}

	
	public String getPostID(){
		return postId;
	}
	
	public String getPostDate(){
		return postDate;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public String getUserPic(){
		return userImage;
	}
	
	public String getMessage(){
		return message;
	}
	
	public String getCaption(){
		return caption;
	}
	
	public String getPictureURL(){
		return pictureUrl;
	}
	
	public int getLikesNumber(){
		return likesNumber;
	}
	
	public void setLikesNumber(int number){
		likesNumber = number;
	}
	
	public int getCommentNumber(){
		return commentNumber;
	}


	public int getWidth() {
		return width;
	}

	public int getHeight(){
		return height;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}


	public void setHeight(int height) {
		this.height = height;		
	}


	public void setImageUrl(String image) {
		// TODO Auto-generated method stub
		pictureUrl = image;
	}
	
}
