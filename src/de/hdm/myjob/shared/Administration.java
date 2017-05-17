package de.hdm.myjob.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.myjob.shared.bo.Inhalt;
import de.hdm.myjob.shared.bo.Profil;

@RemoteServiceRelativePath("admin")
public interface Administration extends RemoteService{
	
	public void init() throws IllegalArgumentException;
	
	public String getTestString() throws IllegalArgumentException;
	
	public Vector<Inhalt> getInhaltFor(Profil p) throws IllegalArgumentException;

}
