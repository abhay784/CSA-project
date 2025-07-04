package legend;

import java.awt.Rectangle;

public class EventHandler
{
	GamePanel gp;
	Rectangle eventRect;
	Sound music = new Sound();
	public int eventRectDefaultX, eventRectDefaultY;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new Rectangle();
		eventRect.x = 23;
		eventRect.y = 23;
		eventRect.width = 10;
		eventRect.height = 10;
		eventRectDefaultX = eventRect.x;
		eventRectDefaultY = eventRect.y;
		
	}
	public void checkEvent() {
		if(hit(18, 8,"any")==true) {
			if(gp.gameState == gp.playState) {
				gp.gameState = gp.combatState;
				System.out.print("hi");
				gp.stopMusic();
				gp.playMusic(0);
			}
		}
	}
	public boolean hit(int eventCol,int eventRow, String reqDirection) {
		boolean hit = false;
		
		gp.player.solidArea.x = gp.player.worldX+gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY+gp.player.solidArea.y;
		eventRect.x = eventCol*gp.tileSize + eventRect.x;
		eventRect.y = eventRow*gp.tileSize + eventRect.y;
		if(gp.player.solidArea.intersects(eventRect)) {
			if(gp.player.direction.contentEquals(reqDirection)||reqDirection.contentEquals("any")) {
				hit = true;
			}
		}
		
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRect.x = eventRectDefaultX;
		eventRect.y = eventRectDefaultY;
		
		return hit;
	
	}
	
}