package com.game.simplewordgame;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

import de.fit.caple.cam.domain.core.Relatedentity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This is the start menu for the simple word game.
 * @author Sampo Pietikäinen
 *
 */
public class TenseSelect extends Activity {
	private SimpleApp app;
	private Relatedentity activity = new Relatedentity();
	private static final String TAG = "Error";
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		activity.setName(this.getClass().getSimpleName());
		activity.setId("06");
		app =((SimpleApp)getApplicationContext());
		app.createCamEvent("Start", activity);
		setContentView(R.layout.select_tense);
		startButton();
		helpButton();
		verbListButton();
		futureButton();
		conditionalButton();

			
		
	}
	private void helpButton() {
    	final Button help = (Button) findViewById(R.id.present);
    	help.setOnClickListener(new OnClickListener() {
    	    public void onClick(View v1) {
    	    
    	        	Intent intent1 = new Intent(v1.getContext(), SimpleWordGame.class);
    	        	intent1.putExtra("tense", 0);
    	        	app.createCamEvent("Select present", activity);
    	        	startActivity(intent1);
    	        	finish();
    	        }

    	    

    	    
    	});
		
	}
	private void startButton(){
    	//final Results result = new Results();
    	final Button start = (Button) findViewById(R.id.imperfect);
    	start.setOnClickListener(new OnClickListener() {
    	    public void onClick(View v) {
    	    
    	        	Intent intent = new Intent(v.getContext(), SimpleWordGame.class);
    	        	intent.putExtra("tense", 1);
    	        	app.createCamEvent("Select imperfect", activity);
    	        	startActivity(intent);
    	        	finish();
                    
    	        }

    	    

    	    
    	});
    	
    }
	private void verbListButton() {
    	final Button vl = (Button) findViewById(R.id.pc);
    	vl.setOnClickListener(new OnClickListener() {
    	    public void onClick(View v2) {
    	    
    	        	Intent intent2 = new Intent(v2.getContext(), SimpleWordGame.class);
                    intent2.putExtra("tense", 2);
                    app.createCamEvent("Select pc", activity);
    	        	startActivity(intent2);
    	        	finish();
    	        }

    	    

    	    
    	});
	
	}
	private void futureButton() {
    	final Button vl = (Button) findViewById(R.id.future);
    	vl.setOnClickListener(new OnClickListener() {
    	    public void onClick(View v2) {
    	    
    	        	Intent intent2 = new Intent(v2.getContext(), SimpleWordGame.class);
                    intent2.putExtra("tense", 3);
                    app.createCamEvent("Select future", activity);
    	        	startActivity(intent2);
    	        	finish();
    	        }

    	    

    	    
    	});
	
	}
	private void conditionalButton() {
    	final Button vl = (Button) findViewById(R.id.conditional);
    	vl.setOnClickListener(new OnClickListener() {
    	    public void onClick(View v2) {
    	    
    	        	Intent intent2 = new Intent(v2.getContext(), SimpleWordGame.class);
                    intent2.putExtra("tense", 4);
                    app.createCamEvent("Select conditional", activity);
    	        	startActivity(intent2);
    	        	finish();
    	        }

    	    

    	    
    	});
	
	}
	@Override
	protected void onPause(){
		app.createCamEvent("Pause", activity);
		super.onPause();
		
	}
	@Override
	protected void onResume(){
		app.createCamEvent("Resume", activity);
		super.onResume();
	}
}
