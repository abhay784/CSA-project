package legend;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable{
	final int originalTileSize =16;
	final int scale = 3;
	public final int tileSize = originalTileSize*scale;
	final int maxScreenCol = 16;
	final int maxScreenRow =12;
	final int screenWidth = tileSize * maxScreenCol;
	final int screenHeight = tileSize * maxScreenRow;
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this, keyH);
	
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
		gameThread = new Thread(this);
		gameThread.start();
	}
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta =0;
		long lastTime = System.nanoTime();
		long currentTime;
		while(gameThread!=null)
		{

			currentTime = System.nanoTime();
			delta +=(currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			if(delta>=1)
			{
				update();
				repaint();
				delta--;
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
>>>>>>> 519e92d7304f2ef7ed2ec3fbadde55272d209e81
			}
            
		}
	}
	public void update() {
		player.update();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		player.draw(g2);

		g2.dispose();
	}
}