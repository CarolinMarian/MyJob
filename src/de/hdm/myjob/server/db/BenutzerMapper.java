package de.hdm.myjob.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	
	/**
	 * 
	 * BEnutzer �ber ID auslesen
	   */
	  public Benutzer findByKey(int id) {
	    // DB-Verbindung holen
	    Connection con = DBConnection.connection();

	    try {
	      // Leeres SQL-Statement (JDBC) anlegen
	      Statement stmt = con.createStatement();

	      // Statement ausf�llen und als Query an die DB schicken
	      ResultSet rs = stmt
	          .executeQuery("SELECT * FROM benutzer "
	              + "WHERE benutzerid=" + id );

	      /*
	       * Da id Prim�rschl�ssel ist, kann max. nur ein Tupel zur�ckgegeben
	       * werden. Pr�fe, ob ein Ergebnis vorliegt.
	       */
	      if (rs.next()) {
	        // Ergebnis-Tupel in Objekt umwandeln
	        Benutzer b = new Benutzer();
	        b.setId(rs.getInt("benutzerid"));
	        b.setFirstName(rs.getString("vorname"));
	        b.setLastName(rs.getString("nachname"));
	        b.setEmail(rs.getString("email"));

	        return b;
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	      return null;
	    }

	    return null;
	  }
	
	  
	  /**
	   * BEnutzer einf�gen
	   */
	  
	  public Benutzer insertBenutzer(Benutzer b) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      /*
		       * Zun�chst schauen wir nach, welches der momentan h�chste
		       * Prim�rschl�sselwert ist.
		       */
		      ResultSet rs = stmt.executeQuery("SELECT MAX(benutzerid) AS maxid "
		          + "FROM benutzer ");

		      // Wenn wir etwas zur�ckerhalten, kann dies nur einzeilig sein
		      if (rs.next()) {
		        /*
		         * c erh�lt den bisher maximalen, nun um 1 inkrementierten
		         * Prim�rschl�ssel.
		         */
		       b.setId(rs.getInt("maxid") + 1);

		        stmt = con.createStatement();

		        // Jetzt erst erfolgt die tats�chliche Einf�geoperation
		        stmt.executeUpdate("INSERT INTO benutzer (benutzerid, email, vorname, nachname) "
		            + "VALUES (" + b.getId() + ",'" + b.getEmail() + "','"
		            + b.getFirstName() + "','" + b.getLastName() + "')");
		      }
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
		    
		    return b;
		  }



}
