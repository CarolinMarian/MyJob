package de.hdm.myjob.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

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

		Button stellenausschreibungenAnsehenButton = new Button("Meine Stellenausschreibungen ansehen");
		stellenausschreibungenAnsehenButton.setStylePrimaryName("myjob-menubutton");

		stellenausschreibungenAnsehenButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ShowDefinition stelle = new ShowStellenausschreibung();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(stelle);
			}

		});

		Button stellenbeschreibungenAnzeigenButton = new Button("Jobs");
		stellenbeschreibungenAnzeigenButton.setStylePrimaryName("myjob-menubutton");

		stellenbeschreibungenAnzeigenButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}

		});

		Button bewerbungenAnzeigenButton = new Button("Meine Bewerbungen");
		bewerbungenAnzeigenButton.setStylePrimaryName("myjob-menubutton");

		bewerbungenAnzeigenButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

			}

		});

		this.add(profilAnzeigenButton);
		this.add(stellenausschreibungenErstellenButton);
		this.add(stellenausschreibungenAnsehenButton);
		this.add(bewerbungenAnzeigenButton);

	}

}
