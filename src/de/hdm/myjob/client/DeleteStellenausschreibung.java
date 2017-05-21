package de.hdm.myjob.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.myjob.client.DeleteProfil.ProfilCallback;
import de.hdm.myjob.shared.AdministrationAsync;
import de.hdm.myjob.shared.bo.Benutzer;
import de.hdm.myjob.shared.bo.Profil;
import de.hdm.myjob.shared.bo.Stellenausschreibung;

public class DeleteStellenausschreibung extends ShowDefinition {

	// Klassenobjekte erzeugen
	Stellenausschreibung stelle = new Stellenausschreibung();
	// Benutzer benutzer = new Benutzer();
	// Profil profil = new Profil();

	// Konstruktor erstellen der die übergebene ID in das Klassenobjekt
	// abspeichert
	public DeleteStellenausschreibung(int stellenid) {
		this.stelle.setStellenId(stellenid);
		run();
	}

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void run() {
		AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();
		verwaltung.deleteStellenausschreibung(stelle, new DeleteStelle());
	}

	class DeleteStelle implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
		}

		@Override
		public void onSuccess(Void result) {
			ShowDefinition stelle = new ShowStellenausschreibung();
			RootPanel.get("Details").clear();
			RootPanel.get("Details").add(stelle);
		}
	}
}
