package com.application.models;

import java.util.List;

public class GenericModel {

	private String id;
	private List<String> areaPoints;
	private String name;
	private int score ;
	private String image;
	private String description;
	private String attractionsDescription;//will be object
	private String parkingLot;//will be object
	
	String facebookAppID ;
	
	
	public String getfacebookAppID() {
		return facebookAppID;
	}
	
	public void setfacebookAppID(String id) {
		this.facebookAppID = id;
	}
	
	
	public String getid() {
		return id;
	}
	
	public void setid(String mID) {
		this.id = mID;
	}
	
	public List<String> getAreaPoints() {
		return areaPoints;
	}
	
	public void setAreaPoints(List<String> mAreaPoints) {
		this.areaPoints = mAreaPoints;

	}

	public String getName() {
		return name;
	}
	
	public void setName(String mName) {
		this.name = mName;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int mScore) {
		this.score = mScore;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String mImage) {
		this.image = mImage;

	}

	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String mDescription) {
		this.description = mDescription;
	}
	
	public String getAttractionsDescription() {
		return attractionsDescription;
	}
	
	public void setAttractionsDescription(String mAttractionsDescription) {
		this.attractionsDescription = mAttractionsDescription;
	}
	
	public String getParkingLot() {
		return parkingLot;
	}
	
	public void setParkingLot(String mParkingLot) {
		this.parkingLot = mParkingLot;
	}

}
