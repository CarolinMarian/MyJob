package de.hdm.myjob.server.report;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.myjob.server.AdministrationImpl;
import de.hdm.myjob.shared.Administration;
import de.hdm.myjob.shared.ReportAdministration;

@SuppressWarnings("serial")
public class ReportAdministrationImpl extends RemoteServiceServlet implements ReportAdministration{
	
	  private Administration administration = null;
	  
	  public ReportAdministrationImpl() throws IllegalArgumentException {
		  
	  }
	  
	  public void init() throws IllegalArgumentException {
		   AdministrationImpl a = new AdministrationImpl();
		    a.init();
		    this.administration = a;
		  }
	  
	  protected Administration getVerwaltung() {
		    return this.administration;
		  }
	  

}
