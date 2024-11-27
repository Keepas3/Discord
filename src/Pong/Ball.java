package Pong;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Ball extends Rectangle {
	Random random;
	int XVelocity;
	int YVelocity;
	int initialSpeed = 7; // Change this for easier or harder game

	Ball(int x, int y, int width, int height) {
		super(x, y, width, height);
		random = new Random();
		int randomXDirection = random.nextInt(2); // Creates a random number of either 0 or 1
		if (randomXDirection == 0) // if 0 means
			randomXDirection--;
		setXDirection(randomXDirection * initialSpeed);
		int randomYDirection = random.nextInt(2); // Creates a random number of either 0 or 1
		if (randomXDirection == 0) // if 0 means
			randomYDirection--;
		setYDirection(randomYDirection * initialSpeed);
	}

	public void setXDirection(int randomXDirection) {
		XVelocity = randomXDirection;
	}

	public void setYDirection(int randomYDirection) {
		YVelocity = randomYDirection;
	}

	public void move() {
		x += XVelocity;
		y += YVelocity;
	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, height, width);
	}
}

