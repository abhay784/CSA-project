package legend;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import tile.TileManager;

import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable{
	final int originalTileSize =16;
	final int scale = 3;
	public final int tileSize = originalTileSize*scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow =12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	//world settings
	public final int maxWorldCol=16;
	public final int maxWorldRow=12;
	public final int worldWidth =tileSize * maxWorldCol;
	public final int worldHeight =tileSize * maxWorldRow;
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this);
	public Player player = new Player(this, keyH);
	//public entity npc[]= new entity[10]; 
	//public AssetSetter aSetter = newAssetSetter(this);
	
	int FPS = 60;
	TileManager tileM = new TileManager(this);
	Sound sound = new Sound();
	
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
	public void setupGame() {
		//aSetter.setObject();
		playMusic(1);
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
			}
		}
	}
	public void update() {
		player.update();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		tileM.draw(g2);
		player.draw(g2);

		g2.dispose();
	}
	public void playMusic(int i) {
		sound.setFile(i);
		sound.play();
		sound.loop();
	}
	public void stopMusic() {
		sound.stop();
	}
}