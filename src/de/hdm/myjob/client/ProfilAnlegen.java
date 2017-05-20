package de.hdm.myjob.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.myjob.client.ShowEigenschaften.InhalteCallback;
import de.hdm.myjob.client.ShowEigenschaften.ProfilCallback;
import de.hdm.myjob.shared.AdministrationAsync;
import de.hdm.myjob.shared.bo.Profil;

public class ProfilAnlegen extends ShowDefinition {

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return "Profil anlegen";
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();
		
		verwaltung.createProfil(new ProfilCallback(this));
		
	}
	
	
class ProfilCallback implements AsyncCallback<Void> {
		
		private ShowDefinition showdef = null;
		
		
		public ProfilCallback (ShowDefinition s){
			this.showdef = s;
		}
		
			
		@Override
		public void onFailure(Throwable caught) {
			 this.showdef.append("Fehler bei der Abfrage " + caught.getMessage());
		}

		@Override
		public void onSuccess(Void result) {
			this.showdef.append("Profil angelegt");
//			ShowDefinition showdef = new ShowProfil();
//			RootPanel.get("Details").clear();
//			RootPanel.get("Details").add(showdef);

			
		}
		
 }

}
