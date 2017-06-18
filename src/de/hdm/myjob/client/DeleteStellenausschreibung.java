package de.hdm.myjob.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.myjob.shared.AdministrationAsync;
import de.hdm.myjob.shared.bo.Stellenausschreibung;

public class DeleteStellenausschreibung extends ShowDefinition {

	// Klassenobjekte erzeugen
	Stellenausschreibung stelle = new Stellenausschreibung();

	// Konstruktor erstellen der die übergebene ID in das Klassenobjekt
	// abspeichert
	public DeleteStellenausschreibung(int stellenid) {
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
		verwaltung.deleteStellenausschreibung(stelle, new DeleteStelle(this));
	}

	class DeleteStelle implements AsyncCallback<Void> {
		
		ShowDefinition showdef = null;
		
		public DeleteStelle(ShowDefinition s){
			this.showdef = s;
		}

		@Override
		public void onFailure(Throwable caught) {
		}

		@Override
		public void onSuccess(Void result) {
			ShowDefinition stelle = new ShowAllStellenausschreibungenId();
			RootPanel.get("Details").clear();
			RootPanel.get("Details").add(stelle);
		}
	}
}
