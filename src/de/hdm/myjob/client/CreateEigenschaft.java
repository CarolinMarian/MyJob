package de.hdm.myjob.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.myjob.shared.AdministrationAsync;
import de.hdm.myjob.shared.bo.Eigenschaft;
import de.hdm.myjob.shared.bo.Inhalt;

public class CreateEigenschaft extends ShowDefinition {

	@Override
	protected String getHeadlineText() {
		String headline = "Erstelle dein Profil";
		return headline;
	}

	@Override
	protected void run() {
		
		this.append("Anlegen eines Profils");
		
		//Anfragen der MyJobAdministration
		AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();
		
		verwaltung.getAllEigenschaften(new CreateEigenschaften(this));
		
	}
	
	class CreateEigenschaften implements AsyncCallback<Vector<Eigenschaft>>{
		
		private ShowDefinition showDef = null;
		
	public CreateEigenschaften(ShowDefinition d){
			this.showDef=d;
			
		}

		@Override
		public void onFailure(Throwable caught) {
			this.showDef.append("Fehler bei der Abfrage" + caught.getMessage());
			
		}

		@Override
		public void onSuccess(Vector<Eigenschaft> result) {
			HorizontalPanel horPanel = new HorizontalPanel();
			this.showDef.add(horPanel);
			
			if(result != null){
				
				CellTable<Eigenschaft> cellTable = new CellTable<Eigenschaft>();
				cellTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

				TextColumn<Eigenschaft> bezeichnungColumn = new TextColumn<Eigenschaft>(){
				
					@Override
					public String getValue(Eigenschaft eigenschaft) {
						
						return eigenschaft.getBezeichnung();
					}
					
					
				};
				
				TextInputCell eingabeCell = new TextInputCell();
				Column<Eigenschaft,String> eingabeColumn = new Column<Eigenschaft,String>(eingabeCell){

					@Override
					public String getValue(Eigenschaft object) {
						// TODO Auto-generated method stub
						return null;
					}
					
				};
				
				cellTable.addColumn(bezeichnungColumn, "Bezeichnung");
				cellTable.addColumn(eingabeColumn, "Eingabe");
				
				
				List<Eigenschaft> list = result;
				cellTable.setRowCount(list.size(), true);
				cellTable.setRowData(0, list);
				horPanel.add(cellTable);
				
				

				
				
			}
			
		}
		
		
	}

}
