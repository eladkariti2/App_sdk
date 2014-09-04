package com.application.facebook.model;


public class FBFeed extends FbModel{

	private FBPostContainer feed;
	

	public FBPostContainer getPosts() {
		return feed;
	}

	public void setPosts(FBPostContainer feeds) {
		this.feed = feeds;
	}
	
	
}
