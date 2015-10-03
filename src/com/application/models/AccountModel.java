package com.application.models;

import java.util.List;

public class AccountModel  {
	
	private String mID;
	private String mFbPageID;
	private String mVersionNumber;
	private String mDepracatedText;
	private String mAdLink;
	private String mAdProvider;
	private String mBaanerAd;
	private String mGameLink;
	private List<BeachModel> mBeaches;


	
	public String getID() {
		return mID;
	}
	
	public void setID(String id) {
		this.mID = mID;
	}


	public String getFBPageID() {
		// TODO Auto-generated method stub
		//String id = "515881818544964"; // temp
		return mFbPageID;
	}


	public String getVersionNumber() {
		return mVersionNumber;
	}

	public void setVersionNumber(String mVersionNumber) {
		this.mVersionNumber = mVersionNumber;
	}

	public String getDepracatedText() {
		return mDepracatedText;
	}

	public void setDepracatedText(String mDepracatedText) {
		this.mDepracatedText = mDepracatedText;
	}

	public String getAdLink() {
		return mAdLink;
	}

	public void setAdLink(String mAdLink) {
		this.mAdLink = mAdLink;
	}

	public String getAdProvider() {
		return mAdProvider;
	}

	public void setAdProvider(String mAdProvider) {
		this.mAdProvider = mAdProvider;
	}

	public String getmBaanerAd() {
		return mBaanerAd;
	}

	public void setBaanerAd(String mBaanerAd) {
		this.mBaanerAd = mBaanerAd;
	}

	public String getGameLink() {
		return mGameLink;
	}

	public void setGameLink(String mGameLink) {
		this.mGameLink = mGameLink;
	}

	public List<BeachModel> getBeaches() {
		return mBeaches;
	}

	public void setBeaches(List<BeachModel> mBeaches) {
		this.mBeaches = mBeaches;
	}
}
