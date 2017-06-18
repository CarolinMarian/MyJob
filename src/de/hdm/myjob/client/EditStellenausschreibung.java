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
import de.hdm.myjob.shared.bo.Stellenausschreibung;

public class EditStellenausschreibung extends ShowDefinition {

	// Kommunikation mit der Datenbank
	AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();
	// Klassenobjekte erzeugen
	Stellenausschreibung stelle = new Stellenausschreibung();
	Benutzer benutzer = new Benutzer();

	// Panels definieren
	private HorizontalPanel horPanel = new HorizontalPanel();
	private VerticalPanel verPanel = new VerticalPanel();
	// Tabelle + Textboxen definieren
	FlexTable editStellenausschreibungFlexTable = new FlexTable();
	private TextBox valueBoxBezeichnung = new TextBox();
	private TextBox valueBoxBeschreibung = new TextBox();
	private DateBox dateBoxFrist = new DateBox();
	// Datum definieren
	private DateTimeFormat fristFormat = DateTimeFormat.getFormat("dd.MM.yyyy");
	private Label fristInhalt = new Label();
	// Button definieren
	private Button editStellenausschreibungButton = new Button("Stellenausschreibung bearbeiten");

	// Konstruktor erstellen der die übergebene ID in das Klassenobjekt
	// abspeichert
	public EditStellenausschreibung(int stellenid) {
		this.stelle.setStellenId(stellenid);
		run();
	}

	// Headline festlegen
	@Override
	protected String getHeadlineText() {
		String headline = "Stellenausschreibung bearbeiten";
		return headline;
	}

	// Run-Methode
	@Override
	protected void run() {

		// BenutzerId hardcoded

		benutzer.setId(1);

		this.add(verPanel);

		// Tabelle befüllen
		editStellenausschreibungFlexTable.setText(0, 0, "Bezeichnung");
		editStellenausschreibungFlexTable.setText(1, 0, "Ausschreibungstext");
		editStellenausschreibungFlexTable.setText(2, 0, "Frist");

		editStellenausschreibungFlexTable.setWidget(0, 2, valueBoxBezeichnung);
		editStellenausschreibungFlexTable.setWidget(1, 2, valueBoxBeschreibung);

		// Format ändern des Datums
		dateBoxFrist.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date frist = event.getValue();
				String fristString = DateTimeFormat.getFormat("dd.MM.yyyy").format(frist);
				fristInhalt.setText(fristString);
			}
		});
		editStellenausschreibungFlexTable.setWidget(2, 2, dateBoxFrist);

		// Stelle in UpdateFenster befüllen
		verwaltung.showStellenausschreibungByStellenId(stelle.getStellenId(), new ShowStelle(this));

		// Button der auslöst, dass die Änderung in die DB geschrieben wird
		editStellenausschreibungButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				verwaltung.editStellenausschreibung(valueBoxBezeichnung.getText(), valueBoxBeschreibung.getText(),
						getFrist(), benutzer.getId(), stelle.getStellenId(), new UpdateStelle());
			}
		});

		verPanel.add(editStellenausschreibungFlexTable);
		verPanel.add(horPanel);
		verPanel.add(editStellenausschreibungButton);
	}

	class ShowStelle implements AsyncCallback<Stellenausschreibung> {

		ShowDefinition showdef = null;
		
		public ShowStelle(ShowDefinition s){
			this.showdef = s;
		}
		
		@Override
		public void onFailure(Throwable caught) {
			this.showdef.append("Fehler bei der Abfrage " + caught.getMessage());
		}

		@Override
		public void onSuccess(Stellenausschreibung result) {
			valueBoxBezeichnung.setText(result.getBezeichnung());
			valueBoxBeschreibung.setText(result.getBeschreibungstext());
			dateBoxFrist.setValue(result.getFrist());
		}
	}

	class UpdateStelle implements AsyncCallback<Stellenausschreibung> {
		
		@Override
		public void onFailure(Throwable caught) {
		}

		@Override
		public void onSuccess(Stellenausschreibung result) {
			ShowDefinition stelle = new ShowOneStellenausschreibung(result.getStellenId());
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
