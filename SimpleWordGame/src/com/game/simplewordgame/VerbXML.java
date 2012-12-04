package com.game.simplewordgame;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;



@Root(name="verb")
public class VerbXML{
	/*
	public VerbXML(Conjugation conjugation, String trunk,
			String infintive) {
		super();
		this.conjugation = conjugation;
		this.trunk = trunk;
		this.infinitive = infintive;
	}
	
	public VerbXML(Conjugation conjugation,
			String infintive) {
		super();
		this.conjugation = conjugation;
		
		this.infinitive = infintive;
	}
	*/
	@Element(name="conjugation")
	private Conjugation conjugation;
	
	@Element(name= "trunk", required=false)
	private String trunk;
	
	@Element(name="infinitive")
	private String infinitive;
	
	public String getInfintive() {
		if(trunk != null){
			return getTrunk().concat(infinitive);}
		return infinitive;
	}
	
	public String getTrunk() {
		if(trunk == null){
			return "";
		}
		return trunk;
	}
	
	public String getJe() {
		if(trunk != null ){
		return getTrunk().concat(conjugation.getJe());}
		return conjugation.getJe();
	}

	public String getTu() {
		if(trunk != null){
			return getTrunk().concat(conjugation.getTu());}
		return conjugation.getTu();
	}

	public String getIl() {
		if(trunk != null){
			return getTrunk().concat(conjugation.getIl());}
		return conjugation.getIl();
	}

	public String getNous() {
		if(trunk != null){
			return getTrunk().concat(conjugation.getNous());}
		return conjugation.getNous();
	}

	public String getVous() {
		if(trunk != null){
			return getTrunk().concat(conjugation.getVous());}
		return conjugation.getVous();
	}

	public String getIls() {
		if(trunk != null){
			return getTrunk().concat(conjugation.getIls());}
		return conjugation.getIls();
	}
	
}


