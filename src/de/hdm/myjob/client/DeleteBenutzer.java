package de.hdm.myjob.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.myjob.shared.AdministrationAsync;
import de.hdm.myjob.shared.bo.Benutzer;

public class DeleteBenutzer extends ShowDefinition{

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		
		AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();

		verwaltung.getBenutzerById(1, new BenutzerCallback(this));
		
		
	}
	
	
	
	
	
	class BenutzerCallback implements AsyncCallback<Benutzer> {
	    private ShowDefinition showdef = null;

	    public BenutzerCallback(ShowDefinition s) {
	      this.showdef = s;
	    }

	    @Override
	    public void onFailure(Throwable caught) {
	      this.showdef.append("Fehler bei der Abfrage " + caught.getMessage());
	    }

	    @Override
		public void onSuccess(Benutzer b) {
	      if (b != null) {
	  		AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();
	  	//	verwaltung.deleteProfil(p, new DelProfilCallback(this.showdef));
	      }
	    }

	  }
	
	
	
	
	
class DelProfilCallback implements AsyncCallback<Void> {
		
		private ShowDefinition showdef = null;
		
		public DelProfilCallback (ShowDefinition s){
			this.showdef  = s;
		}
		
			
		@Override
		public void onFailure(Throwable caught) {
			
			 this.showdef.append("Fehler bei der Abfrage " + caught.getMessage());
			
		}

		@Override
		public void onSuccess(Void result) {
			ShowDefinition showdef = new ShowProfil();
			RootPanel.get("Details").clear();
			RootPanel.get("Details").add(showdef);
		}

		
	}






}
