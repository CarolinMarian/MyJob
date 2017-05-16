package de.hdm.myjob.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.myjob.server.db.BenutzerMapper;
import de.hdm.myjob.server.db.BewerbungMapper;
import de.hdm.myjob.server.db.EigenschaftMapper;
import de.hdm.myjob.server.db.InhaltMapper;
import de.hdm.myjob.server.db.ProfilMapper;
import de.hdm.myjob.server.db.StellenausschreibungMapper;
import de.hdm.myjob.shared.Administration;

@SuppressWarnings("serial")
public class AdministrationImpl extends RemoteServiceServlet implements Administration{

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
	    this.stellenausschreibungMapper = StellenausschreibungMapper.stellenbeschreibungMapper();
	  
	}
	
	public String getTestString() throws IllegalArgumentException {
		
		String test = this.eigenschaftMapper.findByKey(1);
		return test;
		
	}

}
