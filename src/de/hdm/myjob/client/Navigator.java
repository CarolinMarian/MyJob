package de.hdm.myjob.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.myjob.shared.AdministrationAsync;
import de.hdm.myjob.shared.bo.Benutzer;

public class Navigator extends ShowDefinition {

	@Override
	protected String getHeadlineText() {
		return null;
	}

	@Override
	protected void run() {

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

		Button stellenausschreibungenErstellenButton = new Button("Stellenausschreibung erstellen");
		stellenausschreibungenErstellenButton.setStylePrimaryName("myjob-menubutton");

		stellenausschreibungenErstellenButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ShowDefinition stelle = new CreateStellenausschreibung();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(stelle);
			}

		});

		Button stellenausschreibungenAnsehenButton = new Button("Stellenausschreibungen meines Profils ansehen");
		stellenausschreibungenAnsehenButton.setStylePrimaryName("myjob-menubutton");

		stellenausschreibungenAnsehenButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ShowDefinition stelle = new ShowAllStellenausschreibungenId();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(stelle);
			}

		});

		Button allStellenausschreibungenAnsehenButton = new Button("Alle vorhandenen Stellenausschreibungen ansehen");
		allStellenausschreibungenAnsehenButton.setStylePrimaryName("myjob-menubutton");

		allStellenausschreibungenAnsehenButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ShowDefinition stelle = new ShowAllStellenausschreibungen();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(stelle);
			}

		});

		Button bewerbungenAnzeigenButton = new Button("Meine Bewerbungen");
		bewerbungenAnzeigenButton.setStylePrimaryName("myjob-menubutton");

		bewerbungenAnzeigenButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ShowDefinition bewerbung = new ShowBewerbungen();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(bewerbung);
			}

		});
		

		Button profilDelButton = new Button("Profil vollständig löschen");
		profilDelButton.setStylePrimaryName("myjob-menubutton");
		
		profilDelButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				
				AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();
				verwaltung.getBenutzerById(5, new AsyncCallback<Benutzer>(){

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Benutzer result) {
						// TODO Auto-generated method stub
						AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();
						verwaltung.deleteBenutzer(result, new AsyncCallback<Void>(){

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(Void result) {
								// TODO Auto-generated method stub
								ShowDefinition showdef = new ShowProfil();
								RootPanel.get("Details").clear();
								RootPanel.get("Details").add(showdef);
							}
							
						});
					}
					
				});
				
				
			}
			
		});

		this.add(profilAnzeigenButton);
		this.add(stellenausschreibungenErstellenButton);
		this.add(stellenausschreibungenAnsehenButton);
		this.add(allStellenausschreibungenAnsehenButton);
		this.add(bewerbungenAnzeigenButton);
		this.add(profilDelButton);

	}

}
