package com.game.simplewordgame;

public class VerbJSON {
	

	private CharSequence infintive;
	private CharSequence je;
	private CharSequence tu;
	private CharSequence il;
	private CharSequence nous;
	private CharSequence vous;
	private CharSequence ils;
	
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
	public VerbJSON(String trunk,String infintive, String je, String tu,
			String il, String nous, String vous,
			String ils) {
		super();
		this.infintive = trunk.concat(infintive) ;
		this.je = trunk.concat(je);
		this.tu = trunk.concat(tu);
		this.il = trunk.concat(il);
		this.nous = trunk.concat(nous);
		this.vous = trunk.concat(vous);
		this.ils = trunk.concat(ils);
	}
	
	public VerbJSON(CharSequence infintive, CharSequence je, CharSequence tu,
			CharSequence il, CharSequence nous, CharSequence vous,
			CharSequence ils) {
		super();
		this.infintive = infintive;
		this.je = je;
		this.tu = tu;
		this.il = il;
		this.nous = nous;
		this.vous = vous;
		this.ils = ils;
	}

	public CharSequence getInfintive() {
		return infintive;
	}

	public CharSequence getJe() {
		return je;
	}

	public CharSequence getTu() {
		return tu;
	}

	public CharSequence getIl() {
		return il;
	}

	public CharSequence getNous() {
		return nous;
	}

	public CharSequence getVous() {
		return vous;
	}

	public CharSequence getIls() {
		return ils;
	}
	
	
	

}
