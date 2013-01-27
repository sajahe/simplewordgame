package com.game.simplewordgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;



public class Results extends Activity{
	private SimpleApp app;
		public void onCreate(Bundle savedInstanceState){
			super.onCreate(savedInstanceState);
			app =((SimpleApp)getApplicationContext());
			app.createCamEvent("Start "+this.getClass().getSimpleName());
			setContentView(R.layout.result);
			
			Bundle extras = getIntent().getExtras();
			int number = extras.getInt("i1");
			TextView textview = (TextView) findViewById(R.id.result);
			
			textview.append(number+"/"+10);
			if (number == 10){
			TextView tv = (TextView) findViewById(R.id.pefect);
			tv.setText("Perfect Score!");
			
			}
			
			app.createCamEvent("Score "+this.getClass().getSimpleName()+" "+number);
			menuButton();
			newButton();
		}
		private void menuButton() {
	    	final Button menu = (Button) findViewById(R.id.main_menu);
	    	menu.setOnClickListener(new OnClickListener() {
	    	    public void onClick(View v1) {
	    	    
	    	        	//Intent intent1 = new Intent(v1.getContext(), StartMenu.class);
	                    //startActivity(intent1);
	    	    		app.createCamEvent("Select menu "+this.getClass().getSimpleName());
	                    finish();
	    	        }

	    	    

	    	    
	    	});
			
		}
		private void newButton(){
	    	//final Results result = new Results();
	    	final Button start = (Button) findViewById(R.id.new_game);
	    	start.setOnClickListener(new OnClickListener() {
	    	    public void onClick(View v) {
	    	    
	    	        	Intent intent = new Intent(v.getContext(), SimpleWordGame.class);
	    	        	app.createCamEvent("Select new game "+this.getClass().getSimpleName());
	    	        	startActivity(intent);
	                    finish();
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

