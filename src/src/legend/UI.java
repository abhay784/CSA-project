package legend;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UI {
	GamePanel gp;
	Graphics2D g2;
	Font arial_40;
	int subState =0;
	public int commandNum=0;
	Font Serif;
	BufferedImage battleScreen, playerDead,playerWin;


	
	//Always instantiate in the constructor
	public UI(GamePanel gp)  {
		this.gp=gp;
		//first parameter is the font name
		//2nd parameter font style, plain, italic, bold
		//3rd parameter is size
		//don't call this in draw
		arial_40 =new Font("Arial", Font.PLAIN, 40);
	    try {
	        battleScreen = ImageIO.read(getClass().getResourceAsStream("/tiles/battle_screen.png"));
	        playerDead = ImageIO.read(getClass().getResourceAsStream("/player/dreamy_dead.png"));
	        playerWin = ImageIO.read(getClass().getResourceAsStream("/player/playerWin.png"));
	        }
	    catch(IOException e) {
	          e.printStackTrace();
	     }
		
	}


	public void draw(Graphics2D g2) {
		this.g2=g2;
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		if(gp.gameState==gp.titleState) {
			drawTitleScreen();
		}
		
		if(gp.gameState == gp.playState){
			//playState stuff later
		}
		
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}

		if(gp.gameState == gp.combatState) {
			if(gp.player.DreamyHealth>0&&gp.player.PlayerHealth>0)
			{
				drawCombatScreen();
			}
			if(gp.player.PlayerHealth<=0) {
				drawGameOverScreen();
			}
			if(gp.player.DreamyHealth<=07&&gp.player.PlayerHealth>0) {
				drawWinScreen();
			}
		}

		
	}
	public void drawTitleScreen() {
		g2.setFont(Serif);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
		String text = "The Legend Of Dreamy";
		int x = getXForCenteredText(text);
		int y = gp.tileSize*3;
		
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		x = gp.screenWidth/2-50;
		y = gp.tileSize*4;
		g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
		
		text = "START GAME";
		x = getXForCenteredText(text);
		y += gp.tileSize*5;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-gp.tileSize, y);
		}
	}
	public void drawPauseScreen() {
		String text ="Paused";
		int x;
		x= getXForCenteredText(text);
		int y= gp.screenHeight/2;	
		g2.drawString(text,x,y);
	}
	
	public int getXForCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x =gp.screenWidth/2- length/2;
		return x;
	}
	public void drawCombatScreen() {
		g2.setColor(Color.white);
		//70F controls the size
		g2.setFont(g2.getFont().deriveFont(70F));
		//sub window
		int frameX = 0;
		int frameY=gp.tileSize*7;
		int frameWidth= gp.tileSize*16;
		int frameHeight = gp.tileSize*5;
		
		g2.drawImage(battleScreen, 0, 0, 256*3, 112*3, null);
		drawSubWindow(frameX,frameY,frameWidth,frameHeight);

		
		switch(subState) {
		case 0: optionsTop(frameX, frameY); break;
		case 1: options_combat(frameX, frameY); break;
		case 2: options_items(frameX, frameY); break;
		case 3: attackMode(frameX,frameY);break;
		
		case 5: run(frameX,frameY); break;
		}
	}
	
	public void optionsTop(int frameX, int frameY) {
		int textX;
		int textY;
		//attack
		String text = "Attack";
		textX=getXForCenteredText(text)-gp.tileSize*4;
		textY = frameY+gp.tileSize*2;		
		g2.drawString(text, textX, textY);
		if(commandNum==0) {
			g2.drawString(">",textX-50,textY);
		}
		//guard
		text = "Guard";
		textX=getXForCenteredText(text)+gp.tileSize*4;
		textY = frameY+gp.tileSize*2;		
		g2.drawString(text, textX, textY);
		if(commandNum==2) {
			g2.drawString(">",textX-50,textY);
			if(gp.keyH.enterPressed == true) {
				// do something
			}
		}
		//items
		text = "Items";
		textX=getXForCenteredText(text)-gp.tileSize*4;
		textY = frameY+gp.tileSize*4;		
		g2.drawString(text, textX, textY);
		if(commandNum==1) {
			g2.drawString(">",textX-50,textY);
		}
		//run
		text = "Run";
		textX=getXForCenteredText(text)+gp.tileSize*4;
		textY = frameY+gp.tileSize*4;		
		g2.drawString(text, textX, textY);
		if(commandNum==3) {
			g2.drawString(">",textX-50,textY);
			if(gp.keyH.enterPressed == true) {
				//do something
			}
		}
	}
	
	public void options_combat(int frameX, int frameY) {
		int textX;
		int textY;
		
		//Slice
		String text = "Slice";
		textX=getXForCenteredText(text)-gp.tileSize*4;
		textY = frameY+gp.tileSize*2;		
		g2.drawString(text, textX, textY);
		if(commandNum==0) {
			g2.drawString(">",textX-50,textY);
		}
		//NutShot
		text = "Nutshot";
		textX=getXForCenteredText(text)-gp.tileSize*4;
		textY = frameY+gp.tileSize*4;		
		g2.drawString(text, textX, textY);
		if(commandNum==1) {
			g2.drawString(">",textX-50,textY);
		}
		//Charge
		text = "Charge";
		textX=getXForCenteredText(text)+gp.tileSize*4;
		textY = frameY+gp.tileSize*2;		
		g2.drawString(text, textX, textY);
		if(commandNum==2) {
			g2.drawString(">",textX-50,textY);
		}
		//run
		text = "Heal";
		textX=getXForCenteredText(text)+gp.tileSize*4;
		textY = frameY+gp.tileSize*4;		
		g2.drawString(text, textX, textY);
		if(commandNum==3) {
			g2.drawString(">",textX-50,textY);
		}
		
	}
	
	public void options_items(int frameX, int frameY) {
		int textX;
		int textY;
		
		//String
		String text = "You have none!";
		//make it smaller
		g2.setFont(g2.getFont().deriveFont(70F));
		textX=getXForCenteredText(text);
		textY = frameY+gp.tileSize*2;		
		g2.drawString(text, textX, textY);

	}
	public String attackText;
	public boolean check;
	public void attackMode(int frameX, int frameY) {
		int textX;
		int textY;

		if(gp.ui.commandNum==0) {
			attackText = "Slice";
		}
		else if(gp.ui.commandNum==1) {
			if(gp.player.attackLanded)
			{
				attackText = "NutShot, it landed";
			}
			else
			{
				attackText = "NutShot,but it missed";
			}
		}
		else if(gp.ui.commandNum==2)
		{
			attackText = "Charge";
		}
		else {
			if(gp.player.counter<=3) {
				attackText = "Heal";
			}
			else {
				attackText = "Out of Heals";
			}
			
		}
		if(check) {
			attackText = "Guard";
		}
		//String
		String text = "DreamyHealth:"+gp.player.DreamyHealth;
		String text2 = "PlayerHealth:"+gp.player.PlayerHealth;
		String textUserAttack = "You used:"+attackText;
		String textDreamyAttack = "Dreamy used:"+gp.player.DreamyAttack;
		//make it smaller
		g2.setFont(g2.getFont().deriveFont(50F));
		textX=getXForCenteredText(text);
		textY = frameY+gp.tileSize*2;		
		g2.drawString(text, textX-150, textY-50);
		g2.drawString(text2, textX-150, textY);
		g2.drawString(textUserAttack, textX-150, textY+50);
		g2.drawString(textDreamyAttack, textX-150, textY+100);
		
	}
	public void drawSubWindow(int x, int y, int width, int height) {
		//if you add a 4th parameter to the color constructor
		// you change the opacity
		Color color = new Color (0,0,0);
		g2.setColor(color);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		color = new Color(255,255,255);
		g2.setColor(color);
		//defines the width of outline of graphics
		// which are rendered with a graphics2D
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5,y+5, width-10, height-10, 25,25);
	}
	public void drawGameOverScreen() {
		int frameX=0;
		int frameY=0;
		int frameWidth= gp.tileSize*16;
		int frameHeight= gp.tileSize*12;
		
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		String text1 ="Game over,";
		String text2 ="you suck!";
		int textX1=getXForCenteredText(text1);
		int textX2=getXForCenteredText(text2);
		int textY = gp.tileSize*2;
		g2.setFont(g2.getFont().deriveFont(100F));
		g2.drawString(text1, textX1-gp.tileSize*3, textY);
		g2.setFont(g2.getFont().deriveFont(140F));
		g2.drawString(text2, textX2-gp.tileSize*4, textY+gp.tileSize*3);
		g2.drawImage(playerDead, gp.tileSize*5, gp.tileSize*6, gp.tileSize*6, gp.tileSize*4, null);
		
	}
	public void drawWinScreen() {
		int frameX=0;
		int frameY=0;
		int frameWidth= gp.tileSize*16;
		int frameHeight= gp.tileSize*12;
		
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		String text1 ="You win,";
		String text2 ="you rock!";
		int textX1=getXForCenteredText(text1);
		int textX2=getXForCenteredText(text2);
		int textY = gp.tileSize*2;
		g2.setFont(g2.getFont().deriveFont(100F));
		g2.drawString(text1, textX1-gp.tileSize*3, textY);
		g2.setFont(g2.getFont().deriveFont(140F));
		g2.drawString(text2, textX2-gp.tileSize*4, textY+gp.tileSize*3);
		g2.drawImage(playerWin, gp.tileSize*5, gp.tileSize*6, gp.tileSize*6, gp.tileSize*4, null);
	}
	public void run(int frameX, int frameY) {
		int textX;
		int textY;
		
		g2.setFont(g2.getFont().deriveFont(50F));
		String text = "You attempt to run!";
		textX=getXForCenteredText(text)-gp.tileSize*3;
		textY = frameY+gp.tileSize+10*2;
		g2.drawString(text, textX, textY);
		
		text = "However, you remember that";
		textX=getXForCenteredText(text)-gp.tileSize+20;
		textY = frameY+gp.tileSize*2+20;		
		g2.drawString(text, textX, textY);
		
		text ="Dreamy slaughtered your family";
		textX=getXForCenteredText(text);
		textY = frameY+gp.tileSize*3+20;		
		g2.drawString(text, textX, textY);
		
		text="and you must get revenge.";
		textX=getXForCenteredText(text)-gp.tileSize-10;
		textY = frameY+gp.tileSize*4+20;		
		g2.drawString(text, textX, textY);
	}
}

