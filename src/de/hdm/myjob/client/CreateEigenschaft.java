package de.hdm.myjob.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.myjob.shared.AdministrationAsync;
import de.hdm.myjob.shared.bo.Eigenschaft;
import de.hdm.myjob.shared.bo.Inhalt;

public class CreateEigenschaft  extends DialogBox{

	VerticalPanel verPanel = new VerticalPanel();
	VerticalPanel verPanel2 = new VerticalPanel();
	HorizontalPanel buttonPanel = new HorizontalPanel();
	ListBox listBox = new ListBox();
	TextBox textBox = new TextBox();
	TextBox textBox2 = new TextBox();
	TextBox textBox3 = new TextBox();
	DateBox dateBox = new DateBox();
	Label label = new Label();
	Label label2 = new Label();
	Label label3 = new Label();
	Button speicherButton = new Button("Speichern");
	Button abbrechenButton = new Button("Abbrechen");
	

	int referenzId = 3;
	String type ="";
	String angabe= "";
	String bezeichnung = "";
	
	

public CreateEigenschaft(String type){ 
	this.type = type;
	run();
	
	
}

	protected void run() {
		
		setText("Eigenschaften hinzufuegen");
		
		eigenschaften();
		
		speichern();
		
		abbrechen();
		
		
		
	}
	

		public void eigenschaften(){
			
			// Enable animation.
		      setAnimationEnabled(true);
		      
		      // Enable glass background.
		      setGlassEnabled(true);
		      
		verPanel.add(listBox);	
		listBox.addItem("Waehlen Sie ...");
		listBox.addItem("Bildungsabschluss (z.B. Bachelor of ..)");
		listBox.addItem("Wohnort");	
		listBox.addItem("Geburtsdatum");
		listBox.addItem("Berufserfahrung in Jahren");
			
			
		change();	
		
		verPanel.add(verPanel2);
		verPanel.add(buttonPanel);
				
		setWidget(verPanel);
	
			
				
			}
		
	
	public void change(){
		listBox.addChangeHandler(new ChangeHandler(){

			@Override
			public void onChange(ChangeEvent event) {
				
				String value =	listBox.getSelectedItemText();
				
				if(value == "Wohnort"){
					
					verPanel2.clear();
					textBox.setText("");
				
					label.setText("Stadt");
					verPanel2.add(label);
					verPanel2.add(textBox);
					
					
				}
				
				else if(value == "Bildungsabschluss (z.B. Bachelor of ..)"){
					
					verPanel2.clear();
					textBox.setText("");
					
					label.setText("Bildungsabschluss");
					verPanel2.add(label);
					verPanel2.add(textBox);
					
					
					
					
				}
				
				else if(value == "Geburtsdatum"){
					
					verPanel2.clear();
					textBox.setText("");
					
					label.setText("Geburtsdatum");
					verPanel2.add(label);
					verPanel2.add(textBox);
				
					
				}
				
				else if(value == "Berufserfahrung in Jahren"){
					
					verPanel2.clear();
					textBox.setText("");
					
					label.setText("Berufserfahrung");
					verPanel2.add(label);
					verPanel2.add(textBox);
				}
				
			}
			
			
		});
	}
	
	
	
	public void speichern(){
		
		buttonPanel.add(speicherButton);
		speicherButton.addClickHandler(new SpeichernHandler (this))
		
		;}
	
	public void abbrechen(){
		
		buttonPanel.add(abbrechenButton);
		abbrechenButton.addClickHandler(new AbbrechenHandler(this));
	}
	
	class AbbrechenHandler implements ClickHandler{
		
		private CreateEigenschaft c = null;
		
		public AbbrechenHandler (CreateEigenschaft createEigenschaft) {
			this.c = createEigenschaft;
		}

		@Override
		public void onClick(ClickEvent event) {
			
			c.hide();
			  
			
		}
		
		
	}
	
	class SpeichernHandler implements ClickHandler {
		
		private CreateEigenschaft c = null;
		
		public SpeichernHandler(CreateEigenschaft createEigenschaft) {
			this.c = createEigenschaft;
		}

		@Override
		public void onClick(ClickEvent event) {
			
			bezeichnung = label.getText();
			angabe = textBox.getText();
						
			anlegen();
			
			
			c.hide();
			
			ShowDefinition showEigenschaft = new ShowEigenschaften();
			RootPanel.get("Details").clear();
			RootPanel.get("Details").add(showEigenschaft);  
			
			
			
			
			
		}
		
	
	
	}
	
	public void anlegen(){
		
		//Anfragen der MyJobAdministration
		AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();
		
		verwaltung.anlegenEigenschaft(referenzId, bezeichnung, type, angabe, new AsyncCallback<Eigenschaft>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Eigenschaft result) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
	}
			
		
			
	//	}
		
		
	}


