package com.game.simplewordgame;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="conjugation")
public class Conjugation{
	
	
	public String getTense() {
		return tense;
	}
	/*
	public Conjugation(String tense,String je, String tu, String il,
			String nous, String vous, String ils) {
		super();
		this.tense = tense;
		this.je = je;
		this.tu = tu;
		this.il = il;
		this.nous = nous;
		this.vous = vous;
		this.ils = ils;
	}*/
	@Attribute(name ="tense")
	private String tense;
	
	@Element(name="je")
	private String je;
	@Element(name="tu")
	private String tu;
	@Element(name="il")
	private String il;
	@Element(name="nous")
	private String nous;
	@Element(name="vous")
	private String vous;
	@Element(name="ils")
	private String ils;
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
