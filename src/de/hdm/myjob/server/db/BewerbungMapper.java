package de.hdm.myjob.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.myjob.shared.bo.Stellenausschreibung;

public class BewerbungMapper {

	/**
	 * SINGELTON-Eigenschaft sicherstellen
	 */

	public static BewerbungMapper bewerbungMapper = null;

	protected BewerbungMapper() {

	}

	public static BewerbungMapper bewerbungMapper() {
		if (bewerbungMapper == null) {
			bewerbungMapper = new BewerbungMapper();
		}

		return bewerbungMapper;
	}

	// Neue Bewerbung erstellen
	public void insertBewerbung(int stellenid, int nutzerid) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt = con.createStatement();

			/*
			 * Einfuegeoperation
			 */
			stmt.executeUpdate(
					"INSERT INTO bewerbung (stellenid,benutzerid) " + "VALUES (" + stellenid + "," + nutzerid + ")");
		}

		catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	// Ausgabe aller Bewerbungen zu einer bestimmten BenutzerId
	public Vector<Stellenausschreibung> getBewerbungById(int nutzerid) {
		Connection con = DBConnection.connection();
		Vector<Stellenausschreibung> result = new Vector<Stellenausschreibung>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT * FROM bewerbung INNER JOIN stellenausschreibung ON bewerbung.stellenid = stellenausschreibung.stellenid WHERE bewerbung.benutzerid= "
							+ nutzerid);

			while (rs.next()) {
				Stellenausschreibung stellenausschreibung = new Stellenausschreibung();
				stellenausschreibung.setStellenId(rs.getInt("stellenid"));
				stellenausschreibung.setBezeichnug(rs.getString("bezeichnung"));
				stellenausschreibung.setBeschreibungstext(rs.getString("beschreibung"));
				stellenausschreibung.setFrist(rs.getDate("frist"));

				// Hinzufuegen des neuen Objekts zum Ergebnisvektor
				result.add(stellenausschreibung);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	public void deleteBewerbung(int stellenid) {
		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM bewerbung WHERE stellenid=" + stellenid);
		}

		catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

}
