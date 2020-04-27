'''
Created on Jan 26, 2019
@author: Carlos Martinez
'''

import socket
host = '127.0.0.1'
port = 50003
size = 1024
connectionBoolean = 1

# create a socket1
socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# bind the socket1 to the current host name and port number designated
socket.bind((host, port))

# keep a backlog of five connection
socket.listen(1)
print("Echo Server listening!")

# socket1 accepting connections
client, address = socket.accept()

# processing data
while connectionBoolean == 1:
    
    # Receiving data
    data = client.recv(size)
    data = data.decode("utf-8") # ("ascii")
    print(data)

    # if the client sends quit we end the loop 
    if data == 'quit':
        connectionBoolean = 0

    # else we return the user input
    elif data:
        client.send(data.encode("utf-8")) # "ascii"))
        connectionBoolean = 0 # REMOVE

# Confirm Server Closing
client.close()
print('Quit')