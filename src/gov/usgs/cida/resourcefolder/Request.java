package gov.usgs.cida.servicefolder;

import java.net.URI;
import java.net.URL;
import java.util.Map;

/**
 * This Java interface is partially consistent with the HTTP Request.
 * The purpose of this Interface is:
 * <ul>
 *	<li>To declare the required behavior of a Request in the CIDA Service
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
public interface Request {
	
	/**
	 * This is the URI that is equivalent to the URL of the target service 
	 * endpoint: the destination of the Request. It is present in this Interface
	 * to permit stateless intercepts. Unless modified, the Request can 
	 * always be forwarded to its original destination.
	 * @return 
	 */
	public URI getEndpointURI();
	
	/**
	 * The ServiceDefinitionURI points to a definition of a Service that is
	 * <ul>
	 *	<li>Web-accessible</li>
	 *	<li>Sufficient to describe all uses of the Service</li>
	 *	<li>Preferably, but not necessarily, machine-readable.</li>
	 * </ul>
	 * 
	 * At minimum, a Service describes a Request-Response tree in which all
	 * supported Request Verbs are listed. For each such Verb, the valid
	 * parameters should be called out, their semantics described, and their 
	 * value ranges called out. Responses, including semantically significant 
	 * 
	 * 
	 * @return 
	 */
	public URI getServiceDefinitionURI();

	public Verb getVerb();
	
	public Map<String, Object> getParameters();
	
	/**
	 * Optional: Provides urlParameters in properly escaped (urlencoded) form.
	 * Convenience method for obtaining wire params.
	 * @return 
	 * @throws UnsupportedOperationException if not implemented.
	 */
	public Map<String, String> getSerializedParameters()
			throws UnsupportedOperationException;
	
	/**
	 * Returns a new instance with its URI replaced by the URI corresponding
	 * to the new destination URI.
	 * @param destination
	 * @return 
	 */
	public Request delegate(URI destination);
	
}
