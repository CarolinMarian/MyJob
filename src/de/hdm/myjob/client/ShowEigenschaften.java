package de.hdm.myjob.client;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
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

		
		AdministrationAsync verwaltung = ClientsideSettings.getVerwaltung();
		
		verwaltung.findByBenutzer(3, new EigenschaftCallback(this));
		
	}
	
	class EigenschaftCallback implements AsyncCallback<Vector<Eigenschaft>> {
		
		ShowDefinition showdef = null;
		
		public EigenschaftCallback(ShowDefinition s){
			
			this.showdef=s;
		}
		
		FlexTable table = new FlexTable();
		
		
		
		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Eigenschaft> result) {
			int rowNum = table.getRowCount();
			
			for (Eigenschaft e : result){
				table.setText(rowNum, 0, e.getBezeichnung() + ":");
				table.setText(rowNum, 1, e.getAngabe());
				rowNum++;
				
			}
			
			this.showdef.add(table);
			
			Button eigenschaftenAnlegen = new Button("Eigenschaften anlegen");
			this.showdef.add(eigenschaftenAnlegen);
			
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
	
	

}
