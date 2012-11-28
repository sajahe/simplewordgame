package com.game.simplewordgame;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.os.Environment;



public class JSONHandler {
	private JSONArray array;

	public JSONHandler(String s) {
		super();
		
		try {
			parseStringIntoJSON(s);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public VerbJSON parseVerbJSON(String s) throws JSONException {
		
		for (int i = 0; i < array.length(); i++) {
			JSONObject jObject = array.getJSONObject(i);
			String infinitive = null;
			if (jObject.isNull("trunk")){
				infinitive=jObject.getString("infinitive"); 
			}else{
				infinitive=jObject.getString("trunk").concat(jObject.getString("infinitive"));
			}
            if(infinitive != null && infinitive.equals(s)){
            	JSONObject jObjectConjugations =jObject.getJSONObject("present indicative");
        		String trunk;
        		
        		if (!jObject.isNull("trunk")){
        			
        			trunk = jObject.getString("trunk");
        			return new VerbJSON(trunk, jObject.getString("infinitive"), jObjectConjugations.getString("je"), jObjectConjugations.getString("tu"), 
        					jObjectConjugations.getString("il"), jObjectConjugations.getString("nous"), jObjectConjugations.getString("vous"), jObjectConjugations.getString("ils"));
        		}else{
        			return new VerbJSON(jObject.getString("infinitive"), jObjectConjugations.getString("je"), jObjectConjugations.getString("tu"), 
        					jObjectConjugations.getString("il"), jObjectConjugations.getString("nous"), jObjectConjugations.getString("vous"), jObjectConjugations.getString("ils"));
        		}
			}
            
			
		}
		return null;
		
	}


	public VerbJSON parseVerbJSON(int index) throws JSONException {
		JSONObject jObject = array.getJSONObject(index);
		JSONObject jObjectConjugations =jObject.getJSONObject("present indicative");
		String trunk;
		
		if (!jObject.isNull("trunk")){
			
			trunk = jObject.getString("trunk");
			return new VerbJSON(trunk, jObject.getString("infinitive"), jObjectConjugations.getString("je"), jObjectConjugations.getString("tu"), 
					jObjectConjugations.getString("il"), jObjectConjugations.getString("nous"), jObjectConjugations.getString("vous"), jObjectConjugations.getString("ils"));
		}else{
			return new VerbJSON(jObject.getString("infinitive"), jObjectConjugations.getString("je"), jObjectConjugations.getString("tu"), 
					jObjectConjugations.getString("il"), jObjectConjugations.getString("nous"), jObjectConjugations.getString("vous"), jObjectConjugations.getString("ils"));
		}
	}
	
public void parseStringIntoJSON(String string) throws JSONException{
		
		JSONTokener	jstokener = new JSONTokener(string);
		//jstokener.
		array = new JSONArray(jstokener);
		//jsarray.
		
	}

	

	public static String readInputStream(InputStream fis) {
		
		//if (fis != null) {
		String s = null;
			Writer writer = new StringWriter();

			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader(new InputStreamReader(fis,
						"UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
				s =writer.toString();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
			return s;
			
		//}
		
	}

	private static String readTextFile(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		StringBuilder text = new StringBuilder();
		String line;

		while ((line = reader.readLine()) != null) {
			text.append(line);
			text.append("\n");
		}
		reader.close();
		return text.toString();

	}
	private String getJSONFromAssets(InputStream open){
		
		String text = new String();
		
		 text = JSONHandler.readInputStream(open);
		if (text.equals(null)){
			
		}
		return text;
	}

}
