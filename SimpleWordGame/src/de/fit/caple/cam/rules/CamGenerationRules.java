package de.fit.caple.cam.rules;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;



import de.fit.caple.cam.domain.core.Event;
import de.fit.caple.cam.domain.core.Relatedentity;
import de.fit.caple.cam.domain.core.Session;
import de.fit.caple.cam.instance.CamInstance;

/**
 * class used to create CamInstances from a Rule-File.
 * A rule maps a set of given parameter to the respective CamInstance attributes (as defined in the CAM Schema).
 * This class reads a complete rule-file and caches all rules in a rule hash-map
 * The keys of the rule hash-map are the rule-keys as set in the rule-file
 * 
 * @author Martin Friedrich
 * 04.01.2011
 *
 */
public class CamGenerationRules {
	
	private final String ACTION = "Key";
	private final String EVENT = "* Event ->";
	private final String ENTITY = "* Entity ->";
	private final String ENTITY_ARRAY = "* Entity[] ->";
	private final String SESSION = "* Session ->";
	private final String LINE_SEPARATOR = " ->";
	private final String OBJECT_SEPARATOR = "; ";
	
	public final static String KEY_VALUE_SEPARATOR = ":="; 
	
	private Map<String, EventGenerationRule> rules;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	// initialize rules defined in a rules file (.txt)
	// the rules are stored in a HashMap called rules (key=rulename, value=rule)
	public CamGenerationRules (String rulesFile) {
		updateRules(rulesFile);
	}
	
	/**
	 * @return the rules
	 */
	public Map<String, EventGenerationRule> getRules() {
		return rules;
	}

	/**
	 * deletes all existing rules from cache to build new rules from given rule-file
	 * @param rulesFile local file or url to a remote rule file
	 */
	public void updateRules(String rulesFile) {
		rules = new HashMap<String, EventGenerationRule>();
		String key = null;
		
		try {
			BufferedReader reader = null;
			if (rulesFile.startsWith("http://")) {
				URL url = new URL(rulesFile);
				InputStream is = url.openStream(); 
		        reader = new BufferedReader(new InputStreamReader(is));
			} else {
				reader = new BufferedReader(new FileReader(rulesFile));
			}
			
			EventGenerationRule eventGenerationRule = null;
			
			String line = null;
			while ((line = reader.readLine()) != null) {
				if(line.startsWith(ACTION)) {
					if(key != null) {
						rules.put(key, eventGenerationRule);
					}
					key = line.substring(ACTION.length()).trim();
				} else if(line.startsWith("*")) {
					if(line.startsWith(EVENT)){
						eventGenerationRule = new EventGenerationRule(getAttributes(line));
					} else if(line.startsWith(ENTITY) || line.startsWith(ENTITY_ARRAY)) {
						EntityGenerationRule entityGenerationRule = new EntityGenerationRule(getAttributes(line)); 
						eventGenerationRule.addEntityGenerationRule(entityGenerationRule);
						if (line.startsWith(ENTITY_ARRAY)) {
							entityGenerationRule.setArrayRule(true);							
						}
					} else if (line.startsWith(SESSION)){
						SessionGenerationRule sessionGenerationRule = new SessionGenerationRule(getAttributes(line)); 
						eventGenerationRule.addSessionGenerationRule(sessionGenerationRule);
					} 
				}
			} 
			rules.put(key, eventGenerationRule);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * creates a CamInstance POJO from a parameter hash-map and a rule-key 
	 * the rule contains dynamic mapping values -> reference to a specific attribute of the hash-map to put the dynamic value to its specified place
	 * values starting with "$" reference an entry of the attribute map
	 * 
	 * @param ruleKey to identify and select the rule to use to build a CAM POJO 
	 * @param attributes a hash-map comprising the required information to create a CAM Instance
	 * @return a CamInstance POJO
	 * @throws Exception 
	 */
	public CamInstance fromRule(String ruleKey, Map<String, String> attributes) throws Exception {
		CamInstance camInstance = null;
		EventGenerationRule eventRule = rules.get(ruleKey);
		if (eventRule != null) {
			//Event
			Event event = new Event();
			event.setName(getValue(eventRule.getName(), attributes));
			// TODO: timestamp
			try {
				event.setDatetime(sdf.parse(getValue(eventRule.getTimestamp(), attributes)));
			} catch (ParseException e) {
				e.printStackTrace();
			} //Date ist immer in Map
			event.setSharingLevel(getValue(eventRule.getSharinglevel(), attributes));
			
			List<EntityGenerationRule> entityGenerationRules = eventRule.getEntityGenerationRules();
			
			//EventRelatedEntities
			for(EntityGenerationRule entityGenerationRule : entityGenerationRules) {
				if (!entityGenerationRule.isArrayRule()) {
					Relatedentity relatedentity = generateRelatedentity(entityGenerationRule, attributes);
					event.addRelatedentity(relatedentity, entityGenerationRule.getRole());
				} else {
					// first check if all given arrays have the same size!
					String name = getValue(entityGenerationRule.getName(), attributes);
					String entityId = getValue(entityGenerationRule.getEntityId(), attributes);
					String mimeType = getValue(entityGenerationRule.getMimeType(), attributes);
					try {
						// convert all array-variables into JSONArray Objects and put them into a list
						HashMap<String, JSONArray> arrays = new HashMap<String, JSONArray>();
						if (name != null && name.startsWith("[")) {
							arrays.put("names", new JSONArray(name));
						}
						if (entityId != null && entityId.startsWith("[")) {
							arrays.put("entityIds", new JSONArray(entityId));
						}
						if (mimeType != null && mimeType.startsWith("[")) {
							arrays.put("mimeTypes", new JSONArray(mimeType));
						}
						
						// check if all arrays have the same size - else exception
						int arraySize = -1;
						for (String key : arrays.keySet()) {
							if (arraySize < 0) {
								arraySize = arrays.get(key).length();
							} else {
								if (arraySize != arrays.get(key).length()) {
									throw new Exception("arrays have different size - " + arrays.get(key));
								}
							}
						}
						
						// create new entity for each element in array
						for (int i=0; i<arraySize; i++) {
							Relatedentity relatedentity = new Relatedentity();
							for (String key : arrays.keySet()) {
								if (key.compareTo("names") == 0) {
									name = arrays.get(key).getString(i);
								} else if (key.compareTo("entityIds") == 0) {
									entityId = arrays.get(key).getString(i);
								} else if (key.compareTo("mimeTypes") == 0) {
									mimeType = arrays.get(key).getString(i);
								}
							}
							relatedentity.setName(name);
							relatedentity.setEntityId(entityId);
							relatedentity.setMimetype(mimeType);
							event.addRelatedentity(relatedentity, entityGenerationRule.getRole());
							
							// TODO: currently this case does not support to add Metadata!!!
							// Metadata also in array notation: each Entity gets one Metadata
							// check arraysize, Entity[2] gets Metadata[2]
//							RelatedentityMetadata rmd = generateMetadata(entityGenerationRule, attributes);
//							if (rmd != null) {
//								relatedentity.addMetadata(rmd);
//							}							
						}
					} catch (JSONException e) {
						throw e;
					} catch (Exception e) {
						throw e;
					}
				}
			}
			
			//Session
			Session session = null;
			for (SessionGenerationRule sessionGenerationRule : eventRule.getSessionGenerationRules()) {
				if (sessionGenerationRule != null) {
					session = new Session();
					session.setDomain(getValue(sessionGenerationRule.getDomain(), attributes));
					session.setIpAddress(getValue(sessionGenerationRule.getIpAddress(), attributes));
					session.setSessionId(getValue(sessionGenerationRule.getSessionId(), attributes));
					event.addSession(session);
				}
			}
			camInstance = new CamInstance();
			camInstance.setEvent(event);
		}
		return camInstance;
	}
	
	private Relatedentity generateRelatedentity(EntityGenerationRule entityGenerationRule, Map<String, String> attributes) throws NoSuchAlgorithmException {
		Relatedentity relatedentity = new Relatedentity();
		relatedentity.setName(getValue(entityGenerationRule.getName(), attributes));
		relatedentity.setEntityId(getValue(entityGenerationRule.getEntityId(), attributes));
		relatedentity.setMimetype(getValue(entityGenerationRule.getMimeType(), attributes));

		return relatedentity;
	}
	
	private String[] getAttributes(String line){
		String[] attributes = line.split(LINE_SEPARATOR);
		if(attributes.length == 2) {
			return attributes[1].split(OBJECT_SEPARATOR);
		}
		return new String[]{};
	}
	
	private String getValue(String checkValue, Map<String, String> attributes) {
		if(checkValue != null && checkValue.startsWith("$")) {
			checkValue = attributes.get(checkValue.substring(1));
		}
		return checkValue;
	}
}
