package com.application.messagebroker;

import java.lang.ref.WeakReference;
import java.util.Hashtable;
import java.util.Vector;

import android.util.Log;

public class APMessageBroker {
	/**
	 * The Class name.
	 */
	private static final String TAG = APMessageBroker.class.getSimpleName();

	/**
	 * The singleton instance.
	 */
	private static final APMessageBroker _instance = new APMessageBroker();
	
	/**
	 * The broker listeners.
	 */
	private Hashtable<Integer, Vector<WeakReference<IAPBrokerListener>>> _listeners = new Hashtable<Integer, Vector<WeakReference<IAPBrokerListener>>>();
	
	/**
	 * This method creates a new instance of APMessageBroker
	 * and in the singleton case protect from been init.
	 */
	private APMessageBroker() {
		// do nothing...
	}
	
	/**
	 * This method gets the singleton instance.
	 */
	public static APMessageBroker getInstance() {
		return _instance;
	}
	
	/**
	 * This method adds a new listener to the list.
	 * @param listener the broker listener object.
	 */
	public void addListener(Integer nType, IAPBrokerListener listener) {
		Log.d(TAG, "addListener: nType = "+nType+", listener = "+listener);
		
		Vector<WeakReference<IAPBrokerListener>> vecListeners = _listeners.get(nType);
		
		if (vecListeners == null) {
			vecListeners = new Vector<WeakReference<IAPBrokerListener>>();
			_listeners.put(nType, vecListeners);
		}
		
		if (!listenerExists(vecListeners, listener)) {
			Log.d(TAG,"addListener: listener does not exist!");
			vecListeners.add(new WeakReference<IAPBrokerListener>(listener));
		}
		
	}
	
	/**
	 * This method removes listener from the list. 
	 * @param listener
	 */
	public void removeListener(Integer nType, IAPBrokerListener listener) {
		Log.d(TAG,"removeListener: nType = "+nType+", listener = "+listener);
		
		Vector<WeakReference<IAPBrokerListener>> vecListeners = _listeners.get(nType);
		if(vecListeners != null){
			removeListenerIfExists(vecListeners, listener);
		}
	}
	
	/**
	 * This method fire a list of registered event to 
	 * provided type. 
	 * @param nType the notification type.
	 */
	public void fireNotificationsByType(Integer nType, Object eventParams) {
		Log.d(TAG,"fireNotificationsByType: nType = "+nType+", eventParams = "+eventParams);
		
		Vector<WeakReference<IAPBrokerListener>> vecListeners = _listeners.get(nType);
		
		if (vecListeners != null) {
			for (WeakReference<IAPBrokerListener> listener : vecListeners) {
				Log.d(TAG,"fireNotificationsByType: listener = "+listener);
				
				if (listener.get() != null) {
					listener.get().onBrokerEventOccurred(nType, eventParams);
				}
			}
		}
		
	}
	
	
	public void clear(){
		Log.d(TAG,"clear");
		_listeners = new Hashtable<Integer, Vector<WeakReference<IAPBrokerListener>>>();
	}
	
	
	
	private boolean listenerExists(Vector<WeakReference<IAPBrokerListener>> vecListeners, IAPBrokerListener listener) {
		
		boolean result = false;
		
		for (WeakReference<IAPBrokerListener> weakReferenceListener: vecListeners) {
			if (listener.equals(weakReferenceListener.get())) {
				result = true;
				break;
			}
		}
		
		return result;
		
	}
	
	private void removeListenerIfExists(Vector<WeakReference<IAPBrokerListener>> vecListeners, IAPBrokerListener listener) {
		
		int listnerIndex = -1;
		int count = 0;
		for (WeakReference<IAPBrokerListener> weakReferenceListener: vecListeners) {
			if (listener.equals(weakReferenceListener.get())) {
				listnerIndex = count;
				break;
			}
			count++;
		}
		if(listnerIndex > -1){
			vecListeners.remove(listnerIndex);

		}
		
	}


}
