package legend;

import javax.swing.JPanel;

public class BattlePanel extends JPanel implements Runnable {
	final int originalTileSize =16;
	final int scale = 3;
	public final int tileSize = originalTileSize*scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow =12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	//KeyHandler keyA = new KeyHandler();
	Thread gameThread;
	
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
