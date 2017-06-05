package de.hdm.myjob.client;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;

import de.hdm.myjob.shared.AdministrationAsync;
import de.hdm.myjob.shared.bo.Benutzer;
import de.hdm.myjob.shared.bo.Eigenschaft;
import de.hdm.myjob.shared.bo.Inhalt;

public class ShowEigenschaften extends ShowDefinition {
	
	

	@Override
	protected String getHeadlineText() {
		String headline = "Dein Profil:";
		return headline;
	}

	@Override
	protected void run() {
		
		button();
		
		
		AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();
		
	//verwaltung.getEigenschaftById(3, callback);
		
	}
	
	public void button(){
				
		Button eigenschaftenAnlegen = new Button("Eigenschaften anlegen");
		this.add(eigenschaftenAnlegen);
		
		eigenschaftenAnlegen.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String typ ="b";
				CreateEigenschaft createE = new CreateEigenschaft(typ);
				createE.show();
				createE.center();
				
			}
			
		});
			
		
	}
	



}
