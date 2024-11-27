package Pong;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import javax.swing.*;
public class GameFrame extends JFrame {
	public GameFrame() {
		
		GamePanal panal = new GamePanal();	
		
	            //ImageIcon icon = new ImageIcon(getClass().getResource("Pong.png"));
	           // Image image = icon.getImage();
	          //  Image scaledImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
	           // ImageIcon smootherIcon = new ImageIcon(scaledImage);
	           // this.setIconImage(smootherIcon.getImage());
	       
	           
	            
	        
		this.add(panal);
		this.setTitle("Pong");
		this.setResizable(true);	//Change if want minimize or maximize screen
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
}
