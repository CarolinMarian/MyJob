package de.hdm.myjob.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AdministrationAsync {
	
	void init (AsyncCallback<Void> callback);
	void getTestString (AsyncCallback<String> callback);

}
