import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class Joc extends JPanel implements KeyListener, ActionListener 
{
	private boolean play = false;
	private int score = 0;
	private int bricks = 21;
	private Timer time;
	private int delay = 10;
	private int playerx = 310;
	
	private int ballx = 350;
	private int bally = 350;
	private int ballxdir = 1;
	private int ballydir = 2;
	
	private Bricks map;
	
	public Joc()
	{
		map = new Bricks(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time = new Timer( delay, this);
		time.start();		
	}
	
	public void paint(Graphics g)
	{
		// Fundal
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		// Margini
		g.setColor(Color.pink);
		g.fillRect(0, 0, 3, 692);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(682, 0, 3, 592);
		// Bara
		g.setColor(Color.cyan);
		g.fillRect(playerx, 550,100, 8);
		// Ball
		g.setColor(Color.pink);
		g.fillOval(ballx, bally,20, 20);
		
		// Bricks
		map.draw((Graphics2D) g);
		
		// Scor
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("", Font.BOLD, 25));
		g.drawString(""+score, 590, 30);
		
		if(bally > 570)
		{
			play = false;
			ballxdir = 0;
			ballydir = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("Cuci", Font.BOLD, 35));
			g.drawString("Game Over", 190, 300);
			
			g.setFont(new Font("Cuci", Font.BOLD, 35));
			g.drawString("Press Enter To Restrat", 230, 350);
			
			
		}
		
		if(bricks <= 0)
		{
			play = false;
			ballxdir = 0;
			ballydir = 0;
			g.setColor(Color.cyan);
			g.setFont(new Font("Cuci", Font.BOLD, 35));
			g.drawString("You Won", 190, 300);
			
			g.setFont(new Font("Cuci", Font.BOLD, 35));
			g.drawString("Press Enter To Restrat", 230, 350);
			
		}
		
		g.dispose();

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		time.start();
		if(play)
		{
			if(new Rectangle(ballx, bally, 20,20).intersects(new Rectangle(playerx,550,100,8)))
			{
				ballydir = - ballydir;
			}
			
			A: for(int i = 0; i<map.map.length; i++) {
				for(int j = 0; j<map.map[0].length; j++)
					if(map.map[i][j] > 0) {
						int bX = j* map.bx + 80;
						int bY = i* map.by + 50;
						int bx = map.bx;
						int by = map.by;
						
						Rectangle rect = new Rectangle(bX,bY,bx,by);
						Rectangle ballrect = new Rectangle(ballx,bally,20,20);
						Rectangle brect = rect;
						
						if(ballrect.intersects(brect))
						{
							map.setValue(0, i ,j);
							bricks--;
							score += 5;
							if(ballx + 19 <= brect.x || ballx +1 >= brect.x + brect.width)
							{
								ballxdir = -ballxdir;
							}
							else
								ballydir = -ballydir;
							break A;
						}
					}
			}
			
					
			ballx += ballxdir;
			bally += ballydir;
			if(ballx < 0)
			{
				ballxdir = -ballxdir;
			}
			if(bally < 0)
			{
				ballydir = -ballydir;
			}
			if(ballx > 670)
			{
				ballxdir = -ballxdir;
			}
		}
		repaint();
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerx >= 580)
			{
				playerx = 580;
			}
			else
				moveRight();
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				if(playerx < 10)
				{
					playerx = 10;
				}
				else
					moveLeft();
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			if(!play)
			{
				play  = true;
				ballx = 350;
				bally = 350;
				ballxdir = -1;
				ballydir = -2;
				playerx = 310;
				score = 0;
				bricks = 21;
				map = new Bricks(3,7);
			}
		}
	}
	
	public void moveRight()
	{
		play = true;
		playerx+=30;
	}
	public void moveLeft()
	{
		play = true;
		playerx-=30;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

}
