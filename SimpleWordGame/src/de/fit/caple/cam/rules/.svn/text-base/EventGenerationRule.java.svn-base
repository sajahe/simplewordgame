package de.fit.caple.cam.rules;

import java.util.ArrayList;
import java.util.List;

/**
 * wraps a rule to build an Event Rule for from a line of a rule describing the Event mapping
 * 
 * @author Martin Friedrich
 * 04.01.2011
 *
 */
public class EventGenerationRule {

	private String name;
	private String timestamp;
	private String sharinglevel;
	private List<EntityGenerationRule> entityGenerationRules;
	private List<SessionGenerationRule> sessionGenerationRules;
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @return the sharinglevel
	 */
	public String getSharinglevel() {
		return sharinglevel;
	}

	/**
	 * @return the entityGenerationRules
	 */
	public List<EntityGenerationRule> getEntityGenerationRules() {
		return entityGenerationRules;
	}
	
	/**
	 * @param entityGenerationRules the entityGenerationRules to add
	 */
	public void addEntityGenerationRule(EntityGenerationRule entityGenerationRule){
		this.entityGenerationRules.add(entityGenerationRule);
	}

	/**
	 * @return the sessionGenerationRule
	 */
	public List<SessionGenerationRule> getSessionGenerationRules() {
		return sessionGenerationRules;
	}
	
	/**
	 * @param sessionGenerationRule the sessionGenerationRule to set
	 */
	public void addSessionGenerationRule(SessionGenerationRule sessionGenerationRule) {
		this.sessionGenerationRules.add(sessionGenerationRule);
	}

	/**
	 * Constructor:
	 * 		builds an EntityGenerationRule using all assignments of the respective rule-line
	 * @param lineAttributes list containing all assignments of the rule-line. e.g. ["name:=login", "timestamp:=$timestamp", "sharinglevel:=$sharinglevel"] 
	 */
	public EventGenerationRule(String[] lineAttributes){
		
		for(String attribute : lineAttributes){
			String[] valuePair = attribute.split(CamGenerationRules.KEY_VALUE_SEPARATOR);
			if(valuePair.length == 2) {
				String valuePairKey = valuePair[0].trim();
				String valuePairValue = valuePair[1].trim();
				if(valuePairKey.equals("name")){
					this.name = valuePairValue;
				} else if(valuePairKey.equals("timestamp")){
					this.timestamp = valuePairValue;
				}  else if(valuePairKey.equals("sharinglevel")){
					this.sharinglevel = valuePairValue;
				}
			}
		}
		entityGenerationRules = new ArrayList<EntityGenerationRule>();
		sessionGenerationRules = new ArrayList<SessionGenerationRule>();
	}
}