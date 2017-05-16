package de.hdm.myjob.shared.bo;

public class Kontakt extends BusinessObject {
	
	 private static final long serialVersionUID = 1L;

	  /**
	   * Der Vorname des Kontakts.
	   */
	  private String firstName = "";
	  
	  /**
	   * Der Nachname des Kontakts
	   */
	  private String lastName = "";
	  
	  /**
	   * Die Emailadresse des Kontakts.
	   */
	  private String emailadresse = "";
	  
	  /**
	   * Die Telefonnummer des Kontakts
	   */
	  private int telefonnummer = 0;
	  
	  /**
	   * No-Argument Konstuktor
	   */
	  public Kontakt(){
		  super();
	  }
	  
	  
	  /**
	   * Auslesen des Vornamens.
	   */
	  public String getFirstName() {
	    return this.firstName;
	  }

	  /**
	   * Setzen des Vornamens.
	   */
	  public void setFirstName(String name) {
	    this.firstName = name;
	  }

	  /**
	   * Auslesen des Nachnamens.
	   */
	  public String getLastName() {
	    return this.lastName;
	  }

	  /**
	   * Setzen des Nachnamens.
	   */
	  public void setLastName(String name) {
	    this.lastName = name;
	  }
	  
	  /**
	   * Auslesen der Emailadresse.
	   */
	  public String getEmailadresse() {
	    return this.emailadresse;
	  }

	  /**
	   * Setzen der Emailadresse.
	   */
	  public void setEmailadresse(String emailadresse) {
	    this.emailadresse = emailadresse;
	  }
	  
	  /**
	   * Auslesen der Telefonnummer.
	   */
	  public int getTelefonnummer() {
	    return this.telefonnummer;
	  }

	  /**
	   * Setzen der Telefonnummer.
	   */
	  public void setTelefonnummer(int telefonnummer) {
	    this.telefonnummer = telefonnummer;
	  }

	  /**
	   * Erzeugen einer einfachen textuellen Darstellung der jeweiligen Instanz.
	   * Diese besteht aus dem Text, der durch die <code>toString()</code>-Methode
	   * der Superklasse erzeugt wird, ergänzt durch den Vor- und Nachnamen des 
	   * jeweiligen Kontakts.
	   */
	  @Override
	public String toString() {
	    return super.toString() + " " + this.firstName + " " + this.lastName + " " + this.emailadresse + " " + this.telefonnummer;
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
	    if (o != null && o instanceof Kontakt) {
	      Kontakt k = (Kontakt) o;
	      try {
	        return super.equals(k);
	      }
	      catch (IllegalArgumentException e) {
	        return false;
	      }
	    }
	    return false;
	  }


}
