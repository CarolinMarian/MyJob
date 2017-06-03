package de.hdm.myjob.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.myjob.shared.AdministrationAsync;
import de.hdm.myjob.shared.ReportAdministrationAsync;
import de.hdm.myjob.shared.bo.Benutzer;
import de.hdm.myjob.shared.report.AllInhalteOfBenutzerReport;
import de.hdm.myjob.shared.report.HTMLReportWriter;

public class ProfilReport extends ShowDefinition {

	@Override
	protected String getHeadlineText() {
		return "Report fuer mein Profil";
	}

	@Override
	protected void run() {

		AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();

		verwaltung.getBenutzerById(1, new BenutzerCallback(this));

	}


	class BenutzerCallback implements AsyncCallback<Benutzer> {

		private ShowDefinition showdef = null;


		public BenutzerCallback (ShowDefinition s){
			this.showdef = s;
		}


		@Override
		public void onFailure(Throwable caught) {
			this.showdef.append("Fehler bei der Abfrage " + caught.getMessage());

		}

		@Override
		public void onSuccess(Benutzer b) {

			if (b != null) {
				ReportAdministrationAsync reportVerwaltung = ClientsideSettings.getReportVerwaltung();

				reportVerwaltung.createAllInhalteOfBenutzerReport(b,
						new AllInhalteOfBenutzerReportCallback(this.showdef));
			}

		}


	}


	class AllInhalteOfBenutzerReportCallback
	implements AsyncCallback<AllInhalteOfBenutzerReport> {
		private ShowDefinition showdef = null;

		public AllInhalteOfBenutzerReportCallback(ShowDefinition s) {
			this.showdef = s;
		}

		@Override
		public void onFailure(Throwable caught) {
			this.showdef.append("Fehler bei der Abfrage " + caught.getMessage());
		}

		@Override
		public void onSuccess(AllInhalteOfBenutzerReport report) {
			if (report != null) {
				HTMLReportWriter writer = new HTMLReportWriter();
				writer.process(report);
				this.showdef.append(writer.getReportText());
			}
		}
	}



}
