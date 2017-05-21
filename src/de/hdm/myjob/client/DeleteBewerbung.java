package de.hdm.myjob.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.myjob.client.DeleteStellenausschreibung.DeleteStelle;
import de.hdm.myjob.shared.AdministrationAsync;
import de.hdm.myjob.shared.bo.Bewerbung;
import de.hdm.myjob.shared.bo.Stellenausschreibung;

public class DeleteBewerbung extends ShowDefinition {
	Stellenausschreibung stelle = new Stellenausschreibung();

	public DeleteBewerbung(int stellenid) {
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
