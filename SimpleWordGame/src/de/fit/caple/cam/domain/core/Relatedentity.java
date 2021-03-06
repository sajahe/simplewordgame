package de.fit.caple.cam.domain.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



import de.fit.caple.cam.domain.relation.EventRelatedentity;
import de.fit.caple.cam.utils.PersistResponse;

/**
 * This Class represents the CAM Entity Object
 * Entity represents Persons, Items, Application, Devices which are 
 * related to the event
 * e.g. sending an email has at least 3 Entities:
 * 		Sender (Person)
 * 		Receiver (Person)
 * 		Message (Item)
 * 
 * @author Martin Friedrich, Uwe Kirschenmann
 * 04.01.2011
 *
 */

public class Relatedentity{
		private String id = null;
	
	private String name = null;
	
	private String entityId = null;
	
	private String mimetype = null;
	
	
	private String metadataId = null;
	
	private String metadataReference = null;
	
	private List<EventRelatedentity> events;
	
	
	public Relatedentity() {
		this.events = new ArrayList<EventRelatedentity>();
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the entityId
	 */
	public String getEntityId() {
		return entityId;
	}

	/**
	 * @param entityId the entityId to set
	 */
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	/**
	 * @return the mimetype
	 */
	public String getMimetype() {
		return mimetype;
	}

	/**
	 * @param mimetype the mimetype to set
	 */
	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	/**
	 * @return the metadataId
	 */
	public String getMetadataId() {
		return metadataId;
	}

	/**
	 * @param metadataId the metadataId to set
	 */
	public void setMetadataId(String metadataId) {
		this.metadataId = metadataId;
	}

	/**
	 * @return the metadataReference
	 */
	public String getMetadataReference() {
		return metadataReference;
	}

	/**
	 * @param metadataReference the metadataReference to set
	 */
	public void setMetadataReference(String metadataReference) {
		this.metadataReference = metadataReference;
	}
	
	/**
	 * @return the eventRelatedentityRelations
	 */
	public List<EventRelatedentity> getEventRelatedentityRelations() {
		return events;
	}

	/**
	 * @param eventRelatedentityRelations the eventRelatedentityRelations to set
	 */
	public void setEventRelatedentityRelations(
			List<EventRelatedentity> eventRelatedentityRelations) {
		this.events = eventRelatedentityRelations;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Relatedentity [id=" + id + ", name=" + name + ", entityId="
				+ entityId + ", mimetype=" + mimetype + ", metadataId="
				+ metadataId + ", metadataReference=" + metadataReference + "]";
	}
	
	/**
	 * Persists an Relatedentity object with all sub-objects into the database linked in the Entitymanager
	 * Check for duplicate entries: 
	 *   do not persist Relatedentity if an Entry with the same
	 *   same name, type, reference and mimetype already exists in the database
	 * 
	 * @param entityManager that contains the database connection
	 * @return a PersistResponse giving info about what has been persisted 
	 * 
	 * @throws JDBCConnectionException
	 * @throws TransactionException
	 * @throws PersistenceException
	 */
	/*
	public PersistResponse persist(EntityManager entityManager) throws JDBCConnectionException, TransactionException, PersistenceException {
		PersistResponse pr = new PersistResponse();
		if (this != null && (this.getName() != null || 
				this.getEntityId() != null || this.getMimetype() != null 
		)) {
			// now all Context IDs are there
			// check if relatedentity is already existing
			String nameStatement = ((this.getName() == null) ? "is null" : "= :name");
			String entityIdStatement = ((this.getEntityId() == null) ? "is null" : "= :entityId");
			String mimetypeStatement = ((this.getMimetype() == null) ? "is null" : "= :mimetype");
			String metadataIdStatement = ((this.getMetadataId() == null) ? "is null" : "= :metadataId");
			String metadataReferenceStatement = ((this.getMetadataReference() == null) ? "is null" : "= :metadataReference");
			
			String SQL_QUERY ="select id from Relatedentity where name " + nameStatement + 
				" and entityId " + entityIdStatement +
				" and mimetype " + mimetypeStatement +
				" and metadataId " + metadataIdStatement +
				" and metadataReference " + metadataReferenceStatement;
			
			Query query = entityManager.createQuery(SQL_QUERY);
			if (this.getName() != null) {
				query.setParameter("name", this.getName());
			}
			if (this.getEntityId() != null) {
				query.setParameter("entityId", this.getEntityId());
			}
			if (this.getMimetype() != null) {
				query.setParameter("mimetype", this.getMimetype());
			}
			if (this.getMetadataId() != null) {
				query.setParameter("metadataId", this.getMetadataId());
			}
			if (this.getMetadataReference() != null) {
				query.setParameter("metadataReference", this.getMetadataReference());
			}
			
			String result = null;
			List resultList = query.getResultList();
			if (resultList != null && resultList.size() > 0) {
				result = (String) query.getResultList().get(0);
			}
			this.id = result;
			if (this.getId() != null) {	// entry already in DB
				pr.setId(this.getId());
				pr.setSuccess(true);
				pr.setRelatedentityCount(pr.getRelatedentityCount()+1);
			} else { // store new Relatedebtity
				if (!entityManager.getTransaction().isActive()) {
					entityManager.getTransaction().begin();
				}
				
				entityManager.persist(this);
				pr.setSuccess(true);
				pr.setId(this.getId());
				pr.setRelatedentityCount(pr.getRelatedentityCount()+1);
			}
		} 
		return pr;
	}*/
}
