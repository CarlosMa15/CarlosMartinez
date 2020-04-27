'''
Created on Jan 26, 2019
@author: Carlos Martinez
'''

import socket
import sys

host = '127.0.0.1'
port = int(sys.argv[1])
size = 1024
message = ''
address = (host,port)

# Create a socket1
socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Connect to the socket1 made in the server side
socket.connect(address)

# exit loop condition of user input ‘quit’
while message != 'quit':

    #get user input to send to server side
    message = input("Enter message: ") # GET http://www.google.com/ HTTP/1.1

    # send user input to server and receive in data from the server  
    socket.send(message.encode("utf-8"))
    
    buffer = socket.recv(1024) 
    data = buffer
    while len(buffer) > 0:
        buffer = socket.recv(1024)
        data = data + buffer
       
    print("\n********** Message Received by Server **********") 
    try:
        data = data.decode("utf-8",errors="replace")
        print(data)
        message = "quit" # Remove
    except UnicodeDecodeError:
        print("Can't Decode Response")
        message = "quit" # Remove

# confirm client stopping and close socket1
socket.close()
print('Quit')