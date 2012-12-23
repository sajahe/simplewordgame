package de.fit.caple.cam.rules;

/**
 * wraps a rule to build a Session Rule for from a line of a rule describing the Event mapping
 * 
 * @author Martin Friedrich
 * 04.01.2011
 *
 */
public class SessionGenerationRule {

	private String ipAddress;
	private String domain;
	private String sessionId;
	
	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * Constructor:
	 * 		builds an SessionGenerationRule using all assignments of the respective rule-line
	 * @param lineAttributes list containing all assignments of the rule-line. e.g. ["ipAddress:=xyz"] 
	 */
	public SessionGenerationRule(String[] lineAttributes){
		for(String attribute : lineAttributes){
			String[] valuePair = attribute.split(CamGenerationRules.KEY_VALUE_SEPARATOR);
			if(valuePair.length == 2) {
				String valuePairKey = valuePair[0].trim();
				String valuePairValue = valuePair[1].trim();
				if(valuePairKey.equals("ipAddress")){
					this.ipAddress = valuePairValue;
				} else if(valuePairKey.equals("domain")){
					this.domain = valuePairValue;
				}else if(valuePairKey.equals("sessionId")){
					this.sessionId = valuePairValue;
				}
			}
		}
	}
}
