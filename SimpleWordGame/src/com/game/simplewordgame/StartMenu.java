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
public class StartMenu extends Activity {
	private static final String TAG = "Error";
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
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
                    startActivity(intent1);
    	        }

    	    

    	    
    	});
		
	}
	private void startButton(){
    	//final Results result = new Results();
    	final Button start = (Button) findViewById(R.id.start);
    	start.setOnClickListener(new OnClickListener() {
    	    public void onClick(View v) {
    	    
    	        	Intent intent = new Intent(v.getContext(), SimpleWordGame.class);
                    startActivity(intent);
                    
    	        }

    	    

    	    
    	});
    	
    }
	private void verbListButton() {
    	final Button vl = (Button) findViewById(R.id.verb_list);
    	vl.setOnClickListener(new OnClickListener() {
    	    public void onClick(View v2) {
    	    
    	        	Intent intent2 = new Intent(v2.getContext(), VerbList.class);
                    startActivity(intent2);
    	        }

    	    

    	    
    	});
	
	}
}
