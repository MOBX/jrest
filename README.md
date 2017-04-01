# jrest

Using HTTP Methods for RESTful Services


```
The HTTP verbs comprise a major portion of our “uniform interface” constraint and provide us the action counterpart to the noun-based resource. The primary or most-commonly-used HTTP verbs (or methods, as they are properly called) are POST, GET, PUT, PATCH, and DELETE. These correspond to create, read, update, and delete (or CRUD) operations, respectively. There are a number of other verbs, too, but are utilized less frequently. Of those less-frequent methods, OPTIONS and HEAD are used more often than others.
```

#### Below is a table summarizing recommended return values of the primary HTTP methods in combination with the resource URIs:

| HTTP Verb	| CRUD	| Entire Collection (e.g. /customers)	| Specific Item (e.g. /customers/{id}) | 
| --------  | -----: | ----:  								|----:  								|
|POST	|Create	|201 (Created), 'Location' header with link to /customers/{id} containing new ID.	|404 (Not Found), 409 (Conflict) if resource already exists.. |
|GET	|Read	|200 (OK), list of customers. Use pagination, sorting and filtering to navigate big lists.	|200 (OK), single customer. 404 (Not Found), if ID not found or invalid. |
|PUT	|Update/Replace	|404 (Not Found), unless you want to update/replace every resource in the entire collection.	|200 (OK) or 204 (No Content). 404 (Not Found), if ID not found or invalid. |
|PATCH	|Update/Modify	|404 (Not Found), unless you want to modify the collection itself.	|200 (OK) or 204 (No Content). 404 (Not Found), if ID not found or invalid. |
|DELETE	|Delete	|404 (Not Found), unless you want to delete the whole collection—not often desirable.	|200 (OK). 404 (Not Found), if ID not found or invalid. |


#### HTTP Status Codes

This page is created from HTTP status code information found at ietf.org and Wikipedia. Click on the category heading or the status code link to read more.

| 1xx Informational|   						 |  			| 
| --------  | -----: | ----: |
| 100 Continue   | 101 Switching Protocols   | 102 Processing (WebDAV)| 


|2xx Success|   						 |  			| 
| --------  | -----: | ----: |
| 200 OK   | 201 Created   | 202 Accepted| 
| 203 Non-Authoritative Information    | 204 No Content|     205 Reset Content| 
| 206 Partial Content   | 207 Multi-Status (WebDAV)   | 208 Already Reported (WebDAV)| 
| 226 IM Used|  |  | 