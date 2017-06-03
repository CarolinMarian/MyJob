package de.hdm.myjob.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.myjob.shared.bo.Benutzer;
import de.hdm.myjob.shared.report.AllInhalteOfAllBenutzerReport;
import de.hdm.myjob.shared.report.AllInhalteOfBenutzerReport;

public interface ReportAdministrationAsync {
	
	void init(AsyncCallback<Void> callback);
	
	void createAllInhalteOfBenutzerReport (Benutzer b, AsyncCallback<AllInhalteOfBenutzerReport> callbak);
	
	void createAllInhalteOfAllBenutzerReport (AsyncCallback<AllInhalteOfAllBenutzerReport> callbak);

}
