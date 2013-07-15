package gov.usgs.cida.resourcefolder.basicimpl;

import gov.usgs.cida.resourcefolder.ContentDeliveryType;
import gov.usgs.cida.resourcefolder.MessageBody;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;

/**
 * For the majority of cases in which a String message body is the natural
 * fit. No support for explicit XML (String content can be valid
 * XML serialization, of course.) Not recommended for raw binary
 * data.
 * 
 * Instances are immutable.
 * 
 * @author Bill Blondeau <wblondeau@usgs.gov>
 */
public class StringMessageBody implements MessageBody {
	
	private String content;
	private List<ContentDeliveryType> deliveryTypes = new ArrayList<>();
	
	public StringMessageBody(String content) {
		
		// TODO sanity checks
		
		this.content = content;
		this.deliveryTypes.add(ContentDeliveryType.STRING);
		this.deliveryTypes.add(ContentDeliveryType.CHARACTER);
	}

	@Override
	public List<ContentDeliveryType> getContentDeliveryTypes() {
		return new ArrayList<>(this.deliveryTypes);
	}
	
	@Override
	public InputStream deliverAsStream() {
		InputStream stream = new ByteArrayInputStream(this.content.getBytes());
		
		return stream;
	}

	@Override
	public Reader deliverAsCharacters() {
		StringReader reader = new StringReader(this.content);
		
		return reader;
	}

	@Override
	public byte[] deliverAsByteArray() {
		return this.content.getBytes();
	}

	@Override
	public String deliverAsString() {
		return this.content;
	}

	@Override
	public Document deliverAsDOMDocument() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Not supported.");
	}
}
