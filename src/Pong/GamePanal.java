package Pong;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanal extends JPanel implements Runnable {
	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int) (1000 * (0.555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1;
	Paddle paddle2;
	Ball ball;
	Score score;

	GamePanal() {
		newPaddles();
		newBall();
		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);

		gameThread = new Thread(this);
		gameThread.start();
	}

	public void newBall() { // Starts the ball at the center of the screen
		random = new Random();
		// ball = new
		// Ball((GAME_WIDTH/2)-BALL_DIAMETER/2,(GAME_HEIGHT/2)-(BALL_DIAMETER/2),BALL_DIAMETER,BALL_DIAMETER);
		// // Makes ball appear at middle
		ball = new Ball((GAME_WIDTH / 2) - BALL_DIAMETER / 2, random.nextInt(GAME_HEIGHT - BALL_DIAMETER),
				BALL_DIAMETER, BALL_DIAMETER); // Makes ball appear random
	}

	public void newPaddles() {
		paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH,
				PADDLE_HEIGHT, 2);
	}

	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}

	public void draw(Graphics g) {
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
	}

	public void move() { // Makes the paddles & ball movement more responsive
		paddle1.move();
		paddle2.move();
		ball.move();
	}

	public void checkCollision() {
		// bounces ball off top and bottom edges
		if (ball.y <= 0) {
			ball.setYDirection(-ball.YVelocity);
		}
		if (ball.y >= GAME_HEIGHT - BALL_DIAMETER)
			ball.setYDirection(-ball.YVelocity);
		// bounces ball off paddles
		if (ball.intersects(paddle1)) {
			ball.XVelocity = Math.abs(ball.XVelocity);
			ball.XVelocity++; // increase ball speed when hits paddle / more difficult OPTIONAL
			if (ball.YVelocity > 0) {
				ball.YVelocity++; // also makes it more difficult OPTIONAL
			} else
				ball.YVelocity--;
			ball.setXDirection(ball.XVelocity);
			ball.setYDirection(ball.YVelocity);
		}
		if (ball.intersects(paddle2)) {
			ball.XVelocity = Math.abs(ball.XVelocity);
			ball.XVelocity++; // increase ball speed when hits paddle / more difficult
			if (ball.YVelocity > 0) {
				ball.YVelocity++; // also makes it more difficult
			} else
				ball.YVelocity--;
			ball.setXDirection(-ball.XVelocity);
			ball.setYDirection(ball.YVelocity);
		}
		// stops paddles at edges
		if (paddle1.y <= 0) // CHECKS UP
			paddle1.y = 0;
		if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) // CHECKS DOWN
			paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
		if (paddle2.y <= 0) // CHECKS UP
			paddle2.y = 0;
		if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) // CHECKS DOWN
			paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;
		// GIVES A PLAYER POINT AND RESETS THE GAME
		if (ball.x <= 0) {
			score.player2++;
			newPaddles();
			newBall();
			// System.out.println("Player 2: " +score.player2); / Prints score at the bottom
			// but it looks ugly so optional
		}
		if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
			score.player1++;
			newPaddles();
			newBall();
			// System.out.println("Player 1: " + score.player1); / Prints score at the
			// bottom but it looks ugly so optional
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		double amount = 60;
		double ns = 1000000000 / amount;
		double delta = 0;
		while (true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				move();
				checkCollision();
				repaint();
				delta--;
				// System.out.println("a");
			}
		}
	}

	public class AL extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}

		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
}

