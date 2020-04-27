'''
Created on Jan 26, 2019
@author: Carlos Martinez

This python class was resigned to act like a proxy.
This proxy only sends 1 message it receives from
the client send it to the remote server then sends
the respond it receives from the remote server to
the client. This proxy only can handle 1 message at
the moment.
'''

# Import Statements
import socket
import sys

host = '127.0.0.1'
port = int(sys.argv[1])
key = sys.argv[2]
validExpression = True
extentionValadility = False

# Create a socket
socket1 = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Bind the socket to the current host name and port number designated
socket1.bind((host, port))

# Socket starts Listening
socket1.listen(1)
print("Proxy Server listening!")

# Socket accepting connections
client, address = socket1.accept()

# Receiving Data and Processing message
data = client.recv(1024)
data = data.decode("utf-8")
print("Proxy Received ",data)

# A few miner checks of input from the client
if " HTTP/1.0" not in data and " HTTP/1.1" not in data:
    validExpression = False
if "GET " not in data and "www." not in data:
    validExpression = False
if "/~" in data:
    extentionValadility =  True
  
# More miner checks of the input from the client and braking it down  
parts = data.split()
if len(parts) != 3:
    validExpression = False
    
if validExpression:
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
        
    # Server turns to clients and connect to server
    socket2 = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    port1 = 80 # 50002
    address1 = (message,port1)
    message = parts[0] + " " + middleSection + " " + parts[2] + "\r\nHost: " + message
    message = message + "\r\n\r\nConnection: close\r\n\r\nAccept-Encoding: None\r\n\r\n"
    socket2.connect(address1)
    
    # Send message to remote server
    print("********** Message To Remote Server **********")
    print(message)
    socket2.send(message.encode("utf-8"))
    
    # Receive message from remote server
    data = socket2.recv(1024)
    data = data.decode("utf-8")
    
    # closing connection to remote server
    socket2.close()
    
else:
    data = "HTTP/1.1 400 Bad Request"
    
# send message to client
print("********** Message Received from Remote Server **********")
print(data)
client.send(data.encode("utf-8"))

# Confirm Server Closing
socket1.close()
client.close()
print('Quit')