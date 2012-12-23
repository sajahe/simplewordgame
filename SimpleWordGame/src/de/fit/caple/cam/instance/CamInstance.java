package de.fit.caple.cam.instance;



import org.json.JSONException;
import org.scribe.utils.URLUtils;

/*import com.google.gson.Gson;
import com.google.gson.GsonBuilder;*/

import de.fit.caple.cam.domain.core.Event;
import de.fit.caple.cam.utils.PersistResponse;
import de.fit.caple.cam.utils.Utils;

/**
 * This class is a wrapper class.
 * The root element for CAM is the Event Element 
 * We build a wrapper around the Event Element to be able to add Meta Information like the CAM Version
  *  
 * @author Martin Friedrich
 * 04.01.2011
 *
 */
public class CamInstance {
	private String version = null;
	private Event event = null;

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
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
	 * WON'T WORK IN ANDROID!
	 * Persists an Event object with all sub-objects into the database linked in the Entitymanager
	 * Does not check for duplicate events since we assume that each event is unique
	 * 
	 * @param entityManager that contains the database connection. (to externalize connection and Database Settings)
	 * @return a PersistResponse giving info about what has been persisted 
	 * 
	 * @throws JDBCConnectionException
	 * @throws TransactionException
	 * @throws PersistenceException
	 */
	/*public PersistResponse persist(EntityManager entityManager) throws JDBCConnectionException, TransactionException, PersistenceException {
		this.addCircles();
		PersistResponse pr = new PersistResponse();
		if (event != null) {
			pr = event.persist(entityManager);
		}
		return pr;
	}*/
	

	/**
	 * Converts a CAM POJO into a CAM Json String using google gson lib
	 * @return JSON String representation of the CamInstance
	 */
	public String toJson() {
		this.removeCircles();
		String json ="";
		try {
			json = Utils.toJson(this);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * Converts a CAM Json String into a CAM POJO
	 * @param json string representation of a CamInstance used to build a CamInstance POJO
	 * @return POJO of CamInstance
	 */
	public static CamInstance fromJson(String json) {
		/*json = URLUtils.percentDecode(json);
		GsonBuilder gb = new GsonBuilder();
		gb.serializeNulls();
		gb.setDateFormat("yyyy-MM-dd HH:mm:ss");
		Gson gson = gb.setPrettyPrinting().create();		
		CamInstance camInstance = gson.fromJson(json, CamInstance.class);
		if (camInstance != null) {
			camInstance.addCircles();
		}
		return camInstance;*/
		return null;
	}
	
	// required for serialization
	// remove circles of all n:m connections!
	private void removeCircles() {
		if (this.event != null) {
			this.event.removeCircles();
		}
	}
	
	// required for serialization
	// add circles of all n:m connections!
	private void addCircles() {
		if (this.event != null) {
			this.event.addCircles();
		}
	}
}
