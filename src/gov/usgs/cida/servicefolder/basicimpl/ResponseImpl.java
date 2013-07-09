package gov.usgs.cida.servicefolder.basicimpl;

import gov.usgs.cida.servicefolder.StatusCode;
import gov.usgs.cida.servicefolder.MessageBody;
import gov.usgs.cida.servicefolder.Response;
import java.net.URI;

/**
 *
 * @author Bill Blondeau <wblondeau@usgs.gov>
 */
public class ResponseImpl implements Response {
	
	private URI serviceDefinitionURI;
	private URI responsibleEndpoint;
	private StatusCode statusCode;
	private MessageBody messageBody;
	
	public ResponseImpl(URI serviceDefinitionURI, URI responsibleEndpoint,
			StatusCode statusCode, MessageBody messageBody) {
		
		this.serviceDefinitionURI = serviceDefinitionURI;
		this.responsibleEndpoint = responsibleEndpoint;
		this.statusCode = statusCode;
		this.messageBody = messageBody;
	}

	@Override
	public URI getServiceDefinitionURI() {
		return this.serviceDefinitionURI;
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
	public MessageBody getMessageBody() {
		return this.messageBody;
	}
	
	@Override
	public String toString() {
		String retval = "HTTP/1.1 " + this.statusCode + "\r\n";
		// not doing headers just yet...
		
		// terminate headers
		retval += "\r\n";
		
		retval += this.messageBody.deliverAsString();
		
		return retval;
	}

	
}
