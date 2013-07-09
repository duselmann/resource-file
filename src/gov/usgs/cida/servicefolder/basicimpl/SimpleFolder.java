package gov.usgs.cida.servicefolder.basicimpl;

import gov.usgs.cida.servicefolder.Folder;
import gov.usgs.cida.servicefolder.Request;
import gov.usgs.cida.servicefolder.Response;
import gov.usgs.cida.servicefolder.ServicePoint;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.w3c.dom.Document;

/**
 *
 * @author Bill Blondeau <wblondeau@usgs.gov>
 */
public class SimpleFolder implements Folder {
	
	private URI folderURI;
	private Set<ServicePoint> servicePoints = new HashSet<>();
	private Set<Folder> childFolders = new HashSet<>();
	
	

	@Override
	public URI getFolderURI() {
		return this.folderURI;
	}

	@Override
	public List<ServicePoint> getServicePoints() {
		// defensive copy
		return new ArrayList<>(this.servicePoints);
	}

	@Override
	public List<Folder> getContainedFolders() {
		return new ArrayList<>(this.childFolders);
	}

	@Override
	public Document reportStatus() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}


	@Override
	public Response delegate(Request request) {
		
		// this is where the delegation to all contained items happens.
		
		// TODO sanity
		
		List<Response> collectedResponses = new ArrayList<>();
		
		// execute any 
		for (Iterator<ServicePoint> it = this.servicePoints.iterator(); it.hasNext();) {
			ServicePoint current = it.next();
			if (request.getServiceDefinitionURI().equals(current.getServiceDefinitionURI())) {

				// make a Request copy aimed at the ServicePoint
				Request toService = request.delegate(current.getEndpointURI());
				// call
				Response fromService = current.callService(toService);
				
				// stash
				collectedResponses.add(fromService);
			}
		}
		
		// now, forward to each of the child folders in turn
		for (Iterator<Folder> it = this.childFolders.iterator(); it.hasNext();) {
			Folder current = it.next();
			
			// make a request and send it recursively
			Request toChild = request.delegate(current.getFolderURI());

			collectedResponses.add(current.delegate(toChild));
		}
		
		return null;
	}

	@Override
	public List<URI> getOfferedServices() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
