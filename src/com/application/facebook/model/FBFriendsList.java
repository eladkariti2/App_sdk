package com.application.facebook.model;

import java.util.List;

public class FBFriendsList extends FBModel{

	
	protected List<FBFriend> data;
	protected FBPagination paging;
	
	public List<FBFriend>  getFriends(){
		return data;
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
	public boolean  hasFriends(){
		return data != null && !data.isEmpty();
		
	}
	
	
}