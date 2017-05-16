package de.hdm.myjob.client;

import com.google.gwt.user.client.ui.RootPanel;


public class ShowProfil extends ShowDefinition{
	
	@Override
	protected String getHeadlineText() {
		String headlineText = "Profil"; 
		return headlineText;
	}

	@Override
	protected void run() {
		
		ShowDefinition showsuper = new ShowEigenschaften();
        RootPanel.get("Details").add(showsuper);
		
		
	}


}
