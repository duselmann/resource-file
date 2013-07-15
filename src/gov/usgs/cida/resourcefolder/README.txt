The Service Folder service management framework.

This is a set of classes and data specifications that permits basic invocation
and aggregation of services.

For this context, "service" is a request-response message exchange modeled
after, and highly consistent with, HTTP. However, this framework is designed as
pure Java classes and interfaces, which would permit efficient operation of
multi-service management within a JVM. It is expected that the framework 
would be extended to HTTP in order to communicate over the wire. These
core classes do not know, and do not need to know, whether some or all
of the messages are being sent via HTTP.

What does this framework do?

SERVICE AND MESSAGE DEFINITION:
The Request, Response, and MessageBody interfaces, together with enumerations
for Verb (on the Request) and StatusCode (on the Response) provide a simple 
and workable affordance for most service invocation and aggregation
problems.

FOLDER AGGREGATION
Consider an ordinary computer file system. It is organized by "folders",
which can contain files and other folders. That's all there is to it. 
The Service Folder framework steals the metaphor and the recursive design: 

	* "Service Folders" are container items with a narrow set of 
responsibilities.

	* Service Points" are invocation components which can call a service.
This is much like a file in a filesystem, which can access a storage location.

	* Service Folders can contain Service Points, and can contain other
Service Folders.

	* Service Points have one primary method, callService(Request), which
represents a call to a service. What happens within the service is opaque
to the Service Folder framework. The method returns a Response.

	* Service Folders have one primary method, dispatch(Request), which 
forwards the Request to a subset of its contained Service Points, and to all
of its contained Folders. This method aggregates the Responses it receives,
and returns a Response containing the aggregated Responses.

AGGREGATION REPRESENTATION
Since Folder Aggregation is strictly hierarchical, it provides an obvious
scheme of representation for a Response message body. The aggregation
representation begins with the abstract hierarchy:

	-Current Folder
		-Service Point
		-Folder A
			-Service Point
		-Folder B
			-Service Point
			-Service Point
		-Folder C
		-Folder D
			-Service Point
			-Folder E
				-Service Point
				-Service Point
			-Folder F
		-Folder G

In this example, Service Points that do not 
			
