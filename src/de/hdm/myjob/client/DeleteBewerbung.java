package de.hdm.myjob.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.myjob.shared.AdministrationAsync;
import de.hdm.myjob.shared.bo.Stellenausschreibung;

public class DeleteBewerbung extends ShowDefinition {

	// Klasseninstanzen definieren
	Stellenausschreibung stelle = new Stellenausschreibung();

	// Konstruktor erstellen der die übergebene ID in das Klassenobjekt
	// abspeichert
	public DeleteBewerbung(int stellenid) {
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
		// Kommunikation mit der Datenbank
		AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();
		verwaltung.deleteBewerbung(stelle.getStellenId(), new EntferneBewerbung());
	}

	class EntferneBewerbung implements AsyncCallback<Void> {
		@Override
		public void onFailure(Throwable caught) {
		}

		@Override
		public void onSuccess(Void result) {
			ShowDefinition bewerbung = new ShowBewerbungen();
			RootPanel.get("Details").clear();
			RootPanel.get("Details").add(bewerbung);
		}
	}
}
