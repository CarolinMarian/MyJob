package de.hdm.myjob.server;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.myjob.server.db.BenutzerMapper;
import de.hdm.myjob.server.db.EigenschaftMapper;
import de.hdm.myjob.server.db.InhaltMapper;
import de.hdm.myjob.server.db.StellenausschreibungMapper;
import de.hdm.myjob.shared.Administration;
import de.hdm.myjob.shared.bo.Benutzer;
import de.hdm.myjob.shared.bo.Eigenschaft;
import de.hdm.myjob.shared.bo.Inhalt;
import de.hdm.myjob.shared.bo.Profil;
import de.hdm.myjob.shared.bo.Stellenausschreibung;

@SuppressWarnings("serial")
public class AdministrationImpl extends RemoteServiceServlet implements Administration {

	private BenutzerMapper benutzerMapper = null;
	private EigenschaftMapper eigenschaftMapper = null;
	private InhaltMapper inhaltMapper = null;
	private StellenausschreibungMapper stellenausschreibungMapper = null;

	public AdministrationImpl() throws IllegalArgumentException {
	}

	public void init() throws IllegalArgumentException {

		this.benutzerMapper = BenutzerMapper.benutzerMapper();
		this.eigenschaftMapper = EigenschaftMapper.eigenschaftMapper();
		this.inhaltMapper = InhaltMapper.inhaltMapper();
		this.stellenausschreibungMapper = StellenausschreibungMapper.stellenausschreibungMapper();

	}

	/*
	 * -------------------------------------------------------------------------
	 * ------------------------- PROFIL
	 * -------------------------------------------------------------------------
	 * -------------------------
	 */

	



	/**
	 * Alle Inhalte eines Profils ausgeben
	 */
	public Vector<Inhalt> getInhaltFor(Profil p) throws IllegalArgumentException {

		return this.inhaltMapper.findByProfil(p);

	}

	

	/*
	 * -------------------------------------------------------------------------
	 * ------------------------- EIGENSCHAFT
	 * -------------------------------------------------------------------------
	 * -------------------------
	 */

	/**
	 * Ausgeben einer Eigenschaft anhand der Id
	 */

	public Eigenschaft getEigenschaftById(int id) throws IllegalArgumentException {
		return this.eigenschaftMapper.findByKey(id);

	}
	
	public Vector<Eigenschaft> getAllEigenschaften() throws IllegalArgumentException {
		return this.eigenschaftMapper.getAllEigenschaften();
	}
	
	@Override
	public Eigenschaft anlegenEigenschaft(int referenzId, String bezeichnung, String type,
			String angabe) throws IllegalArgumentException {
		
		Eigenschaft eigenschaft = new Eigenschaft();
		eigenschaft.setBezeichnung(bezeichnung);
		eigenschaft.setAngabe(angabe);
		eigenschaft.setType(type);
		
		return this.eigenschaftMapper.anlegenEigenschaft(eigenschaft, referenzId);
	}
	
	public Vector<Eigenschaft> findByBenutzer(int id) throws IllegalArgumentException {
		return this.eigenschaftMapper.findByBenutzer(id);
	}
	

	/*
	 * -------------------------------------------------------------------------
	 * ------------------------- STELLENAUSSCHREIBUNG
	 * -------------------------------------------------------------------------
	 * -------------------------
	 */

	/**
	 * Erstellen einer Stellenbeschreibung
	 */
	@Override
	public Stellenausschreibung createStellenausschreibung(String bezeichnung, String beschreibung, Date frist,
			Benutzer nutzerid) throws IllegalArgumentException {
		Stellenausschreibung s = new Stellenausschreibung();
		s.setBezeichnug(bezeichnung);
		s.setBeschreibungstext(beschreibung);
		s.setFrist(frist);

		return this.stellenausschreibungMapper.insertStellenausschreibung(s, nutzerid);
	}

	/**
	 * Update einer Stellenbeschreibung
	 */
	@Override
	public Stellenausschreibung editStellenausschreibung(String bezeichnung, String beschreibung, Date frist,
			int nutzerid, int stellenid) throws IllegalArgumentException {
		Stellenausschreibung s = new Stellenausschreibung();
		s.setStellenId(stellenid);
		s.setBezeichnug(bezeichnung);
		s.setBeschreibungstext(beschreibung);
		s.setFrist(frist);

		return this.stellenausschreibungMapper.updateStellenausschreibung(s, nutzerid);
	}

	/**
	 * Stellenausschreibung löschen
	 */
	@Override
	public void deleteStellenausschreibung(Stellenausschreibung stelle) throws IllegalArgumentException {
		this.stellenausschreibungMapper.deleteStellenausschreibung(stelle);
	}

	/**
	 * Ausgabe der Stellenbeschreibungen eines Nutzers
	 */
	@Override
	public Vector<Stellenausschreibung> showStellenausschreibung(int benutzerid) throws IllegalArgumentException {
		return this.stellenausschreibungMapper.getStellenbeschreibungById(benutzerid);
	}

	/**
	 * Ausgabe einer Stellenbeschreibungen mit bestimmter StellenId
	 */
	@Override
	public Stellenausschreibung showStellenausschreibungByStellenId(int stellenid) throws IllegalArgumentException {
		return this.stellenausschreibungMapper.getStellenbeschreibungByStellenId(stellenid);
	}

	/**
	 * Alle Stellenausschreibungen anzeigen
	 */
	@Override
	public Vector<Stellenausschreibung> showAllStellenausschreibungen() throws IllegalArgumentException {
		return this.stellenausschreibungMapper.getAllStellenausschreibungen();
	}

	/*
	 * -------------------------------------------------------------------------
	 * ------------------------- BEWERBUNG
	 * -------------------------------------------------------------------------
	 * -------------------------
	 */

	/**
	 * Bewerbung anlegen
	 */
	@Override
	public void createBewerbung(int stellenid, int nutzerid) throws IllegalArgumentException {
		this.stellenausschreibungMapper.insertBewerbung(stellenid, nutzerid);
	}

	/**
	 * Alle Bewerbungen eines Profils ansehen
	 */
	@Override
	public Vector<Stellenausschreibung> showBewerbungen(int nutzerid) throws IllegalArgumentException {
		return this.stellenausschreibungMapper.getBewerbungById(nutzerid);
	}

	/**
	 * Bewerbung entfernen
	 */
	@Override
	public void deleteBewerbung(int stellenid) throws IllegalArgumentException {
		this.stellenausschreibungMapper.deleteBewerbung(stellenid);
	}

	
}
