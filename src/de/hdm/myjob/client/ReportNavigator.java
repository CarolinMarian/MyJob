package de.hdm.myjob.client;

import com.google.gwt.user.client.ui.Button;

public class ReportNavigator extends ShowDefinition {

	@Override
	protected String getHeadlineText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		
		Button profilReport = new Button("Profil Report");
		profilReport.setStylePrimaryName("myjob-menubutton");
		this.add(profilReport);
		
		Button allProfileReport = new Button("Alle Profile Report");
		allProfileReport.setStylePrimaryName("myjob-menubutton");
		this.add(allProfileReport);
		
	}

}
