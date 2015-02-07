package com.application.facebook.model;


import java.util.List;

public class FBLikes {
	
	protected List<FBModel> data;
	protected FBLikesSummary summary;
	protected boolean isUserLike;

	public List<FBModel> getLikes() {
		return data;
	}

	public int getLikesNumber() {
		return summary.getTotal_count();
	}

	public void setLikesNumber(int likeNumber) {
		summary.setTotal_count(likeNumber);
	}

	public void setUserLike(boolean userLike) {
		isUserLike = userLike;
	}

	public boolean getUserLike() {
		return isUserLike;
	}

}