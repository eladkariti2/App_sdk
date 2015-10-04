package com.application.models;

public class BeachModel extends GenericModel {

	private Whether whether;
	private int WomensNumber;
	private int MensNumber;
	private double EntryFee;
	

	public int getWomenNumber() {
		return WomensNumber;
	}
	
	public void setWomenNumber(int mWomenNumber) {
		this.WomensNumber = mWomenNumber;
	}
	
	public int getMensNumber() {
		return MensNumber;
	}

	public void setMensNumber(int mMensNumber) {
		this.MensNumber = mMensNumber;
	}

	
	public double getEntryFee() {
		return EntryFee;
	
	}
	
	public void setEntryFee(double mEntryFee) {
		this.EntryFee = mEntryFee;
	}

	public Whether getWhether() {
		return whether;
	}

	public void setWhether(Whether whether) {
		this.whether = whether;
	}
	
	
	
	
}
