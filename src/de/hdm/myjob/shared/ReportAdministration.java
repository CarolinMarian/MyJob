package de.hdm.myjob.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.myjob.shared.bo.Benutzer;
import de.hdm.myjob.shared.report.AllInhalteOfAllBenutzerReport;
import de.hdm.myjob.shared.report.AllInhalteOfBenutzerReport;

@RemoteServiceRelativePath("reportadmin")
public interface ReportAdministration extends RemoteService{
	
	 public void init() throws IllegalArgumentException;

	 public abstract AllInhalteOfBenutzerReport createAllInhalteOfBenutzerReport(
	      Benutzer b) throws IllegalArgumentException;


	 public abstract AllInhalteOfAllBenutzerReport createAllInhalteOfAllBenutzerReport()
	      throws IllegalArgumentException;

}
