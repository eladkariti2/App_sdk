package com.application.models;

public class BeachModel extends GenericModel {

	private String whether;
	private int womenNumber;
	private int mensNumber;
	private double entryFee;
	
	public String getWhether() {
		return whether;
	}
	
	public void setWhether(String mWhether) {
		this.whether = mWhether;
	}

	public int getWomenNumber() {
		return womenNumber;
	}
	
	public void setWomenNumber(int mWomenNumber) {
		this.womenNumber = mWomenNumber;
	}
	
	public int getMensNumber() {
		return mensNumber;
	}

	public void setMensNumber(int mMensNumber) {
		this.mensNumber = mMensNumber;
	}

	
	public double getEntryFee() {
		return entryFee;
	
	}
	
	public void setEntryFee(double mEntryFee) {
		this.entryFee = mEntryFee;
	}
	
	
	
	
}
