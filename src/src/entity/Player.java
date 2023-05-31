package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import legend.GamePanel;
import legend.KeyHandler;

public class Player extends entity {
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	public int solidAreaDefaultX;
	public int solidAreaDefaultY;
	
	
	public Player(GamePanel gp, KeyHandler keyH)
	{
		super(gp);
		
		this.keyH= keyH;
		//subtract half the tile size to make it centered
		screenX=gp.screenWidth/2 - gp.tileSize/2;
		screenY=gp.screenHeight/2 - gp.tileSize/2;
		
		//hitbox
		solidArea= new Rectangle();
		//by pixels
		solidArea.x=8;
		solidArea.y=16;
		solidArea.width=32;
		solidArea.height=32;
		
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		setDefaultValues();
		getPlayerImage();
		
	}
	public void setDefaultValues()
	{
		//players position on the world map
		worldX= gp.tileSize *8;
		worldY=gp.tileSize*7;
		speed =4;
		direction ="down";
	}
	public void getPlayerImage() {
		try {
			up1=ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2=ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1=ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2=ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1=ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2=ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1=ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2=ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void update() {
		if(keyH.upPressed ==true || keyH.downPressed ==true 
		|| keyH.leftPressed ==true || keyH.rightPressed ==true){
		if(keyH.upPressed == true)
		{
			direction="up";
			
		}
		else if(keyH.downPressed == true)
		{
			direction="down";
			
		}
		else if(keyH.leftPressed == true)
		{
			direction="left";
		}
		else if(keyH.rightPressed == true)
		{
			direction="right";
		}
		
		//Based on the direction,
		//check tile collision
		collisionOn =false;
		gp.cChecker.checkTile(this);
		
		gp.eHandler.checkEvent();
		//if collision is false, player can move
		if(collisionOn==false)
            switch(direction) {
            case"up": worldY-=speed; break;
            case"down": worldY+=speed; break;
            case"left": worldX-=speed; break;
            case"right": worldX+=speed; break;
              }

        spriteCounter++;
        if(spriteCounter>12)
        {
            if(spriteNum==1) {
                spriteNum=2;
            }
            else if(spriteNum==2) {
                spriteNum=1;
            }
            spriteCounter=0;
        }
    }
    }
	public void draw(Graphics2D g2) {
	//	g2.setColor(Color.white);
	//	g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		BufferedImage image= null;
		switch(direction) {
		case "up":
			if(spriteNum==1) {
				image =up1;
			}
			if(spriteNum==2) {
				image =up2;
			}
			break;
		case "down":
			if(spriteNum==1) {
				image =down1;
			}
			if(spriteNum==2) {
				image =down2;
			}
			break;
		case "left":
			if(spriteNum==1) {
				image =left1;
			}
			if(spriteNum==2) {
				image =left2;
			}
			break;
		case "right":
			if(spriteNum==1) {
				image =right1;
			}
			if(spriteNum==2) {
				image =right2;
			}
			break;
		}
		int x = screenX;
		int y=screenY;
		if(screenX >worldX) {
			x=worldX;
		}
		if(screenY> worldY) {
			y=worldY;
		}
		  int rightOffSet =gp.screenWidth -screenX;
		  if(rightOffSet > gp.worldWidth-worldX){
			  x = gp.screenWidth-(gp.worldWidth-worldX);
		  }
		 
		  int bottomOffSet = gp.screenHeight -screenY;
		  if(bottomOffSet > gp.worldHeight - worldY){
			  y = gp.screenHeight - (gp.worldHeight -worldY);
		  }
	g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
	public int PlayerHealth;
	public int DreamyHealth;
	public double chargeAttack =1.0;
	public boolean attack= false;
	public String DreamyAttack;
	public boolean attackLanded = false;
	public int counter = 1;
	public void slice() {
		DreamyHealth-=50*chargeAttack;
		chargeAttack = 1.0;
	}
	public void nutShot() {
		if(Math.random()>=0.3) {
			DreamyHealth-=75*chargeAttack;
			chargeAttack = 1.0;
			attackLanded = true;
		}
	}
	public void Charge() {
		chargeAttack = 1.5;
	}
	public void Heal() {
		if(counter<4)
		{
			PlayerHealth+=50;
		}
	}
	public int enemyCharge = 1;
	public void enemyAttack() {
		
		double afterAttack = Math.random();
		if(afterAttack<=0.25) {
			PlayerHealth-=30*enemyCharge;
			DreamyAttack = "Saxophone Dance";
			enemyCharge--;
			attack = true;

		}
		else if(afterAttack<=0.5) {
			PlayerHealth-=30*enemyCharge;
			DreamyAttack = "Big Buster Attack";
			enemyCharge--;
			attack = true;
		}
		else if(afterAttack<=0.75) {
			enemyCharge += 1;
			DreamyAttack = "Dreamy Charge";
			attack = true;
		}
		else {
			PlayerHealth-=20;
			DreamyAttack = "Glock";
			enemyCharge--;
			attack = true;
		}
	}
}
