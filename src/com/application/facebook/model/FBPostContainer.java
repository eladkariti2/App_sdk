package com.application.facebook.model;

import java.util.ArrayList;
import java.util.List;

public class FBPostContainer extends FBModel{


	private List<FBPost> data;
	private FBPagination paging;
	
	public List<FBPost> getPosts() {
		List<FBPost> posts = new ArrayList<FBPost>();
		if(data!= null)	{
			posts = data;
		}
		return posts;
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
	
	public String getPrevious(){
		String result = "";
		if(paging != null){
			result = paging.getPrevious();
		}
		return result;
	}
	
}
