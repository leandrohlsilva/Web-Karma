/*******************************************************************************
 * Copyright 2012 University of Southern California
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 	http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * This code was developed by the Information Integration Group as part 
 * of the Karma project at the Information Sciences Institute of the 
 * University of Southern California.  For more information, publications, 
 * and related projects, please see: http://www.isi.edu/integration
 ******************************************************************************/

package edu.isi.karma.modeling;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModelingConfiguration {

	private static Logger logger = LoggerFactory.getLogger(ModelingConfiguration.class);

    private static Boolean manualAlignment;
    private static Boolean nodeClosure;
    private static Boolean propertiesDirect;
    private static Boolean propertiesIndirect;
    private static Boolean propertiesWithOnlyDomain;
    private static Boolean propertiesWithOnlyRange;
    private static Boolean propertiesWithoutDomainRange;
    private static Boolean propertiesSubClass;
    
    private static String karmaSourcePrefix;
    private static String karmaServicePrefix; 

	public static void load() {
        try {
            Properties modelingProperties = loadParams("modeling");

            if(modelingProperties.getProperty("manual.alignment") != null)
            	manualAlignment = Boolean.parseBoolean(modelingProperties.getProperty("manual.alignment"));

            if(modelingProperties.getProperty("node.closure") != null)
            	nodeClosure = Boolean.parseBoolean(modelingProperties.getProperty("node.closure"));
            
            if(modelingProperties.getProperty("properties.direct") != null)
            	propertiesDirect = Boolean.parseBoolean(modelingProperties.getProperty("properties.direct"));
            
            if(modelingProperties.getProperty("properties.indirect") != null)
            	propertiesIndirect = Boolean.parseBoolean(modelingProperties.getProperty("properties.indirect"));

            if(modelingProperties.getProperty("properties.with.only.domain") != null)
            	propertiesWithOnlyDomain = Boolean.parseBoolean(modelingProperties.getProperty("properties.with.only.domain"));

            if(modelingProperties.getProperty("properties.with.only.range") != null)
            	propertiesWithOnlyRange = Boolean.parseBoolean(modelingProperties.getProperty("properties.with.only.range"));

            if(modelingProperties.getProperty("properties.without.domain.range") != null)
            	propertiesWithoutDomainRange = Boolean.parseBoolean(modelingProperties.getProperty("properties.without.domain.range"));

            if(modelingProperties.getProperty("properties.subclass") != null)
            	propertiesSubClass = Boolean.parseBoolean(modelingProperties.getProperty("properties.subclass"));

            karmaSourcePrefix = modelingProperties.getProperty("karma.source.prefix");
            karmaServicePrefix = modelingProperties.getProperty("karma.service.prefix");

        } catch (IOException e) {
            logger.error("Error occured while reading config file ...");
            System.exit(1);
        }
    }
	
	private static Properties loadParams(String file)
            throws IOException {
        Properties prop = new Properties();
        ResourceBundle bundle = ResourceBundle.getBundle(file);
        Enumeration<String> enumeration = bundle.getKeys();
        String key;
        while (enumeration.hasMoreElements()) {
            key = (String) enumeration.nextElement();
            prop.put(key, bundle.getObject(key));
        }
        return prop;
    }
	
	public static Boolean getNodeClosure() {
		if (nodeClosure == null)
			load();
		
		if (getManualAlignment() == true)
			return false;
		
		return nodeClosure;
	}

	public static Boolean getManualAlignment() {
		if (manualAlignment == null)
			load();
		return manualAlignment;
	}

	public static Boolean getPropertiesDirect() {
		if (propertiesDirect == null)
			load();
		return propertiesDirect;
	}

	public static Boolean getPropertiesIndirect() {
		if (propertiesIndirect == null)
			load();
		return propertiesIndirect;
	}

	public static Boolean getPropertiesWithOnlyDomain() {
		if (propertiesWithOnlyDomain == null)
			load();
		return propertiesWithOnlyDomain;
	}

	public static Boolean getPropertiesWithOnlyRange() {
		if (propertiesWithOnlyRange == null)
			load();
		return propertiesWithOnlyRange;
	}

	public static Boolean getPropertiesWithoutDomainRange() {
		if (propertiesWithoutDomainRange == null)
			load();
		return propertiesWithoutDomainRange;
	}

	public static Boolean getPropertiesSubClass() {
		if (propertiesSubClass == null)
			load();
		return propertiesSubClass;
	}

	public static String getKarmaSourcePrefix() {
		if (karmaSourcePrefix == null)
			load();
		return karmaSourcePrefix.trim();
	}

	public static String getKarmaServicePrefix() {
		if (karmaServicePrefix == null)
			load();
		return karmaServicePrefix.trim();
	}
	
	
	
}
