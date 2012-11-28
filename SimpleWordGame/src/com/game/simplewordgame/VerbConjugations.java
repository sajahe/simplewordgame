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


public class VerbConjugations extends Activity{
	private JSONHandler jsonh;
	private final String[] testVerbs= {"avoir","aller","boire","connaître","courir","devoir","dire","être","faire","lire","partir","pouvoir","prendre","savoir","venir","voir","vouloir"};
	private Context context;
	private String conjugatedVerb;
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.verb_conjugations);
		  conjugatedVerb= getIntent().getExtras().getString("verb");
		  context = this;
		  
		  
		  testSpeed();
		  //createJson();
		  //showDefault();
		// showXML();
		  
		 
		  
		  
		  
		  
		  
	}
	private void testSpeed() {
		long start =System.currentTimeMillis();
		Log.i("Start verbs", (""+System.currentTimeMillis()));
		createJson();
		for (int i = 0; i < testVerbs.length; i++) {
			//showXML(testVerbs[i]);
			showJson(testVerbs[i]);
		}
		Log.i("En verbs", (""+(System.currentTimeMillis()-start)));
	}
	private Context getThisContext(){
		return context;
	} 
	private void showXML(String s){
		VerbsXML vXmls = XMLHandler.getVerbXML(this.getThisContext());
		//VerbXML vXml = vXmls.getVerb().get(2);
		VerbXML vXml = vXmls.getVerbXML(s);
		TextView tv3 = (TextView) findViewById(R.id.textView3);
		  tv3.setText(vXml.getInfintive());
		TextView tv = (TextView) findViewById(R.id.textView1);
		  tv.setText(vXml.getJe()+"\n"+vXml.getTu()+"\n"+vXml.getIl());
		  TextView tv2 = (TextView) findViewById(R.id.textView4);
		  tv2.setText(vXml.getNous()+"\n"+vXml.getVous()+"\n"+vXml.getIls());
		
	}
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
	private void showJson(String s){
		
		try {
			//VerbJSON vjson = jsonh.parseVerbJSON(2);
			VerbJSON vjson = jsonh.parseVerbJSON(s);
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
}
