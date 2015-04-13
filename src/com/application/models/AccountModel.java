package com.application.models;

import java.util.ArrayList;
import java.util.List;

import com.application.helper.StaticObjectHalper;
import com.application.utils.JsonUtil;

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
		List<BeachModel> models = StaticObjectHalper.createStaticBeachList();
		list= models;
		return list;
	}

	public void setList(List<BeachModel> list) {
		this.list = list;
	}

	public String getFBPageID() {
		// TODO Auto-generated method stub
		String id = "515881818544964"; // temp
	//	id  = "135130956523111";//וואלה חדשות 
		return id;
	}

	
}
