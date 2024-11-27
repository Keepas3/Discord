package discord;

import java.awt.BasicStroke;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.Shape;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;

import createorjoinServers.createOrJoinServers;

import discordServer.Server;
import discordServer.clientSide;
import extra.CircularIcon;
import extra.Titlebar;
import extra.checkCredentials;
import extra.MYSQLConnector;
import extra.DiscordLogo;


public class homePage extends JFrame implements ActionListener {

	JPanel ovalButtonPanel = new JPanel(new BorderLayout());
	public static ArrayList<serverIcon> serverIcons = new ArrayList<>();
	JFrame mainFrame = new JFrame("Main Frame");
	home h = new home();
//	JFrame channelFrame = new JFrame("Channel 1 Info");
//    JPanel channelLabel = new JPanel();
//    JFrame channelFrame1 = new JFrame("Channel 1 Info");
//    JLabel channelLabel1 = new JLabel("Channel 1 Information Goes Here");
	Titlebar customTitleBar = new Titlebar(); 
	CircularIcon c = new CircularIcon();
	DiscordLogo d = new DiscordLogo();
	MYSQLConnector s = new MYSQLConnector();
	JButton discordButton = d.createButtonWithFullImage("Discord image.jpg", 100, 100);
	//Server serverFrame = new Server("", 1);
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int maxWidth = (int) screenSize.getWidth(); // Maximum width of the screen
    int maxHeight = (int) screenSize.getHeight(); // Maximum width of the screen
	public homePage() {
		//serverFrame.disposeFrame();
	     
	     ImageIcon logo = new ImageIcon("Discord image.jpg");
	     Image scaledImage = logo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
	     ImageIcon scaledLogo = new ImageIcon(scaledImage);
		  Image discordIcon = c.createCircularImage(logo.getImage(), 50);
		  mainFrame.setIconImage(discordIcon);
	        Font ct = new Font("GG Sans", Font.PLAIN, 15);
		  
		  int x =400;
		  int y =30;
		  JLabel friends = new JLabel("Friends");
		  friends.setFont(ct);
		  friends.setBackground(getBackground());
		  friends.setForeground(Color.lightGray);
		  friends.setBounds(x, y, 250, 30);
		  mainFrame.add(friends);
		  
		  x+=150;
		  JLabel online = new JLabel("Online");
		  online.setFont(ct);
		  online.setBackground(getBackground());
		  online.setForeground(Color.lightGray);
		  online.setBounds(x, y, 250, 30);
		  mainFrame.add(online);
		  
		  x+=100;
		  JLabel all = new JLabel("All");
		  all.setFont(ct);
		  all.setBackground(getBackground());
		  all.setForeground(Color.lightGray);
		  all.setBounds(x, y, 250, 30);
		  mainFrame.add(all);
		  
		  x+=80;
		  JLabel pending = new JLabel("Pending");
		  pending.setFont(ct);
		  pending.setBackground(getBackground());
		  pending.setForeground(Color.lightGray);
		  pending.setBounds(x, y, 250, 30);
		  mainFrame.add(pending);
		  
		  x+=100;
		  JLabel Blocked = new JLabel("Blocked");
		  Blocked.setFont(ct);
		  Blocked.setBackground(getBackground());
		  Blocked.setForeground(Color.lightGray);
		  Blocked.setBounds(x, y, 250, 30);
		  mainFrame.add(Blocked);
		  
		  x+=120;
		  JButton friendAdd = new JButton("Add Friend");
		  friendAdd.setBackground(Color.green);
		  friendAdd.setBounds(x, y, 105, 30);
		  friendAdd.setFocusable(false);
		  mainFrame.add(friendAdd);
		  
		  JTextField find = new JTextField("Find or search a conversation", 20);
		  find.setBackground(Color.BLACK);
		  find.setForeground(Color.lightGray);
	        find.setBounds(110, 30, 250, 30);
	        mainFrame.add(find);
	        
	        
		  JTextField textField = new JTextField("Search", 20);
		  textField.setBackground(Color.BLACK);
		  textField.setForeground(Color.lightGray);
	        textField.setBounds(400, 100, 800, 35); 

	        
	        mainFrame.add(textField);
		  mainFrame.setLayout(new BorderLayout());
          mainFrame.add(customTitleBar, BorderLayout.NORTH);
          
          mainFrame.setUndecorated(true);
		  mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

//		  mainFrame.addWindowListener(new WindowAdapter() {
//              @Override
//              public void windowIconified(WindowEvent e) {
//            	  if (serverFrame.isVisible()) {
//                      serverFrame.disposeFrame();
////              }
//              }
//          });
		  
		  JPanel buttonPanel = new JPanel();
		//  buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); 
		  buttonPanel.setBackground(Color.darkGray);
		//  Insets insets = new Insets(10,10,10,10);
		 GridLayout gridLayout = new GridLayout(0, 1, 1, 1);
		buttonPanel.setLayout(gridLayout);
		for (int i = 0; i < 10; i++) {
		    serverIcon servericon = new serverIcon("");
		    servericon.addActionListener(this);
		    servericon.setPreferredSize(new Dimension(100, 30));
		    servericon.setAlignmentX(Component.LEFT_ALIGNMENT);
//		  
		    
		    servericon.setFocusable(false);

		    buttonPanel.add(servericon);
		    serverIcons.add(servericon);
		    
		
		    
		}

		//  mainFrame.getContentPane().add(h);
		  mainFrame.getContentPane().add(h, BorderLayout.CENTER);
		  JScrollPane scrollPane = new JScrollPane(buttonPanel);
	        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	        mainFrame.getContentPane().add(buttonPanel, BorderLayout.WEST);

	      
		  mainFrame.setVisible(true);


          
          
		
	}

//	}
	  class serverIcon extends JButton {
		  ImageIcon logo = new ImageIcon("Discord image.jpg");
			CircularIcon c = new CircularIcon();
			Image discordIcon = c.createCircularImage(logo.getImage(), 1500);
			MYSQLConnector se = new MYSQLConnector();
			int maxServercount =10;
		  public serverIcon(String label) {
		    super(label);
		    // Make the button a square
		    Dimension size = getPreferredSize();
		    //Dimension size =50;
		    size.width = size.height = Math.max(size.width, size.height) ;
		    setPreferredSize(size);
		    // Make the background transparent
		    setContentAreaFilled(false);
		  }

		  // Draw the circular button
		  protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        Graphics2D g2d = (Graphics2D) g;
		        
		        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		        
		        if (getModel().isArmed()) {
		            g2d.setColor(Color.WHITE);
		        } else {
		            g2d.setColor(Color.RED);
		        }

		        // Fill the circle
		        int diameter = Math.min(getWidth() - 20, getHeight() - 20);
		        int x = (getWidth() - diameter) / 2;
		        int y = (getHeight() - diameter) / 2;
		        g2d.fillOval(x, y, diameter, diameter); // draws all the buttons
		        
		       // g2d.dispose();
		        if (serverIcons.indexOf(this) == 0) {
		        	g2d.drawImage(discordIcon, x, y, diameter, diameter, this);
		  
		        }
		        if (serverIcons.indexOf(this) ==1) {
		            int centerX = getWidth() / 2;
		            int centerY = getHeight() / 2;
		            int length = 10;
		            int thickness = 5;
		            
		            g2d.setStroke(new BasicStroke(thickness));
		            g2d.setColor(Color.BLACK);
		            g2d.drawLine(centerX - length / 2, centerY, centerX + length / 2, centerY); // the horizontal line
		            g2d.drawLine(centerX, centerY - length / 2, centerX, centerY + length / 2); // the vertical line
		            g2d.dispose();
		        }
		       // if(serverIcons.indexOf(this) >1) {
		        List<String> serverNames = se.retrieveAllServerNames();
		       
		        int ovalWidth = getSize().width - 20;
		        int ovalHeight = getSize().height - 20;
		        int ovalX = 10;
		        int ovalY = 10;

		        int textX = ovalX / 2;
		        int textY = ovalY / 2;
//		   
		        for (int i = 2; i < 10; i++) {
		            if (serverIcons.indexOf(this) == i) {
		                String serverName;
		                if (!serverNames.isEmpty() && serverNames.size() > (i - 2)) {
		                    serverName = (serverNames.get(i - 2) != null && !serverNames.get(i - 2).isEmpty()) ?
		                            serverNames.get(i - 2) : "No Server Name";
		                } else {
		                    serverName = "";
		                }

		                String text = serverName;

		                FontMetrics fm = g2d.getFontMetrics();
		                int textWidth = fm.stringWidth(text);

		                

		                textX = ovalX - textWidth / 2;
		                textY = ovalY + fm.getAscent() / 2;

		                g2d.setColor(Color.BLACK);
		                g2d.drawString(text, textX + 40, textY + 35);
		            }
		          
		        }
		        

		        
		        
		  }
		  // Draw the border of the button
		  protected void paintBorder(Graphics g) {
			  Graphics2D g2d = (Graphics2D) g;

			    int ovalWidth = getSize().width - 20;
			    int ovalHeight = getSize().height - 20;
			    int ovalX = 10;
			    int ovalY = 10;
			    
			  //  g2d.drawOval(ovalX, ovalY, ovalWidth, ovalHeight);

		  }

		  // Hit detection
		  Shape shape;

		  public boolean contains(int x, int y) {
		    // If the button has changed size, make a new shape object
		    if (shape == null || !shape.getBounds().equals(getBounds())) {
		      shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
		    }
		    return shape.contains(x, y);
		  }
	 }

	class home extends JPanel {
		 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	     int maxWidth = (int) screenSize.getWidth(); // Maximum width of the screen
	     int maxHeight = (int) screenSize.getHeight(); // Maximum width of the screen
	   
	     public home() {
	    	 setBackground(new Color(40,40,40));
	     }
		@Override
      protected void paintComponent(Graphics g) {
			
          super.paintComponent(g);
          Graphics2D g2d = (Graphics2D) g;
          int x = 50;
          int y = 75;
          
          g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
          g2d.setColor(Color.lightGray);
          g2d.drawLine(400, 10, 400, 30);
          // Draw multiple ovals
          g2d.setColor(Color.BLACK);
        //  g2d.drawRect(x, y, maxWidth, maxHeight);
      	g2d.drawRect(0, 40, maxWidth-65, maxHeight-70); //x,y,width,height Outer rectangle
		g2d.drawRect(0, 40, 275, maxHeight-70); // draw the friends/conversation side of the board
		g2d.drawRect(maxWidth-400,40,400,maxHeight-40); // draw the online now on the right side of the board
		g2d.drawLine(0, 0, 0, 50);
		g2d.drawLine(275, 0, 275, 200); // draws line to connect boards
		g2d.drawLine(maxWidth-400, 0, maxWidth-400, 200); // draws line to connect boards
		g2d.drawLine(0, 0, maxWidth,0);
		
		g2d.drawRect(0, maxHeight-100, 275, 100); // draw the profile area
		g2d.fillRect(0, maxHeight-100, 275, 100); // draw the profile area\
		
		g2d.setColor(Color.lightGray);
		g2d.drawOval(10, maxHeight-80, 50, 50);
		
		g2d.drawOval(50, maxHeight-50, 20, 20);
		g2d.setColor(Color.green);
		g2d.fillOval(50, maxHeight-50, 20, 20);
		
		
		g2d.setFont(new Font("GG Sans", Font.PLAIN, 15));
		Login login = new Login();
    	login.disposeFrame();
    	String Username = login.getUsername();
    	if(Username == null || Username.isEmpty()) {
    		g2d.drawString("Annoymous", 70, maxHeight-60);
    	}
    	else {
    	
    		g2d.drawString(Username, 70, maxHeight-60);
    	}
		
		
		g2d.setFont(new Font("GG Sans", Font.PLAIN, 15));
		g2d.setColor(Color.LIGHT_GRAY);
	//	g2d.drawString("Discord", 20, 20);
		g2d.setFont(new Font("GG Sans", Font.PLAIN, 20));
		g2d.drawString("Friends", x, y);
		g2d.drawString("Message Requests", x-15, y+75);
		
		g2d.setFont(new Font("GG Sans", Font.PLAIN, 15));
		g2d.drawString("Direct Messages", 20, y+150);
		g2d.drawString("+", x+175, y+150);
        ;
          
      }
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 MYSQLConnector ser = new MYSQLConnector();
	
		
		if (e.getSource() == serverIcons.get(2)) {
            String name = ser.retrieveServerName();
            int id = ser.retrieveServerIdByName(name);
            Server s = new Server(name, id);
            JFrame serverFrame = s.getFrame();
            serverFrame.setVisible(true);
//            if( s != null && s.getFrame().isVisible()) {
//            	s.disposeFrame();
//            }
            			
}
//		 if(e.getSource() == serverIcons.get(3)) {
//       	  String name2 = ser.retrieveServerName();
//             int id2 = ser.retrieveServerIdByName("Bryan second");
//             Server s2 = new Server(name2, id2);
//             JFrame serverFrame2 = s2.getFrame();
//             serverFrame2.setVisible(true);
//         }
//		 if(e.getSource() == serverIcons.get(4)) {
//	       	  String name3 = ser.retrieveServerName();
//	             int id3 = ser.retrieveServerIdByName("Bryan third");
//	             Server s3 = new Server(name3, id3);
//	             JFrame serverFrame2 = s3.getFrame();
//	             serverFrame2.setVisible(true);
//	         }

 
		if(e.getSource() == serverIcons.get(1)) {
			createOrJoinServers c = new createOrJoinServers();
			
		}
	}

	 
}
