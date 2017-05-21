package de.hdm.myjob.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.myjob.client.DeleteStellenausschreibung.DeleteStelle;
import de.hdm.myjob.shared.AdministrationAsync;
import de.hdm.myjob.shared.bo.Benutzer;
import de.hdm.myjob.shared.bo.Stellenausschreibung;

public class CreateBewerbung extends ShowDefinition {

	Stellenausschreibung stelle = new Stellenausschreibung();
	Benutzer nutzer = new Benutzer();

	// Konstruktor erstellen der die übergebene ID in das Klassenobjekt
	// abspeichert
	public CreateBewerbung(int stellenid) {
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

		nutzer.setId(1);

		AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();
		verwaltung.createBewerbung(stelle.getStellenId(), nutzer.getId(), new ErstelleBewerbung());
	}

	class ErstelleBewerbung implements AsyncCallback<Void> {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Void result) {
			// TODO Auto-generated method stub
			
		}

	}

}
