package gov.usgs.cida.resourcefolder.basicimpl;

import gov.usgs.cida.resourcefolder.StatusCode;
import gov.usgs.cida.resourcefolder.MessageBody;
import gov.usgs.cida.resourcefolder.Response;
import gov.usgs.cida.resourcefolder.Util;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Bill Blondeau <wblondeau@usgs.gov>
 */
public class ResponseImpl implements Response {
	
	private URI resourceDefinitionURI;
	private URI responsibleEndpoint;
	private StatusCode statusCode;
	private Map<String, String> headers;
	private MessageBody messageBody;
	
	public ResponseImpl(URI resourceDefinitionURI, URI responsibleEndpoint,
			StatusCode statusCode, Map<String, String> headers, MessageBody messageBody) {
		
		this.resourceDefinitionURI = resourceDefinitionURI;
		this.responsibleEndpoint = responsibleEndpoint;
		this.statusCode = statusCode;
		this.messageBody = messageBody;
	}

	@Override
	public URI getResourceDefinitionURI() {
		return this.resourceDefinitionURI;
	}
	
	@Override
	public URI getResponsibleEndpoint() {
		return this.responsibleEndpoint;
	}

	@Override
	public StatusCode getStatus() {
		return this.statusCode;
	}
	
	@Override
	public Map<String, String> getHeaders() {
		return new HashMap<>(this.headers);
	}

	@Override
	public MessageBody getMessageBody() {
		return this.messageBody;
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

	@Override
	public String getHTTPStartLine() {
		return "HTTP/1.1 " + this.statusCode;
	}

	
}
