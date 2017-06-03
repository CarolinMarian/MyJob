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
	 * BEnutzer über ID auslesen
	   */
	  public Benutzer findByKey(int id) {
	    // DB-Verbindung holen
	    Connection con = DBConnection.connection();

	    try {
	      // Leeres SQL-Statement (JDBC) anlegen
	      Statement stmt = con.createStatement();

	      // Statement ausfüllen und als Query an die DB schicken
	      ResultSet rs = stmt
	          .executeQuery("SELECT * FROM benutzer "
	              + "WHERE benutzerid=" + id );

	      /*
	       * Da id Primärschlüssel ist, kann max. nur ein Tupel zurückgegeben
	       * werden. Prüfe, ob ein Ergebnis vorliegt.
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
	   * BEnutzer einfügen
	   */
	  
	  public Benutzer insertBenutzer(Benutzer b) {
		    Connection con = DBConnection.connection();

		    try {
		      Statement stmt = con.createStatement();

		      /*
		       * Zunächst schauen wir nach, welches der momentan höchste
		       * Primärschlüsselwert ist.
		       */
		      ResultSet rs = stmt.executeQuery("SELECT MAX(benutzerid) AS maxid "
		          + "FROM benutzer ");

		      // Wenn wir etwas zurückerhalten, kann dies nur einzeilig sein
		      if (rs.next()) {
		        /*
		         * c erhält den bisher maximalen, nun um 1 inkrementierten
		         * Primärschlüssel.
		         */
		       b.setId(rs.getInt("maxid") + 1);

		        stmt = con.createStatement();

		        // Jetzt erst erfolgt die tatsächliche Einfügeoperation
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
