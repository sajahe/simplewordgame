package com.game.simplewordgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;





public class VerbList extends ListActivity {
	private static Context mContext;
	Verb verb = new Verb();
	RawFileHandler rfh = new RawFileHandler();
	CharSequence[] verbs;
	//private ArrayAdapter<CharSequence> verbsAA;
	
	
	
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  mContext = this;
		setContentView(R.layout.verb_list_2);
		// ListView lv = (ListView)findViewById(R.id.text);
	
		//verbArrayMaker();
		//CharSequence[] verbs = {"poo","num","klo","kjio"};
		verbArrayMaker();
		 /*verbsAA = new ArrayAdapter<CharSequence>(this,
					R.id.text,verbs);*/
		 //View lo = findViewById(android.R.layout.simple_list_item_1);
		 ListAdapter adapter =new ArrayAdapter<CharSequence>(this,
				  R.layout.list_text_blue,R.id.text_list , verbs);
		 
		 /*
		 //verbsAA =  new ArrayAdapter<CharSequence>(this, android.R.layout.simple_list_item_1, R.id.text, verbs);
		 verbsAA =  new ArrayAdapter<CharSequence>(this, android.R.layout.simple_list_item_1, verbs);
		
	    
		//this.setListAdapter(new ArrayAdapter<String>(this, R.layout.verb_list, verbs));
		ArrayAdapter<CharSequence> aA = new ArrayAdapter<CharSequence>(this,
				android.R.layout.simple_list_item_1, verbs);
		lv.setAdapter(verbsAA);
		
		*/
		//lv.setTextFilterEnabled(true);
		 setListAdapter(adapter);
		/* OnItemClickListener(new OnItemClickListener() {
			    public void onItemClick(AdapterView<?> parent, View view,
				        int position, long id) {
				      // When clicked, show a toast with the TextView text
				      Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
				          Toast.LENGTH_SHORT).show();
				    }
				  });
		 setOnItemClickListener(new OnItemClickListener() {
			    public void onItemClick(AdapterView<?> parent, View view,
			        int position, long id) {
			      // When clicked, show a toast with the TextView text
			      Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
			          Toast.LENGTH_SHORT).show();
			    }
			  });*/
		 
		 
		  
	}
	 protected void onListItemClick(ListView l, View v, int position, long id) {
	        super.onListItemClick(l, v, position, id);
	        Object o = this.getListAdapter().getItem(position);
	        CharSequence verb = o.toString();
	        Intent intent1 = new Intent(v.getContext(), VerbConjugations.class);
	        intent1.putExtra("verb", verb);
            startActivity(intent1);
	    }
	public static Context getContext(){
        return mContext;
    }
	
	
private void verbArrayMaker() {
		verbs = new CharSequence[rfh.howManyRows(getContext())];
		InputStream is = this.getResources().openRawResource(R.raw.verbs);
		String rivi = "";
		InputStreamReader isr = new InputStreamReader(is);
		
		
		BufferedReader breader= new BufferedReader(isr);
		int i =0;
		try {
			
			while (i < verbs.length) {
	
				rivi = breader.readLine();
				verb.parseVerb(rivi);
				verbs[i] = verb.getInfinitive();
				i++;
			}
		}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	private String lineParser() {
		// TODO Auto-generated method stub
		return null;
	}  
}
