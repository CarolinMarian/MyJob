package de.hdm.myjob.server.db;

public class InhaltMapper {

	/**
	 * SINGELTON-Eigenschaft sicherstellen
	 */
	
	public static InhaltMapper inhaltMapper = null;
	
	protected InhaltMapper(){
		
	}
	
	public static InhaltMapper inhaltMapper() {
	    if (inhaltMapper == null) {
	    	inhaltMapper = new InhaltMapper();
	    }

	    return inhaltMapper;
	  }
}
