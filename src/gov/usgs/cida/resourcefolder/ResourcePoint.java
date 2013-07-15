package gov.usgs.cida.resourcefolder;

import java.net.URI;
import org.w3c.dom.Document;

/**
 * This Interface provides the external face of a CIDA Resource.
 * 
 * @author Bill Blondeau <wblondeau@usgs.gov>
 */
public interface ResourcePoint {
	
	/**
	 * Each ResourcePoint is considered to be a persistent entity. As such, it
	 * is identified by its own URI.
	 * @return the URI identifying this persistent instance of the Resource
	 */
	public URI getEndpointURI();
	
	/**
	 * In order to be offered by a ResourcePoint instance, a Resource must
	 * have a definitional URI.
	 * @return the URI of the definition of the Resource offered by this instance
	 */
	public URI getResourceDefinitionURI();
	
	/**
	 * This is the core Resource execution logic. It emulates the HTTP
	 * Request/Response pattern.
	 * 
	 * @param resourceURI
	 * @param request
	 * @return 
	 */
	public Response callResource(Request request);

	
}