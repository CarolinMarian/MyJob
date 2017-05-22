package de.hdm.myjob.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.myjob.shared.bo.Profil;
import de.hdm.myjob.shared.report.AllInhalteOfAllProfileReport;
import de.hdm.myjob.shared.report.AllInhalteOfProfilReport;

@RemoteServiceRelativePath("reportadmin")
public interface ReportAdministration extends RemoteService{
	
	 public void init() throws IllegalArgumentException;

	 public abstract AllInhalteOfProfilReport createAllInhalteOfProfilReport(
	      Profil p) throws IllegalArgumentException;


	 public abstract AllInhalteOfAllProfileReport createAllInhalteOfAllProfileReport()
	      throws IllegalArgumentException;

}
