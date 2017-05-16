package de.hdm.myjob.server.db;

public class BewerbungMapper {
	
	/**
	 * SINGELTON-Eigenschaft sicherstellen
	 */
	
	public static BewerbungMapper bewerbungMapper = null;
	
	protected BewerbungMapper(){
		
	}
	
	public static BewerbungMapper bewerbungMapper() {
	    if (bewerbungMapper == null) {
	      bewerbungMapper = new BewerbungMapper();
	    }

	    return bewerbungMapper;
	  }

}
