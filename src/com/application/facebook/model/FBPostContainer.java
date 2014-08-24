package com.application.facebook.model;

import java.util.List;

public class FBPostContainer {

	private List<FBPost> data;
	private FBContinuesModel paging;
	
	public List<FBPost> getPosts() {
		return data;
	}

	public void setPosts(List<FBPost> data) {
		this.data = data;
	}
	
	public String getNext(){
		return paging.getNext();
	}
	
}
