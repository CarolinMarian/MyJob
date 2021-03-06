package de.hdm.myjob.client;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.myjob.shared.AdministrationAsync;
import de.hdm.myjob.shared.bo.Benutzer;
import de.hdm.myjob.shared.bo.Stellenausschreibung;

public class ShowAllStellenausschreibungenId extends ShowDefinition {
	Benutzer benutzer = new Benutzer();

	// Panels definieren
	private HorizontalPanel horPanel = new HorizontalPanel();
	private VerticalPanel verPanel = new VerticalPanel();
	// Tabelle definieren
	FlexTable showStellenausschreibungFlexTable = new FlexTable();

	@Override
	protected String getHeadlineText() {
		String headline = "Eine Übersicht über alle Stellenausschreibungen dieses Profils:";
		return headline;
	}

	@Override
	protected void run() {

		// BenutzerId hardcoden
		benutzer.setId(1);

		this.add(verPanel);

		// alle für den Nutzer vorhandenen Stellenausschreibungen ausgeben
		AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();
		verwaltung.showStellenausschreibung(benutzer.getId(), new ShowStelle());

		verPanel.add(showStellenausschreibungFlexTable);
		verPanel.add(horPanel);
	}

	class ShowStelle implements AsyncCallback<Vector<Stellenausschreibung>> {

		@Override
		public void onFailure(Throwable caught) {
			Label failLabel = new Label("onFailure wurde betreten");
			verPanel.add(failLabel);
		}

		@Override
		public void onSuccess(Vector<Stellenausschreibung> result) {
			showStellenausschreibungFlexTable.setText(0, 0, "StellenId");
			showStellenausschreibungFlexTable.setText(0, 1, "Bezeichnung");
			showStellenausschreibungFlexTable.setText(0, 2, "Ausschreibungstext");
			showStellenausschreibungFlexTable.setText(0, 3, "Frist");
			showStellenausschreibungFlexTable.setText(0, 4, "");
			showStellenausschreibungFlexTable.setText(0, 5, "");
			showStellenausschreibungFlexTable.setText(0, 6, "");

			int row = 0;
			for (final Stellenausschreibung s : result) {
				row++;
				showStellenausschreibungFlexTable.setText(row, 0, String.valueOf(s.getStellenId()));
				showStellenausschreibungFlexTable.setText(row, 1, s.getBezeichnung());
				showStellenausschreibungFlexTable.setText(row, 2, s.getBeschreibungstext());
				Date frist = s.getFrist();
				String fristString = DateTimeFormat.getFormat("dd.MM.yyyy").format(frist);
				showStellenausschreibungFlexTable.setText(row, 3, fristString);

				// Button erstellen um Update durchzuführen - wird der Button
				// ausgelöst wird man zu der entsprechenden Ausschreibung
				// weitergeleitet, um dieses bearbeiten zu können
				Button stelleAnzeigenButton = new Button("Details ansehen");
				stelleAnzeigenButton.setStylePrimaryName("myjob-menubutton");
				stelleAnzeigenButton.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						ShowDefinition stelle = new ShowOneStellenausschreibung(s.getStellenId());
						RootPanel.get("Details").clear();
						RootPanel.get("Details").add(stelle);
					}
				});

				// // Button erstellen um Update durchzuführen - wird der Button
				// // ausgelöst wird man zu der entsprechenden Ausschreibung
				// // weitergeleitet, um dieses bearbeiten zu können
				// Button stelleBearbeitenButton = new
				// Button("Stellenausschreibung berbeiten");
				// stelleBearbeitenButton.setStylePrimaryName("myjob-menubutton");
				// stelleBearbeitenButton.addClickHandler(new ClickHandler() {
				// @Override
				// public void onClick(ClickEvent event) {
				// ShowDefinition stelle = new
				// EditStellenausschreibung(s.getStellenId());
				// RootPanel.get("Details").clear();
				// RootPanel.get("Details").add(stelle);
				// }
				// });

				// // Button erstellen um Delete durchzuführen - wird der Button
				// // ausgelöst wird die entsprechende Ausschreibung gelöscht
				// Button stelleLoeschenButton = new
				// Button("Stellenausschreibung loeschen");
				// stelleLoeschenButton.setStylePrimaryName("myjob-menubutton");
				// stelleLoeschenButton.addClickHandler(new ClickHandler() {
				// @Override
				// public void onClick(ClickEvent event) {
				// ShowDefinition stelle = new
				// DeleteStellenausschreibung(s.getStellenId());
				// RootPanel.get("Details").clear();
				// RootPanel.get("Details").add(stelle);
				// }
				// });

				// showStellenausschreibungFlexTable.setWidget(row, 4,
				// stelleBearbeitenButton);
				// showStellenausschreibungFlexTable.setWidget(row, 5,
				// stelleLoeschenButton);
				showStellenausschreibungFlexTable.setWidget(row, 4, stelleAnzeigenButton);
			}
		}

	}

}
