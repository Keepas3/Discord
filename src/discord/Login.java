package discord;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import extra.CircularIcon;
import extra.DiscordLogo;
import extra.Titlebar;
import extra.checkCredentials;
public class Login implements ActionListener{

	
	private static final JFrame frame = new JFrame("Discord Simulation");
	
	JPanel mainPanel = new JPanel(new GridBagLayout());
	
//	private static final JPanel topPanel = new JPanel();
//	private static final JPanel leftPanel = new JPanel();
	private JLabel title = new JLabel("Welcome Back!");
	private static final JLabel username = new JLabel("Username:");
	private static final JLabel password = new JLabel("Password:");
	private static final JTextField typeUserName = new JTextField(30);
	private static final JPasswordField typePassword = new JPasswordField(30);
	private static final JButton login = new JButton("Login");
	private static final JLabel success = new JLabel("");
	private static final JButton register = new JButton("Register");
	DiscordLogo d = new DiscordLogo();
	//JButton discordButton = d.createButtonWithFullImage("Discord image.jpg", 200, 200);
//	 private HashMap<String,String> namesandPasswords;
	String user = typeUserName.getText();
	public Login() { // Additional class created to handle logins
		Titlebar Title = new Titlebar(); 
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(Title, BorderLayout.NORTH);
        
        ImageIcon logo = new ImageIcon("Discord image.jpg");	
		CircularIcon c = new CircularIcon();
		Image discordIcon = c.createCircularImage(logo.getImage(), 100);
		 JLabel imageLabel = new JLabel(new ImageIcon(discordIcon));
		  frame.setIconImage(discordIcon);
		  
		GridBagConstraints gbc = new GridBagConstraints();
		mainPanel.setBackground(Color.DARK_GRAY);
        gbc.insets = new Insets(30, 0, 15, 30); // Padding between components
		JLabel northLabel = new JLabel("North");
        JLabel southLabel = new JLabel("South");
        JButton eastButton = new JButton("East");
        JButton westButton = new JButton("West");
        JButton centerButton = new JButton("Center");
        
        gbc.gridx = 0;
        gbc.gridy = 0;
//        imageLabel.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
//        imageLabel.setRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        mainPanel.add(imageLabel,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        Font l = new Font("GG Sans", Font.PLAIN, 14);
	      title.setFont(l);
     //   title.setPreferredSize(new Dimension(200,80));
        title.setForeground(Color.white);
        mainPanel.add(title, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        username.setForeground(Color.white);
        mainPanel.add(username, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(typeUserName, gbc);
     //   mainPanel.add(northLabel, BorderLayout.NORTH);
      // mainPanel.add(southLabel, BorderLayout.SOUTH);
     //   mainPanel.add(eastButton, BorderLayout.EAST);
      //  mainPanel.add(westButton, BorderLayout.WEST);
       // mainPanel.add(centerButton, BorderLayout.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 2;
        password.setForeground(Color.white);
        mainPanel.add(password, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(typePassword, gbc);
		frame.add(mainPanel);	
		
		gbc.gridx= 1;
	    gbc.gridy= 3;
	    success.setForeground(Color.RED);
	    mainPanel.add(success,gbc);
		
		gbc.gridx = 1;
        gbc.gridy = 4;
        login.setFocusable(false);    
        login.addActionListener(this);
        login.setBackground(new Color(51,153,255));
        login.setForeground(Color.white);
        login.setPreferredSize(new Dimension(350,25));
        mainPanel.add(login, gbc);
     
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        register.setFocusable(false);    
        register.addActionListener(this);
        register.setBackground(new Color(51,153,255));
        register.setForeground(Color.white);
        register.setPreferredSize(new Dimension(100,18));
        mainPanel.add(register, gbc);
        

		
//		mainPanel.add(topPanel,BorderLayout.NORTH);
        frame.setUndecorated(true);
		frame.setResizable(true);
		frame.setSize(550,500);
	//	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLocationRelativeTo(null);
	//	frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
//		  frame.getContentPane().setBackground(Color.DARK_GRAY);
		//window.getContentPane().setBackground(new Color(77,77,77));;
		 frame.add(mainPanel);
			
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// TODO Auto-generated method stub
		if(e.getSource() == register) {
			Register r = new Register();
		}
//		String user = typeUserName.getText();
//		String pass = typePassword.getText();
//		if(user.equals("abc" ) && pass.equals("123"))
		if(e.getSource() == login) {
			String user = typeUserName.getText();
			String pass = typePassword.getText();
		
			try {
				boolean loginSuccessful = checkCredentials.check(user, pass);
				if(loginSuccessful) {
					success.setForeground(Color.green);
					success.setText("Logging in!");
					homePage h = new homePage();
					frame.dispose();
				}
				else {
					success.setText("Username or password is incorrect!");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
//		if(namesandPasswords.containsKey(user)) {
		}
//		
	}
	public String getUsername() {
		return user;
	}
	public void disposeFrame() {
		frame.dispose();
		//mainPanel.dispose();;
	}

}
