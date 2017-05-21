package de.hdm.myjob.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

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
		
		profilReport.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				ShowDefinition showdef = new ProfilReport();
				RootPanel.get("Details").clear();
				RootPanel.get("Details").add(showdef);
			}

		});

		
		Button allProfileReport = new Button("Alle Profile Report");
		allProfileReport.setStylePrimaryName("myjob-menubutton");
		this.add(allProfileReport);
		
	}

}
