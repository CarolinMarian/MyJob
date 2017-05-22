package de.hdm.myjob.shared.bo;

public class Inhalt extends BusinessObject {
	
	 private static final long serialVersionUID = 1L;
	 
	 /**
		 * Fremdschlüsselbeziehung zu Eigenschaft
		 */
		private int eigenschaftsId=0;
		
		/**
		 * Fremdschlüsselbeziehung zu Profil
		 */
		private int profilId=0;
		
		/**
		 * Angabe des Users
		 */
		
		private String angabe="";
		
		/**
		 * Auslesen des Fremschslüssels zu Eigenschaft
		 */
		
		public int getEigenschaftsId(){
			return this.eigenschaftsId;
			
		}
		
		/**
		 * Setzen des Fremdschlüssels zu Eigenschaft
		 */
		public void setEigenschaftsId(int eigenschaftsId){
			this.eigenschaftsId=eigenschaftsId;
		}
		
		/**
		 * Auslesen des Fremdschlüssels zu Profil
		 */
		
		public int getProfilId(){
			return this.profilId;
		}
		
		/**
		 * Setzen des Fremdschlüssels zu Profil
		 */
		public void setProfilId(int profilId){
			this.profilId=profilId;
		}
		
		/**
		 * Auslesen der Angabe
		 */
		
		public String getAngabe(){
			return angabe;
			
		}
		
		/**
		 * Setzen der Angabe
		 */
		public void setAngabe(String angabe){
			this.angabe = angabe;
		}
	
	  /**
	   * No-Argument Konstuktor
	   */
	  public Inhalt(){
		  super();
	  }
	  
	  /**
	   * Erzeugen einer einfachen textuellen Darstellung der jeweiligen Instanz.
	   * Diese besteht aus dem Text, der durch die <code>toString()</code>-Methode
	   * der Superklasse erzeugt wird, ergänzt durch den Vor- und Nachnamen des 
	   * jeweiligen Kontakts.
	   */
	public String toString() {
	  return super.toString() + "  " + this.eigenschaftsId + " " + this.profilId + " " + this.angabe;
//	  return "noch anpassen";
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
	    if (o != null && o instanceof Inhalt) {
	      Inhalt i = (Inhalt) o;
	      try {
	        return super.equals(i);
	      }
	      catch (IllegalArgumentException e) {
	        return false;
	      }
	    }
	    return false;
	  }


}
