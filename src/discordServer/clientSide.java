package discordServer;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Pong.GameFrame;
import Pong.MainPong;
import discord.Login;
import discord.homePage;
import extra.MYSQLConnector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class clientSide {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8080;

    private BufferedReader reader;
    private PrintWriter writer;
    private static JFrame frame;
    private JTextArea textArea;
    private JTextField textField;
    boolean running = true;
    private String Username;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int maxWidth = (int) screenSize.getWidth(); // Maximum width of the screen
    int maxHeight = (int) screenSize.getHeight(); // Maximum width of the screen
    ArrayList<String> messages = new ArrayList<>();
    String url = "jdbc:mysql://127.0.0.1:3306/serverinfo";
    String username = "root";
     String password = "Discordproject12";
    MYSQLConnector s = new MYSQLConnector();
    String channel = s.retrieveServerName();
   // String serverName = "Bryan's Server";
    Server server = new Server();
    public void run() {
    	getUsername();
        createGUI();
        connectToServer();
        
        fetchAndDisplayMessages();
        //fetchAndDisplayMessages2();
    }
//    public void onServerIconClick(Object source) {
    	
  //  }
   
    private void fetchAndDisplayMessages() {
    	String serverName = "Bryan's Server";
        ArrayList<String> messages = fetchMessagesForServerFromDatabase(serverName);
        
       
        updateGUIWithMessages(messages);
    }
//private void fetchAndDisplayMessages2() {
//        
//    	String serverName = "Bryan second";
//        ArrayList<String> messages = fetchMessagesForServerFromDatabase(serverName);
//        
//       
//        updateGUIWithMessages(messages);
//    }
//    private void fetchAndDisplayMessagesForAllServers() {
//        ArrayList<String> serverNames = getAllServerNamesFromDatabase(); 
//
//        for (String serverName : serverNames) {
//            ArrayList<String> messages = fetchMessagesForServerFromDatabase(serverName);
//            updateGUIWithMessages(messages);
//        }
//    }
    private ArrayList<String> getAllServerNamesFromDatabase() {
        ArrayList<String> serverNames = new ArrayList<>();
        String url = "jdbc:mysql://127.0.0.1:3306/serverinfo";
        String username = "root";
         String password = "Discordproject12";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT server_name FROM your_table"; // Replace 'your_table' with your actual table name
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                while (resultSet.next()) {
                    String serverName = resultSet.getString("server_name");
                    serverNames.add(serverName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception (logging, error message, etc.)
        }
        return serverNames;
    }
    private void getUsername() {
    	Login login = new Login();
    	login.disposeFrame();
    	Username = login.getUsername();
        //Username = JOptionPane.showInputDialog("Enter your username:");
        if (Username == null || Username.isEmpty()) {
            Username = "Anonymous"; 
        }
    }
    private void createGUI() {
        frame = new JFrame("Chat Client - " + Username);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowClosing(WindowEvent e) {
        		stopClient();
        	}
        	
        });
//        Container contentPane = frame.getContentPane();
//        contentPane.setBackground(new Color(40,40,40));
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(true);
        //panel.setBackground(new Color(40, 40, 40));

        textArea = new JTextArea();
        Font writing = new Font("GG Sans", Font.PLAIN, 15); 
        textArea.setFont(writing);
        textArea.setForeground(Color.WHITE);
        textArea.setEditable(false);
        textArea.setBackground(Color.BLACK);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        textField = new JTextField();
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 String message = textField.getText();
                 sendMessage(Username + " : " +message, channel);
                
                if (message.trim().equalsIgnoreCase("/leave")) {
                    stopClient(); 
                }
                else if (message.trim().equalsIgnoreCase("/play")) {
                    // Open the frame to play (replace this with your specific frame opening logic)
                	String message2 = Username + " is playing Pong";
                	//textArea.setForeground(Color.RED);
                	sendMessage( message2, channel);
                	GameFrame game = new GameFrame();
                }
                textField.setText("");
            }
        });
        panel.add(textField, BorderLayout.SOUTH);

        frame.setUndecorated(true);
        frame.setBackground(new Color(40,40,40));
        frame.getContentPane().add(panel);
        frame.setBounds(355, 70, maxWidth-675, maxHeight-80);
       // frame.setSize(800, 300);
//        frame.getContentPane().setBackground(new Color(40, 40, 40));
        frame.setVisible(true);
    }
    public static  JFrame getFrame() {
        return frame;
    }
    public static void disposeFrame() {
        if (frame != null) {
            frame.dispose(); 
        }
    }

    private void connectToServer() {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            Thread messageReceiver = new Thread(new MessageReceiver());
            messageReceiver.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String message,String channel) {
        writer.println(message);
        saveMessageToDatabase(message, channel);
    }
    private void saveMessageToDatabase(String message,String channel) {
        try (Connection connection = DriverManager.getConnection(url, username, password);) {
        	String insertQuery = "INSERT INTO messages (channel_name, message_text) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, channel);
            preparedStatement.setString(2, message);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException
        }
    }

    public void fetchMessagesFromServerusingName(String serverName) {
    	String currentServerName = "Bryan's Server";
    	String url = "jdbc:mysql://127.0.0.1:3306/serverinfo";
        String username = "root";
         String password = "Discordproject12";
        if (serverName.equals(currentServerName)) {
            // Code to send a request to the server to fetch messages for 'serverName'
            // Wait for the response from the server with the messages
            ArrayList<String> messages = fetchMessagesForServerFromDatabase(serverName);
            updateGUIWithMessages(messages); // Method to display messages on GUI
        }
    }
//    public void fetchMessagesFromServerusingName2(String serverName) {
//    	String currentServerName = "Bryan second";
//    	String url = "jdbc:mysql://127.0.0.1:3306/serverinfo";
//        String username = "root";
//         String password = "Discordproject12";
//        if (serverName.equals(currentServerName)) {
//            // Code to send a request to the server to fetch messages for 'serverName'
//            // Wait for the response from the server with the messages
//            ArrayList<String> messages = fetchMessagesForServerFromDatabase(serverName);
//            updateGUIWithMessages(messages); // Method to display messages on GUI
//        }
//    }

    public void displayMessagesOnGUI(String serverName) {
        ArrayList<String> messages = fetchMessagesForServerFromDatabase(serverName);
        updateGUIWithMessages(messages); // This method updates the GUI with messages
    }

    private void updateGUIWithMessages(ArrayList<String> messages) {
        StringBuilder displayText = new StringBuilder();
        for (String message : messages) {
            displayText.append(message).append("\n");
        }
        textArea.setText(displayText.toString()); // Update the JTextArea with fetched messages
    }

    private ArrayList<String> fetchMessagesForServerFromDatabase(String server) {
        ArrayList<String> messages = new ArrayList<>();
        String url = "jdbc:mysql://127.0.0.1:3306/serverinfo";
        String username = "root";
        String password = "Discordproject12";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT message_text FROM messages WHERE channel_name = ?");
            preparedStatement.setString(1, server);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                messages.add(resultSet.getString("message_text"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException
        }

        return messages;
    }
    class MessageReceiver implements Runnable {
        public void run() {
            try {
                String message;
                while ((message = reader.readLine()) != null) {
                    textArea.append(message + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
                resetConnection();
            }
        }
    }
    public void stopClient() {
        running = false; 
        try {
            writer.println("/leave"); 
            Thread.sleep(100); 
            frame.dispose(); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void resetConnection() {
        try {
            writer.close();
            reader.close();
            Thread.sleep(500); // Wait for a moment before reconnecting (adjust as needed)
            connectToServer(); // Re-establish the connection
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void startServer() {
    	serverSide s;
    	clientSide c;
    	 s = new serverSide();
   	    c = new clientSide();
		Thread serverThread = new Thread(() -> { 
		s.run(); // Assuming runServer() is the method that starts the server operations
    });
    serverThread.start();
    
    Thread clientThread = new Thread(() -> { 
		c.run(); 
    });
    clientThread.start();
	}

}


