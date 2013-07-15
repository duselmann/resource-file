package gov.usgs.cida.resourcefolder;

import java.net.URI;
import java.util.Map;

/**
 *
 * @author Bill Blondeau <wblondeau@usgs.gov>
 */
public interface Message {

	/**
	 * The ResourceDefinitionURI identifies a definition of the semantics 
	 * and characteristics of the Resource's request/response behavior.
	 * @return 
	 */	
	URI getResourceDefinitionURI();
	
	/**
	 * Convenience method. Returns an HTTP 1.1 start-line for the instance.
	 * @return 
	 */
	String getHTTPStartLine();
	
	Map<String, String> getHeaders();

	MessageBody getMessageBody();

	
}
