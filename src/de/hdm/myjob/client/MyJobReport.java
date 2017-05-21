package de.hdm.myjob.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class MyJobReport implements EntryPoint {

	@Override
	public void onModuleLoad() {
		
		RootPanel.get("Navigator").add(new ReportNavigator ());
		
	}

}
