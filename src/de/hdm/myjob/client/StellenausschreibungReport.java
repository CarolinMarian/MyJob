package de.hdm.myjob.client;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.myjob.shared.AdministrationAsync;
import de.hdm.myjob.shared.ReportAdministrationAsync;
import de.hdm.myjob.shared.bo.Benutzer;
import de.hdm.myjob.shared.bo.Stellenausschreibung;
import de.hdm.myjob.shared.report.AllInhalteOfStellenausschreibungReport;
import de.hdm.myjob.shared.report.HTMLReportWriter;

public class StellenausschreibungReport extends ShowDefinition {
	private Benutzer benutzer = new Benutzer();
	private Stellenausschreibung stelle = new Stellenausschreibung();

	private VerticalPanel verPanel = new VerticalPanel();
	private HorizontalPanel horPanel = new HorizontalPanel();

	// private ListBox auswahl = new ListBox();

	AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();

	@Override
	protected String getHeadlineText() {
		return "Report fuer alle Stellenausschreibungen deines Profils";
	}

	@Override
	protected void run() {
		this.add(verPanel);

		// BenutzerId hardcoden
		benutzer.setId(1);

		verwaltung.showStellenausschreibung(benutzer.getId(), new StelleCallback(this));

		// ReportAdministrationAsync reportVerwaltung =
		// ClientsideSettings.getReportVerwaltung();
		// reportVerwaltung.createAllInhalteOfStellenausschreibungReport(stelle,
		// new AllInhalteOfStellenausschreibungReportCallback(this));

		// Dropdown Menü aller Stellenausschreibungen erstellen
		// befuelleBox();

		// horPanel.add(auswahl);
		verPanel.add(horPanel);
	}

	class StelleCallback implements AsyncCallback<Vector<Stellenausschreibung>> {
		private ShowDefinition showdef = null;

		public StelleCallback(ShowDefinition s) {
			this.showdef = s;
		}

		@Override
		public void onFailure(Throwable caught) {
			this.showdef.append("Fehler bei der Abfrage " + caught.getMessage());
		}

		@Override
		public void onSuccess(Vector<Stellenausschreibung> stelle) {
			if (stelle != null) {

				for (Stellenausschreibung s : stelle) {
					ReportAdministrationAsync reportVerwaltung = ClientsideSettings.getReportVerwaltung();

					// reportVerwaltung.createAllInhalteOfStellenausschreibungReport(stelle,
					// new
					// AllInhalteOfStellenausschreibungReportCallback(this.showdef));
				}
			}
		}
	}
	// public void befuelleBox() {
	// verwaltung.showStellenausschreibung(benutzer.getId(), new
	// ShowStelle(this));
	// }

	// class ShowStelle implements AsyncCallback<Vector<Stellenausschreibung>> {
	//
	// private ShowDefinition showdef = null;
	//
	// public ShowStelle(ShowDefinition s) {
	// this.showdef = s;
	// }
	//
	// @Override
	// public void onFailure(Throwable caught) {
	// this.showdef.append("Fehler bei der Abfrage " + caught.getMessage());
	// }
	//
	// @Override
	// public void onSuccess(final Vector<Stellenausschreibung> result) {
	// if (result.isEmpty()) {
	// auswahl.setVisible(false);
	// Label anzeige = new Label();
	// anzeige.setText("Bisher existiert keine Stellenausschreibung zu Ihrem
	// Profil");
	// } else {
	// for (final Stellenausschreibung s : result) {
	// auswahl.addItem(s.getBezeichnung());
	// }
	//
	// }
	//
	// auswahl.addClickHandler(new ClickHandler() {
	//
	// @Override
	// public void onClick(ClickEvent event) {
	// //
	// verwaltung.showStellenausschreibungByStellenId(auswahl.getSelectedItemText(),
	// // ShowStelleById(this));
	// ReportAdministrationAsync reportVerwaltung =
	// ClientsideSettings.getReportVerwaltung();
	// reportVerwaltung.createAllInhalteOfStellenausschreibungReport(stelle,
	// new AllInhalteOfStellenausschreibungReportCallback(showdef));
	// }
	// });
	// }
	//
	// }

	class AllInhalteOfStellenausschreibungReportCallback
			implements AsyncCallback<AllInhalteOfStellenausschreibungReport> {
		private ShowDefinition showdef = null;

		public AllInhalteOfStellenausschreibungReportCallback(ShowDefinition s) {
			this.showdef = s;
		}

		@Override
		public void onFailure(Throwable caught) {
			this.showdef.append("Fehler bei der Abfrage " + caught.getMessage());
		}

		@Override
		public void onSuccess(AllInhalteOfStellenausschreibungReport result) {
			this.showdef.append(result.getTitle());
			if (result != null) {
				HTMLReportWriter writer = new HTMLReportWriter();
				writer.process(result);
				this.showdef.append(writer.getReportText());
			}
		}
	}
}
