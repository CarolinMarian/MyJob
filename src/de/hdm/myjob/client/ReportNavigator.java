package de.hdm.myjob.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

public class ReportNavigator extends ShowDefinition {

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub

		Button profilReport = new Button("Profil Report");
		profilReport.setStylePrimaryName("myjob-menubutton");
		this.add(profilReport);

		profilReport.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				ShowDefinition showdef = new ProfilReport();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showdef);
			}

		});

		Button allProfileReport = new Button("Alle Profile Report");
		allProfileReport.setStylePrimaryName("myjob-menubutton");
		this.add(allProfileReport);

		allProfileReport.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				ShowDefinition showdef = new AlleProfileReport();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showdef);
			}

		});

		Button stellenausschreibungReport = new Button("Alle Stellenausschreibung zu einem Benutzer Report");
		stellenausschreibungReport.setStylePrimaryName("myjob-menubutton");
		this.add(stellenausschreibungReport);

		stellenausschreibungReport.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				ShowDefinition showdef = new StellenausschreibungReport();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showdef);
			}

		});

		Button bewerbungReport = new Button("Alle Bewerbungen Report");
		bewerbungReport.setStylePrimaryName("myjob-menubutton");
		this.add(bewerbungReport);

		bewerbungReport.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				ShowDefinition showdef = new BewerbungReport();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showdef);
			}

		});

	}

}
