package de.hdm.myjob.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.myjob.shared.AdministrationAsync;
import de.hdm.myjob.shared.ReportAdministrationAsync;
import de.hdm.myjob.shared.bo.Profil;
import de.hdm.myjob.shared.report.AllInhalteOfProfilReport;
import de.hdm.myjob.shared.report.HTMLReportWriter;

public class ProfilReport extends ShowDefinition {

	@Override
	protected String getHeadlineText() {
		return "Report fuer mein Profil";
	}

	@Override
	protected void run() {

		AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();

	//	verwaltung.getProfilFor(1, new ProfilCallback(this));

	}


	class ProfilCallback implements AsyncCallback<Profil> {

		private ShowDefinition showdef = null;


		public ProfilCallback (ShowDefinition s){
			this.showdef = s;
		}


		@Override
		public void onFailure(Throwable caught) {
			this.showdef.append("Fehler bei der Abfrage " + caught.getMessage());

		}

		@Override
		public void onSuccess(Profil profil) {

			if (profil != null) {
				ReportAdministrationAsync reportVerwaltung = ClientsideSettings.getReportVerwaltung();

				reportVerwaltung.createAllInhalteOfProfilReport(profil,
						new AllInhalteOfProfilReportCallback(this.showdef));
			}

		}


	}


	class AllInhalteOfProfilReportCallback
	implements AsyncCallback<AllInhalteOfProfilReport> {
		private ShowDefinition showdef = null;

		public AllInhalteOfProfilReportCallback(ShowDefinition s) {
			this.showdef = s;
		}

		@Override
		public void onFailure(Throwable caught) {
			this.showdef.append("Fehler bei der Abfrage " + caught.getMessage());
		}

		@Override
		public void onSuccess(AllInhalteOfProfilReport report) {
			if (report != null) {
				HTMLReportWriter writer = new HTMLReportWriter();
				writer.process(report);
				this.showdef.append(writer.getReportText());
			}
		}
	}



}
