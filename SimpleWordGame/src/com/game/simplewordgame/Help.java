package com.game.simplewordgame;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class Help extends Activity {
	private SimpleApp app;
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		app =((SimpleApp)getApplicationContext());
		app.createCamEvent("Start "+this.getClass().getSimpleName());
		setContentView(R.layout.help);
		//Resources res = getResources();
		CharSequence styledText = Html.fromHtml(getResources().getString(R.string.help));
		TextView tv = (TextView) findViewById(R.id.help_text);
		tv.setText(styledText);
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
