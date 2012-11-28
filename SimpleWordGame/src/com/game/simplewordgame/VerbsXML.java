package com.game.simplewordgame;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;

/**
 * This is for testing the speed of xml
 * 
 * @author sampo
 * 
 */


@Root(name="verbs")
public class VerbsXML {
	
	public VerbsXML(){
		super();
	}
	
	public VerbsXML(List<VerbXML> verb) {
		super();
		this.verb = verb;
	}

	@ElementList (name="irregular_verbs")
	private List<VerbXML> verb;
	
	public VerbXML getVerbXML(String infinitive){
		for (VerbXML v : verb) {
			if (v!=null && v.getTrunk().concat(v.getInfintive()).equals(infinitive)){
				return v;
			}
		}	
		return null; 
	}
	
	public List<VerbXML> getVerb() {
		return verb;
	}

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
	/*
	public VerbXML(String trunk,String infintive, String je, String tu,
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
	}*/
	/*
	public VerbXML(CharSequence infintive, CharSequence je, CharSequence tu,
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
	*/

	

	
	
	
}
