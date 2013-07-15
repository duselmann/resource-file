package gov.usgs.cida.resourcefolder;

import java.net.URI;

/**
 * 
 * @author Bill Blondeau <wblondeau@usgs.gov>
 */
public interface Response extends Message {
	
	/**
	 * This is endpoint data for the ResourcePoint that generated this Response.
	 * 
	 * @return 
	 */
	public URI getResponsibleEndpoint();
	
	public StatusCode getStatus();
	
	
}
