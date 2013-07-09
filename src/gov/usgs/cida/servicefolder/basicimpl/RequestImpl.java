package gov.usgs.cida.servicefolder.basicimpl;

import gov.usgs.cida.servicefolder.Verb;
import gov.usgs.cida.servicefolder.Request;
import java.net.URI;
import java.util.Map;

/**
 *
 * @author Bill Blondeau <wblondeau@usgs.gov>
 */
public class RequestImpl implements Request {
	
	private Verb verb;
	private URI endpointURI;
	private URI serviceDefinitionURI;
	private Map<String, Object> parameters;
	private Map<String, String> parametersSerialized;
	
	public RequestImpl (URI serviceDefinitionURI, URI endpointURI, 
			Verb verb, Map<String, Object> parameters) {
		
		//TODO sanity checks
		
		this.serviceDefinitionURI = serviceDefinitionURI;
		this.endpointURI = endpointURI;
		this.verb = Verb.GET;
		this.parameters = parameters;
		
	}

	@Override
	public Verb getVerb() {
		return this.verb;
	}

	@Override
	public Map<String, Object> getParameters() {
		return this.parameters;
	}

	@Override
	public Map<String, String> getSerializedParameters() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public URI getEndpointURI() {
		return this.endpointURI;
	}

	@Override
	public URI getServiceDefinitionURI() {
		return this.serviceDefinitionURI;
	}

	@Override
	public Request delegate(URI destination) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
