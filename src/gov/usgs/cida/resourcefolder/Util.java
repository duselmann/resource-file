package gov.usgs.cida.servicefolder;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * A noninstantiable library class providing functions of general utility
 * for CIDA Service Management.
 * 
 * @author Bill Blondeau <wblondeau@usgs.gov>
 */
public class Util {
	
	//to enforce noninstantiability
	private Util() {}
	
	
	/**
	 * Cleans, normalizes, and verifies that the argument makes an acceptable
	 * URL.
	 * 
	 * @param target
	 * @return 
	 */
	public static URI validTarget(URI target) {
		if (target == null) throw new IllegalArgumentException("Null target");
		
		target = target.normalize();
		// roundtrip through URL should squeeze out any gross errors of form
		try {
			target = target.toURL().toURI();
		}
		catch (MalformedURLException | URISyntaxException urex) {
			throw new IllegalArgumentException("unacceptable target URI", urex);
		}
			 
		return target;
	}
	

	/**
	 * Creates a formally correct HTTP Request message from the instance.
	 * 
	 * Note that the returned message must conform to the HTTP/1.1 format. 
	 * Such conformance includes, but is not limited to:
	 * <ul>
	 *	<li>The use of CR/LF sequence as a line terminator</li>
	 *	<li>Correct <code>Content-length</code> and <code>Content-type</code>
	 *		headers</li>
	 * </ul>
	 * Note that this Interface uses a nonstandard header for stashing the
	 * information returned by getServiceURI(): <code>Service-definition</code>
	 * 
	 * @return 
	 */
	public String serializeToHTTP() {
		return "bah!";
	}
	
	
	/**
	 * A convenience method to provide a formally correct HTTP Request message
	 * from the instance, omitting the message body. This is equivalent to
	 * <code>this.serializeToHttp().substring(0, this.indexOf("\r\n\r\n") + 1)</code>
	 * if there is a message body, and identical to <code>this.serializeToHttp()</code>
	 * if there is no message body.
	 * The purpose of this method is primarily support for development and
	 * diagnostics when uploading an inconveniently large file. Discarding 
	 * the message body has no useful production function.
	 * 
	 * @return 
	 */
	public String serializeToBodilessHTTP() {
		return "bah!";
	}
}
