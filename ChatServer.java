import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 12345; // Port number for the server
    private static Set<PrintWriter> clientWriters = new HashSet<>(); // Set to store client output streams

    public static void main(String[] args) {
        System.out.println("Chat server started...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) { // Create a server socket listening on the specified port
            while (true) {
                new ClientHandler(serverSocket.accept()).start(); // Accept client connections and start a new handler thread for each client
            }
        } catch (IOException e) {
            e.printStackTrace(); // Print any IOExceptions
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.socket = socket; // Store the client socket
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Create input stream from client
                out = new PrintWriter(socket.getOutputStream(), true); // Create output stream to client

                synchronized (clientWriters) {
                    clientWriters.add(out); // Add this client's output stream to the set
                }

                String message;
                while ((message = in.readLine()) != null) { // Read messages from the client
                    System.out.println("Received: " + message);
                    synchronized (clientWriters) {
                        for (PrintWriter writer : clientWriters) {
                            writer.println(message); // Broadcast message to all clients
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close(); // Close the socket when done
                } catch (IOException e) {
                    e.printStackTrace();
                }
                synchronized (clientWriters) {
                    clientWriters.remove(out); // Remove this client's output stream from the set
                }
            }
        }
    }
}
