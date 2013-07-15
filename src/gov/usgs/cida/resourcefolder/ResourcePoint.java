package gov.usgs.cida.servicefolder;

import java.net.URI;
import org.w3c.dom.Document;

/**
 * This Interface provides the external face of a CIDA Service.
 * 
 * @author Bill Blondeau <wblondeau@usgs.gov>
 */
public interface ServicePoint {
	
	/**
	 * Each ServicePoint is considered to be a persistent entity. As such, it
	 * is identified by its own URI.
	 * @return the URI identifying this persistent instance of the Service
	 */
	public URI getEndpointURI();
	
	/**
	 * In order to be offered by a ServicePoint instance, a Service must
	 * have a definitional URI.
	 * @return the URI of the definition of the Service offered by this instance
	 */
	public URI getServiceDefinitionURI();
	
	/**
	 * Since this Interface concerns itself primarily with HTTP wire operations,
	 * the offered service must describe its API using a serialized declaration.
	 * The Service API definition MUST include, <em>for each Request defined
	 * by the Service</em>, 
	 * <ul>
	 *	<li>the verb (GET/PUT/POST/DELETE)</li>
	 *	<li>the accepted Request parameters with name, value constraints, 
	 *		and semantics of each</li>
	 *	<li>Internet Media Type (aka MIME Type) of the returned content in 
	 *		the message body.</li>
	 * </ul>
	 * 
	 * The Request definition SHOULD include:
	 * <ul>
	 *	<li>an informative textual description of the Request's semantics</li>
	 *	<li>any constraints (for example, XML Schema or Relax NG) that will 
	 *		apply to the returned representation</li>
	 *	<li>a declaration of significant side effects (i.e., meaningful 
	 *		serverside state changes)</li>
	 *	<li>any specific reasons for certain error codes (do not re-document 
	 *		well-understood error conditions! It's only desirable to make a
	 *		point of describing specific error semantics)</li>
	 * </ul>
	 * 
	 * The API message should be machine-readable. If not externally defined,
	 * the necessary machine vocabularies and semantics should be stated in 
	 * human-readable terms in this API definition.
	 * 
	 * @return A serialized representation (presumably in XML) of the service
	 *		API
	 */
	public String getAPI();
	
	/**
	 * This OPTIONAL convenience method provides the API description as 
	 * a W3C DOM object.
	 * @return the W3C DOM Document which would result if the return of 
	 *		<code>this.getAPI()</code> were parsed by an XML DOM parser.
	 * @throws UnsupportedOperationException If the implementation does not
	 *		provide this convenience method. (Parse it yourself.)
	 */
	public Document getAPIDOM() throws UnsupportedOperationException;
	
	/**
	 * This is the core Service execution logic. It emulates the HTTP
	 * Request/Response pattern.
	 * 
	 * @param serviceURI
	 * @param request
	 * @return 
	 */
	public Response callService(Request request);

	
}