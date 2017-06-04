package de.hdm.myjob.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.myjob.shared.AdministrationAsync;
import de.hdm.myjob.shared.bo.Benutzer;
import de.hdm.myjob.shared.bo.Stellenausschreibung;

public class CreateBewerbung extends ShowDefinition {

	// Klasseninstanzen definieren
	Stellenausschreibung stelle = new Stellenausschreibung();
	Benutzer nutzer = new Benutzer();

	// Konstruktor erstellen der die übergebene ID in das Klassenobjekt
	// abspeichert
	public CreateBewerbung(int stellenid) {
		this.stelle.setStellenId(stellenid);
		run();
	}

	// Überschrift festlegen
	@Override
	protected String getHeadlineText() {
		return null;
	}

	// Run-Methode
	@Override
	protected void run() {

		// BenutzerId hardcoded
		nutzer.setId(1);

		// Kommunikation mit der Datenbank
		AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();
		verwaltung.createBewerbung(stelle.getStellenId(), nutzer.getId(), new ErstelleBewerbung());
	}

	class ErstelleBewerbung implements AsyncCallback<Void> {
		@Override
		public void onFailure(Throwable caught) {
		}

		@Override
		public void onSuccess(Void result) {
		}
	}
}
