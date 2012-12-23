package de.fit.caple.cam.domain.relation;

import java.io.Serializable;
import java.util.List;


import de.fit.caple.cam.domain.core.Event;
import de.fit.caple.cam.domain.core.Relatedentity;
import de.fit.caple.cam.utils.PersistResponse;

/**
 * This class is used to apply the n:m relation between Event and Relatedentity
 * creates the table "EventRelatedentity" comprising three columns:
 *   + eventid: reference to the Event
 *   + relatedentityid: reference to the Relatedentity
 *   + role: the role of the Relatedentity for this Event
 *   
 * Only for internal usage. Developers using the CAM API should not need to access this Object
 *     
 * @author Martin Friedrich
 * 04.01.2011
 *
 */

public class EventRelatedentity  implements Serializable {
	  
	  private Long eventId = null;
	  
	  private String relatedentityId = null;
	  private String role;
	  /* if this JPA model doesn't create a table for the "PROJ_EMP" entity,
	  *  please comment out the @PrimaryKeyJoinColumn, and use the ff:
	  *  @JoinColumn(name = "employeeId", updatable = false, insertable = false)
	  * or @JoinColumn(name = "employeeId", updatable = false, insertable = false, referencedColumnName = "id")
	  */
	  private Event event;
	 /* the same goes here:
	  *  if this JPA model doesn't create a table for the "PROJ_EMP" entity,
	  *  please comment out the @PrimaryKeyJoinColumn, and use the ff:
	  *  @JoinColumn(name = "projectId", updatable = false, insertable = false)
	  * or @JoinColumn(name = "projectId", updatable = false, insertable = false, referencedColumnName = "id")
	  */
	  private Relatedentity relatedentity;
	/**
	 * @return the eventId
	 */
	public Long getEventId() {
		return eventId;
	}
	/**
	 * @return the projectId
	 */
	public String getRelatedentityId() {
		return relatedentityId;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}
	/**
	 * @param event the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}
	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	/**
	 * @param relatedentityId the relatedentityId to set
	 */
	public void setRelatedentityId(String relatedentityId) {
		this.relatedentityId = relatedentityId;
	}
	/**
	 * @return the relatedentity
	 */
	public Relatedentity getRelatedentity() {
		return relatedentity;
	}
	/**
	 * @param relatedentity the relatedentity to set
	 */
	public void setRelatedentity(Relatedentity relatedentity) {
		this.relatedentity = relatedentity;
	}

	/*
	public PersistResponse persist(EntityManager entityManager) throws JDBCConnectionException, TransactionException, PersistenceException {
		PersistResponse pr = new PersistResponse();
		if (this != null) {
			String relatedentityidStatement = ((this.getRelatedentity().getId() == null) ? "is null" : "= :relatedentityid");
		String eventidStatement = ((this.getEvent().getId() == null) ? "is null" : "= :eventid");
		String roleStatement = ((this.getRole() == null) ? "is null" : "= :role");
		
		String SQL_QUERY ="from EventRelatedentity where relatedentityid " + relatedentityidStatement + 
				" and eventid " + eventidStatement +
				" and role " + roleStatement;
		
		Query query = entityManager.createQuery(SQL_QUERY);
		if (this.getRelatedentity().getId() != null) {
			query.setParameter("relatedentityid", this.getRelatedentity().getId());
		}
		if (this.getEvent().getId() != null) {
			query.setParameter("eventid", this.getEvent().getId());
		}
		if (this.getRole() != null) {
			query.setParameter("role", this.getRole());
		}
		
		EventRelatedentity eventRelatedentityResult = null;
		List resultList = query.getResultList();
		if (resultList != null && resultList.size() > 0) {
			eventRelatedentityResult = (EventRelatedentity) query.getResultList().get(0);
		}
		if (eventRelatedentityResult != null ) {	 // entry already in DB
			pr.setSuccess(false);
		} else {  // store new Session
				entityManager.persist(this);
				pr.setSuccess(true);
				pr.setEventRelatedentityCount(pr.getEventRelatedentityCount()+1);
			}
		}
		return pr;
	}*/
}