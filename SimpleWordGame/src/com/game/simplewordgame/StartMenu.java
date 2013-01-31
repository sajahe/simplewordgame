package com.game.simplewordgame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import de.fit.caple.cam.domain.core.Relatedentity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * This is the start menu for the simple word game.
 * @author Sampo Pietikäinen
 *
 */
public class StartMenu extends Activity {
	private static final String TAG = "Error";
	private Relatedentity activity = new Relatedentity();
	private SimpleApp app;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		app =((SimpleApp)getApplicationContext());
		app.createCamEvent("Start", activity);
		System.out.println("Start "+this.getClass().getSimpleName());
		
		setContentView(R.layout.start);
		startButton();
		helpButton();
		verbListButton();

			
		
	}
	
	private void helpButton() {
    	final Button help = (Button) findViewById(R.id.help);
    	help.setOnClickListener(new OnClickListener() {
    	    public void onClick(View v1) {
    	    
    	        	Intent intent1 = new Intent(v1.getContext(), Help.class);
    	        	app.createCamEvent("Select help", activity);
                    startActivity(intent1);
    	        }

    	    

    	    
    	});
		
	}
	private void startButton(){
    	//final Results result = new Results();
    	final Button start = (Button) findViewById(R.id.start);
    	start.setOnClickListener(new OnClickListener() {
    	    public void onClick(View v) {
    	    
    	        	Intent intent = new Intent(v.getContext(), TenseSelect.class);
    	        	app.createCamEvent("Select new game", activity);
    	        	startActivity(intent);
                    
    	        }

    	    

    	    
    	});
    	
    }
	private void verbListButton() {
    	final Button vl = (Button) findViewById(R.id.verb_list);
    	vl.setOnClickListener(new OnClickListener() {
    	    public void onClick(View v2) {
    	    
    	        	Intent intent2 = new Intent(v2.getContext(), VerbList.class);
    	        	app.createCamEvent("Select list", activity);
                    startActivity(intent2);
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
