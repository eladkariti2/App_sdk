package com.application.models;

import java.util.List;

public class AccountModel {
	
	private String mID;
	private List<GenericModel> list;
	
	public String getmID() {
		return mID;
	}
	
	public void setmID(String mID) {
		this.mID = mID;
	}

	public List<GenericModel> getList() {
		return list;
	}

	public void setList(List<GenericModel> list) {
		this.list = list;
	}

}
