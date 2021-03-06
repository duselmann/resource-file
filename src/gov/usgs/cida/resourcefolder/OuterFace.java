package gov.usgs.cida.resourcefolder;

import java.net.URI;
import java.util.Map;
import org.w3c.dom.Document;

/**
 * The user-facing item in this package. Suitable for connection by
 * web applications, or for exposure as a Resource.
 * 
 * The OuterFace exposes Resources wrapped in a parameterized call method
 * that modifies the process or response according to the OuterFace's
 * contract.
 * 
 * @author Bill Blondeau <wblondeau@usgs.gov>
 */
public interface OuterFace {
	
	public String getParamDefs();
	
	public Document getParamDefsDOM();
	
	/**
	 * The <code>outerFaceParams</code> are defined externally to the Resource
	 * definition found at <code>resourceURI</code>. They serve purposes 
	 * related to user interaction, rather than to the operation of the Resource
	 * proper. Examples:
	 * <ul>
	 *	<li>A Resource provides a response dataset in XML form. The user wants the data
	 *		transformed into a tabular representation, e.g. CSV or TSV. These
	 *		formats are not offered in the Resource's contract. The 
	 *		<code>OuterFace</code> implementation may offer such a transformation
	 *		as part of its contract with the user, invoked by an 
	 *		<code>outerFaceParam</code> such as "<code>mimeType=csv</code>".</li>
	 *	<li>An <code>OuterFace</code> aggregates Resource calls from several
	 *		different sources. The user wants the resulting dataset sorted. The
	 *		<code>OuterFace</code> offers the necessary mergesort, using an
	 *		<code>outerFaceParam</code> such as 
	 *		"<code>order-by=Characteristic,statecode,countycode</code>".</li>
	 *	<li>The user wants to wait for a synchronous response to a query, although
	 *		the back-end Resource exposes only an asynchronous response model. The
	 *		<code>OuterFace</code> assumes the responsibility of providing the
	 *		user with the synchronous (blocking) conversation, with a timed
	 *		rollover to the asynchronous pattern, with <code>outerFaceParam</code>s
	 *		such as "<code>sync=true</code>", "<code>timeout=30</code>"
	 * </ul>
	 * Note that those examples are exactly that: examples. This interface does
	 * not define any specific parameter names or value spaces, only the ability
	 * to offer them to users.
	 * 
	 * 
	 * @param ResourceURI
	 * @param request
	 * @param outerFaceParams
	 * @return
	 * @throws IllegalArgumentException 
	 */
	public Response callResources(URI ResourceURI, Request request, 
			Map <String, Object> outerFaceParams) throws IllegalArgumentException;	
}
