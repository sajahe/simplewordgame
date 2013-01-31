package com.game.simplewordgame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.fit.caple.cam.domain.core.Event;
import de.fit.caple.cam.domain.core.Relatedentity;
import de.fit.caple.cam.domain.core.Session;
import de.fit.caple.cam.instance.CamInstance;
import android.app.Application;
import android.os.Environment;

public class SimpleApp extends Application {
	private Session session = new Session();
	private List<CamInstance> camInstances = new ArrayList<CamInstance>();
    
	
	public void createCamEvent(String name,Relatedentity...entities ){
		
		CamInstance camInstance = new CamInstance();
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
		for (Relatedentity relatedentity : entities) {
			event.addRelatedentity(relatedentity, relatedentity.getName()+"Role");
		}
		
		camInstances.add(camInstance);
		/*
		String jsonCam = camInstance.toJson();
		camInstance.
		System.out.println(jsonCam);*/
	}
	public void createCamFile() {
		String FILENAME = "hello_world";
		String json = camInstances.get(0).toJson();

		System.out.println(json);
		CamInstance newInst = CamInstance.fromJson(json);
		
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
		File externalDir = Environment.getExternalStorageDirectory();
		
		File textFile = new File(externalDir.getAbsolutePath()
				+ File.separator + "cam.json");

		try {
			writeTextFile(textFile, json);
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

}
