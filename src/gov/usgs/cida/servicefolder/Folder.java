package gov.usgs.cida.servicefolder;

import java.net.URI;
import java.util.List;
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
 *		The Service Points each only represents one defined service can represent any mix of services: a ServiceFolder may
 *		have multiple ServicePoints offering the same service but drawing from 
 *		different back-end resources, or it may have a completely heterogeneous
 *		set of services, or any mixture of those two concepts.</li>
 *	<li>A <code>List</code> of other <code>gov.usgs.cida.nesting.ServiceFolder</code>
 *		instances. Each ServiceFolder in the Set, being recursive, is a
 *		Tree structure in general terms.</li>
 * </ul>
 * 
 * In order to make sense of how a ServiceFolder is used to manage Services,
 * there's a close analogy with the normal filesystem. Think of ServiceFolders 
 * as folders, Service Points as files.
 * 
 * This Interface defines a <em>ServiceFolder General Contract</em> of behavior. Any 
 * implementing class that does not correctly obey this Contract is an invalid
 * implementation.
 * 
 * <h4>Provisions of the ServiceFolder General Contract</h4>
 * <ul>
 *	<li><em>Nesting is acyclic.</em> No ServiceFolder may appear more than once
 *		in the subtrees of any given ServiceFolder.</li>
 *	<li><em>A Service offered by ServiceFolders can be accessed through the
 *		parent ServiceFolder</em> even if the parent does not have a ServicePoint
 *		for that Service.</li>
 * </ul>
 * 
 * @author Bill Blondeau <wblondea@usgs.gov>
 */
public interface Folder {
	
	/**
	 * Each ServiceFolder is considered to be a persistent entity. As such, 
	 * it is identified by its own URI.
	 * @return 
	 */
	public URI getFolderURI();
	
	public List<ServicePoint> getServicePoints();
	
	public List<Folder> getContainedFolders();
	
	/**
	 * An optional convenience method listing all Service URIs offered by the 
	 * ServiceFolder's Service Points and Child ServiceFolders. This is 
	 * logically equivalent to a recursive invocation of 
	 * <code>getServicePoints().keys()</code> on <code>this</code> and all 
	 * of its nested children.
	 * 
	 * @return the URIs of all Services accessible through the current instance.
	 */
	public List<URI> getOfferedServices();
	
	public Document reportStatus();
	
	/**
	 * Forward the Service Request to all offering instances, whether a
	 * ServicePoint or a ServiceFolder.
	 * 
	 * When this method is invoked, the ServiceFolder will forward the Request
	 * to:
	 * <ul>
	 *	<li>Any ServicePoints in its collection that offer the service 
	 *		identified by <code>request.getServiceDefinitionURI()</code></li>
	 *	<li>All of its contained Service Folders, in case they or their
	 *		descendants support the service identified by 
	 *		<code>request.getServiceDefinitionURI()</code></li>
	 * </ul>
	 * 
	 * The Response message body will be a tree structure representing
	 * <ul>
	 *	<li>the current instance as root</li>
	 *	<li>all responding descendant Folders in their proper location
	 *		in the tree</li>
	 *	<li>Each ServicePoint offering the Service, also in its proper
	 *		location in the tree.</li>
	 *	<li>All Responses from each ServicePoint, located in the tree
	 *		under their respective ServicePoints.</li>
	 * </ul>
	 * 
	 * The canonical form of the MessageBody is XML, because it is excellent 
	 * for representing acyclic tree structures, because it's well-understood
	 * and widely supported, and because its serialization is HTTP-friendly.
	 * 
	 * However, other forms of the Response message body are also compliant,
	 * assuming they meet the criteria above. Rule of thumb: if 
	 * the response.getMessageBody() contains data whose form is understood
	 * and which can be converted into the XML canonical form with no
	 * pieces missing, it's valid.
	 * 
	 * Note that datasets in the message body may be provided directly, or
	 * may be indirectly supplied by returning a URL pointing to the
	 * location at which the specific dataset is posted.
	 * 
	 * Note that any messages attached to an Error status (4xx or 5xx) can
	 * be left as plain text if that's the understood target form.
	 */
	public Response delegate(Request request);

}
