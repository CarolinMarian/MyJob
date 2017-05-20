package de.hdm.myjob.shared;

import java.util.Date;
import java.util.Vector;

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

	public Vector<Inhalt> getInhaltFor(Profil p) throws IllegalArgumentException;

	public Profil getProfilFor(int id) throws IllegalArgumentException;

	public Stellenausschreibung createStellenausschreibung(String bezeichnung, String beschreibung, Date frist,
			Benutzer nutzerid, Profil profilid) throws IllegalArgumentException;

	public Eigenschaft getEigenschaftById(int id) throws IllegalArgumentException;

	public Vector<Stellenausschreibung> showStellenausschreibung(int benutzerid, int profilid)
			throws IllegalArgumentException;
	
	public void deleteProfil(Profil p) throws IllegalArgumentException;
	
	public void createProfil() throws IllegalArgumentException;
}
