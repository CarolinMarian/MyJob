package de.hdm.myjob.server.db;

import java.util.Vector;

import de.hdm.myjob.shared.bo.Inhalt;
import de.hdm.myjob.shared.bo.Profil;
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
	
	/**
	 * DB-Zugriff Methoden
	 */
	
	/**
	 * Gibt alle Inhalte eines Profils zurück
	 * @param c
	 * @return
	 */
	  public Vector<Inhalt> getInhalteOf(Profil p) {

	    return InhaltMapper.inhaltMapper().findByProfil(p);
	  }

}
