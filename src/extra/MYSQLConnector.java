package extra;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import discordServer.Server;

public class MYSQLConnector extends JPanel {

    List<String> serverNames = retrieveAllServerNames();
    String url = "jdbc:mysql://127.0.0.1:3306/serverinfo";
    String username = "root";
     String password = "Discordproject12";
    String selectQuery = "SELECT server_name FROM server";
    public MYSQLConnector() {
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        // Set font and color for drawing channel names
        g2d.setFont(new Font("GG Sans", Font.PLAIN, 20));
        g2d.setColor(Color.BLACK);
        g2d.drawString("Join a Server", 175, 25);
        g2d.drawRect(0, 50, 500, 400);
        int x =25;
        int y =100;
        int count =1;
        for (String serverName : serverNames) {
        	
            g2d.drawString( count + "." +serverName, x, y);

            JButton selectButton = new JButton("Select");
            selectButton.setFocusable(true);
            selectButton.setBounds(x + 200, y - 15, 80, 20); 
            selectButton.addActionListener(e -> handleServerSelection(serverName)); // Handle button click
            add(selectButton);

            y += 20; // Increment y-coordinate for the next server name
            count++;
        }
       
//    }
    }
    public void handleServerSelection(String serverName) {
        // Perform actions when a server is selected
        System.out.println("Selected server: " + serverName);
        int serverId = retrieveServerIdByName(serverName);
        if (serverId != -1) {
            Server s = new Server(serverName, serverId);
          
        } else {
            System.out.println("Server ID not found for server: " + serverName);
        }
    }
    public int retrieveNumberOfServers() {
    	String url = "jdbc:mysql://127.0.0.1:3306/serverinfo";
 	    String username = "root";
 	     String password = "Discordproject12";
    	String query = "SELECT COUNT(*) AS row_count FROM server";
    	int rowCount = -1;
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // Retrieve the row count (number of servers)
            if (resultSet.next()) {
                 rowCount = resultSet.getInt("row_count");

                // Loop through the servers based on the row count
//                 for (int i = 1; i <= rowCount; i++) {
//                     // Your logic here using the row count
//                     System.out.println("Processing server " + i);
//                     // Perform actions related to each server
//                 }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowCount;
    }
    
    public int retrieveServerIdByName(String serverName) {
    	 String url = "jdbc:mysql://127.0.0.1:3306/serverinfo";
 	    String username = "root";
 	     String password = "Discordproject12";
        int serverId = -1;
        String selectQuery = "SELECT server_id FROM server WHERE server_name = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = conn.prepareStatement(selectQuery)) {

            preparedStatement.setString(1, serverName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    serverId = resultSet.getInt("server_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serverId;
    }
    public String retrieveServerName()  {
    	
        String serverName = null;
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT server_name FROM server")) {

            if (rs.next()) {
                serverName = rs.getString("server_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return serverName;
    }
    public Server retrieveServerFromDatabase(String serverName, int serverId) {
        Server server = null;
        String url = "jdbc:mysql://127.0.0.1:3306/serverinfo";
        String username = "root";
        String password = "Discordproject12";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement preparedStatement;

            if (serverName != null && !serverName.isEmpty()) {
                // Retrieve server by name
                preparedStatement = connection.prepareStatement("SELECT * FROM servers WHERE server_name = ?");
                preparedStatement.setString(1, serverName);
            } else if (serverId != -1) {
                // Retrieve server by ID
                preparedStatement = connection.prepareStatement("SELECT * FROM servers WHERE server_id = ?");
                preparedStatement.setInt(1, serverId);
            } else {
                // Invalid parameters
                return null;
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Retrieve server details from the database
                int retrievedServerId = resultSet.getInt("server_id");
                String retrievedServerName = resultSet.getString("server_name");

                // Create a Server object with the retrieved details
                server = new Server(retrievedServerName, retrievedServerId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException
        }

        return server;
    }
//    public void saveChannelToDatabase(String channelName) {
//    	 String url = "jdbc:mysql://127.0.0.1:3306/serverinfo";
//   	    String username = "root";
//   	     String password = "Discordproject12";
//        try (Connection connection = DriverManager.getConnection(url, username, password)) {
//            String insertQuery = "INSERT INTO channels (channel_name) VALUES (?)";
//            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
//            preparedStatement.setString(1, channelName);
//            preparedStatement.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            
//        }
//    }
    
    public void removeChannelFromDatabase(String channelName) {
    	 String url = "jdbc:mysql://127.0.0.1:3306/serverinfo";
   	    String username = "root";
   	     String password = "Discordproject12";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String deleteQuery = "DELETE FROM channels WHERE channel_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setString(1, channelName);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
    }

   
    public ArrayList<String> retrieveChannelsFromDatabase() {
    	 String url = "jdbc:mysql://127.0.0.1:3306/serverinfo";
   	    String username = "root";
   	     String password = "Discordproject12";
        ArrayList<String> channels = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String selectQuery = "SELECT channel_name FROM channels";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String channelName = resultSet.getString("channel_name");
                channels.add(channelName);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
        return channels;
    }
    
    public List<String> retrieveAllServerNames() {
        List <String> serverNames = new ArrayList<>();
        String url = "jdbc:mysql://127.0.0.1:3306/serverinfo";
	    String username = "root";
	     String password = "Discordproject12";
        String selectQuery = "SELECT server_name FROM server";
        
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = conn.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
             
            while (resultSet.next()) {
                String retrievedServerName = resultSet.getString("server_name");
                serverNames.add(retrievedServerName);
                //System.out.println(serverNames);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return serverNames;
    }
}
