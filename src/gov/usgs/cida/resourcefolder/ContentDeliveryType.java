package gov.usgs.cida.servicefolder;

import java.io.InputStream;
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
 * The "byte" part of <code>byte[]</code> is assumed, since no other array
 * type is particularly sensible for delivering a message body.
 * 
 * @author Bill Blondeau <wblondeau@usgs.gov>
 */
public enum AuxiliaryDeliveryType {
	CHARACTER (InputStream.class),
	BYTE_ARRAY (Array.class),
	STRING (String.class),
	DOM_DOCUMENT (Document.class);
	
	private final Class deliveryClass;
	
	private AuxiliaryDeliveryType(Class deliveryClass) {
		this.deliveryClass = deliveryClass;
	}
	
	public Class getDeliveryClass() {
		return this.deliveryClass;
	}
	
}
