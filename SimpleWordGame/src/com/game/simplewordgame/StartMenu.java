package com.game.simplewordgame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
	private SimpleApp app;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		app =((SimpleApp)getApplicationContext());
		app.createCamEvent("Start "+this.getClass().getSimpleName());
		System.out.println("Start "+this.getClass().getSimpleName());
		createCamFile();
		setContentView(R.layout.start);
		startButton();
		helpButton();
		verbListButton();

			
		
	}
	private void createCamFile() {
		String FILENAME = "hello_world";
		String json = "hello worldSimple!";
		
		boolean mExternalStorageAvailable = false;
		boolean mExternalStorageWriteable = false;
		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {
		   
		    mExternalStorageAvailable = mExternalStorageWriteable = true;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
		    // We can only read the media
		    mExternalStorageAvailable = true;
		    mExternalStorageWriteable = false;
		} else {
		    // Something else is wrong. It may be one of many other states, but all we need
		    //  to know is we can neither read nor write
		    mExternalStorageAvailable = mExternalStorageWriteable = false;
		}
		if(mExternalStorageAvailable == mExternalStorageWriteable == true){
		File externalDir = Environment.getExternalStorageDirectory();
		
		File textFile = new File(externalDir.getAbsolutePath()
				+ File.separator + "simple.json");

		try {
			writeTextFile(textFile, json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		// TODO Auto-generated method stub
		
	}
	private void writeTextFile(File file, String text) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(text);
		writer.close();
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
    	    
    	        	Intent intent = new Intent(v.getContext(), TenseSelect.class);
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
