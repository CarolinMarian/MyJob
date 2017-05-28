package de.hdm.myjob.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.myjob.shared.AdministrationAsync;
import de.hdm.myjob.shared.bo.Profil;


public class ShowProfil extends ShowDefinition{
	
	@Override
	protected String getHeadlineText() {
		String headlineText = "Profil"; 
		return headlineText;
	}

	@Override
	protected void run() {
		
		AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();
		
		verwaltung.getProfilFor(1, new ProfilCallback(this));
	
		
		
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
			
			HorizontalPanel buttonPanel = new HorizontalPanel();
			this.showdef.add(buttonPanel);
			
			if (result == null){
				
				Button profilAnlegenButton = new Button("Profil anlegen");
				profilAnlegenButton.setStylePrimaryName("myjob-menubutton");
				buttonPanel.add(profilAnlegenButton);

				profilAnlegenButton.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
						ShowDefinition showdef = new ProfilAnlegen();
						RootPanel.get("Details").clear();
						RootPanel.get("Details").add(showdef);
					}

				});
				
				
			}
			else {
				
				Button profilBearbeitenButton = new Button("Profil bearbeiten");
				profilBearbeitenButton.setStylePrimaryName("myjob-menubutton");
				buttonPanel.add(profilBearbeitenButton);

				profilBearbeitenButton.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
						ShowDefinition showdef = new EigenschaftenBearbeiten();
						RootPanel.get("Details").clear();
						RootPanel.get("Details").add(showdef);
					}

				});
				
				Button profilLoeschenButton = new Button("Profil loeschen");
				profilLoeschenButton.setStylePrimaryName("myjob-menubutton");
				buttonPanel.add(profilLoeschenButton);

				profilLoeschenButton.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
						ShowDefinition showdef = new DeleteProfil();
						RootPanel.get("Details").clear();
						RootPanel.get("Details").add(showdef);
						
					}

				});
				
				Button profilAnzeigenButton = new Button("Mein Profil");
				profilAnzeigenButton.setStylePrimaryName("myjob-menubutton");

				profilAnzeigenButton.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
						ShowDefinition showdef = new ShowProfil();
						RootPanel.get("Details").clear();
						RootPanel.get("Details").add(showdef);
					}

				});
				
				ShowDefinition showsuper = new ShowEigenschaften();
		        RootPanel.get("Details").add(showsuper);

				
			}
			
		}

		
	}
	
	
	
	

}
