package de.hdm.myjob.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("reportadmin")
public interface ReportAdministration extends RemoteService{
	
	 public void init() throws IllegalArgumentException;


}
