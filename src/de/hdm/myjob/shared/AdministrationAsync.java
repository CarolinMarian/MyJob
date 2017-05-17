package de.hdm.myjob.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.myjob.shared.bo.Inhalt;
import de.hdm.myjob.shared.bo.Profil;

public interface AdministrationAsync {
	
	void init (AsyncCallback<Void> callback);
	
	void getTestString (AsyncCallback<String> callback);
	
	void getInhaltFor (Profil p, AsyncCallback<Vector<Inhalt>> callback);

}
