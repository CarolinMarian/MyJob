package de.hdm.myjob.shared;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.myjob.shared.bo.Benutzer;
import de.hdm.myjob.shared.bo.Eigenschaft;
import de.hdm.myjob.shared.bo.Stellenausschreibung;

public interface AdministrationAsync {

	void init(AsyncCallback<Void> callback);

	/*
	 * -------------------------------------------------------------------------
	 * ------------------------- EIGENSCHAFT
	 * -------------------------------------------------------------------------
	 * -------------------------
	 */

	void getEigenschaftById(int id, AsyncCallback<Eigenschaft> callback);
	void getAllEigenschaften(AsyncCallback<Vector<Eigenschaft>> callback);
	void anlegenEigenschaft(int referenzId, String bezeichnung, String type, String angabe, AsyncCallback<Eigenschaft> callback);
	void findByBenutzer(int id, AsyncCallback<Vector<Eigenschaft>> callback);
	/*
	 * -------------------------------------------------------------------------
	 * ------------------------- STELLENAUSSCHREIBUNG
	 * -------------------------------------------------------------------------
	 * -------------------------
	 */
//	void createStellenausschreibung(String bezeichnung, String beschreibung, Date frist, Benutzer nutzerid,
//			AsyncCallback<Stellenausschreibung> callback);

	void editStellenausschreibung(String bezeichnung, String beschreibung, Date frist, int nutzerid, int profilid,
			int stellenid, AsyncCallback<Stellenausschreibung> callback);

	void deleteStellenausschreibung(Stellenausschreibung stelle, AsyncCallback<Void> callback);

	void showStellenausschreibung(int benutzerid, int profilid, AsyncCallback<Vector<Stellenausschreibung>> callback);

	void showStellenausschreibungByStellenId(int stellenId, AsyncCallback<Stellenausschreibung> callback);

	void showAllStellenausschreibungen(AsyncCallback<Vector<Stellenausschreibung>> callback);

	/*
	 * -------------------------------------------------------------------------
	 * ------------------------- BEWERBUNG
	 * -------------------------------------------------------------------------
	 * -------------------------
	 */

	void createBewerbung(int stellenid, int nutzerid, AsyncCallback<Void> callback);

	void showBewerbungen(int id, AsyncCallback<Vector<Stellenausschreibung>> callback);

	void deleteBewerbung(int stellenid, AsyncCallback<Void> callback);
	
	/*
	 * -------------------------------------------------------------------------
	 * ------------------------- BENUTZER
	 * -------------------------------------------------------------------------
	 * -------------------------
	 */
	
	void getBenutzerById(int id, AsyncCallback<Benutzer> callback);
	void createBenutzer(String mail, String vname, String nname, AsyncCallback<Benutzer> callback);
	
	void deleteBenutzer(Benutzer b, AsyncCallback<Void> callback);
	

}
