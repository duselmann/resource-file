package gov.usgs.cida.servicefolder.basicimpl;

import gov.usgs.cida.servicefolder.Folder;
import gov.usgs.cida.servicefolder.Request;
import gov.usgs.cida.servicefolder.Response;
import gov.usgs.cida.servicefolder.ServicePoint;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.w3c.dom.Document;

/**
 *
 * @author Bill Blondeau <wblondeau@usgs.gov>
 */
public class SimpleFolder implements Folder {
	
	private URI nodeURI;
	private Map<URI, ServicePoint> servicePoints = new HashMap<>();
	private Set<Folder> childNodes = new HashSet<>();
	
	

	@Override
	public URI getNodeURI() {
		return this.nodeURI;
	}

	@Override
	public Map<URI, ServicePoint> getServicePoints() {
		// defensive copy
		return new HashMap<>(this.servicePoints);
	}

	@Override
	public Set<Folder> getChildNodes() {
		return new HashSet<>(this.childNodes);
	}

	@Override
	public Set<URI> getOfferedServices() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Document reportStatus() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Document reportJobStatus(int uowID) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Response dispatch(Request request) {
		// this is where the delegation happens.
		
		// TODO sanity
		
		List<Response> collectedResponses = new ArrayList<>();
		
		// is there a ServicePoint for this service?
		for (Iterator<URI> it = this.servicePoints.keySet().iterator(); it.hasNext();) {
			URI current = it.next();
			if (request.getServiceDefinitionURI().equals(current)) {
				// this key identifies the ServicePoint we want to call. (there
				// will only be one... WAT. Is that right? TODO
				ServicePoint curpoint = this.servicePoints.get(current);
				// make a Request copy aimed at the ServicePoint
				Request toService = request.delegate(curpoint.getEndpointURI());
				// call
				Response fromService = curpoint.callService(toService);
				// stash
				collectedResponses.add(fromService);
			}
		}
		
		// now, forward to each of the child nodes in turn
		for (Iterator<Folder> it = this.childNodes.iterator(); it.hasNext();) {
			Folder current = it.next();
			// make a request and send it recursively
			Request toChild = request.delegate(current.getNodeURI());
			collectedResponses.add(current.dispatch(toChild));
		}
		
		return null;
	}
	
}
