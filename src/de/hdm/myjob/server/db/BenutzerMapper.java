package de.hdm.myjob.server.db;

import java.sql.Connection;
import java.sql.Statement;

import de.hdm.myjob.shared.bo.Benutzer;

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
