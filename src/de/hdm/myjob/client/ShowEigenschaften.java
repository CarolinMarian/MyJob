package de.hdm.myjob.client;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;

import de.hdm.myjob.shared.AdministrationAsync;
import de.hdm.myjob.shared.bo.Benutzer;
import de.hdm.myjob.shared.bo.Eigenschaft;
import de.hdm.myjob.shared.bo.Inhalt;

public class ShowEigenschaften extends ShowDefinition {

	@Override
	protected String getHeadlineText() {
		String headline = "Eigenschaften";
		return headline;
	}

	@Override
	protected void run() {
		
		AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();
		
	//	verwaltung.getProfilFor(1, new ProfilCallback(this));
		
	}
	
	
	
	class BenutzerCallback implements AsyncCallback<Benutzer> {
		
		private ShowDefinition showdef = null;
		
		
		public BenutzerCallback (ShowDefinition s){
			this.showdef = s;
		}
		
			
		@Override
		public void onFailure(Throwable caught) {
			this.showdef.append("Kein Profil vorhanden");
			 this.showdef.append("Fehler bei der Abfrage " + caught.getMessage());
			
		}

		@Override
		public void onSuccess(Benutzer result) {
			
			if (result == null){
				this.showdef.append("No Profil");
			}
			else {
				AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();
				
		//		verwaltung.getInhaltFor(result, new InhalteCallback(this.showdef));
			}
			
			

			
		}

		
	}
	
	
	
	
	
	class InhalteCallback implements AsyncCallback<Vector<Inhalt>> {
		
		private ShowDefinition showdef = null;
		
		
		public InhalteCallback (ShowDefinition s){
			this.showdef = s;
		}
		
			
		@Override
		public void onFailure(Throwable caught) {
			
			 this.showdef.append("Fehler bei der Abfrage " + caught.getMessage());
			
		}

		@Override
		public void onSuccess(Vector<Inhalt> result) {
			
			AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();
			

//			//Celltable --> Wie hlassen sich die Eigenschaftsbezeichnungen hier einfügen?
//			
//			List<Inhalt> INHALTE = result;
//			
//			
//			CellTable<Inhalt> table = new CellTable<Inhalt>();
//			
//			TextColumn<Inhalt> eigColumn = new TextColumn<Inhalt>() {
//
//				@Override
//				public String getValue(Inhalt inhalt) {
//					String s = String.valueOf(inhalt.getEigenschaftsId());
//					return s;
//				}
//				
//			};
//			
//			
//			TextColumn<Inhalt> inColumn = new TextColumn<Inhalt>() {
//
//				@Override
//				public String getValue(Inhalt inhalt) {
//					return inhalt.getAngabe();
//				}
//				
//			};
//			this.showdef.add(table);
//		    table.addColumn(eigColumn, "Eigenschaft");
//		    table.addColumn(inColumn, "Angabe");
//			
//		    ListDataProvider<Inhalt> dataProvider = new ListDataProvider<Inhalt>();
//		    dataProvider.addDataDisplay(table);
//		    
//		    List<Inhalt> list = dataProvider.getList();
//		    for (Inhalt i : INHALTE) {
//		      list.add(i);
//		    }
//			
//			
			
		
			//  Flextable zur Anzeige der Eigenschaftten
		    FlexTable t = new FlexTable();
		    
		    t.addStyleName("FlexTableShow");
			
			t.setText(0, 0, "Eigenschaft");
			t.setText(0, 1, "Angabe");
			
			this.showdef.add(t);
			
			int tCount = 1;
			for (Inhalt i : result){
				
				verwaltung.getEigenschaftById(i.getEigenschaftsId(), new EigenschaftCallback(this.showdef, i, t, tCount));
				
				tCount = tCount +1;
			}
			

			
		}
		
	}
	
	
	
	

	
	class EigenschaftCallback implements AsyncCallback<Eigenschaft> {
		
		private ShowDefinition showdef = null;
		private Inhalt inhalt = null;
		private FlexTable table = null;
		private int count = 0;
		
		
		public EigenschaftCallback (ShowDefinition s, Inhalt i, FlexTable t, int tCount){
			this.showdef = s;
			this.inhalt = i;
			this.table = t;
			this.count = tCount;
			
		}
		
			
		@Override
		public void onFailure(Throwable caught) {
			
			 this.showdef.append("Fehler bei der Abfrage " + caught.getMessage());
			
		}

		@Override
		public void onSuccess(Eigenschaft eigenschaft) {

			//this.showdef.append(eigenschaft.getBezeichnung() + ": " + inhalt.getAngabe());	
			
			//Flextable
			
			table.setText(count, 0, eigenschaft.getBezeichnung());
			table.setText(count, 1, inhalt.getAngabe());

		}
		
	}


	
	


}
