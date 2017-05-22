package de.hdm.myjob.server.db;

import java.sql.*;

import de.hdm.myjob.server.db.DBConnection;
import de.hdm.myjob.shared.bo.Eigenschaft;

public class EigenschaftMapper {
	
	  /**
	   * SINGELTON-Eigenschaft sicherstellen
	   */
	  private static EigenschaftMapper eigenschaftMapper = null;
	  
	  protected EigenschaftMapper() {
	  }
	
	  public static EigenschaftMapper eigenschaftMapper() {
	    if (eigenschaftMapper == null) {
	      eigenschaftMapper = new EigenschaftMapper();
	    }

	    return eigenschaftMapper;
	  }
	  

	  /**
	   * Reine Testzwecke
	   */
	  public Eigenschaft findByKey(int id) {
	    // DB-Verbindung holen
	    Connection con = DBConnection.connection();

	    try {
	      // Leeres SQL-Statement (JDBC) anlegen
	    	if(con != null) {
	    		 Statement stmt = con.createStatement();
	   	      // Statement ausfüllen und als Query an die DB schicken
	   	      ResultSet rs = stmt
	   	          .executeQuery("SELECT eigenschaftid, bezeichnung"
	   	          		+ " FROM eigenschaft WHERE eigenschaftid=" + id);

	   	      /*
	   	       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
	   	       * werden. Prüfe, ob ein Ergebnis vorliegt.
	   	       */
	   	      if (rs.next()) {
	   	        // Ergebnis-Tupel in Objekt umwandeln
	   	        Eigenschaft eig = new Eigenschaft();
	   	        eig.setId(rs.getInt("eigenschaftid"));
	   	        eig.setBezeichnung(rs.getString("bezeichnung"));	   	        

	   	        	
	   	        return eig;
	   	      }
	    		}
	    	else{
	    		System.out.println("ok es gibt no connection");
	    	}
	    	
	     


	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	      return null;
	    }

	    return null;
	  }

	  

}
