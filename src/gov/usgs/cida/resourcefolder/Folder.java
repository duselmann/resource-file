package gov.usgs.cida.resourcefolder;

import java.net.URI;
import java.util.List;
import java.util.Set;
import org.w3c.dom.Document;

/**
 * A recursive aggregating node for nested Resource references. This is the core
 * structural interface of Resource management.
 * 
 * A Folder has one primary method: <code>distribute(Request)</code>, which 
 * distributes the Request parameter to any contained <code>ResourcePoint</code>s that
 * offer the Resource that the Request is seeking; and to all contained Folders
 * for recursive work.
 * 
 * Each Folder contains two data structures:
 * <ul>
 *	<li>A <code>List</code> of Resource Points, which offer access to defined 
 *		Resources as defined in 
 *		<code>gov.usgs.cida.Resourcemanagement.ResourcePoint</code>.</li>
 *	<li>A <code>List</code> of other <code>gov.usgs.cida.nesting.ResourceFolder</code>
 *		instances.</li>
 * </ul>
 * 
 * @author Bill Blondeau <wblondea@usgs.gov>
 */
public interface Folder {
	
	/**
	 * Each ResourceFolder is considered to be a persistent entity. As such, 
	 * it is identified by its own URI. This URI should serve as, or be 
	 * dereferenceable to, an address URL.
	 * @return 
	 */
	public URI getFolderURI();
	
	public Set<ResourcePoint> getResourcePoints();
	
	public Set<Folder> getContainedFolders();
	
	/**
	 * An optional convenience method listing all Resource Definition URIs 
	 * offered by the ResourceFolder's Resource Points and Child ResourceFolders. 
	 * This is logically equivalent to a recursive invocation of 
	 * <code>getResourcePoints()</code> on <code>this</code> and all 
	 * of its nested children.
	 * 
	 * @return the URIs of all Resources accessible through the current instance.
	 */
	public Set<URI> getOfferedResources();
	
	/**
	 * A method specifically intended for reporting availability
	 * @return 
	 */
	public Document reportStatus();
	
	/**
	 * Forward the Resource Request to all offering instances, whether a
	 * ResourcePoint or a ResourceFolder.
	 * 
	 * When this method is invoked, the ResourceFolder will forward the Request
	 * to:
	 * <ul>
	 *	<li>Any ResourcePoints in its collection that offer the Resource 
	 *		identified by <code>request.getResourceDefinitionURI()</code></li>
	 *	<li>All of its contained Resource Folders, in case they or their
	 *		descendants support the Resource identified by 
	 *		<code>request.getResourceDefinitionURI()</code></li>
	 * </ul>
	 * 
	 * The Response message body will be a tree structure representing
	 * <ul>
	 *	<li>the current instance as root</li>
	 *	<li>all responding descendant Folders in their proper location
	 *		in the tree</li>
	 *	<li>Each ResourcePoint offering the Resource, also in its proper
	 *		location in the tree.</li>
	 *	<li>All Responses from each ResourcePoint, located in the tree
	 *		under their respective ResourcePoints.</li>
	 * </ul>
	 * 
	 * The canonical form of the MessageBody is XML. However, other forms of 
	 * the Response message body are also acceptable, assuming they meet the 
	 * criteria of HTTP consistency. 
	 * 
	 * Note that datasets in the message body may be provided directly, or
	 * may be indirectly supplied by returning a URL pointing to the
	 * location at which the specific dataset is posted.
	 * 
	 * Note that any messages attached to an Error status (4xx or 5xx) can
	 * be left as plain text if that's the understood target form.
	 */
	public Response distribute(Request request);
	
	
	public Response collect(List<Response> collectedResponses);

}
