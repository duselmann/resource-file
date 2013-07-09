package gov.usgs.cida.servicefolder;

import java.net.URI;

/**
 * 
 * @author Bill Blondeau <wblondeau@usgs.gov>
 */
public interface Response {
	
	public URI getServiceDefinitionURI();
	
	/**
	 * This is endpoint data for the service point that is taking responsibility
	 * for the Response. It's possible that the request was further delegated,
	 * but the CIDA Service management framework has no way of knowing about 
	 * that. In the aggregation tree, this identifies a leaf node.
	 * @return 
	 */
	public URI getResponsibleEndpoint();
	
	public StatusCode getStatus();
	
	public MessageBody getMessageBody();
	
	
}
