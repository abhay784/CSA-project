package legend;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	public boolean upPressed,downPressed,leftPressed,rightPressed, enterPressed, backPressed = false;
	
	public KeyHandler(GamePanel gp) {
		this.gp =gp;
	}
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		if(code == KeyEvent.VK_P) {
			if(gp.gameState == gp.playState) {
				gp.gameState = gp.pauseState;
			}
			//reset it back
			else if(gp.gameState == gp.pauseState)
			{
			  gp.gameState = gp.playState;
			}
		}
		if(code == KeyEvent.VK_ESCAPE) {
			if(gp.gameState == gp.playState) {
				gp.gameState = gp.combatState;
			}
			//reset it back
			else if(gp.gameState == gp.combatState)
			{
			  gp.gameState = gp.playState;
			}
		}
		if(gp.gameState == gp.combatState) {
			int maxCommandNum=3;
			
			if(code== KeyEvent.VK_W) {
				gp.ui.commandNum--;
				//gp.playSE(9);
				if(gp.ui.commandNum <0) {
					gp.ui.commandNum=maxCommandNum;
				}
			}
			if(code== KeyEvent.VK_S) {
				gp.ui.commandNum++;
				//gp.playSE(9);
				if(gp.ui.commandNum >maxCommandNum) {
					gp.ui.commandNum=0;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.subState==0) {
					if(gp.ui.commandNum == 0) {
					gp.ui.subState = 1;
					gp.ui.commandNum=0;
				  }
				if(gp.ui.commandNum == 1) {
					gp.ui.subState = 2;
					gp.ui.commandNum=0;
				  }
				}
				
			}
			if(code == KeyEvent.VK_DELETE) {
				if(gp.ui.subState ==1 || gp.ui.subState==2) {
				gp.ui.subState = 0;
				gp.ui.commandNum=0;
			 }
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		
	}
	public void optionsState(int code) {
		int maxCommandNum=0;
		switch(gp.ui.subState) {
		case 0: maxCommandNum=3;
		
		}
	}

}
