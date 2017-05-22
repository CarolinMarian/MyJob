package de.hdm.myjob.shared;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.myjob.shared.bo.Benutzer;
import de.hdm.myjob.shared.bo.Eigenschaft;
import de.hdm.myjob.shared.bo.Inhalt;
import de.hdm.myjob.shared.bo.Profil;
import de.hdm.myjob.shared.bo.Stellenausschreibung;

@RemoteServiceRelativePath("admin")
public interface Administration extends RemoteService {

	public void init() throws IllegalArgumentException;

	/*
	 * -------------------------------------------------------------------------
	 * ------------------------- PROFIL
	 * -------------------------------------------------------------------------
	 * -------------------------
	 */

	public Vector<Inhalt> getInhaltFor(Profil p) throws IllegalArgumentException;

	public Profil getProfilFor(int id) throws IllegalArgumentException;

	public void deleteProfil(Profil p) throws IllegalArgumentException;

	public void createProfil() throws IllegalArgumentException;

	/*
	 * -------------------------------------------------------------------------
	 * ------------------------- EIGENSCHAFT
	 * -------------------------------------------------------------------------
	 * -------------------------
	 */

	public Eigenschaft getEigenschaftById(int id) throws IllegalArgumentException;
	public Vector<Eigenschaft> getAllEigenschaften() throws IllegalArgumentException;

	/*
	 * -------------------------------------------------------------------------
	 * ------------------------- STELLENAUSSCHREIBUNG
	 * -------------------------------------------------------------------------
	 * -------------------------
	 */

	public Stellenausschreibung createStellenausschreibung(String bezeichnung, String beschreibung, Date frist,
			Benutzer nutzerid, Profil profilid) throws IllegalArgumentException;

	public Stellenausschreibung editStellenausschreibung(String bezeichnung, String beschreibung, Date frist,
			int nutzerid, int profilid, int stellenid) throws IllegalArgumentException;

	public void deleteStellenausschreibung(Stellenausschreibung stelle) throws IllegalArgumentException;

	public Vector<Stellenausschreibung> showStellenausschreibung(int benutzerid, int profilid)
			throws IllegalArgumentException;

	public Stellenausschreibung showStellenausschreibungByStellenId(int stellenId) throws IllegalArgumentException;

	public Vector<Stellenausschreibung> showAllStellenausschreibungen() throws IllegalArgumentException;

	/*
	 * -------------------------------------------------------------------------
	 * ------------------------- BEWERBUNG
	 * -------------------------------------------------------------------------
	 * -------------------------
	 */

	public void createBewerbung(int stellenid, int nutzerid) throws IllegalArgumentException;

	public Vector<Stellenausschreibung> showBewerbungen(int id) throws IllegalArgumentException;

	public void deleteBewerbung(int stellenid) throws IllegalArgumentException;
}
