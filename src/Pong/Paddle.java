package Pong;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Paddle extends Rectangle{
	int id;
	int YVelocity;
	int speed = 15;
	Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id) {
		super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		this.id = id;
	}
	//public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			switch(id) {
			case 1:
				if(e.getKeyCode()== KeyEvent.VK_W) {	// ADJUST THIS FOR PADDLE SPEED  / MOVES WITH W
					setDirection(-speed);
					move();
				}
				if(e.getKeyCode()== KeyEvent.VK_S) {
					setDirection(speed);
					move();
				}
				break;
			case 2:
				if(e.getKeyCode()== KeyEvent.VK_UP) {	// ADJUST THIS FOR PADDLE SPEED  / MOVES WITH UP ARROW
					setDirection(-speed);
					move();
				}
				if(e.getKeyCode()== KeyEvent.VK_DOWN) {
					setDirection(speed);
					move();
				}
				break;
			}
		}
		public void keyReleased(KeyEvent e) { // WHEN KEY IS RELEASED, STOPS MOVING 
			switch(id) {
			case 1:
				if(e.getKeyCode()== KeyEvent.VK_W) {	
					setDirection(0);
					move();
				}
				if(e.getKeyCode()== KeyEvent.VK_S) {
					setDirection(0);
					move();
				}
				break;
			case 2:
				if(e.getKeyCode()== KeyEvent.VK_UP) {	
					setDirection(0);
					move();
				}
				if(e.getKeyCode()== KeyEvent.VK_DOWN) {
					setDirection(0);
					move();
				}
				break;
			}
		}
		public void setDirection(int yDirection)  {
			YVelocity = yDirection;
		}
		public void move() {
			y= y + YVelocity;
		}
		public void draw(Graphics g) {
			if(id == 1) {
				g.setColor(Color.blue);
			}
			else
				g.setColor(Color.red);
			g.fillRect(x, y, width, height);
		}
		
	}

