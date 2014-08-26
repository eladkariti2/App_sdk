package com.application.facebook.model;

import java.util.List;

public class FBPostContainer  extends FbModel{

	private List<FBPost> data;
	private FBContinuesModel paging;
	
	public List<FBPost> getPosts() {
		return data;
	}

	public void setPosts(List<FBPost> data) {
		this.data = data;
	}
	
	public String getNext(){
		String result = "";
		if(paging != null){
			result = paging.getNext();
		}
		return result;
	}
	
}
