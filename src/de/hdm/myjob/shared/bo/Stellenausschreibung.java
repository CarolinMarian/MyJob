package de.hdm.myjob.shared.bo;

import java.util.Date;

public class Stellenausschreibung extends BusinessObject {

	// Das Attribut serialVersionUID wird von der Oberklasse BusinessObject
	// vererbt
	private static final long serialVersionUID = 1L;

	// Attribute einer Stellenausschreibung
	private int stellenId=0;
	private String bezeichnung = null;
	private String beschreibungstext = null;
	private Date frist = null;

	// No-Argument Constructor
	public Stellenausschreibung() {
		super();
	}

	// Getter & Setter-Methoden
	public int getStellenId() {
		return this.stellenId;
	}

	public void setStellenId(int stellenId) {
		this.stellenId = stellenId;
	}

	public String getBezeichnung() {
		return this.bezeichnung;
	}

	public void setBezeichnug(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public String getBeschreibungstext() {
		return this.beschreibungstext;
	}

	public void setBeschreibungstext(String beschreibungstext) {
		this.beschreibungstext = beschreibungstext;
	}

	public Date getFrist() {
		return this.frist;
	}

	public void setFrist(Date frist) {
		this.frist = frist;
	}

	/**
	 * Erzeugen einer einfachen textuellen Darstellung der jeweiligen Instanz.
	 * Diese besteht aus dem Text, der durch die <code>toString()</code>-Methode
	 * der Superklasse erzeugt wird, erg�nzt durch den Vor- und Nachnamen des
	 * jeweiligen Kontakts.
	 */
	@Override
	public String toString() {
		return super.toString();
	}

	/**
	 * <p>
	 * Feststellen der <em>inhaltlichen</em> Gleichheit zweier Kontakt-Objekte.
	 * Die Gleichheit wird in diesem Beispiel auf eine identische Kontaktnummer
	 * beschr�nkt.
	 * </p>
	 */
	@Override
	public boolean equals(Object o) {
		/*
		 * Abfragen, ob ein Objekt ungl. NULL ist und ob ein Objekt gecastet
		 * werden kann, sind immer wichtig!
		 */
		if (o != null && o instanceof Stellenausschreibung) {
			Stellenausschreibung s = (Stellenausschreibung) o;
			try {
				return super.equals(s);
			} catch (IllegalArgumentException e) {
				return false;
			}
		}
		return false;
	}
}
