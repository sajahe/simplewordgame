package com.game.simplewordgame;

import de.fit.caple.cam.domain.core.Relatedentity;
import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class Help extends Activity {
	private SimpleApp app;
    private Relatedentity activity = new Relatedentity();
    
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		activity.setName(this.getClass().getSimpleName());
		activity.setId("01");
		
		app =((SimpleApp)getApplicationContext());
		app.createCamEvent("Start ", activity);
		app.createCamFile();
		setContentView(R.layout.help);
		//Resources res = getResources();
		CharSequence styledText = Html.fromHtml(getResources().getString(R.string.help));
		TextView tv = (TextView) findViewById(R.id.help_text);
		tv.setText(styledText);
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
