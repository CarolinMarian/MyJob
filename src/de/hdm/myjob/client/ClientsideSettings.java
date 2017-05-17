package de.hdm.myjob.client;

import com.google.gwt.core.client.GWT;

import de.hdm.myjob.shared.Administration;
import de.hdm.myjob.shared.AdministrationAsync;
import de.hdm.myjob.shared.CommonSettings;

public class ClientsideSettings extends CommonSettings {
	
	public static AdministrationAsync verwaltung = null;
	
	
	public static AdministrationAsync getVerwaltung() {
		    // Gab es bislang noch keine BankAdministration-Instanz, dann...
		    if (verwaltung == null) {
		      // Zun�chst instantiieren wir BankAdministration
		      verwaltung = GWT.create(Administration.class);
		    }

		    // So, nun brauchen wir die BankAdministration nur noch zur�ckzugeben.
		    return verwaltung;
		  }

}
