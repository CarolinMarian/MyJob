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

public class ShowBewerbungen extends ShowDefinition {

	// Klasseninstanzen definieren
	Benutzer nutzer = new Benutzer();
	// Panels definieren
	private HorizontalPanel horPanel = new HorizontalPanel();
	private VerticalPanel verPanel = new VerticalPanel();
	// Tabelle definieren
	FlexTable showBewerbungFlexTable = new FlexTable();

	// Überschrift definieren
	@Override
	protected String getHeadlineText() {
		String headline = "Dies sind alle Ihre Bewerbungen:";
		return headline;
	}

	// Run-Methode
	@Override
	protected void run() {

		this.add(verPanel);

		nutzer.setId(1);

		AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();
		verwaltung.showBewerbungen(nutzer.getId(), new AnzeigenBewerbung(this));

		verPanel.add(showBewerbungFlexTable);
		verPanel.add(horPanel);
	}

	class AnzeigenBewerbung implements AsyncCallback<Vector<Stellenausschreibung>> {

		ShowDefinition showdef = null;
		
		public AnzeigenBewerbung(ShowDefinition s) {
			this.showdef = s;
		}
		@Override
		public void onFailure(Throwable caught) {
			this.showdef.append("Fehler bei der Abfrage " + caught.getMessage());
		}

		@Override
		public void onSuccess(Vector<Stellenausschreibung> result) {
			showBewerbungFlexTable.setText(0, 0, "Bezeichnung");
			showBewerbungFlexTable.setText(0, 1, "Ausschreibungstext");
			showBewerbungFlexTable.setText(0, 2, "Frist");
			showBewerbungFlexTable.setText(0, 3, "");
			showBewerbungFlexTable.setText(0, 4, "");

			int row = 0;
			for (final Stellenausschreibung s : result) {
				row++;
				showBewerbungFlexTable.setText(row, 0, s.getBezeichnung());
				showBewerbungFlexTable.setText(row, 1, s.getBeschreibungstext());
				Date frist = s.getFrist();
				String fristString = DateTimeFormat.getFormat("dd.MM.yyyy").format(frist);
				showBewerbungFlexTable.setText(row, 2, fristString);

				// Button erstellen um Stelle anzuzeigen durchzuführen - wird
				// der Button
				// ausgelöst wird man zu der entsprechenden Ausschreibung
				// weitergeleitet, um diese im Detail ansehen zu können
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

				// Button erstellen um Delete durchzuführen
				Button bewerbungLoeschenButton = new Button("Bewerbung zurückziehen");
				bewerbungLoeschenButton.setStylePrimaryName("myjob-menubutton");
				bewerbungLoeschenButton.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						ShowDefinition bewerbung = new DeleteBewerbung(s.getStellenId());
						RootPanel.get("Details").clear();
						RootPanel.get("Details").add(bewerbung);
					}
				});

				showBewerbungFlexTable.setWidget(row, 3, stelleAnzeigenButton);
				showBewerbungFlexTable.setWidget(row, 4, bewerbungLoeschenButton);
			}
		}

	}
}
