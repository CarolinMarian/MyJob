package de.hdm.myjob.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.myjob.shared.bo.Profil;
import de.hdm.myjob.shared.report.AllInhalteOfAllProfileReport;
import de.hdm.myjob.shared.report.AllInhalteOfProfilReport;

public interface ReportAdministrationAsync {
	
	void init(AsyncCallback<Void> callback);
	
	void createAllInhalteOfProfilReport (Profil p, AsyncCallback<AllInhalteOfProfilReport> callbak);
	
	void createAllInhalteOfAllProfileReport (AsyncCallback<AllInhalteOfAllProfileReport> callbak);

}
