package gov.usgs.cida.resourcefolder.basicimpl;

import gov.usgs.cida.resourcefolder.MessageBody;
import gov.usgs.cida.resourcefolder.Verb;
import gov.usgs.cida.resourcefolder.Request;
import gov.usgs.cida.resourcefolder.Util;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 
 * @author Bill Blondeau <wblondeau@usgs.gov>
 */
public class RequestImpl implements Request {
	
	private Verb verb;
	private URI endpointURI;
	private URI resourceDefinitionURI;
	private Map<String, Object> parameters;
	private Map<String, String> parametersSerialized;
	private Map<String, String> headers;
	private MessageBody messageBody;
	
	public RequestImpl (URI resourceDefinitionURI, URI endpointURI, 
			Verb verb, Map<String, Object> parameters, 
			Map<String, String> headers, MessageBody messageBody) {
		
		//TODO sanity checks
		
		this.resourceDefinitionURI = resourceDefinitionURI;
		this.endpointURI = endpointURI;
		this.verb = Verb.GET;
		this.parameters = parameters;
		this.messageBody = messageBody;
		
	}

	@Override
	public URI getResourceDefinitionURI() {
		return this.resourceDefinitionURI;
	}
	
	@Override
	public Verb getVerb() {
		return this.verb;
	}

	@Override
	public Map<String, Object> getURLParameters() {
		return this.parameters;
	}

	@Override
	public URI getEndpointURI() {
		return this.endpointURI;
	}

	@Override
	public Map<String, String> getHeaders() {
		return new HashMap<>(headers);
	}

	@Override
	public MessageBody getMessageBody() {
		return this.messageBody;
	}
	

	@Override
	public Request delegate(URI destination) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public String getHTTPStartLine() {
		return this.verb.toString() + " " + this.getEndpointURI() + " HTTP/1.1" + Util.CRLF;
	}

	@Override
	public String toString() {
		String retval = this.getHTTPStartLine() + Util.CRLF;
		// headers
		for (Iterator<String> it = this.headers.keySet().iterator(); it.hasNext();) {
			String headername = it.next();
			retval += headername + ": " + this.headers.get(headername) + Util.CRLF;
		}
		
		// terminate headers
		retval += Util.CRLF;
		
		retval += this.messageBody.deliverAsString();
		
		return retval;
	}

}
