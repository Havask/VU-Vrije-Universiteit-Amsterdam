import socket 
import threading
import os 

# Connect the socket to the server
server_address = ('143.47.184.219', 5378)

# Try to login to the server
def try_to_login():

    while True:

        # Connect the socket to the server
        client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        client_socket.connect(server_address)

        # Prompt the user to enter their username
        username = input('Enter your username: ')

        # Send a LOGIN message to the server
        login = 'HELLO-FROM '+ username + '\n'

        #Finds the number of bytes to send to the server
        string_bytes = login.encode("utf-8")
        bytes_len = len(string_bytes)
        num_bytes_to_send = bytes_len

        #Sends the messages witht the correct amount of bytes to the server
        while num_bytes_to_send > 0:
            num_bytes_to_send -= client_socket.send(string_bytes[bytes_len-num_bytes_to_send:])

        # Receive a response from the server
        response = client_socket.recv(1024).decode("utf-8")

        # If the username is not taken, return the socket
        if response.startswith("HELLO"):
            return client_socket
        elif response == "BUSY\n":
            print ("Server is busy, try again later")
            quit()
        
        print('The username is already raken, try another one.\n')
        client_socket.close()

#The socket which is logged in to the server
active_socket = try_to_login()

# Listen for messages from the server
def listener():

    while True:

        response = active_socket.recv(4096).decode("utf-8")
        
        print(response)

        if response.startswith("LIST-OK"):
            for name in response.removeprefix("LIST-OK").split(","):
                print(name)
        elif response == "SEND-OK\n":
            print("Message is sent")
        elif response == "BAD-DEST-USER\n":
            print("No such user")
        elif response.startswith("DELIVERY"):
            sender, received_message = response.split()[1:]
            print(f"Received message from {sender}: {received_message}")
        elif response == "BAD-RQST-HDR\n":
            print("Bad header")
        elif response == "BAD-RQST-BODY\n":
            print("Bad body")

# Start the listener thread
t = threading.Thread(target=listener)
t.start()

# Send a message to the server
def send_message(string):

    string_bytes = string.encode("utf-8")
    bytes_len = len(string_bytes)
    num_bytes_to_send = bytes_len

    while num_bytes_to_send > 0:
        num_bytes_to_send -= active_socket.send(string_bytes[bytes_len-num_bytes_to_send:])

#Client loop
while True:

    # Prompt the user to enter their command
    print("Enter a command: ")
    command = input("")
    
    if command.startswith("@"):
        username,message = command.split(" ",1)
        message_send = 'SEND ' + username[1:] + ' ' + message +'\n'
        send_message(message_send)

    elif command == "!quit":
        print("Logging out..\n")
        #closes the socket and exist the program
        active_socket.close()
        os._exit(1)

    elif command == "!who":
        send_message("LIST\n")
