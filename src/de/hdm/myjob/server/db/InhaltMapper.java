package de.hdm.myjob.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.myjob.shared.bo.Inhalt;
import de.hdm.myjob.shared.bo.Profil;

public class InhaltMapper {

	/**
	 * SINGELTON-Eigenschaft sicherstellen
	 */
	
	public static InhaltMapper inhaltMapper = null;
	
	protected InhaltMapper(){
		
	}
	
	public static InhaltMapper inhaltMapper() {
	    if (inhaltMapper == null) {
	    	inhaltMapper = new InhaltMapper();
	    }

	    return inhaltMapper;
	  }

	
	/**
	 * Gibt alle Inhalte für ein Profil zurück
	 * @param p
	 * @return
	 */
	public Vector<Inhalt> findByProfil(Profil p) {
		// TODO Auto-generated method stub
		return findByProfil(p.getId());
	}
	
	/**
	 * GIbt alle Inhalte für eine Profilid zurück
	 * @param id
	 * @return
	 */
	private Vector<Inhalt> findByProfil(int id) {
		
		Connection con = DBConnection.connection();
	    Vector<Inhalt> result = new Vector<Inhalt>();
	    
	    try {
	        Statement stmt = con.createStatement();

	        ResultSet rs = stmt.executeQuery("SELECT profilid, eigenschaftid, angabe FROM inhalt "
	            + "WHERE profilid=" + id + " ORDER BY eigenschaftid");

	        while (rs.next()) {
	        	Inhalt i = new Inhalt();
	          i.setProfilId(rs.getInt("profilid"));
	          i.setEigenschaftsId(rs.getInt("eigenschaftid"));
	          i.setAngabe(rs.getString("angabe"));

	          // Hinzufügen des neuen Objekts zum Ergebnisvektor
	          result.addElement(i);
	        }
	      }
	      catch (SQLException e2) {
	        e2.printStackTrace();
	      }

		return result;
	}
}
