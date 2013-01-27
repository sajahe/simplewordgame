package com.game.simplewordgame;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
	private static final String TAG = "Error";
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		app =((SimpleApp)getApplicationContext());
		app.createCamEvent("Start "+this.getClass().getSimpleName());
		setContentView(R.layout.select_tense);
		startButton();
		helpButton();
		verbListButton();

			
		
	}
	private void helpButton() {
    	final Button help = (Button) findViewById(R.id.present);
    	help.setOnClickListener(new OnClickListener() {
    	    public void onClick(View v1) {
    	    
    	        	Intent intent1 = new Intent(v1.getContext(), SimpleWordGame.class);
    	        	intent1.putExtra("tense", 1);
    	        	startActivity(intent1);
    	        }

    	    

    	    
    	});
		
	}
	private void startButton(){
    	//final Results result = new Results();
    	final Button start = (Button) findViewById(R.id.imperfect);
    	start.setOnClickListener(new OnClickListener() {
    	    public void onClick(View v) {
    	    
    	        	Intent intent = new Intent(v.getContext(), SimpleWordGame.class);
    	        	intent.putExtra("tense", 2);
    	        	startActivity(intent);
                    
    	        }

    	    

    	    
    	});
    	
    }
	private void verbListButton() {
    	final Button vl = (Button) findViewById(R.id.pc);
    	vl.setOnClickListener(new OnClickListener() {
    	    public void onClick(View v2) {
    	    
    	        	Intent intent2 = new Intent(v2.getContext(), SimpleWordGame.class);
                    intent2.putExtra("tense", 3);
    	        	startActivity(intent2);
    	        }

    	    

    	    
    	});
	
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
