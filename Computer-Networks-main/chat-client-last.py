import socket 
import threading

# Connect the socket to the server
server_address = ('143.47.184.219', 5378)

def try_to_login():
    while True:

        client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        client_socket.connect(server_address)

        # Prompt the user to enter their username
        username = input('Enter your username: ')

        # Send a LOGIN message to the server
        login = 'HELLO-FROM '+ username + '\n'
        
        string_bytes = login.encode("utf-8")
        bytes_len = len(string_bytes)
        num_bytes_to_send = bytes_len
        while num_bytes_to_send > 0:
            num_bytes_to_send -= client_socket.send(string_bytes[bytes_len-num_bytes_to_send:])

        server_answer = []
        response = ""
        while "\n" not in response:
            response = client_socket.recv(8).decode("utf-8")
            server_answer = server_answer + [response]
        response="".join(server_answer)


        print(' handshake : ', response)
        if response.startswith("HELLO"):
            return client_socket
        elif response == "BUSY\n":
            print ("Server is busy, try again later")
            quit()
        print('The username is already raken, try another one.\n')
        client_socket.close()

active_socket = try_to_login()


def listener(): 
    while True:
        
        server_answer = []
        response = ""
        while "\n" not in response:
            response = active_socket.recv(1).decode("utf-8")
            server_answer = server_answer + [response]
        response="".join(server_answer)

        if response.startswith("LIST-OK"):
            print("List of users: ", end="")
            response = response.removeprefix("LIST-OK")
            print(response)
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

t=threading.Thread(target=listener, daemon=1)
t.start()


def send_message(string):
    string_bytes = string.encode("utf-8")
    bytes_len = len(string_bytes)
    num_bytes_to_send = bytes_len
    while num_bytes_to_send > 0:
        num_bytes_to_send -= active_socket.send(string_bytes[bytes_len-num_bytes_to_send:])

while True:
    command = input("")
    
    if command.startswith("@"):
        if " " not in command:
            print("Wrong input")
            continue 
        username,message = command.split(" ",1)
        message_send = 'SEND ' + username[1:] + ' ' + message +'\n'
        send_message(message_send)
    elif command == "!quit":
        active_socket.close()
        break

    elif command == "!who":
        send_message("LIST\n")



