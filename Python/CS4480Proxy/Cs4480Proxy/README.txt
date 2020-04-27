Created on Jan 26, 2019
Author: Carlos Humberto Martinez Guadarrama

Phase 1:
This python class was resigned to act like a proxy. This proxy only sends 1 message it receives from
the client send it to the remote server then sends the respond it receives from the remote server to
the client. This proxy only can handle 1 message at the moment.

Phase 2:
The proxy was designed to handle multiply clients. The
proxy now does a check on the headers and sends them
over in the request. The proxy Also takes the resonse
from the remote server and changes the response to any
use of the word simple it gets changed to the word silly

Phase 3:
The proxy is designed to get a request from a client then the proxy connects to the remote server and then
the answer of the remote is checked to see if it contains malware. if it contains malware is sends a
malware message otherwise is modifies the message and send it to client. Modification is simple to silly

Certification:
I Carlos Martinez certify that I wrote this code myself and typed each line of code individually.
I utilized the following resources: Google, https://www.tutorialspoint.com/python/, and youtube
basic python tutorials. I did not python so I had to start with the basics.
https://www.youtube.com/watch?v=SepyXsvWVfo&t=294s for the basics of python networking for
both server and clients.

SOME TESTS
GET http://www.cs.utah.edu/~kobus/simple.html HTTP/1.1\r\n												# BAD REQUEST
GET http://www.cs.utah.edu/~kobus/simple.html HTTP/1.1\r\n\r\n											# VALID REQUEST
GET http://www.cs.utah.edu/~germain/CS4480/ HTTP/1.1\r\n\r\n											# VALID REQUEST
GET http://www.google.com/ HTTP/1.1\r\n\r\n																# VALID REQUEST
GET http://www.cs.utah.edu:80/~germain/CS4480/index.html HTTP/1.1\r\n\r\n								# VALID REQUEST WITH PORT
GET http://www.cs.utah.edu/~germain/CS4480/ HTTP/1.1\r\nCookie: $Version=1; Skin=new;\r\n\r\n			# VALID REQUEST WITH HEADERS
ST http://www.google.com/ HTTP/1.1\r\n\r\n																# VALID REQUEST
DELETE http://www.google.com/ HTTP/1.1\r\n\r\n															# ONLY GET IS IMPLEMENTED
GET http://www.cs.utah.edu/~germain/CS4480/abc HTTP/1.1\r\n\r\n											# VALID REQUEST
GET /~germain/CS4480/abc HTTP/1.1\r\nHost: www.cs.utah.edu\r\n\r\n										# VALID RELATIVE REQUEST
GET /~germain/CS4480/abc HTTP/1.1\r\nHost: www.cs.utah.edu\r\nCookie: $Version=1; Skin=new;\r\n\r\n		# VALID RELATIVE REQUEST HEADERS
GET /~germain/CS4480/abc HTTP/1.1\r\nHost: www.cs.utah.edu:80\r\nCookie: $Version=1; Skin=new;\r\n\r\n	# VALID RELATIVE REQUEST PORT