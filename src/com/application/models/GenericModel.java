package com.application.models;

import java.util.List;

public class GenericModel {

	private String mID;
	private List<String> mAreaPoints;
	private String mName;
	private int mScore ;
	private String mImage;
	private int mLikes;
	private int mComments;
	private String mDescription;
	private String mAttractionsDescription;//will be object
	private String mParkingLot;//will be object
	
	
	public String getmID() {
		return mID;
	}
	
	public void setmID(String mID) {
		this.mID = mID;
	}
	
	public List<String> getmAreaPoints() {
		return mAreaPoints;
	}
	
	public void setmAreaPoints(List<String> mAreaPoints) {
		this.mAreaPoints = mAreaPoints;

	}

	public String getmName() {
		return mName;
	}
	
	public void setmName(String mName) {
		this.mName = mName;
	}
	
	public int getmScore() {
		return mScore;
	}

	public void setmScore(int mScore) {
		this.mScore = mScore;
	}
	
	public String getmImage() {
		return mImage;
	}
	
	public void setmImage(String mImage) {
		this.mImage = mImage;

	}

	public int getmLikes() {
		return mLikes;
	}
	
	public void setmLikes(int mLikes) {
		this.mLikes = mLikes;
	}
	
	public int getmComments() {
		return mComments;
	}

	public void setmComments(int mComments) {
		this.mComments = mComments;
	}
	
	public String getmDescription() {
		return mDescription;
	}
	
	public void setmDescription(String mDescription) {
		this.mDescription = mDescription;
	}
	
	public String getmAttractionsDescription() {
		return mAttractionsDescription;
	}
	
	public void setmAttractionsDescription(String mAttractionsDescription) {
		this.mAttractionsDescription = mAttractionsDescription;
	}
	
	public String getmParkingLot() {
		return mParkingLot;
	}
	
	public void setmParkingLot(String mParkingLot) {
		this.mParkingLot = mParkingLot;
	}

}
