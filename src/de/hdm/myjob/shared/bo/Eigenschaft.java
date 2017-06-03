package de.hdm.myjob.shared.bo;

public class Eigenschaft extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Bezeichnung der Eigenschaft	
	 */
	private String bezeichnung="";
	
	private String angabe="";
	
	private String type="";
	

	/**
	 * No Argument Constructor
	 */
	public Eigenschaft(){
		
		super();
	}

	
	/**
	 * Auslesen der Bezeichnung
	 */
	
	public String getBezeichnung(){
		return bezeichnung;
	}
	
	/**
	 * Setzen der Bezeichnung
	 */

	public void setBezeichnung(String bezeichnung){
		this.bezeichnung= bezeichnung;
	}
	
	public String getAngabe(){
		
		return angabe;
	}
	
	public void setAngabe(String angabe){
		
		this.angabe = angabe;
	}

	public String getType(){
		
		return type;
	}
	
	public void setType(String type){
		
		this.type = type;
	}
	  /**
	   * Erzeugen einer einfachen textuellen Darstellung der jeweiligen Instanz.
	   * Diese besteht aus dem Text, der durch die <code>toString()</code>-Methode
	   * der Superklasse erzeugt wird, ergänzt durch den Vor- und Nachnamen des 
	   * jeweiligen Kontakts.
	   */
	  @Override
	public String toString() {
	    return super.toString() + " " + this.bezeichnung;
	  }
	  

	  /**
	   * <p>
	   * Feststellen der <em>inhaltlichen</em> Gleichheit zweier Kontakt-Objekte.
	   * Die Gleichheit wird in diesem Beispiel auf eine identische Kontaktnummer
	   * beschränkt.
	   * </p>
	   */
	  @Override
	public boolean equals(Object o) {
	    /*
	     * Abfragen, ob ein Objekt ungl. NULL ist und ob ein Objekt gecastet werden
	     * kann, sind immer wichtig!
	     */
	    if (o != null && o instanceof Eigenschaft) {
	      Eigenschaft eig = (Eigenschaft) o;
	      try {
	        return super.equals(eig);
	      }
	      catch (IllegalArgumentException e) {
	        return false;
	      }
	    }
	    return false;
	  }
	
	
	
	

}