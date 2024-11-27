package createorjoinServers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import discordServer.Server;
import extra.CircularIcon;
import extra.MYSQLConnector;

public class joinServers implements ActionListener{
	
	
	JFrame window2 = new JFrame("");
	JLabel title3 = new JLabel("Join a Server");
	JLabel serverName2 = new JLabel("Join a Server:");
	//JFrame window2 = new JFrame("Main Frame");
	JButton joinServer = new JButton("Join Server ");
	//JButton browseServer = new JButton("Browse server options");
	JButton back3 = new JButton("Back");
	// JList <String> serverList;
	 //List<String> serverNames;
	 MYSQLConnector ch = new MYSQLConnector();
	 
	 String url = "jdbc:mysql://127.0.0.1:3306/serverinfo";
	    String username = "root";
	     String password = "Discordproject12";
	public joinServers() {
		ImageIcon logo = new ImageIcon("Discord image.jpg");
		CircularIcon c = new CircularIcon();
		Image discordIcon = c.createCircularImage(logo.getImage(), 1500);
		 window2.setIconImage(discordIcon);
		  
		 window2.setLayout(new BorderLayout());
		 window2.getContentPane().add(ch, BorderLayout.CENTER);
          window2.setUndecorated(true);
          window2.setBackground(Color.WHITE);
          window2.setSize(450,400);
          window2.setLocationRelativeTo(null);
         
          window2.setVisible(true);

		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()== back3) {
			window2.dispose();
		}
		if(e.getSource() == joinServer) {
			MYSQLConnector se = new MYSQLConnector();
			se.retrieveAllServerNames();
		}
	}
	
  }

	 
	


