package de.hdm.myjob.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.myjob.shared.bo.Profil;
import de.hdm.myjob.shared.bo.Stellenausschreibung;
import de.hdm.myjob.shared.report.AllInhalteOfAllProfileReport;
import de.hdm.myjob.shared.report.AllInhalteOfProfilReport;
import de.hdm.myjob.shared.report.AllInhalteOfStellenausschreibungReport;

public interface ReportAdministrationAsync {

	void init(AsyncCallback<Void> callback);

	void createAllInhalteOfProfilReport(Profil p, AsyncCallback<AllInhalteOfProfilReport> callbak);

	// void
	// createAllInhalteOfAllProfileReport(AsyncCallback<AllInhalteOfAllProfileReport>
	// callbak);
	//
	// void createAllInhalteOfStellenausschreibungReport(Stellenausschreibung
	// stelle,
	// AsyncCallback<Vector<AllInhalteOfStellenausschreibungReport>> callback);

}
