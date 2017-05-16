package de.hdm.myjob.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("admin")
public interface Administration extends RemoteService{
	
	public void init() throws IllegalArgumentException;
	
	public String getTestString() throws IllegalArgumentException;

}
