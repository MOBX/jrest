# jrest

Using HTTP Methods for RESTful Services


The HTTP verbs comprise a major portion of our “uniform interface” constraint and provide us the action counterpart to the noun-based resource. The primary or most-commonly-used HTTP verbs (or methods, as they are properly called) are POST, GET, PUT, PATCH, and DELETE. These correspond to create, read, update, and delete (or CRUD) operations, respectively. There are a number of other verbs, too, but are utilized less frequently. Of those less-frequent methods, OPTIONS and HEAD are used more often than others.


#### Below is a table summarizing recommended return values of the primary HTTP methods in combination with the resource URIs:

| HTTP Verb		| CRUD				| Entire Collection (e.g. /customers)															| Specific Item (e.g. /customers/{id}) 											| 
| :--------  	| :--------------- 	| :-------------------------------------------------- 							                | :--------------------------------------------------		                    |
| POST			| Create			| 201 (Created), 'Location' header with link to /customers/{id} containing new ID.				| 404 (Not Found), 409 (Conflict) if resource already exists.. 					|
| GET			| Read				| 200 (OK), list of customers. Use pagination, sorting and filtering to navigate big lists.		| 200 (OK), single customer. 404 (Not Found), if ID not found or invalid. 		|
| PUT			| Update/Replace	| 404 (Not Found), unless you want to update/replace every resource in the entire collection.	| 200 (OK) or 204 (No Content). 404 (Not Found), if ID not found or invalid.	|
| PATCH			| Update/Modify		| 404 (Not Found), unless you want to modify the collection itself.								| 200 (OK) or 204 (No Content). 404 (Not Found), if ID not found or invalid. 	|
| DELETE		| Delete			| 404 (Not Found), unless you want to delete the whole collection—not often desirable.			| 200 (OK). 404 (Not Found), if ID not found or invalid. 						|


#### HTTP Status Codes

This page is created from HTTP status code information found at [ietf.org]() and [Wikipedia](). Click on the `category heading` or the `status code` link to read more.

| 1xx Informational											|   	 										|  											| 
| :----------------------------------------------			| :---------------------------- 				|:---------------------------- 				|
| 100 Continue   											| 101 Switching Protocols   					| 102 Processing (WebDAV) 					| 


| 2xx Success 												|   	 										|  											| 
| :----------------------------------------------			| :---------------------------- 				| :---------------------------- 			|
| 200 OK   													| 201 Created   								| 202 Accepted 								| 
| 203 Non-Authoritative Information     					| 204 No Content           						| 205 Reset Content 						| 
| 206 Partial Content   									| 207 Multi-Status (WebDAV) 					| 208 Already Reported (WebDAV) 			| 
| 226 IM Unused                        		 				|  												|  											| 


| 3xx Redirection											| 												| 											| 
| :----------------------------------------------			| :---------------------------- 				| :---------------------------- 			|
| 300 Multiple Choices 										| 301 Moved Permanently  						| 302 Found 								| 
| 303 See Other  											| 304 Not Modified   							| 305 Use Proxy 							| 
| 306 (Unused)   											| 307 Temporary Redirect    					| 308 Permanent Redirect (experiemental) 	| 



| 4xx Client Error                      					| 												| 											|
| :----------------------------------------------			| :---------------------------- 				|:---------------------------- 				|
| 400 Bad Request  											| 401 Unauthorized  							| 402 Payment Required 						| 
| 403 Forbidden 				 							| 404 Not Found   								| 405 Method Not Allowed 					| 
| 406 Not Acceptable  										| 407 Proxy Authentication Required   			| 408 Request Timeout 						|
| 409 Conflict  											| 410 Gone 						 				| 411 Length Required 						|
| 412 Precondition Failed   								| 413 Request Entity Too Large   				| 414 Request-URI Too Long 					| 
| 415 Unsupported Media Type   								| 416 Requested Range Not Satisfiable    		| 417 Expectation Failed 					| 
| 418 I'm a teapot (RFC 2324)   							| 420 Enhance Your Calm (Twitter)    			| 422 Unprocessable Entity (WebDAV) 		| 
| 423 Locked (WebDAV)   									| 424 Failed Dependency (WebDAV)  				| 425 Reserved for WebDAV 					| 
| 426 Upgrade Required   									| 428 Precondition Required    					| 429 Too Many Requests 					| 
| 431 Request Header Fields Too Large   					| 444 No Response (Nginx)   					| 449 Retry With (Microsoft) 				| 
| 450 Blocked by Windows Parental Controls (Microsoft)   	| 451 Unavailable For Legal Reasons    			| 499 Client Closed Request (Nginx) 		| 


| 5xx Server Error 											| 												| 											| 
| :----------------------------------------------			| :---------------------------- 				|:---------------------------- 				|
| 500 Internal Server Error  								| 501 Not Implemented 							| 502 Bad Gateway							| 
| 503 Service Unavailable  									| 504 Gateway Timeout   						| 505 HTTP Version Not Supported			| 
| 506 Variant Also Negotiates (Experimental) 				| 507 Insufficient Storage (WebDAV)  			| 508 Loop Detected (WebDAV)				| 
| 509 Bandwidth Limit Exceeded (Apache)  					| 510 Not Extended   							| 511 Network Authentication Required		| 
| 598 Network read timeout error   							| 599 Network connect timeout Error     		| 											| 

"`Top 10`" HTTP Status Code. More REST service-specific information is contained in the entry.
