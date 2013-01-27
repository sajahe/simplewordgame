package com.game.simplewordgame;

import java.util.Calendar;
import java.util.Date;

import de.fit.caple.cam.domain.core.Event;
import de.fit.caple.cam.domain.core.Relatedentity;
import de.fit.caple.cam.domain.core.Session;
import de.fit.caple.cam.instance.CamInstance;
import android.app.Application;

public class SimpleApp extends Application {
	private Session session = new Session();
	private CamInstance camInstance = new CamInstance();
    
	
	public void createCamEvent(String name){
	    
		Event event = new Event();
		event.setDatetime(Calendar.getInstance().getTime());
		
		event.setName("SomeEventName");
		event.setDatetime(new Date());
		camInstance.setEvent(event);
		
		//Session
		session.setIpAddress("myIp");
		event.addSession(session);
		Relatedentity user = new Relatedentity();
		user.setMetadataReference("userId");
		user.setMimetype("Person");
		event.addRelatedentity(user, "userRole");
		/*
		String jsonCam = camInstance.toJson();
		camInstance.
		System.out.println(jsonCam);*/
	}

}
