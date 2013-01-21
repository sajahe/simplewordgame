package de.fit.caple.cam.rules;

/**
 * wraps a rule to build an Relatedentity Rule for from a line of a rule describing the Event mapping
 * 
 * @author Martin Friedrich
 * 04.01.2011
 *
 */
public class EntityGenerationRule {

	private String name;
	private String role;
	private String entityId;
	private String mimeType;
	private String metadata;
	private String metadataType;
	private String metadataBinding;
	private boolean arrayRule;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @return the reference
	 */
	public String getEntityId() {
		return entityId;
	}

	/**
	 * @return the mimeType
	 */
	public String getMimeType() {
		return mimeType;
	}

	/**
	 * @return the metadata
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * @return the metadataType
	 */
	public String getMetadataType() {
		return metadataType;
	}

	/**
	 * @return the metadataBinding
	 */
	public String getMetadataBinding() {
		return metadataBinding;
	}

	/**
	 * @return the arrayRule
	 */
	public boolean isArrayRule() {
		return arrayRule;
	}

	/**
	 * @param arrayRule the arrayRule to set
	 */
	public void setArrayRule(boolean arrayRule) {
		this.arrayRule = arrayRule;
	}

	/**
	 * Constructor:
	 * 		builds an EntityGenerationRule using all assignments of the respective rule-line
	 * @param lineAttributes list containing all assignments of the rule-line. e.g. ["type:=person", "role:=viewer", "reference:=$viewer"]
	 */
	public EntityGenerationRule(String[] lineAttributes){
		this.arrayRule = false;
		for(String attribute : lineAttributes){
			String[] valuePair = attribute.split(CamGenerationRules.KEY_VALUE_SEPARATOR);
			if(valuePair.length == 2){
				String valuePairKey = valuePair[0].trim();
				String valuePairValue = valuePair[1].trim();
			
				if(valuePairKey.equals("name")){
					this.name = valuePairValue;
				} else if(valuePairKey.equals("role")){
					this.role = valuePairValue;
				} else if(valuePairKey.equals("entityId")){
					this.entityId = valuePairValue;
				} else if(valuePairKey.equals("mimeType")){
					this.mimeType = valuePairValue;
				} else if(valuePairKey.equals("metadata")){
					this.metadata = valuePairValue;
				} else if(valuePairKey.equals("metadataType")){
					this.metadataType = valuePairValue;
				} else if(valuePairKey.equals("metadataBinding")){
					this.metadataBinding = valuePairValue;
				}
			}
		}
	}
}
