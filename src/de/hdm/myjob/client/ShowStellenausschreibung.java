package de.hdm.myjob.client;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.myjob.shared.AdministrationAsync;
import de.hdm.myjob.shared.bo.Benutzer;
import de.hdm.myjob.shared.bo.Profil;
import de.hdm.myjob.shared.bo.Stellenausschreibung;

public class ShowStellenausschreibung extends ShowDefinition {
	Benutzer benutzer = new Benutzer();
	Profil profil = new Profil();

	// Panels definieren
	private HorizontalPanel horPanel = new HorizontalPanel();
	private VerticalPanel verPanel = new VerticalPanel();
	// Tabelle + Textboxen definieren
	FlexTable showStellenausschreibungFlexTable = new FlexTable();

	@Override
	protected String getHeadlineText() {
		String headline = "Eine Übersicht über alle Stellenausschreibungen:";
		return headline;
	}

	@Override
	protected void run() {

		profil.setId(1);
		benutzer.setId(1);

		this.add(verPanel);

		showStellenausschreibungFlexTable.setText(0, 0, "StellenId");
		showStellenausschreibungFlexTable.setText(0, 1, "Bezeichnung");
		showStellenausschreibungFlexTable.setText(0, 2, "Ausschreibungstext");
		showStellenausschreibungFlexTable.setText(0, 3, "Frist");

		AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();
		verwaltung.showStellenausschreibung(benutzer.getId(), profil.getId(), new ShowStelle());

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
			Label successLabel = new Label("onSuccess wurde betreten");
			verPanel.add(successLabel);

			int row = 0;
			for (Stellenausschreibung s : result) {
				row++;
				showStellenausschreibungFlexTable.setText(row, 0, String.valueOf(s.getStellenId()));
				showStellenausschreibungFlexTable.setText(row, 1, s.getBezeichnung());
				showStellenausschreibungFlexTable.setText(row, 2, s.getBeschreibungstext());
				Date frist = s.getFrist();
				String fristString = DateTimeFormat.getFormat("dd.MM.yyyy").format(frist);
				showStellenausschreibungFlexTable.setText(row, 3, fristString);
			}
		}

	}

}
