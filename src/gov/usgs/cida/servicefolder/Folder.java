package gov.usgs.cida.servicefolder;

import gov.usgs.cida.servicefolder.ServicePoint;
import java.net.URI;
import java.util.Map;
import java.util.Set;
import org.w3c.dom.Document;

/**
 * A recursive aggregating node for nested service references. This is the core
 * structural interface of Service management. It is named a Folder as a reference
 * to the standard metaphor of an ordinary file system: Folders, as defined here,
 * are very much like filesystem folders. Folders can contain Service Points,
 * just as 
 * 
 * Each Folder contains two data structures:
 * <ul>
 *	<li>A <code>List</code> of Service Points, which offer access to defined 
 *		services as defined in <code>gov.usgs.cida.servicemanagement.ServicePoint</code>.
 *		The Service Points can represent any mix of services: a NestedNode may
 *		have multiple ServicePoints offering the same service but drawing from 
 *		different back-end resources, or it may have a completely heterogeneous
 *		set of services, or any mixture of those two concepts.</li>
 *	<li>A <code>Set</code> of other <code>gov.usgs.cida.nesting.NestedNode</code>
 *		instances. Each NestedNode in the Set, being recursive, is of course a
 *		Tree structure in general terms.</li>
 * </ul>
 * 
 * In order to make sense of how a NestedNode is used to manage Services,
 * there's a close analogy with the normal filesystem. Think of NestedNodes as 
 * Folders.
 * 
 * This Interface defines a <em>NestedNode General Contract</em> of behavior. Any 
 * implementing class that does not correctly obey this Contract is an invalid
 * implementation.
 * 
 * <h4>Provisions of the NestedNode General Contract</h4>
 * <ul>
 *	<li><em>Nesting is acyclic.</em> No NestedNode may appear more than once
 *		in the subtrees of NestedNodes.</li>
 *	<li><em>A Service offered by NestedNodes can be accessed through the
 *		parent NestedNode</em> even if the parent does not have a ServicePoint
 *		for that Service.</li>
 *		
 * </ul>
 * 
 * @author Bill Blondeau <wblondea@usgs.gov>
 */
public interface Folder {
	
	/**
	 * Each NestedNode is considered to be a persistent entity. As such, it is identified
	 * by its own URI.
	 * @return 
	 */
	public URI getNodeURI();
	
	public Map<URI, ServicePoint> getServicePoints();
	
	public Set<Folder> getChildNodes();
	
	/**
	 * A convenience method listing all Service URIs offered by the NestedNode's
	 * Service Points and Child NestedNodes. This is logically equivalent to
	 * a recursive invocation of <code>getServicePoints().keys()</code> on
	 * <code>this</code> and all of its nested children.
	 * @return the URIs of all Services accessible through the current instance.
	 */
	public Set<URI> getOfferedServices();
	
	public Document reportStatus();
	
	public Document reportJobStatus(int uowID);
	
	/**
	 * Forward the Service Request to all offering instances, whether a
	 * local ServicePoint or a child NestedNode.
	 * 
	 * When this method is invoked, the NestedNode will forward the invocation
	 * to:
	 * <ul>
	 *	<li>A ServicePoint in its collection that offers the service identified
	 *		by <code>request.getServiceDefinitionURI()</code>, 
	 *		if one is present</li>
	 *	<li>All of its Child Nodes, in case they or their descendants
	 *		support the service identified by 
	 *		<code>request.getServiceDefinitionURI()</code></li>
	 * </ul>
	 * 
	 */
	public Response dispatch(Request request);


	
}
