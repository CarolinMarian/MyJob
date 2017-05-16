package de.hdm.myjob.server.db;

public class StellenausschreibungMapper {
	
	/**
	 * SINGELTON-Eigenschaft sicherstellen
	 */
	
	public static StellenausschreibungMapper stellenausschreibungMapper = null;
	
	protected StellenausschreibungMapper(){
		
	}
	
	public static StellenausschreibungMapper stellenbeschreibungMapper() {
	    if (stellenausschreibungMapper == null) {
	    	stellenausschreibungMapper = new StellenausschreibungMapper();
	    }

	    return stellenausschreibungMapper;
	  }

}
