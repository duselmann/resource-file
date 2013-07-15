package gov.usgs.cida.servicefolder;

import java.net.URI;

/**
 * 
 * @author Bill Blondeau <wblondeau@usgs.gov>
 */
public interface Response {
	
	public URI getServiceDefinitionURI();
	
	/**
	 * This is endpoint data for the ServicePoint that generated this Response.
	 * 
	 * @return 
	 */
	public URI getResponsibleEndpoint();
	
	public StatusCode getStatus();
	
	public MessageBody getMessageBody();
	
	
}
