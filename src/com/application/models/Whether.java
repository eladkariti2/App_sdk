package com.application.models;

public class Whether {

	private String description;
	private String image;
	
	private FlagType flag;
	private Wave wave;
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public FlagType getFlag() {
		return flag;
	}
	
	public void setFlag(FlagType flag) {
		this.flag = flag;
	}

	public Wave getWave() {
		return wave;
	}
	
	public void setWave(Wave wave) {
		this.wave = wave;
	}

	public static class FlagType{
		
		private String description;
		private String image;
		
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
	}
	
	
	public static class Wave{
		
		private String description;
		private String image;
		
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
	}
	
	
	
	
}
