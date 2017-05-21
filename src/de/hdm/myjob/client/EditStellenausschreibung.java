package de.hdm.myjob.client;

import java.util.Date;
import java.util.Vector;

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

import de.hdm.myjob.client.CreateStellenausschreibung.CreateStelle;
import de.hdm.myjob.client.ShowStellenausschreibung.ShowStelle;
import de.hdm.myjob.shared.AdministrationAsync;
import de.hdm.myjob.shared.bo.Benutzer;
import de.hdm.myjob.shared.bo.Profil;
import de.hdm.myjob.shared.bo.Stellenausschreibung;

public class EditStellenausschreibung extends ShowDefinition {

	AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();

	Stellenausschreibung stelle = new Stellenausschreibung();
	Benutzer benutzer = new Benutzer();
	Profil profil = new Profil();
	int profilid;
	int benutzerid;
	int stellenid;

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
	private Button editStellenausschreibungButton = new Button("Stellenausschreibung anlegen");

	// // Konstruktor erstellen
	// public EditStellenausschreibung(final int benutzerid, final int profilid)
	// {
	// this.profilid = profilid;
	// this.benutzerid = benutzerid;
	// run();
	// }

	@Override
	protected String getHeadlineText() {
		String headline = "Stellenausschreibung bearbeiten";
		return headline;
	}

	@Override
	protected void run() {

		profil.setId(1);
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

		verwaltung.showStellenausschreibungByStellenId(stelle.getStellenId(), new ShowStelle());

		editStellenausschreibungButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				verwaltung.editStellenausschreibung(valueBoxBezeichnung.getText(), valueBoxBeschreibung.getText(),
						getFrist(), benutzer, profil, new UpdateStelle());
			}
		});

		verPanel.add(editStellenausschreibungFlexTable);
		verPanel.add(horPanel);
		verPanel.add(editStellenausschreibungButton);
	}

	class UpdateStelle implements AsyncCallback<Stellenausschreibung> {

		@Override
		public void onFailure(Throwable caught) {
			Label failLabel = new Label("onFailure wurde betreten");
			verPanel.add(failLabel);
		}

		@Override
		public void onSuccess(Stellenausschreibung result) {
			Label successLabel = new Label("onSuccess wurde betreten");
			verPanel.add(successLabel);
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

	class ShowStelle implements AsyncCallback<Stellenausschreibung> {

		@Override
		public void onFailure(Throwable caught) {
			Label failLabel = new Label("onFailure wurde betreten");
			verPanel.add(failLabel);
		}

		@Override
		public void onSuccess(Stellenausschreibung result) {
			Label successLabel = new Label("onSuccess wurde betreten");
			verPanel.add(successLabel);

			stelle.setStellenId(result.getStellenId());
			valueBoxBezeichnung.setText(result.getBezeichnung());
			valueBoxBeschreibung.setText(result.getBeschreibungstext());
			dateBoxFrist.setValue(result.getFrist());
		}
	}

}
