package gov.usgs.cida.resourcefolder;

/**
 * @see http://www.w3.org/Protocols/rfc2616/rfc2616-sec9.html
 * @author Bill Blondeau <wblondeau@usgs.gov>
 */
public enum Verb {
	GET ("GET", true, true),
	PUT ("PUT", false, true),
	DELETE ("DELETE", false, true),
	POST ("POST", false, false),
	HEAD ("HEAD", true, true);

	private String name;
	private boolean safe;
	private boolean idempotent;
	
	
	private Verb(String name, boolean safe, 
			boolean idempotent) {
		this.safe = safe;
		this.idempotent = idempotent;
	}
	
	public boolean isSafe() {
		return this.safe;
	}
	
	public boolean isIdempotent() {
		return this.idempotent;
	}

	
	@Override
	public String toString() {
		return this.name();
	}
	

}
