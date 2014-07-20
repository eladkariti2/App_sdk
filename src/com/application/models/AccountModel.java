package com.application.models;

import java.util.List;

public class AccountModel {
	
	private String mID;
	private List<BeachModel> list;
	
	public String getmID() {
		return mID;
	}
	
	public void setmID(String mID) {
		this.mID = mID;
	}

	public List<BeachModel> getList() {
		return list;
	}

	public void setList(List<BeachModel> list) {
		this.list = list;
	}

}
