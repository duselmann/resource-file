package gov.usgs.cida.resourcefolder;

import java.net.URI;
import java.net.URL;
import java.util.Map;

/**
 * This Java interface is partially consistent with the HTTP Request.
 * The purpose of this Interface is:
 * <ul>
 *	<li>To declare the required behavior of a Request in the CIDA Resource
 *		Management framework;</li>
 *	<li>To provide easy translation between this Interface and the various
 *		forms of HTTP Request (e.g. serialized, Servlet Request);</li>
 *	<li>To permit implementations optimized for different circumstances, 
 *		e.g. Stream-based, String-based, compressed binary, reference-based,
 *		etc.</li>
 * </ul>
 * 
 * @author Bill Blondeau <wblondeau@usgs.gov>
 */
public interface Request extends Message {
	
	/**
	 * This is the URI that is equivalent to the URL of the target Resource 
	 * endpoint: the destination address of the Request.
	 * @return 
	 */
	public URI getEndpointURI();
	
	public Verb getVerb();
	
	public Map<String, Object> getURLParameters();
	
	
	/**
	 * Returns a new instance with its URI replaced by the URI corresponding
	 * to the new destination URI. Main purpose is to create separate 
	 * instances for distribution by Resource Folder
	 * @param destination
	 * @return 
	 */
	public Request delegate(URI endpointURI);
	
}
