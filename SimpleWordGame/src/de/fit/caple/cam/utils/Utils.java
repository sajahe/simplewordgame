package de.fit.caple.cam.utils;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/*
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
*/
public class Utils {

	/**
	 * generates a SHA-512 Hash value to a given String value
	 * @param data the string value which should be hashed
	 * @return the SHA-512 Hash value of the given string
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	public static String generateHashValue(String data) throws NoSuchAlgorithmException {
		MessageDigest algo = MessageDigest.getInstance("SHA-512");
    	algo.reset();
    	algo.update(data.getBytes());
    	
    	byte messageDigest[] = algo.digest();
        
    	StringBuffer hexString = new StringBuffer();
    	for (int i=0;i<messageDigest.length;i++) {
    		hexString.append(String.format("%02x", messageDigest[i])); 
    	}
    	return hexString.toString();
	}
	
	public static String toJson(Object obj) throws JSONException {
		GsonBuilder gb = new GsonBuilder();
		
		gb.serializeNulls();
		gb.disableHtmlEscaping();
		gb.setDateFormat("yyyy-MM-dd HH:mm:ss");
		Gson gson = gb.setPrettyPrinting().create();
		
		return gson.toString();
	}
	public static int buildJSON(Field field){
		field.getType();
		return 0;
	}
}
