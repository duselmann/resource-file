package gov.usgs.cida.servicefolder;

/**
 *
 * @author Bill Blondeau <wblondeau@usgs.gov>
 */
public enum StatusCode {
	OK_200(200, "OK"),
	BAD_REQUEST_400(400, "Bad Request"),
	SERVER_ERROR_500(500, "Internal Server Error");
	
	private Integer code;
	private String description;
	
	private StatusCode (int code, String description){
		this.code = code;
		this.description = description;
	}
	
	public int getCode() {
		return this.code;
	}
	
	@Override
	public String toString() {
		return (this.code + " " + this.description);
	}
}
