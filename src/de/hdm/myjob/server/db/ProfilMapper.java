package de.hdm.myjob.server.db;

public class ProfilMapper {
	
	/**
	 * SINGELTON-Eigenschaft sicherstellen
	 */
	
	public static ProfilMapper profilMapper = null;
	
	protected ProfilMapper(){
		
	}
	
	public static ProfilMapper profilMapper() {
	    if (profilMapper == null) {
	    	profilMapper = new ProfilMapper();
	    }

	    return profilMapper;
	  }

}
