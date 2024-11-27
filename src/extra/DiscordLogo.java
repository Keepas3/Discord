package extra;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DiscordLogo {
	 public DiscordLogo() {
		
}
	 public static JButton createButtonWithFullImage(String imagePath, int buttonWidth, int buttonHeight) {
	        BufferedImage img = null;
	        try {
	            img = ImageIO.read(new File("Discord image.jpg"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        int newWidth =buttonWidth, 
	        		newHeight =buttonHeight;


	        Image scaledImg = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
	        ImageIcon scaledIcon = new ImageIcon(scaledImg);

	        JButton button = new JButton() {
	            @Override
	            protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                Graphics2D g2d = (Graphics2D) g;
//	                g.drawImage(scaledIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
	                g2d.drawImage(scaledIcon.getImage(), 0,0,100,100,this);
	            }
	        };

	        button.setContentAreaFilled(false);
	        button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

	        return button;
	    }
	}


