'''
Created on Jan 26, 2019
@author: Carlos Martinez

This python class was designed to act like a proxy.
This proxy only sends 1 message it receives from
the client send it to the remote server then sends
the respond it receives from the remote server to
the client. This proxy only can handle 1 message at
the moment.

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
GET http://www.cs.utah.edu/~kobus/simple.html HTTP/1.1\r\n                                                # BAD REQUEST
GET http://www.cs.utah.edu/~kobus/simple.html HTTP/1.1\r\n\r\n                                            # VALID REQUEST
GET http://www.cs.utah.edu/~germain/CS4480/ HTTP/1.1\r\n\r\n                                            # VALID REQUEST
GET http://www.google.com/ HTTP/1.1\r\n\r\n                                                                # VALID REQUEST
GET http://www.cs.utah.edu:80/~germain/CS4480/index.html HTTP/1.1\r\n\r\n                                # VALID REQUEST WITH PORT
GET http://www.cs.utah.edu/~germain/CS4480/ HTTP/1.1\r\nCookie: $Version=1; Skin=new;\r\n\r\n            # VALID REQUEST WITH HEADERS
ST http://www.google.com/ HTTP/1.1\r\n\r\n                                                                # VALID REQUEST
DELETE http://www.google.com/ HTTP/1.1\r\n\r\n                                                            # ONLY GET IS IMPLEMENTED
GET http://www.cs.utah.edu/~germain/CS4480/abc HTTP/1.1\r\n\r\n                                            # VALID REQUEST
GET /~germain/CS4480/abc HTTP/1.1\r\nHost: www.cs.utah.edu\r\n\r\n                                        # VALID RELATIVE REQUEST
GET /~germain/CS4480/abc HTTP/1.1\r\nHost: www.cs.utah.edu\r\nCookie: $Version=1; Skin=new;\r\n\r\n        # VALID RELATIVE REQUEST HEADERS
GET /~germain/CS4480/abc HTTP/1.1\r\nHost: www.cs.utah.edu:80\r\nCookie: $Version=1; Skin=new;\r\n\r\n    # VALID RELATIVE REQUEST PORT
'''

# Import Statements
import socket
import threading
import sys
import hashlib
import datetime
from datetime import date
import calendar
import json

# Other valid request
validRequest = ["HEAD","POST","PUT","DELETE","CONNECT","OPTIONS","TRACE"]

# This is the list of the common request headers that are accepted in this proxy
properHeaders = ["Host","A-IM","Accept","Accept-Charset","Accept-Encoding"
                 ,"Accept-Language","Accept-Datetime","Access-Control-Request-Method"
                 ,"Access-Control-Request-Headers","Authorization","Cache-Control"
                 ,"Connection","Content-Length","Content-MD5","Content-Type","Cookie"
                 ,"Date","Expect","Forwarded","From","HTTP2-Settings","If-Match"
                 ,"If-Modified-Since","If-None-Match","If-Range","If-Unmodified-Since"
                 ,"Max-Forwards","Origin","Pragma","Proxy-Authorization","Range"
                 ,"Referer","TE","User-Agent","Upgrade","Via","Warning"]

host = '127.0.0.1'

# The port is read if provided, other wise 50005 is default
try:
    port = int(sys.argv[1])
except IndexError:
    port = 50005
    
# The key is read in if provided, other wise a default is provided
try:
    key = sys.argv[2]
except IndexError:
    key = "cc6745e122995e742da4399a512676491f092f4ec9d58b9dd4baefef8d1726ce"

# Create a socket
socket1 = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Bind the socket to the current host name and port number designated
socket1.bind((host, port))

# Socket starts Listening
socket1.listen()
print("Proxy Server listening!")

'''
This method returns weather the header is a proper header or not
'''
def isProperRequest(request):
    
    for i in range(0,len(validRequest)):
        if request == validRequest[i]:
            return True
    
    return False

'''
Returns the bad request string
'''
def badRequest():
    currentDT = datetime.datetime.now()
    my_date = date.today()
    data = "HTTP/1.1 400 Bad Request\r\n" + "Date: " + calendar.day_name[my_date.weekday()][:3]
    data = data + ", " + datetime.date.today().strftime("%d") + " " + datetime.date.today().strftime("%B")[:3]
    data = data + " "  + datetime.date.today().strftime("%Y") + " " + currentDT.strftime("%H:%M:%S")
    data = data + " GMT\r\nServer: CS4480-Proxy\r\nContent-Length: 306\r\nConnection: close\r\n"
    data = data + "Content-Type: text/html; charset=iso-8859-1\r\n\r\n"
    data = data + "<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML 2.0//EN\">\n"
    data = data + "<html><head>\n<title>400 Bad Request</title>\n</head><body>\n<h1>Bad Request</h1>\n"
    data = data + "<p>Your browser sent a request that this server could not understand."
    data = data + "<br />\n</p>\n<hr>\n<address>Apache/2.4.29 (Ubuntu) Server at www.cs.utah.edu Port 80</ad"
    data = data + "dress>\n" + "</body></html>\n"
    return data

'''
Creates malware message
'''
def malwareRequest(resource,jsonString):
    currentDT = datetime.datetime.now()
    my_date = date.today()
    first = ""
    for el in jsonString["scans"]:
        first = el
        break
        
    
    malware = "HTTP/1.1 200 OK\r\n"
    malware = malware + "Date: " + calendar.day_name[my_date.weekday()][:3] + " " + datetime.date.today().strftime("%d") 
    malware = malware + " " + datetime.date.today().strftime("%B")[:3] + " "  + datetime.date.today().strftime("%Y")
    malware = malware + " " + currentDT.strftime("%H:%M:%S") + " GMT\r\n"
    malware = malware + "Server: CS4480-Proxy\r\nConnection: close\r\n\r\n"
    malware = malware + "<html>\n"
    malware = malware + "  <body>\n"
    malware = malware + "     <h1>The File you requested appears to contain Malware.</h2>\n"
    malware = malware + "     <h2>Information:</h2>\n"
    malware = malware + "    <ul>\n"
    malware = malware + "       <li>MD5 Hash: " + resource + "</li>\n"
    malware = malware + "       <li>Positives: " + str((jsonString["positives"])) + "/" + str(jsonString["total"]) + "</li>\n"
    malware = malware + "       <li>Scan Date: " + str(jsonString["scan_date"]) + "</li>\n"
    malware = malware + "       <li>First Scan ID: " + str(first) + "</li>\n"
    malware = malware + "     </ul>\n"
    malware = malware + "     <p>Thanks to VirusTotal for this information.</p>\n"
    malware = malware + '     <p>For more information see <a href="' + str(jsonString["permalink"]) + '">Virus Total Permanent Link</a></p>\n'
    malware = malware + "  </body>\n"
    malware = malware + "</html>\n"
    
    return malware

'''
This method returns weather the header is a proper header or not
'''
def isProperHeader(header):
    for i in range(0,len(properHeaders)):
        if header == properHeaders[i]:
            return True
    
    return False

'''
This method takes care of the functionality that happens when a
new client connects to the proxy server
'''
def clientthread(client):
    hostRelative = ""
    isRelative = False
    isRelativeHost = False
    body = "".encode("utf-8")
    responseStart = ""
    NotImplementedChecker = False
    badRequestChecker = False
    textHTML = False
    canBeDecoded = True
    validExpression = True
    isvalidRequest = False
    extentionValadility = False
    requestName = ""
    
    # Receiving Data and Processing message
    data = client.recv(1000000)    
    data = data.decode("utf-8")
    print("Proxy Received "+data)
    
    # More miner checks of the input from the client and braking it down 
    if '\\r\\n\\r\\n'  != (str(data[len(data) - 8:])):
        validExpression = False
        
    headers = data.split("\\r\\n")
    data = headers[0]
    
    parts = data.split()
    if len(parts) != 3:
        validExpression = False
    
    # A few miner checks of input from the client
    if validExpression and "HTTP/1.0" not in parts[2] and "HTTP/1.1" not in parts[2]:
        validExpression = False
    if "GET" not in parts[0]:
        requestName = parts[0]
        validExpression = False
        isvalidRequest = True
    if "/~" in data:
        extentionValadility =  True
    if parts[1][:1] != "h":
        isRelative = True    
            
    # This checks that they are proper headers
    for i in range(1,len(headers)):
        element = headers[i].split(":")
        if len(headers[i]) != 0 and validExpression:
            validExpression = isProperHeader(element[0])
        if len(element) != 2 and len(headers[i]) != 0 and validExpression and not isRelative:
            validExpression = False;
        if isRelative:
            if "Host" in element[0]:
                isRelativeHost = True
                hostRelative = element[1]
                 
    if isRelative and not isRelativeHost:
        validExpression = False
            
    if validExpression:
        # Server turns to clients and connect to server
        socket2 = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        with socket2:
            
            if not isRelative:
                # Getting the host  
                message = parts[1][7:]
                middleSection = "/"

                # Checking the middle section of the GET command and fixing the host
                if extentionValadility:
                    secondParts = message.split("/~")
                    message = secondParts[0]
                    middleSection = "/~" + secondParts[1]
                else:
                    message = message[:len(message) - 1]
        
                # Gets the port number or uses the default port 80
                port1 = 80
                if ":" in message:
                    sections = message.split(":")
                    message = sections[0]
                    port1 = int(sections[1])
        
                # Gets the address
                address1 = (message,port1)
            
                # Creates the message to the remote server
                message = parts[0] + " " + middleSection + " " + parts[2] + "\r\nHost: " + message
                message = message + "\r\nConnection: close\r\nAccept-Encoding: None\r\n"
        
                # This adds the given headers
                for i in range(1,len(headers)):
                    element = headers[i].split(":")
                    if len(headers[i]) != 0 and element[0] != "Connection" and element[0] != "Accept-Encoding" and element[0] != "Host":
                        message = message + headers[i] + "\r\n"
                
                message = message + "\r\n"
            else:
                port1 = 80
                if ":" in hostRelative:
                    sections = hostRelative.split(":")
                    hostRelative = sections[0]
                    port1 = int(sections[1])
                address1 = (hostRelative[1:],port1)
                
                message = parts[0] + " " + parts[1] + " " + parts[2] + "\r\nHost: " + hostRelative[1:]
                message = message + "\r\nConnection: close\r\nAccept-Encoding: None\r\n"
                
                # This adds the given headers
                for i in range(1,len(headers)):
                    element = headers[i].split(":")
                    if len(headers[i]) != 0 and element[0] != "Connection" and element[0] != "Accept-Encoding" and element[0] != "Host":
                        message = message + headers[i] + "\r\n"
                        
                message = message + "\r\n"

            try:
                # try to connect to the remote server
                socket2.connect(address1)
    
                # Send message to remote server
                print("********** Message To Remote Server **********")
                print(message)
                socket2.send(message.encode("utf-8"))
            
                # Reads in all the message from remote server
                buffer = socket2.recv(1)
                data1 = buffer.decode("utf-8")
                while "\r\n\r\n" not in data1 and len(buffer) > 0:
                    buffer = socket2.recv(1)
                    data1 = data1 + buffer.decode("utf-8")
                                    
                buffer = socket2.recv(100)
                data = buffer
                while len(buffer) > 0:
                    buffer = socket2.recv(100)
                    data = data + buffer
                    
                givenHeaders = data1.split("\r\n")
                for el in givenHeaders:
                    headerSections = el.split(":")
                    if "Content-Type" in headerSections[0]:
                        if "text/html" in headerSections[1]:
                            textHTML = True
                            
                body = data
                responseStart = data1
                            
                if textHTML:
                    try:
                        data = data.decode("utf-8")
                        data = data1 + data
                    except UnicodeDecodeError:
                        canBeDecoded = False
                        data = data1.encode("utf-8") + data
                else:
                    canBeDecoded = False
                    data = data1.encode("utf-8") + data
                    
                # Closing connection to remote server
                socket2.close()
            except ConnectionRefusedError:
                data = badRequest()
                badRequestChecker = True
    
    elif isvalidRequest:
        if isProperRequest(requestName):
            data = "HTTP/1.1 501 Not Implemented\r\n"
            canBeDecoded = True
            NotImplementedChecker = True
        else:
            data = badRequest()
            badRequestChecker = True
            canBeDecoded = True
    else:
        data = badRequest()
        badRequestChecker = True
        canBeDecoded = True
            
    # send message to client
#     print("********** Message Received from Remote Server **********") # NOT ALWAYS A STRING
#     print(data)
    
    # Turns the out from Simple to Silly or simple to silly
    response = data
    if canBeDecoded:
        data = body.decode("utf-8")
        data = data.replace("Simple","Silly")
        data = data.replace("simple","silly")
        data = responseStart + data
        
        if badRequestChecker:
            data  = response
            
        elif NotImplementedChecker:
            data = response
        else:
            response = data
        
    else:
        data = body
        try:
            if data[0:6].decode("utf-8") == "CS4480": 
                data = data[6:]
        except UnicodeDecodeError:
            data = data
    
    
    # Server turns to clients and connect to server
    if not canBeDecoded and not badRequestChecker:
        socket3 = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        host3 = 'www.virustotal.com'
        port3 = 80
        address3 = (host3,port3)
        socket3.connect(address3)
        resource = hashlib.md5(data).hexdigest()
        message3 = "GET www.virustotal.com/vtapi/v2/file/report?apikey="+ key + "&resource=" + resource + " HTTP/1.1\r\nConnection: close\r\n\r\n"
        socket3.send(message3.encode("utf-8"))
            
        buffer = socket3.recv(1024)
        data = buffer
        while len(buffer) > 0:
            buffer = socket3.recv(1024)
            data = data + buffer
    
        data = data.decode("utf-8")
     
        parts = data.split("\r\n\r\n")
        jsonString = json.loads(parts[1])
               
        print("********** Message from Virus Server **********")
        print(data)

        if str(jsonString["response_code"]) == "0":
            data = response
        elif str(jsonString["response_code"]) == "1":
            if str(jsonString["positives"]) == "0":
                data = response
            else:
                data = str(malwareRequest(resource,jsonString))
                canBeDecoded = True
        else:
            data = response
            canBeDecoded = True
    
    # Sends the modified input from remote server to client    
    try:
        client.send(data.encode("utf-8"))
    except AttributeError:
        client.send(data)
    
    # Closes the client connection
    client.close()

# Listens for new clients and handles then in a new thread
while True:
    # Socket accepting connections
    client, address = socket1.accept()
    print("Client Connected")
    thread1 = threading.Thread(target = clientthread, args=(client,))
    thread1.start()

# Confirm Server Closing
socket1.close()
print('Quit')