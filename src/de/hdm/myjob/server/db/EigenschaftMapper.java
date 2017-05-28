package de.hdm.myjob.server.db;

import java.sql.*;
import java.util.Vector;

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
	  
	  public Eigenschaft anlegenEigenschaft(Eigenschaft eigenschaft, int referenzId){
			Connection con= DBConnection.connection();
			
			try{
				Statement stmt= con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT MAX(eigenschaftid) AS maxid "
				          + "FROM eigenschaft ");
				
				if(rs.next()){
					
					/*
					 * EigenschaftId des Objekts wird gesetzt und dabei um 1 erhöht
					 */
					eigenschaft.setId(rs.getInt("maxid")+1);
					
					stmt = con.createStatement();
					
					/*
					 * Einfügeoperation
					 */
					stmt.executeUpdate("INSERT INTO eigenschaft (eigenschaftid,referenzrid,eigenschaftsbezeichnung,angabe,referenztyp) "
					 + "VALUES (" + eigenschaft.getId() + "," + referenzId + ",'" + eigenschaft.getBezeichnung() + "','" + eigenschaft.getAngabe() + "','" + eigenschaft.getType() + "')");
					
				}
			}
			
			catch (SQLException e2){
				e2.printStackTrace();
			}
			
			return eigenschaft;
		}
	  
	  public Vector<Eigenschaft> getAllEigenschaften(){
			Connection con = DBConnection.connection();
			
			Vector<Eigenschaft> result = new Vector<Eigenschaft>();
			
			try{
				
				Statement stmt = con.createStatement();
				
				ResultSet rs = stmt.executeQuery("SELECT * FROM eigenschaft");
				
				while (rs.next()){
					Eigenschaft eigenschaft = new Eigenschaft();
					
					eigenschaft.setId(rs.getInt("eigenschaftid"));
					eigenschaft.setBezeichnung(rs.getString("bezeichnung"));
					
					result.add(eigenschaft);
				}
				
				
			}catch(SQLException e2){
				
				e2.printStackTrace();
				return null;
			}
			
			return result;
		}

	  

}
