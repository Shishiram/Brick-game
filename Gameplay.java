 package breakout;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Gameplay extends JPanel implements ActionListener, KeyListener {
	private boolean play = false;
	private int score = 0;
	private int totalBricks = 21;
	private Timer timer;
	private int delay  = 8;
	private int playerx = 300;
	private int ballposx = 120;
	private int ballposy = 350;
	private int dx = -1;
	private int  dy = -2;
	private Bricks bricks;
	public Gameplay() {
		bricks = new Bricks(3,7);
		addKeyListener(this);
		//addActionListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		timer = new Timer(delay,this);
		timer.start();
	}
	public void paint(Graphics g) {
		//background
		
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		//borders
		g.setColor(Color.yellow);
		g.fillRect(0 , 0, 3, 592);//left
		g.fillRect(0 , 0, 692, 3);//top
		g.fillRect(681 , 0, 3, 592);//right
		
		//map
		bricks.draw((Graphics2D)g);
		
		//score
		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD,25));
		g.drawString(""+score,600 , 25);
		
		//pad
		g.setColor(Color.green);
		g.fillRect(playerx, 550, 100, 8);
		
		//pad
		g.setColor(Color.yellow);
		g.fillOval(ballposx, ballposy, 20, 20);
		
		// the ball
		if(ballposy>570) {
			play = false;
			dx = 0;
			dy = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Game Over,Scores:"+score,190 , 300);
			
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("press ENTER to restart",230 , 350);
		}
		
		if(totalBricks<=0) {
			play = false;
			//dx = 0;
			//dy = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("YOU, Won, SCORE: "+score,190 , 300);
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("press ENTER to restart",230 , 350);
		}
		g.dispose();
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//timer.start();
		
		if(new Rectangle(ballposx,ballposy,20,20).intersects(new Rectangle(playerx,550,100,8))) {
			dy = -dy;
		}
		for(int i=0;i<bricks.map.length;i++) {
			for(int j=0;j<bricks.map[0].length;j++) {
				if(bricks.map[i][j]>0) {
					Rectangle brickRect = new Rectangle(j*bricks.brickWidth+80, i*bricks.brickHeight+50, bricks.brickWidth, bricks.brickHeight);
					Rectangle ballRect = new Rectangle(ballposx,ballposy,20,20);
					if(ballRect.intersects(brickRect)) {
						bricks.setBrickValue(0,i,j);
						totalBricks--;
						score += 5;
						if(ballposx +19 <= brickRect.x || ballposx+1>=brickRect.x + brickRect.width) {
							dx = -dx;
						}
						else {
							dy = -dy;
						}
					}
				}
			}
		}
		if(play) {
			ballposx += dx;
			ballposy += dy;
		}
		if(ballposx < 0) {
			dx = -dx; 
		}
		if(ballposy<0) {
			dy = -dy;
		}
		if(ballposx > 670) {
			dx = -dx;
		}
//		if(ballposy>560) {
	//		dy = -dy;
		//}
		repaint();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerx>=580) {
				playerx = 580;
			}
			else {
				moveRight();
			}
			//repaint();
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerx<=5) {
				playerx = 5;
			}
			else {
				moveLeft();
			}
		//	repaint();
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play) {
				play = true;
				ballposx = 120;
				ballposy = 350;
				dx = -1;
				dy = -2;
				playerx = 310;
				score =0;
				totalBricks =21;
				bricks =  new Bricks(3,7);
				repaint();
			}
		}
	}
	public void moveRight() {
		play = true;
		playerx+=20;
	}
	public void moveLeft() {
		play = true;
		playerx-=20;
	}
	
}
