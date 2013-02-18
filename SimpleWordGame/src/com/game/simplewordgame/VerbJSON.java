package com.game.simplewordgame;

public class VerbJSON {
	
    
	private String infintive;
	private String translation;
	private String je;
	private String tu;
	private String il;
	private String nous;
	private String vous;
	private String ils;
	
	/**
	 * Constructs verb conjugation from similiar trunk
	 *  
	 * @param trunk
	 * @param infintive
	 * Pronouns
	 * @param je = I
	 * @param tu = you
	 * @param il = he 
	 * @param nous = we
	 * @param vous = you (plural or polite)
	 * @param ils = they
	 */
	public VerbJSON(String trunk,String infintive,String translation, String je, String tu,
			String il, String nous, String vous,
			String ils) {
		super();
		this.translation = translation;
		this.infintive = trunk.concat(infintive) ;
		this.je = trunk.concat(je);
		this.tu = trunk.concat(tu);
		this.il = trunk.concat(il);
		this.nous = trunk.concat(nous);
		this.vous = trunk.concat(vous);
		this.ils = trunk.concat(ils);
	}
	
	public VerbJSON(String infintive, String translation, String je, String tu,
			String il, String nous, String vous,
			String ils) {
		super();
		this.infintive = infintive;
		this.translation = translation;
		this.je = je;
		this.tu = tu;
		this.il = il;
		this.nous = nous;
		this.vous = vous;
		this.ils = ils;
	}

	public String getInfintive() {
		return infintive;
	}
	
	public String getTranslation() {
		return translation;
	}

	public String getJe() {
		return je;
	}

	public String getTu() {
		return tu;
	}

	public String getIl() {
		return il;
	}

	public String getNous() {
		return nous;
	}

	public String getVous() {
		return vous;
	}

	public String getIls() {
		return ils;
	}
	
	
	

}
