package de.hdm.myjob.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.myjob.shared.bo.Profil;
import de.hdm.myjob.shared.bo.Stellenausschreibung;
import de.hdm.myjob.shared.report.AllInhalteOfAllProfileReport;
import de.hdm.myjob.shared.report.AllInhalteOfProfilReport;
import de.hdm.myjob.shared.report.AllInhalteOfStellenausschreibungReport;

@RemoteServiceRelativePath("reportadmin")
public interface ReportAdministration extends RemoteService {

	public void init() throws IllegalArgumentException;

	public abstract AllInhalteOfProfilReport createAllInhalteOfProfilReport(Profil p) throws IllegalArgumentException;

	// public abstract AllInhalteOfAllProfileReport
	// createAllInhalteOfAllProfileReport() throws IllegalArgumentException;

	// public abstract Vector<AllInhalteOfStellenausschreibungReport>
	// createAllInhalteOfStellenausschreibungReport(
	// Stellenausschreibung stelle) throws IllegalArgumentException;

}
