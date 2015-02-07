package com.application.facebook.model;

import java.util.ArrayList;
import java.util.List;

public class FBFeed extends FBModel {

	protected FBPostContainer feed;
	
	public List<FBPost> getPosts() {
		List<FBPost> post = new ArrayList<FBPost>();
		if(feed != null){
			post = feed.getPosts();
		}
		return post;
	}

	public void setPosts(FBPostContainer feeds) {
		this.feed = feeds;
	}
	
	public boolean hasPost(){
		return feed != null && feed.getPosts() != null && !feed.getPosts().isEmpty();
	}
}