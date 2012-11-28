package com.game.simplewordgame;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class Help extends Activity {
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);
		//Resources res = getResources();
		CharSequence styledText = Html.fromHtml(getResources().getString(R.string.help));
		TextView tv = (TextView) findViewById(R.id.help_text);
		tv.setText(styledText);
	}
}
