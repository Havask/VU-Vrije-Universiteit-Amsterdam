import socket
import threading

server_address = ('localhost', 5050)
clients = {}

def client_handler(client_socket, addr):
    username = None

    while True:
        try:
            server_answer = []
            response = ""
            while "\n" not in response:
                response = client_socket.recv(1).decode("utf-8")
                server_answer = server_answer + [response]
            response = "".join(server_answer)

            if response.startswith("HELLO-FROM"):
                username = response.strip().split()[1]
                if ' ' in username:
                    client_socket.sendall("BAD-RQST-BODY\n".encode("utf-8"))
                elif username in clients:
                    client_socket.sendall("IN-USE\n".encode("utf-8"))
                else:
                    clients[username] = client_socket
                    client_socket.sendall("HELLO {}\n".format(username).encode("utf-8"))
            elif response.startswith("SEND"):
                _, dest_user, message = response.strip().split(' ', 2)
                if dest_user not in clients:
                    client_socket.sendall("BAD-DEST-USER\n".encode("utf-8"))
                else:
                    clients[dest_user].sendall("DELIVERY {} {}\n".format(username, message).encode("utf-8"))
                    client_socket.sendall("SEND-OK\n".encode("utf-8"))
            elif response == "LIST\n":
                list_users = " ".join(clients.keys())
                client_socket.sendall("LIST-OK {}\n".format(list_users).encode("utf-8"))
            else:
                client_socket.sendall("BAD-RQST-HDR\n".encode("utf-8"))

        except (socket.error, ValueError, IndexError):
            break

    if username and username in clients:
        del clients[username]
    client_socket.close()


def main():
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind(server_address)
    server_socket.listen(64)

    print("Chat server is listening on {}:{}".format(*server_address))

    while True:
        client_socket, addr = server_socket.accept()
        print("New connection from {}".format(addr))
        client_thread = threading.Thread(target=client_handler, args=(client_socket, addr))
        client_thread.start()

if __name__ == "__main__":
    main()
