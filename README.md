# Socket-Chat

This project demonstrates a simple chat server and client application using Inter-Process Communication (IPC) mechanisms with Java sockets. The server accepts connections from multiple clients and relays messages between them.

### Features
- **Server**: Manages client connections and message broadcasting.
- **Client**: Connects to the server, sends messages, and displays messages from other clients.

### Requirements
- Java Development Kit (JDK) 8 or higher

### Getting Started

#### Prerequisites
Ensure you have Java Development Kit (JDK) installed on your system. You can download it from the [official website](https://www.oracle.com/java/technologies/javase-downloads.html).

#### Compilation and Execution

1. **Clone the Repository**
   ```sh
   git clone https://github.com/Saakira22/Socket-Chat-app.git
   cd chat-application
   ```

2. **Compile the Java Files**
   ```sh
   javac ChatServer.java ChatClient.java
   ```

3. **Start the Server**
   ```sh
   java ChatServer
   ```
   The server will start and listen for incoming client connections on port `12345`.

4. **Start the Clients**
   Open multiple terminal windows and run:
   ```sh
   java ChatClient
   ```
   Each client will connect to the server. Type messages in any client window to send them to all connected clients.

### Project Structure
- **ChatServer.java**: The server application that handles client connections and message broadcasting.
- **ChatClient.java**: The client application that connects to the server and allows users to send and receive messages.

### How It Works
1. **Server**:
   - The server listens on a specified port for incoming client connections.
   - For each client, it spawns a new `ClientHandler` thread to handle communication.
   - Messages from any client are broadcast to all connected clients.

2. **Client**:
   - The client connects to the server and starts a `ReadThread` to listen for incoming messages from the server.
   - It also takes user input from the console and sends it to the server.

### Example Usage

Start the server:
```sh
java ChatServer
```

Start a client:
```sh
java ChatClient
```

Chat between clients by typing messages in their respective console windows. Each message will be broadcast to all connected clients.

### Future Enhancements
- Add authentication for clients.
- Implement private messaging between clients.
- Enhance the user interface with a GUI.
