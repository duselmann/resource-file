The Resource Folder management API
==================================

Purpose
-------
This API supports aggregated access to Resources. 

"Resources" are things addressed by URI.

"Access" is the use of request/response message exchange. In particular,
this API defines access as being consistent with HTTP. However, it 
defines message communication as method calls between objects. Translation 
to and from actual HTTP wire communications, if desired, is left to the 
implementation.

"Aggregation" is the distribution of requests to multiple Resources, and the
collection (and possibly combination) of the corresponding responses. This API
restricts aggregation topology to an acyclic tree in which no Resource can
be accessed by more than one path.

This is basically not a hard problem, but it can become very hard without 
proper separation of concerns. This API provides such a logical separation.


The Resource Definition URI
---------------------------

The concept of Aggregation implies common request/response patterns on the part
of the various Resources involved. In order to identify, and distinguish
between, different Resources that may or may not be the same, this API
relies on the concept of a Resource Definition URI.

(Note that this is not the same as a Resource's endpoint URI. The endpoint URI
is only the address of a specific component.)

The Resource Definition URI is pretty much what it sounds like: a unique 
identifier for a distinct request/response behavior contract.

It's a kind of elastic idea. The URI need not point to a web location; if it
does, the resource there can be machine processable, or merely human-
readable. The URI need not even be meaningful outside of
the run time context of the implementation of this API. 

Obviously, there are advantages to having universal URIs that point to 
machine-readable representations, but hey - it's your choice how you implement
this. This API only requires that the Resource Definition URIs identify
distinct request/response behaviors and semantics.



Aggregation Metaphor: the Resource Folder
-----------------------------------------

Everyone is familiar with the computer filesystem metaphor of the "Folder". 
The folder provides two distinct affordances:

	- It establishes a recursive mechanism of containment, providing
		an organizing principle;

	- It serves as an addressing mechanism for its contents (files).

This is precisely consistent with what we're trying to accomplish. So we'll
use the concept of a "Resource Folder", an entity that contains Resource 
access points in the same way filesystem folders contain files. 

Resource Folders also contain other Resource Folders. This recursion
gives us the tree structure we want.

Resource Folders provide a natural and understandable mechanism for organizing 
access to Resources. This is done via Resource Access Points.


Resource Access Points
----------------------

Resources can be accessed in multiple ways. This API does not concern itself
with the details of doing so. The Resource Access Point is a uniform interface 
which says, "My jurisdiction ends here!" Everything beyond this is opaque to the
API.

Implementing this API is largely a matter of implementing a Resource Access 
Point to handle any necessary details of communicating with the corresponding
Resource.



Messages
--------

All primary, or problem-domain, communications within the API are carried out 
by message exchange.

Just as with HTTP, the messages are designated "Request" and "Response", and
always occur in a 1:1 pattern.

Request and Response are consistent with the equivalent forms in HTTP.
Each has a start-line, headers, and an optional message-body.


The parts of the API
--------------------
The API is implemented as a small set of Java interfaces, enumerations, and
utility classes, supported by a simple hierarchical addressing datamodel 
whose conventional representation is XML.

The parts of the API are collected into a single Java package: 
gov.usgs.cida.resourcefolder

This is all that's necessary for an application to implement the API.

However, for convenience, some implementation classes are collected into:
gov.usgs.cida.resourcefolder.basicimpl

And test classes are collected into:
gov.usgs.cida.resourcefolder.test


The resourcefolder package
--------------------------

The following classes are available in gov.usgs.cida.resourcefolder:

Folder: the interface for the Resource Folder itself
----------------------------------------------------
Folder has one primary method: 

	- delegate(Request). When this method is invoked, the Folder distributes 
		copies of the Request parameter to its contained Folders and
		ResourcePoints. This method returns a Response object aggregating
		the responses it receives.

Folder also has methods for reporting its contents.


ResourcePoint: the interface for Resource Access
------------------------------------------------
ResourcePoint has one primary method:

	- callResource(Request). When this method is invoked, the ResourcePoint
		sends the Request (or its logical equivalent as, for example, an
		HTTP Request) to its associated external Resource. It then returns
		the Resource's response to the request, or an error message if 
		the Resource is unresponsive or unreachable. This method returns a 
		Response object.

ResourcePoint also has methods for reporting its status, including a special
method for reporting progress on long-running requests.


Message, Request and Response: message content interfaces
----------------------------------------------------------------------
Message is the superinterface of Request and Response. It exposes data
accessors for properties common to both message subtypes:

	- Resource Definition URI (identifying Resource's behavior)

	- A map of Headers (just like the HTTP Headers)

	- a MessageBody object.

Request defines a data object that is passed between components of this API 
in the "outbound" direction. It exposes data accessors for:

	- Verb (consistent with HTTP HEAD GET, PUT, DELETE, POST)

	- Endpoint URI (address of the destination Resource Point)


Response defines a data object that is passed between components of this API 
in the "outbound" direction. It exposes data accessors for:

	- Status code (useful subset of HTTP Status Codes.)

	- Endpoint URI (address of the Resource Point that provided the response)

There are also some convenience methods.

One note about Request and Response: the Object.toString() method should
return a proper HTTP 1.1 message, in exactly the form defined for HTTP
messages. This includes things such as the use of <CR><LF> line breaks,
correct "Content-Length" header when there is a message body, and the
delivery of content appropriate to the Internet Media Type , as declared
by the "Content-Type" header (example: " text/html; charset=UTF-8".)

If implementations wish to provide some other String representation of the 
message body, that fact should be clearly noted in the overriding method's 
Javadocs.
