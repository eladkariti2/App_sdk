package com.application.listener;

public interface AsyncTaskListener<T> {
	
	public void handleException(Exception e);

	
	public void onTaskComplete(T result);

	
	public void onTaskStart();
	
}