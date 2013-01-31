package com.game.simplewordgame;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import de.fit.caple.cam.domain.core.Event;
import de.fit.caple.cam.domain.core.Relatedentity;
import de.fit.caple.cam.domain.core.Session;
import de.fit.caple.cam.instance.CamInstance;




import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;










/**
 * This is a simple word game for learning french conjugations.
 * 
 * @author Sampo Pietikäinen
 * 
 */
public class SimpleWordGame extends Activity {
	private Verb verb = new Verb();
	private JSONHandler jsonHandler; 
	private String response = "";
	private QuestionVerb[] questions;
	
	private int i = 0;
	private Results result = new Results();
	private int correct = 0;
	private int questionNo = 0;
	private int tense;
	private SimpleApp app;
	private Relatedentity activity = new Relatedentity();
	private Relatedentity tenseEntity = new Relatedentity();
	// private Verb verb = new Verb();
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		try {
			
			// this is the code that I am surrounding in the try/catch block
			super.onCreate(savedInstanceState);
			activity.setName(this.getClass().getSimpleName());
			activity.setId("04");
			app =((SimpleApp)getApplicationContext());
			setContentView(R.layout.main);
			tense = getIntent().getExtras().getInt("tense");
			tenseEntity.setName("Tense");
			
			tenseEntity.setId("05");
			tenseEntity.setMetadataReference(""+tense);
			
			createJson();
			app.createCamEvent("Start", activity, tenseEntity);
			
			questions = jsonHandler.getQuestions(tense, this);
			// verb.testQuestion();
			

			//String row = readRow();
			//verb.parseVerb(row);
			if(savedInstanceState != null){
				correct = savedInstanceState.getInt("correct");
			    questionNo=savedInstanceState.getInt("questionNo");
			   // verb.setForm(savedInstanceState.getString("form"));
			}
			setQuestion();
			setScore();
			setQuestionNo();
			// do{
			answering();
			// }while ();
			nextButton(savedInstanceState);
			

		} catch (Exception e) {
			// this is the line of code that sends a real error message to the
			// log
			Log.e("ERROR", "ERROR IN CODE: " + e.toString());

			// this is the line that prints out the location in
			// the code where the error occurred.
			e.printStackTrace();
		}

	}

	/**
	 * Sets the amount of right answers
	 */
	private void setScore() {
		View lo = findViewById(R.id.bg);
		String score = "Correct answers: " + correct;
		TextView tv = (TextView) findViewById(R.id.score);
		tv.setText(score);
		
		if (correct>0){
			lo.setBackgroundColor(0xFF00FF00);
		}

	}
	/**
	 * Shows the number of the question
	 */
	private void setQuestionNo() {
		String qno = "Question No." + questionNo + " ";
		TextView tv = (TextView) findViewById(R.id.Qno);
		tv.setText(qno);

	}
	/*
	/**
	 * Reads a row from a file
	 * 
	 * @param file
	 * @return
	 *//*
	private String readRow() {
		InputStream is = getResources().openRawResource(R.raw.verbs);
		String rivi = "";
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader breader= new BufferedReader(isr,8192);
		int i = 0;*/
		/*try {
			breader = new BufferedReader(new FileReader(file));
			while (i < randomNumberMaker(verbs.howManyRows(file))) {
				rivi = breader.readLine();
				i++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rivi;*//*
		try {
		
			while (i < randomNumberMaker(howManyRows())) {
				rivi = breader.readLine();
				i++;
			}
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rivi;
	}
	*/
	/**
	 * Gives a random number
	 * 
	 * @param amount biggest number possible
	 * @return random number
	 */
	private int randomNumberMaker(int amount) {
		Random rnd = new Random();
		int number = rnd.nextInt(amount) + 1;
		return number;
	}

	/**
	 * Changes the question after the next button has been clicked.
	 * 
	 */
	private void nextButton(final Bundle savedInstanceState) {
		// final Results result = new Results();
		
		final Button next = (Button) findViewById(R.id.next);
		final Button ok = (Button) findViewById(R.id.ok);
		next.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (i > 8) {

					Intent myIntent = new Intent(v.getContext(), Results.class);
					myIntent.putExtra("i1", correct);
					startActivity(myIntent);
					finish();
				}else{
				View lo = findViewById(R.id.bg);
				lo.setBackgroundColor(0xFFEFFBFE);
				questionNo++;
				setQuestionNo();
				
				// setContentView(R.layout.main);
				setQuestion();
				ok.setEnabled(true);
				next.setVisibility(Button.INVISIBLE);
				clearEditText();
				i++;
				}

			}

			private void clearEditText() {
				EditText edittext = (EditText) findViewById(R.id.edittext);
				edittext.setText("");

			}

		});

	}

	/**
	 * Takes the answer
	 */
	private void answering() {

		final EditText edittext = (EditText) findViewById(R.id.edittext);
		final Button ok = (Button) findViewById(R.id.ok);
		ok.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String teksti = edittext.getText().toString();

				// setContentView(R.layout.main);

				isItRight(teksti);
				ok.setEnabled(false);
			}
		});
		/*
		 * edittext.setOnKeyListener(new OnKeyListener() {
		 * 
		 * public boolean onKey(View v, int keyCode, KeyEvent event) {
		 * 
		 * //If the event is a key-down event on the "enter" button if
		 * ((event.getAction() == KeyEvent.ACTION_DOWN) && ((keyCode ==
		 * KeyEvent.KEYCODE_ENTER))) { String teksti =
		 * edittext.getText().toString(); //Perform action on key press
		 * isItRight(teksti);
		 * //edittext.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION); return
		 * true; }
		 * 
		 * return false; }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * });
		 */
		// return teksti;*/
	}
	

	/**
	 * Shows the infinitive of the verb and the pronoun
	 * 
	 */
	private void setQuestion() {
		final TextView textview = (TextView) findViewById(R.id.pronoun);
		textview.setText(questions[questionNo].getPronoun());
		final TextView textview2 = (TextView) findViewById(R.id.infinitive);
		textview2.setText(questions[questionNo].getQuestion());

	}

	/**
	 * Checks if the answer is right
	 * 
	 * @param teksti
	 */
	private void isItRight(String teksti) {
		Button next = (Button) findViewById(R.id.next);
		if (teksti.equals(questions[questionNo].getAnswer())) {
			Toast.makeText(SimpleWordGame.this, "Correct!", Toast.LENGTH_SHORT)
					.show();
			next.setVisibility(Button.VISIBLE);
			questions[questionNo].setCorrect(true);
			correct++;
			setScore();

		} else {
			View lo = findViewById(R.id.bg);
			questions[questionNo].setCorrect(false);
			lo.setBackgroundColor(0xFFFF0000);
			Toast.makeText(SimpleWordGame.this, "Incorrect!",
					Toast.LENGTH_SHORT).show();
			next.setVisibility(Button.VISIBLE);
		}
	}
	/**
	 * Counts how many lines there are in the data file
	 * @return quantity of the lines
	 */
	public int howManyRows() {
		InputStream is = this.getResources().openRawResource(R.raw.verbs);
		InputStreamReader isr = new InputStreamReader(is);
		int numero =0;
    	BufferedReader breader;
    	try {
    		breader = new BufferedReader(isr);
    		while ((breader.readLine()) != null) numero++;
        		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return numero;
	}
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
	    super.onRestoreInstanceState(savedInstanceState);
	    // Read values from the "savedInstanceState"-object and put them in your textview
	    ((TextView)findViewById(R.id.pronoun)).setText(savedInstanceState.getString("pronoun"));
	    // EditText visibility
	    ((TextView)findViewById(R.id.pronoun)).setVisibility(savedInstanceState.getInt("pronoun_v"));
	    
	    ((TextView)findViewById(R.id.infinitive)).setText(savedInstanceState.getString("infinitive"));
	    // EditText visibility
	    ((TextView)findViewById(R.id.infinitive)).setVisibility(savedInstanceState.getInt("infinitive_v"));
	    correct = savedInstanceState.getInt("correct");
	    questionNo=savedInstanceState.getInt("questionNo");
	    
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putString("pronoun",((TextView)findViewById(R.id.pronoun)).getText().toString());
		outState.putInt("pronoun_v", ((TextView)findViewById(R.id.pronoun)).getVisibility());
		outState.putString("infinitive",((TextView)findViewById(R.id.infinitive)).getText().toString());
		outState.putInt("infinitive_v", ((TextView)findViewById(R.id.infinitive)).getVisibility());
		
		
		
		outState.putInt("correct",correct);
		
		outState.putInt("questionNo",questionNo);
		outState.putString("form", verb.getForm());

	    super.onSaveInstanceState(outState);
	}
	/**
	 * Creates JSONHandler Class which handles the JSONArray
	 */
	private void createJson(){
		try {
			  InputStream is = this.getAssets().open("verbs.json");
			String s = JSONHandler.readInputStream(is);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  try {
			jsonHandler = new JSONHandler(JSONHandler.readInputStream(this.getAssets().open("verbs.json")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 // showJson(); 
		
	}
	private void createCam(){
	    CamInstance camInstance = new CamInstance();
		Event event = new Event();
		event.setDatetime(Calendar.getInstance().getTime());
		
		event.setName("SomeEventName");
		event.setDatetime(new Date());
		camInstance.setEvent(event);
		Session session = new Session();
		session.setIpAddress("myIp");
		event.addSession(session);
		Relatedentity user = new Relatedentity();
		user.setMetadataReference("userId");
		user.setMimetype("Person");
		event.addRelatedentity(user, "userRole");
		String jsonCam = camInstance.toJson();
		System.out.println(jsonCam);
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