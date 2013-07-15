package gov.usgs.cida.resourcefolder;

import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Array;
import org.w3c.dom.Document;

/**
 * All <code>MessageBody</code> instances must support a <code>deliverAsStream</code>
 * method. Sometimes, however, it's convenient to deliver a message body in
 * a different form. 
 * 
 * This class enumerates <strong>additional convenience 
 * representations</strong> which may (or may not) be provided by a 
 * <code>MessageBody</code> implementation.
 * 
 * Each member of the enum is given a Class attribute which represents a 
 * delivery type corresponding to the enum.
 * 
 * Note that the return type for BYTE_ARRAY is <code>java.lang.reflect.Array</code>.
 * The "byte" part of <code>byte[]</code> is assumed, since byte arrays are
 * sometimes useful to deliver binary files.
 * 
 * @author Bill Blondeau <wblondeau@usgs.gov>
 */
public enum ContentDeliveryType {
	STREAM (InputStream.class),
	CHARACTER (Reader.class),
	BYTE_ARRAY (Array.class),
	STRING (String.class),
	DOM_DOCUMENT (Document.class);
	
	private final Class deliveryClass;
	
	private ContentDeliveryType(Class deliveryClass) {
		this.deliveryClass = deliveryClass;
	}
	
	public Class getDeliveryClass() {
		return this.deliveryClass;
	}
	
}
