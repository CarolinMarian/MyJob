package de.hdm.myjob.client;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.myjob.shared.AdministrationAsync;
import de.hdm.myjob.shared.bo.Benutzer;
import de.hdm.myjob.shared.bo.Eigenschaft;

public class ShowProfil extends ShowDefinition {
	

	Label vname = new Label("Vorname");
	Label nname = new Label("Nachname");
	Label mail = new Label("Emailadresse"); //Später aus login-Daten beziehen
	TextBox vnameBox = new TextBox();
	TextBox nnameBox = new TextBox();
	TextBox mailBox = new TextBox();

	String email = "";
	String vorname = "";
	String nachname = "";
	
	@Override
	protected String getHeadlineText() {
		String headlineText = null;
		return headlineText;
	}

	@Override
	protected void run() {

		AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();

		verwaltung.getBenutzerById(5, new BenutzerCallback(this));

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
	      if (b == null) {
	    	  

	    	this.showdef.appendHL("Profil anlegen");
	    	
	    	
	    	this.showdef.add(vname);
	    	this.showdef.add(vnameBox);
	    	this.showdef.add(nname);
	    	this.showdef.add(nnameBox);
	    	this.showdef.add(mail);
	    	this.showdef.add(mailBox);
	    	
	    	Button profilAnlegenButton = new Button("Profil erstellen");
			profilAnlegenButton.setStylePrimaryName("myjob-menubutton");
			this.showdef.add(profilAnlegenButton);
			
		
			profilAnlegenButton.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					// TODO Auto-generated method stub
					email = mailBox.getText();
					vorname = vnameBox.getText();
					nachname = nnameBox.getText();
					
					AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();
			  		verwaltung.createBenutzer(email, vorname, nachname, new AsyncCallback<Benutzer>(){

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void onSuccess(Benutzer result) {
							// TODO Auto-generated method stub
							ShowDefinition showdef = new ShowProfil();
							RootPanel.get("Details").clear();
							RootPanel.get("Details").add(showdef);
							
							
						}
			  			
			  		});
					
				}
				
			});
//			profilAnlegenButton.addClickHandler(new AnlegenClickHandler(this.showdef, email, 
//					vorname, nachname)); 
	  		
	      }
	      
	      else {
	    	  this.showdef.appendHL("Hallo " + b.getFirstName() + " " + b.getLastName());
	    	  ShowDefinition showdef = new ShowEigenschaften();
	    	  RootPanel.get("Details").add(showdef);
	      }
	    }

	}
	
//	class AnlegenClickHandler implements ClickHandler{
//		
//		private ShowDefinition showdef = null;
//		private String mail = "";
//		private String vname = "";
//		private String nname = "";
//		
//		private AnlegenClickHandler(ShowDefinition s, String m, String v, String n) {
//			this.showdef = s;
//			this.mail = m;
//			this.vname = v;
//			this.nname = n;
//			
//		}
//		@Override
//		public void onClick(ClickEvent event) {
//			// TODO Auto-generated method stub
//			AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();
//	  		verwaltung.createBenutzer(mail, vname, nname, new CreateBenutzerCallback(this.showdef));
//		}
//		
//	}
	
	class CreateBenutzerCallback implements AsyncCallback<Benutzer>{
		
		private ShowDefinition showdef = null;

		public CreateBenutzerCallback(ShowDefinition s) {
			this.showdef = s;
		}
		
		
		@Override
		public void onFailure(Throwable caught) {
			
		}

		@Override
		public void onSuccess(Benutzer result) {
			ShowDefinition showdef = new ShowProfil();
			RootPanel.get("Details").add(showdef);
			
		}
		
	}

	
}
