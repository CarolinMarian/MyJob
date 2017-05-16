package de.hdm.myjob.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Navigator extends ShowDefinition {
	
VerticalPanel navPanel = new VerticalPanel();

@Override
protected String getHeadlineText() {
	return null;
}

@Override
protected void run(){
	
	this.add(navPanel);
	
	Button profilAnzeigenButton = new  Button("Mein Profil");
	profilAnzeigenButton.setStylePrimaryName("myjob-menubutton");
	
	profilAnzeigenButton.addClickHandler(new ClickHandler (){

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			ShowDefinition showdef = new ShowProfil();
			RootPanel.get("Details").clear();
	        RootPanel.get("Details").add(showdef);
		}
			
	});
	
	Button stellenbeschreibungenAnzeigenButton = new Button("Jobs");
	stellenbeschreibungenAnzeigenButton.setStylePrimaryName("myjob-menubutton");
	
	stellenbeschreibungenAnzeigenButton.addClickHandler(new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
		
	});
	
	Button bewerbungenAnzeigenButton = new Button("Meine Bewerbungen");
	bewerbungenAnzeigenButton.setStylePrimaryName("myjob-menubutton");
	
	bewerbungenAnzeigenButton.addClickHandler(new ClickHandler(){

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
		
	});
	
	navPanel.add(profilAnzeigenButton);
	navPanel.add(stellenbeschreibungenAnzeigenButton);
	navPanel.add(bewerbungenAnzeigenButton);
	
	
	
}





}
