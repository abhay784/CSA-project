package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import legend.GamePanel;

public class entity
{
	GamePanel gp;
	public int worldX, worldY;
	public int speed;
	public BufferedImage up1, up2, down1, down2,left1, left2, right1, right2;
	public String direction;
	public int spriteCounter=0;
	public int spriteNum=1;
	public Rectangle solidAreaDefaultX,solidAreaDefaultY;
	public Rectangle solidArea = new Rectangle(0,0,40,48);
	public boolean collisionOn= false;

	public entity(GamePanel gp) 
	{
		this.gp = gp;
	}

}
