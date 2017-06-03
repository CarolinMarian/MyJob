package de.hdm.myjob.client;

import com.google.gwt.core.client.GWT;

import de.hdm.myjob.shared.Administration;
import de.hdm.myjob.shared.AdministrationAsync;
import de.hdm.myjob.shared.CommonSettings;
import de.hdm.myjob.shared.ReportAdministration;
import de.hdm.myjob.shared.ReportAdministrationAsync;

public class ClientsideSettings extends CommonSettings {
	
	public static AdministrationAsync verwaltung = null;
	
	
	public static AdministrationAsync getVerwaltung() {
		    // Gab es bislang noch keine BankAdministration-Instanz, dann..
		    if (verwaltung == null) {
		      // Zunï¿½chst instantiieren wir BankAdministration
		      verwaltung = GWT.create(Administration.class);
		    }


		    // So, nun brauchen wir die BankAdministration nur noch zurückzugeben

		    return verwaltung;
		  }
	
	
	
	private static ReportAdministrationAsync reportVerwaltung = null;
	
	public static ReportAdministrationAsync getReportVerwaltung() {
	    // Gab es bislang noch keine ReportGenerator-Instanz, dann...
	    if (reportVerwaltung == null) {
	      // Zunächst instantiieren wir ReportGenerator
	    	reportVerwaltung = GWT.create(ReportAdministration.class);
	    }

	    // So, nun brauchen wir den ReportGenerator nur noch zurückzugeben.
	    return reportVerwaltung;
	  }

}
