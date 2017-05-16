package de.hdm.myjob.server.db;

public class BenutzerMapper {
	
	/**
	 * SINGELTON-Eigenschaft sicherstellen
	 */
	
	public static BenutzerMapper benutzerMapper = null;
	
	protected BenutzerMapper(){
		
	}
	
	public static BenutzerMapper benutzerMapper() {
	    if (benutzerMapper == null) {
	    	benutzerMapper = new BenutzerMapper();
	    }

	    return benutzerMapper;
	  }

}
