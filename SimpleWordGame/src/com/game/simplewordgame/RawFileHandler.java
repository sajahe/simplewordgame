package com.game.simplewordgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;


public class RawFileHandler {
	public static int howManyRows(Context context) {
		InputStream is = context.getResources().openRawResource(R.raw.verbs);
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
	public static String readRow(Context context, String infinitive) {
		InputStream is = context.getResources().openRawResource(R.raw.verbs);
		String rivi = "";
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader breader= new BufferedReader(isr,8192);
		int i = 0;
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
		return rivi;*/
		try {
		
			do {
				rivi = breader.readLine();
				
			}while(!infinitive.equals(rivi.substring(0, infinitive.length())));
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rivi;
	}
}
