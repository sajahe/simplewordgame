package de.fit.caple.cam.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.scribe.utils.URLUtils;

/**
 * class to read a config file
 * 
 * @author Martin Friedrich
 * 04.01.2011
 *
 */
public class ConfigReader {
	
    private Properties props = new Properties();
    private String fileName = "";
    
    public ConfigReader(String filename) {
    	this.fileName = filename;
    	readConfigFile(null);
    }

    private InputStream getResource() throws IOException {
        URL url = ConfigReader.class.getResource("/" + this.fileName);
        return url != null ? url.openStream(): null;
    }

    private void readConfigFile(String path) {
        try {
            InputStream inputStream;
            if (path == null || path.length() == 0)
                inputStream = getResource();
            else
                inputStream = new FileInputStream(URLUtils.percentDecode(path));
            props.load(inputStream);
            //Initialize.initializeLogging();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * read a specific property from the property file
     * @param key of the property to read
     * @return the value of the property
     */
    public String getProperty(String key) {
        return (String) props.get(key);
    }
}

