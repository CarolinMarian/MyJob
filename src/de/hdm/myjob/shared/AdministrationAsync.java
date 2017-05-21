package de.hdm.myjob.shared;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.myjob.shared.bo.Benutzer;
import de.hdm.myjob.shared.bo.Eigenschaft;
import de.hdm.myjob.shared.bo.Inhalt;
import de.hdm.myjob.shared.bo.Profil;
import de.hdm.myjob.shared.bo.Stellenausschreibung;

public interface AdministrationAsync {

	void init(AsyncCallback<Void> callback);

	void getInhaltFor(Profil p, AsyncCallback<Vector<Inhalt>> callback);

	void getProfilFor(int id, AsyncCallback<Profil> callback);

	void createStellenausschreibung(String bezeichnung, String beschreibung, Date frist, Benutzer nutzerid,
			Profil profilid, AsyncCallback<Stellenausschreibung> callback);

	void getEigenschaftById(int id, AsyncCallback<Eigenschaft> callback);

	void showStellenausschreibung(int benutzerid, int profilid, AsyncCallback<Vector<Stellenausschreibung>> callback);

	void deleteProfil(Profil p, AsyncCallback<Void> callback);

	void createProfil(AsyncCallback<Void> callback);

	void editStellenausschreibung(String bezeichnung, String beschreibung, Date frist, int nutzerid, int profilid,
			int stellenid, AsyncCallback<Stellenausschreibung> callback);

	void showStellenausschreibungByStellenId(int stellenId, AsyncCallback<Stellenausschreibung> callback);

	void deleteStellenausschreibung(Stellenausschreibung stelle, AsyncCallback<Void> callback);

	void showAllStellenausschreibungen(AsyncCallback<Vector<Stellenausschreibung>> callback);

}
