package de.hdm.myjob.server.report;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.myjob.server.AdministrationImpl;
import de.hdm.myjob.shared.Administration;
import de.hdm.myjob.shared.ReportAdministration;
import de.hdm.myjob.shared.bo.Inhalt;
import de.hdm.myjob.shared.bo.Profil;
import de.hdm.myjob.shared.report.AllInhalteOfAllProfileReport;
import de.hdm.myjob.shared.report.AllInhalteOfProfilReport;
import de.hdm.myjob.shared.report.Column;
import de.hdm.myjob.shared.report.CompositeParagraph;
import de.hdm.myjob.shared.report.Row;
import de.hdm.myjob.shared.report.SimpleParagraph;

@SuppressWarnings("serial")
public class ReportAdministrationImpl extends RemoteServiceServlet implements ReportAdministration{
	
	  private Administration administration = null;
	  
	  public ReportAdministrationImpl() throws IllegalArgumentException {
		  
	  }
	  
	  public void init() throws IllegalArgumentException {
		   AdministrationImpl a = new AdministrationImpl();
		    a.init();
		    this.administration = a;
		  }
	  
	  protected Administration getVerwaltung() {
		    return this.administration;
		  }

	@Override
	public AllInhalteOfProfilReport createAllInhalteOfProfilReport(Profil p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (this.getVerwaltung() == null)
		      return null;
		
		AllInhalteOfProfilReport report = new AllInhalteOfProfilReport();
		
//		report.setTitle("Alle Ihalte Ihres Profils");
		
		//ADD IMPRINT?
		
		report.setCreated(new Date());
		
		CompositeParagraph header = new CompositeParagraph();

	    // Kundennummer aufnehmen
	    header.addSubParagraph(new SimpleParagraph("Profil-ID.: " + p.getId()));

	    // Hinzufügen der zusammengestellten Kopfdaten zu dem Report
	    report.setHeaderData(header);

	    /*
	     * Ab hier erfolgt ein zeilenweises Hinzufügen von Konto-Informationen.
	     */
	    
	    /*
	     * Zunächst legen wir eine Kopfzeile für die Konto-Tabelle an.
	     */
	    Row headline = new Row();

	    /*
	     * Wir wollen Zeilen mit 2 Spalten in der Tabelle erzeugen. In die erste
	     * Spalte schreiben wir die jeweilige Kontonummer und in die zweite den
	     * aktuellen Kontostand. In der Kopfzeile legen wir also entsprechende
	     * Überschriften ab.
	     */
	    headline.addColumn(new Column("Eigenschaft"));
	    headline.addColumn(new Column("Angabe"));

	    // Hinzufügen der Kopfzeile
	    report.addRow(headline);

	    /*
	     * Nun werden sämtliche Konten des Kunden ausgelesen und deren Kto.-Nr. und
	     * Kontostand sukzessive in die Tabelle eingetragen.
	     */
	    Vector<Inhalt> inhalte = this.administration.getInhaltFor(p);

	    for (Inhalt i : inhalte) {
	      // Eine leere Zeile anlegen.
	      Row inhalteRow = new Row();

	      // Erste Spalte: Eigenschaften
	      inhalteRow.addColumn(new Column(String.valueOf(i.getEigenschaftsId())));
	      
//	      inhalteRow.addColumn(new Column(String.valueOf(this.administration
//		          .getBalanceOf(a))));

	      // Zweite Spalte: Angaben
	      inhalteRow.addColumn(new Column(i.getAngabe()));

	      // und schließlich die Zeile dem Report hinzufügen.
	      report.addRow(inhalteRow);
	    }

	    /*
	     * Zum Schluss müssen wir noch den fertigen Report zurückgeben.
	     */
	    return report;
		
	}

	@Override
	public AllInhalteOfAllProfileReport createAllInhalteOfAllProfileReport() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	  

}
