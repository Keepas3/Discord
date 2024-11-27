package discordServer;

import java.io.*;
import java.net.*;
import java.util.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class serverSide {
    private static final int PORT = 8080;
    private static Set<PrintWriter> clientWriters = new HashSet<>();
    public boolean running = true;
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server is running...");

            while (running) {
                Socket clientSocket = serverSocket.accept();
              //  System.out.println("New client connected.");

                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                clientWriters.add(writer);

                Thread clientHandler = new Thread(new ClientHandler(clientSocket, writer));
                clientHandler.start();
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void broadcastMessage(String message) { // make into comment if dont want to see messages on console
        for (PrintWriter writer : clientWriters) {
            writer.println(message);
        }
    }

    static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private PrintWriter writer;

        ClientHandler(Socket socket, PrintWriter writer) {
            this.clientSocket = socket;
            this.writer = writer;
        }

        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String input;
                while ((input = reader.readLine()) != null) {
                    System.out.println("Received message: " + input);
                    broadcastMessage(input);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
  
 
    public void stopServer() {
        running = false; 
    }


}


