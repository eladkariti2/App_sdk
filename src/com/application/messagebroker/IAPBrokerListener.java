package com.application.messagebroker;

public interface IAPBrokerListener {

	/**
	 * This method is the broker general event. 
	 * @param eventType the event type.
	 * @param eventParams the event additional parameters.
	 */
	public void onBrokerEventOccurred(Integer eventType, Object eventParams);

}
