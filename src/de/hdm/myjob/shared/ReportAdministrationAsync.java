package de.hdm.myjob.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.myjob.shared.bo.Benutzer;
import de.hdm.myjob.shared.bo.Stellenausschreibung;
import de.hdm.myjob.shared.report.AllInhalteOfAllBenutzerReport;
import de.hdm.myjob.shared.report.AllInhalteOfBenutzerReport;
import de.hdm.myjob.shared.report.AllInhalteOfStellenausschreibungReport;

public interface ReportAdministrationAsync {

	void createAllInhalteOfBenutzerReport(Benutzer b, AsyncCallback<AllInhalteOfBenutzerReport> callbak);

	void createAllInhalteOfAllBenutzerReport(AsyncCallback<AllInhalteOfAllBenutzerReport> callbak);

	void createAllInhalteOfStellenausschreibungReport(Benutzer b, Vector<Stellenausschreibung> stelle,
			AsyncCallback<AllInhalteOfStellenausschreibungReport> callbak);
}
