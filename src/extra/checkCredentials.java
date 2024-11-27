package extra;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.Scanner;

	public class checkCredentials {
		 public static String url = "jdbc:mysql://127.0.0.1:3306/serverinfo";
	     public static String username = "root";
	     public static String password = "Discordproject12";
	    static Connection conn= null;


	    public static String getUsername(String enteredUsername) throws SQLException {
	        try (Connection conn = DriverManager.getConnection(url, username, password)) {
	            String sql = "SELECT username FROM users WHERE username = ?";
	            try (PreparedStatement statement = conn.prepareStatement(sql)) {
	                statement.setString(1, enteredUsername);

	                ResultSet resultSet = statement.executeQuery();

	                if (resultSet.next()) {
	                    return resultSet.getString("username"); // Return the username
	                } else {
	                    return null; // User not found
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null; // Error occurred
	        }
	    }
	    public static boolean check( String enteredUsername, String enteredPassword) throws SQLException {
	    	 try(Connection conn = DriverManager.getConnection(url, username, password)) {
	             String sql = "SELECT password_hash FROM users WHERE username = ?";
	             try (PreparedStatement statement = conn.prepareStatement(sql)) {
	                 statement.setString(1, enteredUsername);

	                 ResultSet resultSet = statement.executeQuery();

	                 if (resultSet.next()) {
	                     String storedPassword = resultSet.getString("password_hash");
	                     // Compare entered password with the stored password retrieved from the database
	                     return enteredPassword.equals(storedPassword);
	                 } else {
	                     return false; // User not found
	                 }
	             }
	         } catch (SQLException e) {
	             e.printStackTrace();
	             return false; // Error occurred
	         }
	    }
	}
	

	

