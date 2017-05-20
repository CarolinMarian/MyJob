package de.hdm.myjob.server;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.myjob.server.db.BenutzerMapper;
import de.hdm.myjob.server.db.BewerbungMapper;
import de.hdm.myjob.server.db.EigenschaftMapper;
import de.hdm.myjob.server.db.InhaltMapper;
import de.hdm.myjob.server.db.ProfilMapper;
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
	private BewerbungMapper bewerbungMapper = null;
	private EigenschaftMapper eigenschaftMapper = null;
	private InhaltMapper inhaltMapper = null;
	private ProfilMapper profilMapper = null;
	private StellenausschreibungMapper stellenausschreibungMapper = null;

	public AdministrationImpl() throws IllegalArgumentException {
	}

	public void init() throws IllegalArgumentException {

		this.benutzerMapper = BenutzerMapper.benutzerMapper();
		this.bewerbungMapper = BewerbungMapper.bewerbungMapper();
		this.eigenschaftMapper = EigenschaftMapper.eigenschaftMapper();
		this.inhaltMapper = InhaltMapper.inhaltMapper();
		this.profilMapper = ProfilMapper.profilMapper();
		this.stellenausschreibungMapper = StellenausschreibungMapper.stellenausschreibungMapper();

	}

	/**
	 * Alle Inhalte eines Profils ausgeben
	 */
	public Vector<Inhalt> getInhaltFor(Profil p) throws IllegalArgumentException {

		return this.inhaltMapper.findByProfil(p);

	}

	/**
	 * Ausgeben eines Profils aufgrund der Id
	 */
	public Profil getProfilFor(int id) throws IllegalArgumentException {

		return this.profilMapper.getProfilById(id);

	}

	/**
	 * Erstellen einer Stellenbeschreibung
	 */
	@Override
	public Stellenausschreibung createStellenausschreibung(String bezeichnung, String beschreibung, Date frist,
			Benutzer nutzerid, Profil profilid) throws IllegalArgumentException {
		Stellenausschreibung s = new Stellenausschreibung();
		s.setBezeichnug(bezeichnung);
		s.setBeschreibungstext(beschreibung);
		s.setFrist(frist);

		return this.stellenausschreibungMapper.insertStellenausschreibung(s, nutzerid, profilid);
	}

	/**
	 * Ausgeben einer EIgenschaft anhand der Id
	 */

	public Eigenschaft getEigenschaftById(int id) throws IllegalArgumentException {
		return this.eigenschaftMapper.findByKey(id);

	}

	/**
	 * Ausgabe der Stellenbeschreibungen eines Nutzers
	 */
	@Override
	public Vector<Stellenausschreibung> showStellenausschreibung(int benutzerid, int profilid)
			throws IllegalArgumentException {
		return this.stellenausschreibungMapper.getStellenbeschreibungById(benutzerid, profilid);
	}

	/**
	 * Löschen eines Profils
	 */
	public void deleteProfil(Profil p) throws IllegalArgumentException {
		this.profilMapper.delete(p);
	}
	
	/**
	 * Anlegen eines Profils
	 */
	public void createProfil() throws IllegalArgumentException {
		Profil p = new Profil();
		p.setBenutzerId(1);
		this.profilMapper.insert(p);
	}

}
