package de.fit.caple.cam.domain.relation;

import java.io.Serializable;

/**
 * Class that represents an EventRelatedentity Relation Instance
 *  
 * @author Martin Friedrich
 * 04.01.2011
 *
 */
@SuppressWarnings("serial")
public class EventRelatedentityId  implements Serializable {
	private long eventId;
    private String relatedentityId;
    private String role;
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
    public int hashCode() {
        return (int)(eventId + Integer.valueOf(relatedentityId) + Integer.valueOf(role));
      }
     
      public boolean equals(Object object) {
        if (object instanceof EventRelatedentityId) {
        	EventRelatedentityId otherId = (EventRelatedentityId) object;
          return (otherId.eventId == this.eventId) && (otherId.relatedentityId == this.relatedentityId) && (otherId.role == this.role);
        }
        return false;
      }
}
