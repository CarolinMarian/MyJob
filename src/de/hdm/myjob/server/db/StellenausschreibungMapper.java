package de.hdm.myjob.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.myjob.shared.bo.Benutzer;
import de.hdm.myjob.shared.bo.Profil;
import de.hdm.myjob.shared.bo.Stellenausschreibung;

/**
 * Diese Klasse stellt die Verbindung zwischen der Klasse Stellenausschreibung
 * und der Datenbank dar.
 * 
 * @author Nina
 *
 */
public class StellenausschreibungMapper {

	// statische Variable
	private static StellenausschreibungMapper stellenausschreibungMapper = null;

	// Geschützter Konstruktor
	protected StellenausschreibungMapper() {
	}

	// Statische Methode die Singleton Eigenschaft sicherstellt
	public static StellenausschreibungMapper stellenausschreibungMapper() {
		if (stellenausschreibungMapper == null) {
			stellenausschreibungMapper = new StellenausschreibungMapper();
		}
		return stellenausschreibungMapper;
	}

	// Neue Stellenausschreibung erstellen
	public Stellenausschreibung insertStellenausschreibung(Stellenausschreibung s, Benutzer nutzerid) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(stellenid) AS maxid " + "FROM stellenausschreibung");

			if (rs.next()) {

				/*
				 * StellenbeschreibungId des Objekts wird gesetzt und dabei um 1
				 * erhoeht
				 */
				s.setStellenId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				/*
				 * Einfuegeoperation
				 */
				stmt.executeUpdate(
						"INSERT INTO stellenausschreibung (stellenid,benutzerid,bezeichnung,beschreibung,frist) "
								+ "VALUES (" + s.getStellenId() + "," + nutzerid.getId() + ",'"
								+ s.getBezeichnung() + "','" + s.getBeschreibungstext() + "','" + s.getFrist() + "')");
			}
		}

		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return s;
	}

	// Bestehende Stellenausschreibung verändern
	public Stellenausschreibung updateStellenausschreibung(Stellenausschreibung stellenausschreibung, int nutzerid) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE stellenausschreibung SET bezeichnung=\"" + stellenausschreibung.getBezeichnung()
					+ "\", " + " beschreibung=\"" + stellenausschreibung.getBeschreibungstext() + "\", " + " frist=\""
					+ stellenausschreibung.getFrist() + "\" WHERE stellenid=" + stellenausschreibung.getStellenId());
		}

		catch (SQLException e2) {
			e2.printStackTrace();
		}

		return stellenausschreibung;
	}

	// Bestehende Stellenausschreibung löschen
	public void deleteStellenausschreibung(Stellenausschreibung stellenausschreibung) {
		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();

			stmt.executeUpdate(
					"DELETE FROM stellenausschreibung WHERE stellenid=" + stellenausschreibung.getStellenId());
		}

		catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	// Ausgabe aller Stellenbeschreibungen zu einer bestimmten Id (BenutzerId)
	public Vector<Stellenausschreibung> getStellenbeschreibungById(int benutzerid) {
		Connection con = DBConnection.connection();
		Vector<Stellenausschreibung> result = new Vector<Stellenausschreibung>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM stellenausschreibung WHERE benutzerid= " + benutzerid);

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

	// Ausgabe aller Stellenbeschreibungen zu einer bestimmten StellenId
	public Stellenausschreibung getStellenbeschreibungByStellenId(int stellenid) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM stellenausschreibung WHERE stellenid= " + stellenid);

			if (rs.next()) {
				Stellenausschreibung stellenausschreibung = new Stellenausschreibung();
				stellenausschreibung.setStellenId(rs.getInt("stellenid"));
				stellenausschreibung.setBezeichnug(rs.getString("bezeichnung"));
				stellenausschreibung.setBeschreibungstext(rs.getString("beschreibung"));
				stellenausschreibung.setFrist(rs.getDate("frist"));

				return stellenausschreibung;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return null;
	}

	// Ausgabe aller Stellenbeschreibungen
	public Vector<Stellenausschreibung> getAllStellenausschreibungen() {

		Connection con = DBConnection.connection();

		Vector<Stellenausschreibung> result = new Vector<Stellenausschreibung>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM stellenausschreibung");

			while (rs.next()) {
				Stellenausschreibung stellenausschreibung = new Stellenausschreibung();

				stellenausschreibung.setStellenId(rs.getInt("stellenid"));
				stellenausschreibung.setBezeichnug(rs.getString("bezeichnung"));
				stellenausschreibung.setBeschreibungstext(rs.getString("beschreibung"));
				stellenausschreibung.setFrist(rs.getDate("frist"));

				result.add(stellenausschreibung);
			}

		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return result;
	}

}
