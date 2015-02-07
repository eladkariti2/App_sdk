package com.application.facebook.model;


import java.util.List;

public class FBPhoto  extends FBModel {

	protected String created_time;
	protected String icon;
	protected int height;
	protected int width;
	protected String link;
	protected String picture;
	protected String source;
	protected String updated_time;
	protected FBModel from;
	protected List<FBImage> images;
	
	
	
	
	
	public String getCreated_time() {
		return created_time;
	}

	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUpdated_time() {
		return updated_time;
	}

	public void setUpdated_time(String updated_time) {
		this.updated_time = updated_time;
	}

	public FBModel getFrom() {
		return from;
	}

	public void setFrom(FBModel from) {
		this.from = from;
	}

	public List<FBImage> getImages() {
		return images;
	}

	public void setImages(List<FBImage> images) {
		this.images = images;
	}


}