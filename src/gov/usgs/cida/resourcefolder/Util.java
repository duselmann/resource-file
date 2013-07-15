package gov.usgs.cida.resourcefolder;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * A noninstantiable library class providing functions of general utility
 * for CIDA Resource Management.
 * 
 * @author Bill Blondeau <wblondeau@usgs.gov>
 */
public class Util {
	
	/**
	 * The convention in HTTP is to use a carriage return-linefeed sequence
	 * as a line terminator. That's this, provided as a convenience.
	 */
	public static final String CRLF = "\r\n";
	
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
	 * Creates a formally correct HTTP Request message from the parameter.
	 * 
	 * Note that the returned message must conform to the HTTP/1.1 format. 
	 * Such conformance includes, but is not limited to:
	 * <ul>
	 *	<li>The use of CR/LF sequence as a line terminator</li>
	 *	<li>Correct <code>Content-length</code> and <code>Content-type</code>
	 *		headers</li>
	 * </ul>
	 * Note that this Interface uses a nonstandard header for stashing the
	 * information returned by getResourceURI(): <code>Resource-definition</code>
	 * 
	 * @return 
	 */
	public String serializeToHTTP(Message message) {
		return "bah!";
	}
	
	
	/**
	 * A convenience method to provide a formally correct HTTP Request message
	 * from the instance, omitting the message body. This is equivalent to
	 * <code>this.serializeToHttp().substring(0, this.indexOf("\r\n\r\n") + 1)</code>
	 * if there is a message body, and identical to <code>this.serializeToHttp()</code>
	 * if there is no message body.
	 * 
	 * The purpose of this method is primarily support for development and
	 * diagnostics when handling an inconveniently large file.
	 * 
	 * @return 
	 */
	public String serializeToBodilessHTTP(Message message) {
		return "bah!";
	}
}
