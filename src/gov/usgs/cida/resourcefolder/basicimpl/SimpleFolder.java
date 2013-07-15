package gov.usgs.cida.resourcefolder.basicimpl;

import gov.usgs.cida.resourcefolder.Folder;
import gov.usgs.cida.resourcefolder.Request;
import gov.usgs.cida.resourcefolder.Response;
import gov.usgs.cida.resourcefolder.ResourcePoint;
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
	private Set<ResourcePoint> resourcePoints = new HashSet<>();
	private Set<Folder> childFolders = new HashSet<>();
	
	

	@Override
	public URI getFolderURI() {
		return this.folderURI;
	}

	@Override
	public List<ResourcePoint> getResourcePoints() {
		// defensive copy
		return new ArrayList<>(this.resourcePoints);
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
	public Response distribute(Request request) {
		
		// this is where the delegation to all contained items happens.
		
		// TODO sanity
		
		List<Response> collectedResponses = new ArrayList<>();
		
		// execute any 
		for (Iterator<ResourcePoint> it = this.resourcePoints.iterator(); it.hasNext();) {
			ResourcePoint current = it.next();
			if (request.getResourceDefinitionURI().equals(current.getResourceDefinitionURI())) {

				// make a Request copy aimed at the ResourcePoint
				Request toResource = request.delegate(current.getEndpointURI());
				// call
				Response fromResource = current.callResource(toResource);
				
				// stash
				collectedResponses.add(fromResource);
			}
		}
		
		// now, forward to each of the child folders in turn
		for (Iterator<Folder> it = this.childFolders.iterator(); it.hasNext();) {
			Folder current = it.next();
			
			// make a request and send it recursively
			Request toChild = request.delegate(current.getFolderURI());

			collectedResponses.add(current.distribute(toChild));
		}
		
		return null;
	}

	@Override
	public List<URI> getOfferedResources() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
