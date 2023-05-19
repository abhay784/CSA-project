package legend;

import java.awt.color.*;
import java.awt.Dimension;
import javax.swing.JFrame;


public class LegendOfAmbutukam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("The Legend of Ambatukam");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack();
		window.setLocationRelativeTo(null); 
		window.setVisible(true);
		
		
		gamePanel.startGameThread();
		
	}

}
