package de.hdm.myjob.client;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.myjob.shared.AdministrationAsync;
import de.hdm.myjob.shared.ReportAdministrationAsync;
import de.hdm.myjob.shared.bo.Benutzer;
import de.hdm.myjob.shared.bo.Stellenausschreibung;
import de.hdm.myjob.shared.report.AllInhalteOfStellenausschreibungReport;
import de.hdm.myjob.shared.report.HTMLReportWriter;

public class StellenausschreibungReport extends ShowDefinition {
	private Benutzer benutzer = new Benutzer();

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

		verwaltung.getBenutzerById(benutzer.getId(), new BenutzerCallback(this));

		verwaltung.showStellenausschreibung(benutzer.getId(), new StelleCallback(this));

		verPanel.add(horPanel);
	}

	class BenutzerCallback implements AsyncCallback<Benutzer> {

		private ShowDefinition showdef = null;

		public BenutzerCallback(ShowDefinition s) {
			this.showdef = s;
		}

		@Override
		public void onFailure(Throwable caught) {
			this.showdef.append("Fehler bei der Abfrage " + caught.getMessage());

		}

		@Override
		public void onSuccess(Benutzer b) {
			benutzer.setId(b.getId());
			benutzer.setFirstName(b.getFirstName());
			benutzer.setLastName(b.getLastName());
			benutzer.setEmail(b.getEmail());
		}
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

				ReportAdministrationAsync reportVerwaltung = ClientsideSettings.getReportVerwaltung();

				reportVerwaltung.createAllInhalteOfStellenausschreibungReport(benutzer, stelle,
						new AllInhalteOfStellenausschreibungReportCallback(this.showdef));
			}
		}
	}

	// public void befuelleBox() {
	// verwaltung.showStellenausschreibung(benutzer.getId(), new
	// ShowStelle(this));
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
			if (result != null) {
				HTMLReportWriter writer = new HTMLReportWriter();
				writer.process(result);
				this.showdef.append(writer.getReportText());
			}
		}
	}
}
