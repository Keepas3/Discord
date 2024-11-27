package discord;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import extra.CircularIcon;
import extra.Titlebar;

public class Register implements ActionListener{
	
	private static final JFrame window = new JFrame("Register Account");
	private static final JLabel email = new JLabel("Email:");
	private static final JLabel username = new JLabel("Username:");
	private static final JLabel password = new JLabel("Password:");
	private static final JTextField typeEmail = new JTextField(20);
	private static final JTextField typeUserName = new JTextField(20);
	private static final JPasswordField typePassword = new JPasswordField(20);
	private JLabel title = new JLabel("Create an Account");
	private JButton cont = new JButton("Continue");
	private final JLabel success = new JLabel("");
	private HashMap<String,String> namesandPasswords;
	public Register() {
		
		Titlebar customTitleBar = new Titlebar(); 
        Container contentPane = window.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(customTitleBar, BorderLayout.NORTH);
        
		ImageIcon logo = new ImageIcon("Discord image.jpg");
		CircularIcon c = new CircularIcon();
		Image discordIcon = c.createCircularImage(logo.getImage(), 1500);
		  window.setIconImage(discordIcon);	
		  
		title.setFont(new Font("GG Sans", Font.BOLD, 20));
		title.setBounds(200, 0, 300, 100);
		window.add(title);
		
		email.setBounds(50,100,100,25);
		window.add(email);
		
		username.setBounds(50,200,75,25);
		window.add(username);
		
		typeEmail.setBounds(150,100,300,25);
		window.add(typeEmail);
		
		typeUserName.setBounds(150,200,300,25);
		window.add(typeUserName);
		
		password.setBounds(50,300,100,25);
		window.add(password);
		
		typePassword.setBounds(150,300,300,25);
		window.add(typePassword);
		
		cont.setBounds(50,380,450,30);
		cont.setFocusable(false);
		cont.setBackground(new Color(40,40,40));
        cont.setForeground(Color.white);
		window.add(cont);
		cont.addActionListener(this);
		
	//	success.setBounds(400,10,200,30);
		success.setForeground(Color.red);
		window.add(success);
		
	window.setUndecorated(true);	
	window.setResizable(false);
	window.getContentPane().setBackground(new Color(40,40,40));
	window.setSize(600,600);
	window.setLocationRelativeTo(null);
//	window.setLayout(null);
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.setVisible(true);
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String user = typeUserName.getText();
		String pass = typePassword.getText();
		String mail = typeEmail.getText();
		if(e.getSource()== cont) {
		if(mail.isEmpty() || user.isEmpty() || pass.isEmpty()) {
			success.setBounds(200,400,300,100);
					success.setText("One or more fields is blank");
		}
		else {
			
			String url = "jdbc:mysql://127.0.0.1:3306/serverinfo";
		    String username = "root";
		     String password = "Discordproject12";
		    //Connection connection = null;
		     String insertQuery = "INSERT INTO USERS (username, password_hash) VALUES (?, ?)";
		     
		     try (Connection connection = DriverManager.getConnection(url, username, password);
		          PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
		         
		         preparedStatement.setString(1, user);
		         preparedStatement.setString(2, pass);
		         
		         int rowsAffected = preparedStatement.executeUpdate();
//		         if (rowsAffected > 0) {
////		             System.out.println("User inserted successfully!");
////		         } else {
////		             System.out.println("Failed to insert user.");
////		         }
		         	window.dispose();
		     } catch (SQLException ex) {
		         System.out.println("Error inserting user: " + ex.getMessage());
		     }
		      
		    
		   }
				
				
		}
		
				
	}
		
	
}
