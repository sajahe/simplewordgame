package de.fit.caple.cam.domain.core;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



import de.fit.caple.cam.utils.PersistResponse;


public class Session{
	private Long id = null;

	private String sessionId = null;

	private String domain = null;

	private String ipAddress = null;
	// one session can be part of several events
	private Set<Event> events = null;
	
	public Session() {
		this.events = new HashSet<Event>();
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @param domain the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the events
	 */
	public Set<Event> getEvents() {
		return events;
	}

	/**
	 * @param events the events to add
	 */
	public void addEvent(Event event) {
		this.events.add(event);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((domain == null) ? 0 : domain.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((ipAddress == null) ? 0 : ipAddress.hashCode());
		result = prime * result
				+ ((sessionId == null) ? 0 : sessionId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Session other = (Session) obj;
		if (domain == null) {
			if (other.domain != null) {
				return false;
			}
		} else if (!domain.equals(other.domain)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (ipAddress == null) {
			if (other.ipAddress != null) {
				return false;
			}
		} else if (!ipAddress.equals(other.ipAddress)) {
			return false;
		}
		if (sessionId == null) {
			if (other.sessionId != null) {
				return false;
			}
		} else if (!sessionId.equals(other.sessionId)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Session [id=" + id + ", sessionId=" + sessionId + ", domain= " + domain + "]";
	}

	/**
	 * Persists a Session object with all sub-objects into the database linked in the Entitymanager
	 * Check for duplicate entries: 
	 *   do not persist Session if an Entry with the same
	 *   same sessionId, domain and ipAddress already exists in the database
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
		if (this != null && (this.getSessionId() != null || 
				this.getDomain() != null || this.getIpAddress() != null 
			)) {
			String sessionIdStatement = ((this.getSessionId() == null) ? "is null" : "= :sessionId");
			String domainStatement = ((this.getDomain() == null) ? "is null" : "= :domain");
			String ipAddressStatement = ((this.getIpAddress() == null) ? "is null" : "= :ipAddress");
			String SQL_QUERY ="select id from Session where sessionId " + sessionIdStatement + 
				" and domain " + domainStatement +
				" and ipAddress " + ipAddressStatement;
			
			Query query = entityManager.createQuery(SQL_QUERY);
			if (this.getSessionId() != null) {
				query.setParameter("sessionId", this.getSessionId());
			}
			if (this.getDomain() != null) {
				query.setParameter("domain", this.getDomain());
			}
			if (this.getIpAddress() != null) {
				query.setParameter("ipAddress", this.getIpAddress());
			}
			Long result = null;
			List resultList = query.getResultList();
			if (resultList != null && resultList.size() > 0) {
				result = (Long) query.getResultList().get(0);
			}
			this.id = result;
			if (this.getId() != null) {	// entry already in DB
				pr.setId(this.getId().toString());
				pr.setSuccess(true);
				pr.setSessionCount(pr.getSessionCount()+1);
			} else { // store new Session
				if (!entityManager.getTransaction().isActive()) {
					entityManager.getTransaction().begin();
				}
				entityManager.persist(this);
				pr.setSuccess(true);
				pr.setId(this.getId().toString());
				pr.setSessionCount(pr.getSessionCount()+1);
			}
		}
		return pr;
	}*/
}
