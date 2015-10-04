package com.application.models;

import java.util.List;

public class AccountModel  {
	
	private String Id;
	private String FacebookPageId;
	private String VersionNumber;
	private String DepracatedText;
	private String AdLink;
	private String AdProvider;
	private String mBaanerAd;
	private String GameLink;
	private List<BeachModel> BeachesList;


	
	public String getID() {
		return Id;
	}
	
	public void setID(String id) {
		this.Id = Id;
	}


	public String getFBPageID() {
		// TODO Auto-generated method stub
		//String id = "515881818544964"; // temp
		return FacebookPageId;
	}


	public String getVersionNumber() {
		return VersionNumber;
	}

	public void setVersionNumber(String mVersionNumber) {
		this.VersionNumber = mVersionNumber;
	}

	public String getDepracatedText() {
		return DepracatedText;
	}

	public void setDepracatedText(String mDepracatedText) {
		this.DepracatedText = mDepracatedText;
	}

	public String getAdLink() {
		return AdLink;
	}

	public void setAdLink(String mAdLink) {
		this.AdLink = mAdLink;
	}

	public String getAdProvider() {
		return AdProvider;
	}

	public void setAdProvider(String mAdProvider) {
		this.AdProvider = mAdProvider;
	}

	public String getmBaanerAd() {
		return mBaanerAd;
	}

	public void setBaanerAd(String mBaanerAd) {
		this.mBaanerAd = mBaanerAd;
	}

	public String getGameLink() {
		return GameLink;
	}

	public void setGameLink(String mGameLink) {
		this.GameLink = mGameLink;
	}

	public List<BeachModel> getBeaches() {
		return BeachesList;
	}

	public void setBeaches(List<BeachModel> mBeaches) {
		this.BeachesList = mBeaches;
	}
}
