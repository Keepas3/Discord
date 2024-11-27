package createorjoinServers;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import discordServer.Server;
//import discordServer.Server.CustomPanel;
import extra.CircularIcon;
import extra.Titlebar;


public class createOrJoinServers implements ActionListener {

	JFrame frame = new JFrame("");
	JLabel title = new JLabel("Create a Server");
	JButton createServer = new JButton("Create a new Server");
	JButton joinServer = new JButton("Join an existing server");
	JButton cancel = new JButton("Cancel");
	
	public createOrJoinServers() {

        frame.setLayout(null);
        ImageIcon logo = new ImageIcon("Discord image.jpg");
		CircularIcon c = new CircularIcon();
		Image discordIcon = c.createCircularImage(logo.getImage(), 1500);
		  frame.setIconImage(discordIcon);
		//title.setPreferredSize(new Dimension(300,50));
		  Font font = new Font("GG Sans", Font.BOLD, 30);
	    title.setFont(font);
	    title.setForeground(Color.BLACK);
		title.setBounds(100, 20, 300,30);
	//	title.setMinimumSize(new Dimension(300, 50));
		frame.add(title);
		
		createServer.addActionListener(this);
		createServer.setFocusable(false);
		createServer.setBounds(50,150,200,30);
		frame.add(createServer);
		
		joinServer.addActionListener(this);
		joinServer.setFocusable(false);
		joinServer.setBounds(50,250,200,30);
		frame.add(joinServer);
		
		cancel.addActionListener(this);
		cancel.setFocusable(false);
		cancel.setBounds(20,350,80,20);
		frame.add(cancel);

        
       
		frame.setUndecorated(true);
		

		frame.getContentPane().setBackground(Color.WHITE);
		frame.setResizable(true);
		frame.setSize(450,550);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()== cancel) {
			frame.dispose();
		}
		if(e.getSource()== createServer) {
			createServers c = new createServers();
			frame.setVisible(false);
		}
		if(e.getSource()== joinServer) {
			joinServers j = new joinServers();
			frame.setVisible(false);
		}
	}
}

