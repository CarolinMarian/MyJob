package de.hdm.myjob.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	 * Gibt alle Inhalte eines Profils zur�ck
	 * @param c
	 * @return
	 */
	  public Vector<Inhalt> getInhalteOf(Profil p) {

	    return InhaltMapper.inhaltMapper().findByProfil(p);
	  }
	  
	
	  /**
	   * Gibt ein Profil f�r eine ID zur�ck
	   * @param id
	   * @return
	   */
	  public Profil getProfilById(int id){
		  
		  // DB-Verbindung holen
		    Connection con = DBConnection.connection();

		    try {
		      // Leeres SQL-Statement (JDBC) anlegen
		    	Statement stmt = con.createStatement();
		   	      // Statement ausf�llen und als Query an die DB schicken
		   	      ResultSet rs = stmt
		   	          .executeQuery("SELECT * "
		   	          		+ " FROM profil WHERE profilid=" + id);

		   	      /*
		   	       * Da id Prim�rschl�ssel ist, kann max. nur ein Tupel zur�ckgegeben
		   	       * werden. Pr�fe, ob ein Ergebnis vorliegt.
		   	       */
		   	      if (rs.next()) {
		   	        // Ergebnis-Tupel in Objekt umwandeln
		   	        Profil p = new Profil();
		   	        p.setId(rs.getInt("profilid"));
		   	        
		   	        return p;
		   	      }

		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		      return null;
		    }
 
		return null;
	  }

	  
	  
	  /**
	   * L�schen der Daten eines Profil-Objekts aus der Datenbank.
	   * 
	   * @param a das aus der DB zu l�schende "Objekt"
	   */
	  public void delete(Profil p) {
	    Connection con = DBConnection.connection();

	    try {
	      Statement stmt = con.createStatement();

	      stmt.executeUpdate("DELETE FROM inhalt " + "WHERE profilid=" + p.getId());
	      stmt.executeUpdate("DELETE FROM stellenausschreibung " + "WHERE profilid=" + p.getId());
	      stmt.executeUpdate("DELETE FROM profil " + "WHERE profilid=" + p.getId());

	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }
	  }
	  
	  
	  
	  
}
