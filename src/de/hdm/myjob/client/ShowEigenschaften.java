package de.hdm.myjob.client;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.myjob.shared.AdministrationAsync;
import de.hdm.myjob.shared.bo.Inhalt;
import de.hdm.myjob.shared.bo.Profil;

public class ShowEigenschaften extends ShowDefinition {

	@Override
	protected String getHeadlineText() {
		String headline = "Eigenschaften";
		return headline;
	}

	@Override
	protected void run() {
		
		AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();
		
		verwaltung.getTestString(new TestCallback(this));
		
		verwaltung.getProfilFor(1, new ProfilCallback(this));
		
		

	}
	
	
	class TestCallback implements AsyncCallback<String> {
		
		private ShowDefinition showdef = null;
		
		
		public TestCallback (ShowDefinition s){
			this.showdef = s;
		}
		
			
		@Override
		public void onFailure(Throwable caught) {
			
			 this.showdef.append("Fehler bei der Abfrage " + caught.getMessage());
			
		}

		@Override
		public void onSuccess(String result) {
			
			this.showdef.append("TEST");
			
			this.showdef.append(result);

			
		}
		
	}
	
	
	
	
	class ProfilCallback implements AsyncCallback<Profil> {
		
		private ShowDefinition showdef = null;
		
		
		public ProfilCallback (ShowDefinition s){
			this.showdef = s;
		}
		
			
		@Override
		public void onFailure(Throwable caught) {
			
			 this.showdef.append("Fehler bei der Abfrage " + caught.getMessage());
			
		}

		@Override
		public void onSuccess(Profil result) {
			
			AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();
			
			this.showdef.append("TEST Profil:");
			
			this.showdef.append(result.toString());
			
			verwaltung.getInhaltFor(result, new InhalteCallback(this.showdef, result));

			
		}

		
	}
	
	
	
	
	
	class InhalteCallback implements AsyncCallback<Vector<Inhalt>> {
		
		private ShowDefinition showdef = null;
		private Profil profil = null;
		
		
		public InhalteCallback (ShowDefinition s, Profil p){
			this.showdef = s;
			this.profil = p;
		}
		
			
		@Override
		public void onFailure(Throwable caught) {
			
			 this.showdef.append("Fehler bei der Abfrage " + caught.getMessage());
			
		}

		@Override
		public void onSuccess(Vector<Inhalt> result) {
			
			this.showdef.append("TEST Inhalt:");
			for (Inhalt i : result){
				this.showdef.append(i.toString());
			}
			

			
		}
		
	}
	
	
	
	

}
