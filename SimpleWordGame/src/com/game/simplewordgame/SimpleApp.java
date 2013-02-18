package com.game.simplewordgame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import de.fit.caple.cam.domain.core.Event;
import de.fit.caple.cam.domain.core.Relatedentity;
import de.fit.caple.cam.domain.core.Session;
import de.fit.caple.cam.instance.CamInstance;
import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.provider.ContactsContract.Directory;
import android.telephony.TelephonyManager;

public class SimpleApp extends Application {
	private Session session = new Session();
	//This is the global time stamp which is for cam filenames
	private long time = Calendar.getInstance().getTimeInMillis();
	private List<CamInstance> camInstances = new ArrayList<CamInstance>();
    
	
	public void createCamEvent(String name,Relatedentity...entities ){
		
		CamInstance camInstance = new CamInstance();
		camInstance.setVersion(this.getString(R.string.json_version));
		Event event = new Event();
		event.setDatetime(Calendar.getInstance().getTime());
		
		event.setName(name);
		
		camInstance.setEvent(event);
		
		
		
		//Session
		session.setIpAddress("myIp");
		
		event.addSession(session);
		Relatedentity user = new Relatedentity();
		
		user.setMetadataReference("userId");
		user.setMimetype("User");
		Relatedentity location = new Relatedentity();
		location.setId("location");
		location.setMetadataReference(this.getResources().getConfiguration().locale.getISO3Country());
		event.addRelatedentity(location,"location");
		Relatedentity origin = new Relatedentity();
		TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		origin.setMetadataReference(tm.getSimCountryIso());
		origin.setId("origin");
		event.addRelatedentity(origin,"origin");
		Relatedentity language = new Relatedentity();
		language.setMetadataReference(this.getResources().getConfiguration().locale.getDisplayLanguage());
		language.setId("language");
		event.addRelatedentity(language,"language");
		for (Relatedentity relatedentity : entities) {
			if(event != null){
				
			event.addRelatedentity(relatedentity, relatedentity.getName()+"Role");
			}
		}
		
		camInstances.add(camInstance);
		/*
		String jsonCam = camInstance.toJson();
		camInstance.
		System.out.println(jsonCam);*/
	}
	public void createCamFile() {
		StringBuilder json = new StringBuilder("["); 
		
		for (int i = 0; i < camInstances.size()-1; i++) {
			json.append(camInstances.get(i).toJson()+",");
			
			
		}
		json.append(camInstances.get(camInstances.size()-1).toJson()+"]");
		
		
		
		System.out.println(json);
		
		
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
		File externalDir =new File( Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator +"simplewordgame"+File.separator);
		externalDir.mkdir();
		String filename = "cam"+Calendar.getInstance().getTimeInMillis()+".json";
		File textFile = new File(externalDir.getAbsolutePath()+File.separator+ filename);

		try {
			writeTextFile(textFile, json.toString());
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
	public void createJsonFile(String json, String name) {
		
		
		
		System.out.println(json);
		
		
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
		File externalDir =new File( Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator +"simplewordgame"+File.separator);
		externalDir.mkdir();
		String filename = name+".json";
		File textFile = new File(externalDir.getAbsolutePath()+File.separator+ filename);

		try {
			writeTextFile(textFile, json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		// TODO Auto-generated method stub
		
	}
	
	

}
