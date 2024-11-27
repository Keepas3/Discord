package createorjoinServers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import discordServer.Server;
import extra.CircularIcon;
import extra.MYSQLConnector;

public class createServers implements ActionListener {
		
		JFrame window = new JFrame("");
		JLabel title2 = new JLabel("Customize your Server");
		JLabel subtitle = new JLabel("Give your server a personality with a name.");
		JLabel nameOfserver = new JLabel("Server Name:");
		JButton createServer2 = new JButton("Create ");
		JButton back2 = new JButton("Back");
		 JTextField typeServerName = new JTextField(100);
		 String serverName = "";
		 String url = "jdbc:mysql://127.0.0.1:3306/serverinfo";
		    String username = "root";
		     String password = "Discordproject12";
		public createServers() {
			ImageIcon logo = new ImageIcon("Discord image.jpg");
			CircularIcon c = new CircularIcon();
			Image discordIcon = c.createCircularImage(logo.getImage(), 1500);
			window.setIconImage(discordIcon);
			Font l = new Font("GG Sans", Font.BOLD, 25);
		    title2.setFont(l);
		    title2.setForeground(Color.BLACK);
			title2.setBounds(50, 20, 300,30);
			window.add(title2);
			
			Font l2 = nameOfserver.getFont().deriveFont(Font.PLAIN, 15f);
		      subtitle.setFont(l2);
			subtitle.setBounds(50,50,300,50);
			window.add(subtitle);
			
			//Font l2 = serverName.getFont().deriveFont(Font.PLAIN, 10f);
		      nameOfserver.setFont(l2);
			nameOfserver.setBounds(50,150,300,50);
			window.add(nameOfserver);
			
			typeServerName.setBounds(50,200,200,30);
			window.add(typeServerName);
			
			createServer2.addActionListener(this);
			createServer2.setFocusable(false);
			createServer2.setBounds(300 , 265,100,30);
			window.add(createServer2);
			System.out.println(serverName);
			
			
			
			back2.addActionListener(this);
			back2.setFocusable(false);
			back2.setBounds(20,275,80,20);
			window.add(back2);
			
			window.setUndecorated(true);
			window.setLayout(null);
			window.getContentPane().setBackground(Color.GRAY);
			window.setResizable(true);
			window.setSize(450,300);
			window.setLocationRelativeTo(null);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setVisible(true);
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()== back2) {
				window.dispose();
				//frame.setVisible(true);
			}
			if(e.getSource() == createServer2) {
			     serverName = typeServerName.getText();
			    try (Connection conn = DriverManager.getConnection(url, username, password)) {
			        String insertQuery = "INSERT INTO server (server_name) VALUES (?)";
			        try (PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {
			            preparedStatement.setString(1, serverName);

			            int rowsInserted = preparedStatement.executeUpdate();
			            if (rowsInserted > 0) {
			                System.out.println("A new server was created successfully.");
			            } else {
			                System.out.println("Failed to create a new server.");
			            }
			        }
			    } catch (SQLException ex) {
			        ex.printStackTrace();
			    }
			    MYSQLConnector a = new MYSQLConnector();
			    int serverId = a.retrieveServerIdByName(serverName);
		        if (serverId != -1) {
		            Server s = new Server(serverName, serverId);
				window.dispose();
			}
		}
		 
		
	}
}
