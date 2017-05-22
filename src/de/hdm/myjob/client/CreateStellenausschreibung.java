package de.hdm.myjob.client;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.myjob.shared.AdministrationAsync;
import de.hdm.myjob.shared.bo.Benutzer;
import de.hdm.myjob.shared.bo.Profil;
import de.hdm.myjob.shared.bo.Stellenausschreibung;

public class CreateStellenausschreibung extends ShowDefinition {

	// Panels definieren
	private HorizontalPanel horPanel = new HorizontalPanel();
	private VerticalPanel verPanel = new VerticalPanel();
	// Tabelle + Textboxen definieren
	FlexTable createStellenausschreibungFlexTable = new FlexTable();
	private TextBox valueBoxBezeichnung = new TextBox();
	private TextBox valueBoxBeschreibung = new TextBox();
	private DateBox dateBoxFrist = new DateBox();
	// Datum definieren
	private DateTimeFormat fristFormat = DateTimeFormat.getFormat("dd.MM.yyyy");
	private Label fristInhalt = new Label();
	// Button definieren
	private Button createStellenausschreibungButton = new Button("Stellenausschreibung anlegen");
	// Klasseninstanzen definieren
	Benutzer b = new Benutzer();
	Profil p = new Profil();

	// Überschrift festlegen
	@Override
	protected String getHeadlineText() {
		String headline = "Bitte erstellen Sie Ihre Stellenausschreibung";
		return headline;
	}

	// Run-Methode
	@Override
	protected void run() {

		this.add(verPanel);

		// Tabelle befüllen
		createStellenausschreibungFlexTable.setText(0, 0, "Bezeichnung");
		createStellenausschreibungFlexTable.setText(1, 0, "Ausschreibungstext");
		createStellenausschreibungFlexTable.setText(2, 0, "Frist");

		createStellenausschreibungFlexTable.setWidget(0, 2, valueBoxBezeichnung);
		createStellenausschreibungFlexTable.setWidget(1, 2, valueBoxBeschreibung);

		// Format ändern des Datums
		dateBoxFrist.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date frist = event.getValue();
				String fristString = DateTimeFormat.getFormat("dd.MM.yyyy").format(frist);
				fristInhalt.setText(fristString);
			}
		});
		createStellenausschreibungFlexTable.setWidget(2, 2, dateBoxFrist);

		// IDs Hardcoden bis Klassen vollständig
		b.setId(1);
		p.setId(1);

		createStellenausschreibungButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// Kommunikation mit der Datenbank
				AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();
				verwaltung.createStellenausschreibung(valueBoxBezeichnung.getText(), valueBoxBeschreibung.getText(),
						getFrist(), b, p, new CreateStelle());
			}
		});

		// verPanel.add(celltable);
		horPanel.add(createStellenausschreibungButton);
		verPanel.add(createStellenausschreibungFlexTable);
		verPanel.add(horPanel);
	}

	class CreateStelle implements AsyncCallback<Stellenausschreibung> {

		@Override
		public void onFailure(Throwable caught) {
			Label failLabel = new Label("onFailure wurde betreten");
			verPanel.add(failLabel);
		}

		@Override
		public void onSuccess(Stellenausschreibung result) {
			ShowDefinition stelle = new ShowAllStellenausschreibungenId();
			RootPanel.get("Details").clear();
			RootPanel.get("Details").add(stelle);
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
