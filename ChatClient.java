import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost"; // Server address
    private static final int SERVER_PORT = 12345; // Server port

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) { // Connect to the server
            new ReadThread(socket).start(); // Start a new thread to read messages from the server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // Output stream to send messages to the server
            Scanner scanner = new Scanner(System.in); // Scanner to read user input

            System.out.println("Connected to chat server");

            while (scanner.hasNextLine()) {
                out.println(scanner.nextLine()); // Send user input to the server
            }
        } catch (IOException e) {
            e.printStackTrace(); // Print any IOExceptions
        }
    }

    private static class ReadThread extends Thread {
        private Socket socket;
        private BufferedReader in;

        public ReadThread(Socket socket) {
            this.socket = socket; // Store the socket
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Create input stream from server
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            String message;
            try {
                while ((message = in.readLine()) != null) { // Read messages from the server
                    System.out.println(message); // Print messages to the console
                }
            } catch (IOException e) {
                e.printStackTrace(); // Print any IOExceptions
            } finally {
                try {
                    socket.close(); // Close the socket when done
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
