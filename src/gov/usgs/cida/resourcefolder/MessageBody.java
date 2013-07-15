package gov.usgs.cida.servicefolder;

import java.io.InputStream;
import java.io.Reader;
import java.util.List;
import org.w3c.dom.Document;

/**
 * This interface provides a convenient and flexible set of methods for 
 * delivering content corresponding to an HTTP Message Body. The default,
 * and required, method for delivery returns the message body content
 * as an <code>InputStream</code>.
 * 
 * The other methods defined in this interface are optional, and provided
 * for practical convenience. The <code>getAuxiliaryDeliveryTypes</code>
 * method allows calling code to test for availability before calling
 * any of the optional methods.
 * 
 * It is very definitely an 80/20 kind of solution, making no attempt to be
 * logically complete. New content delivery forms can be added to this 
 * interface if corresponding changes are made in AuxiliaryDeliveryType.
 * 
 * @author Bill Blondeau <wblondeau@usgs.gov>
 */
public interface MessageBody {
	
	/**
	 * Optional: declares which auxiliary read types are available from the
	 * implementing class.
	 * @return 
	 */
	public List<AuxiliaryDeliveryType> getAuxiliaryDeliveryTypes();
	
	/**
	 * This is the only mandatory delivery method. An HTTP message body is defined as
	 * a sequence of octets (bytes) so this will always work.
	 * @return the message body content, or <code>null</code> if the message body is empty
	 */
	public InputStream deliverAsStream();
	
	/**
	 * Optional: provides the message body as character data. Corresponds to
	 * the presence of <code>AuxiliaryDeliveryType.CHARACTER</code> in the
	 * return from <code>this.getAuxiliaryDeliveryTypes</code>.
	 * 
	 * @return the message body content, or <code>null</code> if the message body is empty
	 * @throws UnsupportedOperationException  if not implemented
	 */
	public Reader deliverAsCharacters() throws UnsupportedOperationException;
	
	
	/**
	 * Optional: provides the message body as byte array data. Corresponds to
	 * the presence of <code>AuxiliaryDeliveryType.BYTE_ARRAY</code> in the
	 * return from <code>this.getAuxiliaryDeliveryTypes</code>.
	 * @return the message body content, or <code>null</code> if the message body is empty
	 * @throws UnsupportedOperationException if not implemented
	 */
	public byte[] deliverAsByteArray() throws UnsupportedOperationException;
	
	
	/**
	 * Optional: provides the message body as String data. Corresponds to
	 * the presence of <code>AuxiliaryDeliveryType.STRING</code> in the
	 * return from <code>this.getAuxiliaryDeliveryTypes</code>.
	 * 
	 * @return the message body content, or <code>null</code> if the message body is empty
	 * @throws UnsupportedOperationException if not implemented
	 */
	public String deliverAsString() throws UnsupportedOperationException;
	
	
	/**
	 * Optional: provides the message body as String data. Corresponds to
	 * the presence of <code>AuxiliaryDeliveryType.STRING</code> in the
	 * return from <code>this.getAuxiliaryDeliveryTypes</code>.
	 * @return the message body content, or <code>null</code> if the message body is empty
	 * @throws UnsupportedOperationException if not implemented
	 */
	public Document deliverAsDOMDocument() throws UnsupportedOperationException;
	
}
