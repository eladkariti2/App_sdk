package com.application.models;

public class BeachModel extends GenericModel {

	private Whether whether;
	private int womenNumber;
	private int mensNumber;
	private double entryFee;
	

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

	public Whether getWhether() {
		return whether;
	}

	public void setWhether(Whether whether) {
		this.whether = whether;
	}
	
	
	
	
}
