package com.application.models;

import java.util.List;

public class GenericModel {

	private String Id;
	private List<String> areaPoints;
	private String Name;
	private int Score;
	private String ImagesJson;
	private String Description;
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
		return Id;
	}
	
	public void setid(String mID) {
		this.Id = mID;
	}
	
	public List<String> getAreaPoints() {
		return areaPoints;
	}
	
	public void setAreaPoints(List<String> mAreaPoints) {
		this.areaPoints = mAreaPoints;

	}

	public String getName() {
		return Name;
	}
	
	public void setName(String mName) {
		this.Name = mName;
	}
	
	public int getScore() {
		return Score;
	}

	public void setScore(int mScore) {
		this.Score = mScore;
	}
	
	public String getImage() {
		return ImagesJson;
	}
	
	public void setImage(String mImage) {
		this.ImagesJson = mImage;

	}

	
	public String getDescription() {
		return Description;
	}
	
	public void setDescription(String mDescription) {
		this.Description = mDescription;
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
