package com.game.simplewordgame;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;

import org.json.JSONException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.widget.AnalogClock;
import android.widget.TextView;

/**
 * This activity is for presenting all conjugations of one verb. Right now there 
 * are also test methods for testing speed of deserialization of XML and JSON these
 * should moved to somewhere else at some point.
 * 
 * @author sampo
 *
 */
public class VerbConjugations extends Activity{
	private JSONHandler jsonh;
	private SimpleApp app;
	private final String[] testVerbs= {"avoir","aller","boire","connaître","courir","devoir","dire","être","faire","lire","partir","pouvoir","prendre","savoir","venir","voir","vouloir"};
	private Context context;
	private String conjugatedVerb;
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.verb_conjugations);
		  conjugatedVerb= getIntent().getExtras().getString("verb");
		  app =((SimpleApp)getApplicationContext());
		  app.createCamEvent("Start "+this.getClass().getSimpleName()+" "+conjugatedVerb);
		  context = this;
		  
		  
		  //testSpeed();
		  createJson();
		  showJson(conjugatedVerb);
		  //showDefault();
		// showXML();
		  
		 
		  
		  
		  
		  
		  
	}
	/**
	 * Testing method which measures the speed of deserialization
	 */
	private void testSpeed() {
		long start =System.currentTimeMillis();
		Log.i("Start verbs ", (""+System.currentTimeMillis()));
		
		//createJson();
		//Log.i("JSON array created verbs ", (""+(System.currentTimeMillis()-start)));
		for (int i = 0; i < testVerbs.length; i++) {
			//showXML(testVerbs[i]);
			Log.i("Before verb "+i+" ", (""+(System.currentTimeMillis()-start)));
			//showJson(testVerbs[i]);
			showXML(testVerbs[i]);
			Log.i("After verb ", (""+(System.currentTimeMillis()-start)));
		}
		Log.i("End of verbs ", (""+(System.currentTimeMillis()-start)));
	}
	private Context getThisContext(){
		return context;
	} 
	/**
	 * Creates VerbXML class instance from XML element and shows it
	 * @param s = String of verb's infinitive to be conjugated 
	 */
	private void showXML(String s){
		VerbsXML vXmls = XMLHandler.getVerbXML(this.getThisContext());
		//VerbXML vXml = vXmls.getVerb().get(2);
		VerbXML vXml = vXmls.getVerbXML(s);
		Log.i("XML verb", ("Verb: "+vXml.getInfintive()+" "+vXml.getJe()+" "+vXml.getTu()+" "+vXml.getIl()+" "+vXml.getNous()+" "+vXml.getVous()+" "+vXml.getIls()));
		TextView tv3 = (TextView) findViewById(R.id.textView3);
		  tv3.setText(vXml.getInfintive());
		TextView tv = (TextView) findViewById(R.id.textView1);
		  tv.setText(vXml.getJe()+"\n"+vXml.getTu()+"\n"+vXml.getIl());
		  TextView tv2 = (TextView) findViewById(R.id.textView4);
		  tv2.setText(vXml.getNous()+"\n"+vXml.getVous()+"\n"+vXml.getIls());
		
	}
	/**
	 * Creates JSONHandler Class which handles the JSONArray
	 */
	private void createJson(){
		try {
			  InputStream is = this.getAssets().open("verbs.json");
			String s = JSONHandler.readInputStream(is);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  try {
			jsonh = new JSONHandler(JSONHandler.readInputStream(this.getAssets().open("verbs.json")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 // showJson(); 
		
	}
	/**
	 * Creates VerbJSON class instance from JSONHandler and shows its conjugations
	 * 
	 * @param s = String of verb's infinitive to be conjugated 
	 */
	private void showJson(String s){
		
		try {
			//VerbJSON vjson = jsonh.parseVerbJSON(2);
			
			VerbJSON vjson = jsonh.parseVerbJSON(s);
			
			Log.i("En verbs", ("Verb: "+vjson.getInfintive()+" "+vjson.getJe()+" "+vjson.getTu()+" "+vjson.getIl()+" "+vjson.getNous()+" "+vjson.getVous()+" "+vjson.getIls()));
			 TextView tv3 = (TextView) findViewById(R.id.textView3);
			  tv3.setText(vjson.getInfintive());
			TextView tv = (TextView) findViewById(R.id.textView1);
			  tv.setText(vjson.getJe()+"\n"+vjson.getTu()+"\n"+vjson.getIl());
			  TextView tv2 = (TextView) findViewById(R.id.textView4);
			  tv2.setText(vjson.getNous()+"\n"+vjson.getVous()+"\n"+vjson.getIls());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void showDefault(String s){
		 String verb = s; //().getExtras().getString("verb");
		  String[] conjugations = Verb.conjugate( (RawFileHandler.readRow(this, verb)));
		  
		  TextView tv3 = (TextView) findViewById(R.id.textView3);
		  tv3.setText(verb);
		  
		  TextView tv = (TextView) findViewById(R.id.textView1);
		  tv.setText(conjugations[0]);
		
	}
	@Override
	protected void onPause(){
		app.createCamEvent("Pause "+this.getClass().getSimpleName());
		super.onPause();
		
	}
	@Override
	protected void onResume(){
		app.createCamEvent("Resume "+this.getClass().getSimpleName());
		super.onResume();
		
		
	}
}
