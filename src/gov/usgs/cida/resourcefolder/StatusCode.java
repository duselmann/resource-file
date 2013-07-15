package gov.usgs.cida.resourcefolder;

/**
 *
 * @author Bill Blondeau <wblondeau@usgs.gov>
 */
public enum StatusCode {
	OK_200(200, "OK", ""),
	CREATED_201(201, "Created", "A new resource has been created. Its URI is "
			+ "in the message body."),
	ACCEPTED_202(202, "Accepted", "The request has been accepted for "
			+ "processing, but is not yet done. A URI for tracking its status "
			+ "is in the message body."),
	BAD_REQUEST_400(400, "Bad Request", ""),
	UNAUTHORIZED_401(401, "Unauthorized", "You must authenticate yourself "
			+ "in order to access this resource (assuming you have necessary "
			+ "permissions in the first place.)"),
	FORBIDDEN_403(403, "Forbidden", "If you have authenticated, your "
			+ "permissions were insufficient. If you have not authenticated,"
			+ "it wouldn't help: your access is being denied for a reason that "
			+ "overrides permissions."),
	NOT_FOUND_404(404, "Not Found", ""),
	NOT_FOUND_405(405, "Method Not Allowed", "Your request specified an "
			+ "HTTP method (GET, POST, etc) that this resource does not support."),
	SERVER_ERROR_500(500, "Internal Server Error", "");
	
	private Integer code;
	private String label;
	private String description;
	
	private StatusCode (int code, String label, String description){
		this.code = code;
		this.label = label;
		this.description = description;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	@Override
	public String toString() {
		return (this.code + " " + this.label);
	}
}
