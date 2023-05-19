package legend;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	final int originalTileSize =16;
	final int scale = 3;
	final int tileSize = originalTileSize*scale;
	final int maxScreenCol = 16;
	final int maxScreenRow =12;
	final int screenWidth = tileSize * maxScreenCol;
	final int screenHeight = tileSize * maxScreenRow;
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	
	int FPS = 60;
	int posX = 100;
	int posY =100;
	int userSpeed = 4;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });
		
	}

	public void startGameThread() {
		Thread gameThread = new Thread(this);
		gameThread.start();
	}
	public void run() {
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime()+drawInterval;
		while(gameThread!=null)
		{
			update();
			
			repaint();
			

			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;

				
				if(remainingTime<0)
				{
					remainingTime = 0;
				}
				Thread.sleep((long) remainingTime);
				
				nextDrawTime+=drawInterval;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void update() {
		if(keyH.upPressed == true)
		{
			posY-=userSpeed;
		}
		else if(keyH.downPressed == true)
		{
			posY+=userSpeed;
		}
		else if(keyH.leftPressed == true)
		{
			posX-=userSpeed;
		}
		else if(keyH.rightPressed == true)
		{
			posX+=userSpeed;
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.white);
		g2.fillRect(posX, posY, tileSize, tileSize);
		g2.dispose();
	}
}