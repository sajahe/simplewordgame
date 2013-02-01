package com.game.simplewordgame;

import java.util.Random;



import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 
 * @author sampo
 * 
 */
public class Verb extends Activity {
	private String infinitive = "";
	private String pron = "";
	private String form = "";
	private final static String[] pronouns = new String[] { "je/j'", "tu",
			"il/elle", "nous", "vous", "ils/elles" };

	/**
	 * Creates a test verb for testing
	 */
	public void testQuestion() {
		setInfinitive("aller");
		setPron("Je");
		setForm("vais");

	}

	/**
	 * Separates the infinitive and the conjugated verb from String line
	 * 
	 * @param rivi
	 *            line whitch includes the infinitive and conjugated verbs
	 */
	public void parseVerb(String rivi) {
		Random rnd = new Random();
		int i = 0;
		int j = 0;
		int k = 0;
		int number = rnd.nextInt(6) + 1;
		
		// StringBuilder sb = new StringBuilder(rivi);
		while ('|' != rivi.charAt(i)) {
			i++;
		}
		k = i + 1;
		setInfinitive(rivi.substring(0, i));
		while (j < number) {
			i++;
			while (('|' != rivi.charAt(i) )) {
				i++;
			}
			j++;
			if ((j + 1) == number)
				k = i + 1;
		}
		setForm(rivi.substring(k, i));
		setPron(pronouns[number - 1]);
	}
	/*verbArrayMaker(){
		return null;
	}*/
	

	/**
	 * public void createPron(int number, String rivi){ int i = 0; int j = 0;
	 * int k = 0;
	 * 
	 * //StringBuilder sb = new StringBuilder(rivi); while ('|'!=
	 * rivi.charAt(i)){ i++; } setInfinitive(rivi.substring(0, i)); while
	 * (j<number){ i++; while ('|'!= rivi.charAt(i)){ i++; } j++; if ((j+1)==
	 * number) k=i+1; } setForm(rivi.substring(k, i));
	 * 
	 * 
	 * }
	 */

	/**
	 * For testing the Verb Class
	 */
	public void onCreate(Bundle savedInstanceState) {
		Verb verb = new Verb();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		verb.testQuestion();

	}
	private String getLineByInfinitive(String inf){
			
		return inf;
		
	}

	public void setInfinitive(String infinitive) {
		this.infinitive = infinitive;
	}

	public String getInfinitive() {
		return infinitive;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getForm() {
		return form;
	}

	public void setPron(String pron) {
		this.pron = pron;
	}

	public String getPron() {
		return pron;
	}

	public String[] getPronouns() {
		return pronouns;
	}
	public static String[] conjugate(String verb){
		String singular = "";
		String plural = "";
		int end=0;
		int start=0;
		for (int i = 0; i <7; i++) {
			start=end;
			if(i<1){
			while (('|' != verb.charAt(end))) {
				end++;
			}end++;}
			else if(i<4){
				while (('|' != verb.charAt(end))) {
					end++;
				}
			
			singular = singular+"\n"+pronouns[i-1]+" "+verb.substring(start, end);
			end++;
			}else {
				
				while (('|' != verb.charAt(end))) {
					end++;
				}
				
				plural = plural+"\n"+pronouns[i-1]+" "+verb.substring(start, end);
				end++;
			}

		}
		String[] s ={singular,plural};
		return s;
		
	}
	/*
	 * This static method retrieves wanted tense with given id 
	 */
	
	
}
