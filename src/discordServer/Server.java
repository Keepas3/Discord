package discordServer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import extra.CircularIcon;
import extra.Titlebar;
import extra.MYSQLConnector;


public class Server implements ActionListener {

	public String serverName = "";
	public String roles;
	public ArrayList<String> members;
	public int channels;
	public int userid =1;
	public int serverid =1;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int maxWidth = (int) screenSize.getWidth(); // Maximum width of the screen
    int maxHeight = (int) screenSize.getHeight(); // Maximum width of the screen
     JFrame frame = new JFrame();
	public Server(String serverName, int serverID) {
	
		this.serverName = serverName;
		this.serverid = serverID;
		
		ImageIcon logo = new ImageIcon("Discord image.jpg");
		CircularIcon c = new CircularIcon();
		Image discordIcon = c.createCircularImage(logo.getImage(), 1500);
		
		
		JPanel centerPanel = new CustomPanel();
		
		JScrollPane scrollPane = new JScrollPane(centerPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
        centerPanel.setBackground(new Color(40,40,40));
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        
  
        
        frame.setBounds(80,25,maxWidth,1000);
		
		  frame.setIconImage(discordIcon);
		  frame.setUndecorated(true);
		  frame.setVisible(true);
		
	}

     
	public Server() {
		 
		
		//JLabel serverName = new JLabel("_____'s Server");
		Titlebar customTitleBar = new Titlebar(); 
      Container contentPane = frame.getContentPane();

      ImageIcon logo = new ImageIcon("Discord image.jpg");
		CircularIcon c = new CircularIcon();
		Image discordIcon = c.createCircularImage(logo.getImage(), 1500);
		
		JPanel centerPanel = new CustomPanel();
        centerPanel.setBackground(new Color(40,40,40));
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
        

        
        frame.setBounds(80,25,maxWidth,1000);
		
		  frame.setIconImage(discordIcon);
		  frame.setUndecorated(true);
		  frame.setVisible(true);		  
		  
	}
	 class CustomPanel extends JPanel {
		 private ArrayList<JButton> customButton = new ArrayList<>();
		 int maxChannels = 5;
		 int currentchannels =3;
		 private ArrayList<JButton> channels = new ArrayList<>();
		 JButton channelButton;
		 JButton channel;
   	  JButton addChannels;
   	  JButton removeChannels;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    int maxWidth = (int) screenSize.getWidth(); // Maximum width of the screen
	    int maxHeight = (int) screenSize.getHeight(); // Maximum width of the screen
	   // JButton customButton = new JButton("Custom Button");
	    int xStart=0;
	    int yStart=200;
	    private int buttonWidth = 275;
	    private int buttonHeight = 50;
	    private int verticalGap = 5;
	    int x = 0;
	    int y =200;
	    MYSQLConnector ser = new MYSQLConnector();
	    int serverId = ser.retrieveServerIdByName(serverName); 

	    String url = "jdbc:mysql://127.0.0.1:3306/serverinfo";
	    String username = "root";
	    String password = "Discordproject12";
	    public CustomPanel() {
	    	//channels = new ArrayList<>();
	    	 setPreferredSize(new Dimension(400, 300)); // Set the preferred size of the panel
	    	 setLayout(null);
	    	 createInitialTextChannels(); 
	    	 createInitialGameRoom();
	         setUpandRemoveChannels();

	    	

	    }
	    private void createInitialTextChannels() {
	        int x = xStart;
	        int y = yStart;
	        for (int i = 0; i < currentchannels; i++) {
	            JButton channelButton = new JButton("Channel " + (i + 1));
	            channelButton.setBounds(x, y, 275, 50);
	            y += 55;
	       
	            channelButton.setFocusable(false);
	            channelButton.setBackground(Color.gray);
	            channels.add(channelButton);

	            channelButton.addActionListener((ActionEvent e) -> {
	                int index = channels.indexOf(e.getSource());
	                if (index != -1) {
	                   // System.out.println("Opening channel " + (index + 1));
	                    clientSide a = new clientSide();
		            	 JFrame frame = clientSide.getFrame();
		                
		              //   String channelName = promptForChannelName();
		                 a.startServer();
//		                 if (channelName != null && !channelName.isEmpty()) {
//		                     JButton clickedButton = (JButton) e.getSource();
//		                  
//		                     clickedButton.setText(channelName);
//		                  
//		                 }
	                }
	               // clientSide.disposeFrame();
	            });
	            add(channelButton);
	        }
	        revalidate();
	        repaint();
	    }
	    private void createInitialGameRoom() {
	        int x = xStart;
	        int y = yStart;
	        for (int i = 0; i < currentchannels; i++) {
	            JButton channelButton = new JButton("GameRoom " + (i + 1));
	            channelButton.setBounds(x, y+400, 275, 50);
	            y += 55;
	       
	            channelButton.setFocusable(false);
	            channelButton.setBackground(Color.gray);
	            channels.add(channelButton);

	            channelButton.addActionListener((ActionEvent e) -> {
	                int index = channels.indexOf(e.getSource());
	                if (index != -1) {
	                   // System.out.println("Opening channel " + (index + 1));
	                    clientSide a = new clientSide();
		            	 JFrame frame = clientSide.getFrame();
		                
		                 String channelName = promptForChannelName();
		                 a.startServer();
//		                 if (channelName != null && !channelName.isEmpty()) {
		                     JButton clickedButton = (JButton) e.getSource();
		                  
		                     clickedButton.setText(channelName);
		                  
		             //    }
	                }
	               // clientSide.disposeFrame();
	            });
	            add(channelButton);
	        }
	        revalidate();
	        repaint();
	    }
	    private String promptForChannelName() {
	    	
	        return JOptionPane.showInputDialog(null, "Enter the channel name:", "Channel Name", JOptionPane.QUESTION_MESSAGE);
	    }
private int promptForChannelType() {	
	String[] options = { "Chat Room", "Game Room" };
	int choice;
     choice = JOptionPane.showOptionDialog(null, "Select Room Type", "Room Selection",
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    if (choice == 0) {
  
        createTextChannels(1);
    } else if (choice == 1) {
        
        createGameRoom(1);
    } else {
       
        System.out.println("No selection made.");
    }
    return choice;
	    }
	    private void setUpandRemoveChannels() {
	        JButton addChannels = new JButton("+");
	        addChannels.setBounds(xStart + 95 + 150, yStart - 25, 30, 30);
	        addChannels.setOpaque(false); // Make the button transparent
	         addChannels.setContentAreaFilled(false); 
	         addChannels.setFocusable(false);
	         addChannels.setBorderPainted(false);
	        addChannels.addActionListener(e -> {
	        	currentchannels++;
	        	//ser.saveChannelToDatabase(channelName);
	        	//ser.saveChannelsToDatabase(serverId, channelNames);
	        	promptForChannelType();
	        	
	         
	        });
	        add(addChannels);

	        JButton removeChannels = new JButton("-");
	        removeChannels.setBounds(xStart + 95 + 120, yStart - 25, 30, 30);
	        removeChannels.setOpaque(false); // Make the button transparent
	         removeChannels.setContentAreaFilled(false); 
	         removeChannels.setFocusable(false);
	         removeChannels.setBorderPainted(false);
	        removeChannels.addActionListener(e -> {
	            if (currentchannels > 0) {
	                remove(channels.get(channels.size() - 1));
	                channels.remove(channels.size() - 1);
	                revalidate();
	                repaint();
	                currentchannels--;
	            }
	        });
	        add(removeChannels);
	    }

	    private void createTextChannels(int addChannels) {
	        int x = xStart;
	       // int y = channels.size() * 55 + yStart;
	        int y =365;
	        //System.out.println(y);
	        for (int i = 0; i < addChannels; i++) {
	            JButton channelButton = new JButton(promptForChannelName());
	            channelButton.setBounds(x, 365, 275, 50);
	            y += 55;
	            channelButton.setFocusable(false);
	            channels.add(channelButton);

	            channelButton.addActionListener((ActionEvent e) -> {
	                int index = channels.indexOf(e.getSource());
	                if (index != -1) {
	                   // System.out.println("Opening channel " + (index + 1));
	                	 clientSide a = new clientSide();
		            	 JFrame frame = clientSide.getFrame();
		            	 if(frame!=null)
		                     clientSide.disposeFrame(); 
		                 
		                 //System.out.println("Opening channel");
		                 a.startServer();
//		                 String channelName = promptForChannelName();
//		                 if (channelName != null && !channelName.isEmpty()) {
//		                     JButton clickedButton = (JButton) e.getSource();
//		                     clickedButton.setText(channelName);
		                // }
	                }
	                //clientSide.disposeFrame();
	       
	            });
	            add(channelButton);
	        }
	        revalidate();
	        repaint();
	    }
	    public JButton getChannelButton(int index) {
	        if (index >= 0 && index < channels.size()) {
	            return channels.get(index);
	        }
	        return null;
	    }
	    
	    private void createGameRoom(int addChannels) {
	        int x = xStart;
	        int y = channels.size() * 50 + yStart;
	        for (int i = 0; i < addChannels; i++) {
	            JButton channelButton = new JButton(promptForChannelName());
	            channelButton.setBounds(x, 765, 275, 50);
	            y += 55;
	            channelButton.setFocusable(false);
	            channels.add(channelButton);

	            channelButton.addActionListener((ActionEvent e) -> {
	                int index = channels.indexOf(e.getSource());
	                if (index != -1) {
	                   // System.out.println("Opening channel " + (index + 1));
	                	 clientSide a = new clientSide();
		            	 JFrame frame = clientSide.getFrame();
		            	 if(frame!=null)
		                     clientSide.disposeFrame(); 
		                 
		                // System.out.println("Opening channel");
		                 a.startServer();
//		                 String channelName = promptForChannelName();
//		                 if (channelName != null && !channelName.isEmpty()) {
//		                     JButton clickedButton = (JButton) e.getSource();
//		                     clickedButton.setText(channelName);
//		                 }
	                }
	             //   clientSide.disposeFrame();
	       
	            });
	            add(channelButton);
	        }
	        revalidate();
	        repaint();
	    }


	    
	    private void drawTextChannels(Graphics2D g2d) {
	    	
	        for (int i = 0; i < currentchannels; i++) {
	            int x = xStart;
	            int y = yStart + i * (buttonHeight + verticalGap);
	            
	            g2d.setColor(Color.RED); // Set the color for the rectangle
	            g2d.drawRect(x, y, buttonWidth, buttonHeight); // Draw a filled rectangle for each text Channel button
	            y += 50;
	        }
	    }
	    
       
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
//            int x =0;
//            int y =0;
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
           g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
          Font l = new Font("GG Sans", Font.PLAIN, 20);
          Font s = new Font("GG Sans", Font.PLAIN, 15);
	      g2d.setFont(l);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(0, 40, maxWidth-65, maxHeight-70); //x,y,width,height Outer rectangle
    		g2d.drawRect(0, 40, 275, maxHeight-70); // draw the friends/conversation side of the board
    		g2d.drawRect(maxWidth-400,40,400,maxHeight-40); // draw the online now on the right side of the board
    		g2d.drawLine(0, 0, 0, 50);
    		g2d.drawLine(275, 0, 275, 200); // draws line to connect boards
    		g2d.drawLine(maxWidth-400, 0, maxWidth-400, 200); // draws line to connect boards
    		g2d.drawLine(0, 0, maxWidth,0);
    		
 
    		
    		g2d.setColor(Color.lightGray);
    		
    	     
    	    
            g2d.drawString(serverName, 50, 25);
            g2d.drawString("Welcome to the Server", 325, 25);
            g2d.setFont(s);
            String text = "Text Channels";
            FontMetrics fontMetrics = g2d.getFontMetrics();
            int textWidth = fontMetrics.stringWidth(text);

        //    g2d.setColor(Color.WHITE);
            
            g2d.drawString(text, xStart + 10, yStart - 15);
            g2d.drawString("+", xStart + textWidth + 150, yStart - 15);
            g2d.drawString("-", xStart+textWidth+120, yStart-15);
    
            String text2 = "GameRoom";
            g2d.drawString(text2, xStart+10, 580);
           
        }

	}

	public String getServerName() {
		return serverName;
	}
	public void setServerName(String s) {
		this.serverName = s;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		if(e.getSource()== channels) {
//			
//		}
	}


	public JFrame getFrame() {
		// TODO Auto-generated method stub
		return frame;
	}


	public void disposeFrame() {
		// TODO Auto-generated method stub
		frame.dispose();
	}


	public boolean isVisible() {
		return frame.isVisible();
		// TODO Auto-generated method stub
	}


	
	
	}
