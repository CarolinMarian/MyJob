package de.hdm.myjob.client;

import java.util.Date;

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
import de.hdm.myjob.shared.bo.Profil;
import de.hdm.myjob.shared.bo.Stellenausschreibung;

public class ShowOneStellenausschreibung extends ShowDefinition {

	// Klassenobjekte erzeugen
	// Inhalt inhalt = new Inhalt();
	Stellenausschreibung stelle = new Stellenausschreibung();
	Benutzer nutzer = new Benutzer();
	// Panels definieren
	private HorizontalPanel horPanel = new HorizontalPanel();
	private VerticalPanel verPanel = new VerticalPanel();
	// Tabelle + Textboxen definieren
	FlexTable showOneStellenausschreibungFlexTable = new FlexTable();
	private Label bezeichnung = new Label();
	private Label beschreibung = new Label();
	// Datum definieren
	private DateTimeFormat fristFormat = DateTimeFormat.getFormat("dd.MM.yyyy");
	private Label fristInhalt = new Label();
	// Button hinzufügen
	Button stelleBearbeitenButton = new Button("Stellenausschreibung bearbeiten");
	Button stelleLoeschenButton = new Button("Stellenausschreibung loeschen");
	Button bewerbenButton = new Button("Bewerben");

	// Konstruktor erstellen der die übergebene ID in das Klassenobjekt
	// abspeichert
	public ShowOneStellenausschreibung(int stellenid) {
		this.stelle.setStellenId(stellenid);
		run();
	}

	// Überschrift definieren
	@Override
	protected String getHeadlineText() {
		String headline = "Details zur Stellenausschreibung";
		return headline;
	}

	// Run-Methode
	@Override
	protected void run() {

		this.add(verPanel);

		nutzer.setId(1);

		// Tabelle befüllen
		showOneStellenausschreibungFlexTable.setText(0, 0, "Bezeichnung");
		showOneStellenausschreibungFlexTable.setText(1, 0, "Ausschreibungstext");
		showOneStellenausschreibungFlexTable.setText(2, 0, "Frist");

		showOneStellenausschreibungFlexTable.setWidget(0, 1, bezeichnung);
		showOneStellenausschreibungFlexTable.setWidget(1, 1, beschreibung);
		showOneStellenausschreibungFlexTable.setWidget(2, 1, fristInhalt);

		showOneStellenausschreibungFlexTable.setWidget(4, 0, stelleBearbeitenButton);
		showOneStellenausschreibungFlexTable.setWidget(4, 1, stelleLoeschenButton);
		showOneStellenausschreibungFlexTable.setWidget(4, 2, bewerbenButton);

		// Stellenausschreibung anhand der ID anzeigen
		AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();
		verwaltung.showStellenausschreibungByStellenId(stelle.getStellenId(), new ShowStelle());

		verPanel.add(showOneStellenausschreibungFlexTable);
		verPanel.add(horPanel);
	}

	class ShowStelle implements AsyncCallback<Stellenausschreibung> {

		@Override
		public void onFailure(Throwable caught) {
			Label failLabel = new Label("onFailure wurde betreten");
			verPanel.add(failLabel);
		}

		@Override
		public void onSuccess(final Stellenausschreibung result) {
			bezeichnung.setText(result.getBezeichnung());
			beschreibung.setText(result.getBeschreibungstext());

			Date frist = result.getFrist();
			String fristString = DateTimeFormat.getFormat("dd.MM.yyyy").format(frist);
			fristInhalt.setText(fristString);

			// // Info anhand der ID anzeigen
			// AdministrationAsync verwaltung =
			// ClientsideSettings.getVerwaltung();
			// verwaltung.showInfo(info.getInfoId(), new ShowStelle());

			// Button erstellen um Update durchzuführen - wird der Button
			// ausgelöst wird man zu der entsprechenden Ausschreibung
			// weitergeleitet, um dieses bearbeiten zu können
			stelleBearbeitenButton.setStylePrimaryName("myjob-menubutton");
			stelleBearbeitenButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					ShowDefinition stelle = new EditStellenausschreibung(result.getStellenId());
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(stelle);
				}
			});

			// Button erstellen um Delete durchzuführen - wird der Button
			// ausgelöst wird die entsprechende Ausschreibung gelöscht
			stelleLoeschenButton.setStylePrimaryName("myjob-menubutton");
			stelleLoeschenButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					ShowDefinition stelle = new DeleteStellenausschreibung(result.getStellenId());
					RootPanel.get("Details").clear();
					RootPanel.get("Details").add(stelle);
				}
			});

			// Button erstellen um Bewerbung durchzuführen - wird der Button
			// ausgelöst wird die entsprechende Ausschreibung gelöscht
			bewerbenButton.setStylePrimaryName("myjob-menubutton");
			bewerbenButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					ShowDefinition bewerben = new CreateBewerbung(result.getStellenId());
					RootPanel.get("Details").clear();
					ShowDefinition bewerbunganzeigen = new ShowBewerbungen();
					RootPanel.get("Details").add(bewerbunganzeigen);
				}
			});
		}
	}

	// Methode um das aktuell formatierte Datum des addValueChangeHandler für
	// die DB erneut zu formattieren (dazu Zugriff auf befülltes Label in der
	// addValueChangeHandler)
	Date getFrist() {
		Date frist = fristFormat.parse(fristInhalt.getText());
		java.sql.Date sqlDate = new java.sql.Date(frist.getTime());
		return sqlDate;
	}
}
