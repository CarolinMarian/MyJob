package de.hdm.myjob.shared.report;

/**
 * <p>
 * Diese Klasse wird ben�tigt, um auf dem Client die ihm vom Server zur
 * Verf�gung gestellten <code>Report</code>-Objekte in ein menschenlesbares
 * Format zu �berf�hren.
 * </p>
 * <p>
 * Das Zielformat kann prinzipiell beliebig sein. Methoden zum Auslesen der in
 * das Zielformat �berf�hrten Information wird den Subklassen �berlassen. In
 * dieser Klasse werden die Signaturen der Methoden deklariert, die f�r die
 * Prozessierung der Quellinformation zust�ndig sind.
 * </p>
 * 
 * @author Thies
 */
public abstract class ReportWriter {
	
	  /**
	   * �bersetzen eines <code>AllInhalteOfProfilReport</code> in das
	   * Zielformat.
	   * 
	   * @param r der zu �bersetzende Report
	   */
	  public abstract void process(AllInhalteOfBenutzerReport r);
	  
	   /** 
	    * �bersetzen eines <code>AllInhalteOfProfilReport</code> in das
	   * Zielformat.
	   * 
	   * @param r der zu �bersetzende Report
	   */
	  public abstract void process(AllInhalteOfAllBenutzerReport r);
}

