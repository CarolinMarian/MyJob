package de.hdm.myjob.shared.bo;

public class Profil extends BusinessObject {
	
	 private static final long serialVersionUID = 1L;
	
	 /**
	  * Benutzerid (Fremdschlüssel)
	  */
	 private int benutzerId = 0;
	 
	 /**
	  * Auslesen der BenutzerId
	  */
	public int getBenutzerId() {
		return this.benutzerId;
	}
	
	/**
	 * Setzen der BenutzerId
	 * @param benutzerId
	 */

	public void setBenutzerId(int benutzerId) {
		this.benutzerId = benutzerId;
		
	}
	 
	 
	 
	  /**
	   * No-Argument Konstuktor
	   */
	  public Profil(){
		  super();
	  }
	  
	  /**
	   * Erzeugen einer einfachen textuellen Darstellung der jeweiligen Instanz.
	   * Diese besteht aus dem Text, der durch die <code>toString()</code>-Methode
	   * der Superklasse erzeugt wird, ergänzt durch den Vor- und Nachnamen des 
	   * jeweiligen Kontakts.
	   */
//	  @Override
//	public String toString() {
//	    return super.toString() + "  " + this.lastName + " " + this.emailadresse + " " + this.telefonnummer;
//	  return "noch anpassen";
//	  }
	  

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
	    if (o != null && o instanceof Profil) {
	      Profil p = (Profil) o;
	      try {
	        return super.equals(p);
	      }
	      catch (IllegalArgumentException e) {
	        return false;
	      }
	    }
	    return false;
	  }


}
