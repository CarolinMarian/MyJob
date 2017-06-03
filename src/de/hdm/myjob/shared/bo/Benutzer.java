package de.hdm.myjob.shared.bo;

public class Benutzer extends BusinessObject {
	
	 private static final long serialVersionUID = 1L;
	 
	  /**
	   * No-Argument Konstuktor
	   */
	  public Benutzer(){
		  super();
	  }
	  
	  /**
	   *  Emailadresse
	   */
	  private String email = "";
	  
	  /**
	   * Vorname
	   */
	  private String firstName = "";
	  
	  /**
	   * Nachname
	   */
	  private String lastName = "";
	  
	  
	  /**
		 * Auslesen der Emil
		 */
		
		public String getEmail(){
			return email;
		}
		
		/**
		 * Setzen der Email
		 */

		public void setEmail(String email){
			this.email= email;
		}
		
		
		/**
		 * Auslesen des Vornamen
		 */
		
		public String getFirstName(){
			return firstName;
		}
		
		/**
		 * Setzen der Bezeichnung
		 */

		public void setFirstName(String name){
			this.firstName= name;
		}
		
		
		/**
		 * Auslesen des Nachnamens
		 */
		
		public String getLastName(){
			return lastName;
		}
		
		/**
		 * Setzen der Bezeichnung
		 */

		public void setLastName(String name){
			this.lastName= name;
		}
	  
	  /**
	   * Erzeugen einer einfachen textuellen Darstellung der jeweiligen Instanz.
	   * Diese besteht aus dem Text, der durch die <code>toString()</code>-Methode
	   * der Superklasse erzeugt wird, ergänzt durch den Vor- und Nachnamen des 
	   * jeweiligen Kontakts.
	   */
	  @Override
	public String toString() {
		  return super.toString() + "  " + this.lastName + " " + this.firstName + " " + this.email;
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
	    if (o != null && o instanceof Benutzer) {
	      Benutzer b = (Benutzer) o;
	      try {
	        return super.equals(b);
	      }
	      catch (IllegalArgumentException e) {
	        return false;
	      }
	    }
	    return false;
	  }


}
