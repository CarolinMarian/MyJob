package de.hdm.myjob.shared.bo;

public class Bewerbung extends BusinessObject {

	// Das Attribut serialVersionUID wird von der Oberklasse BusinessObject
	// vererbt
	private static final long serialVersionUID = 1L;

	// Attribute einer Stellenausschreibung
	private int stellenId = 0;
	private int benutzerId = 0;

	// No-Argument Constructor
	public Bewerbung() {
		super();
	}

	// Getter & Setter-Methoden
	public int getStellenId() {
		return this.stellenId;
	}

	public void setStellenId(int stellenId) {
		this.stellenId = stellenId;
	}

	public int getBenutzerId() {
		return this.benutzerId;
	}

	public void setBenutzerId(int profilId) {
		this.benutzerId = profilId;
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
		if (o != null && o instanceof Bewerbung) {
			Bewerbung b = (Bewerbung) o;
			try {
				return super.equals(b);
			} catch (IllegalArgumentException e) {
				return false;
			}
		}
		return false;
	}

}
