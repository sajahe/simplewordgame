package com.game.simplewordgame;

import java.io.File;
import java.io.IOException;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;



import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.XmlResourceParser;

public class XMLHandler {
	public static VerbsXML getVerbXML(Context context){
		Serializer serializer = new Persister();
		
		try {
			return serializer.read(VerbsXML.class, context.getResources().openRawResource(R.raw.verbs_xml));
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	/*
	public static String getXMLString() {
		Resources res = context.getResources();
		XmlResourceParser parser = res.getXml(resource);
		String s = null;
		try {
			int nro = parser.getEventType();

			while (nro != XmlPullParser.END_DOCUMENT) {
				s = s + " " + parser.getName();

				if (nro == XmlPullParser.START_TAG
						&& parser.getName().equals("verb")) {
					
					for (int i = 0; i < parser.getAttributeCount(); i++) {
						if (parser.getAttributeName(i).equals("d")) {
							s = parser.getAttributeValue(i);
							return s;
						}
						s = s + parser.getName();
					}

				}

				try {
					nro = parser.next();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return s;
		// return parser.getName();

	}*/
}
