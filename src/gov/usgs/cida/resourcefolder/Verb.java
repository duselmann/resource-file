package gov.usgs.cida.servicefolder;

/**
 *
 * @author Bill Blondeau <wblondeau@usgs.gov>
 */
public enum Verb {
	GET ("GET", true, true, false),
	PUT ("PUT", false, true, false),
	DELETE ("DELETE", false, true, false),
	POST ("POST", false, false, false),
	HEAD ("HEAD", true, true, false),
	FUNCTION ("FUNCTION", true, true, true);

	private String name;
	private boolean safe;
	private boolean idempotent;
	private boolean referentiallyTransparent;
	
	
	private Verb(String name, boolean safe, 
			boolean idempotent, boolean referentiallyTransparent) {
		this.safe = safe;
		this.idempotent = idempotent;
		this.referentiallyTransparent = referentiallyTransparent;
	}
	
	public boolean isSafe() {
		return this.safe;
	}
	
	public boolean isIdempotent() {
		return this.idempotent;
	}

	public boolean isReferentiallyTransparent() {
		return this.referentiallyTransparent;
	}
	
	@Override
	public String toString() {
		return this.name();
	}
	

}
