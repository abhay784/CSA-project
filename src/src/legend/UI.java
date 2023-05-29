package legend;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class UI {
	GamePanel gp;
	Graphics2D g2;
	Font arial_40;
	int subState =0;
	public int commandNum=0;
	
	//Always instantiate in the constructor
	public UI(GamePanel gp) {
		this.gp=gp;
		//first parameter is the font name
		//2nd parameter font style, plain, italic, bold
		//3rd parameter is size
		//don't call this in draw
		arial_40 =new Font("Arial", Font.PLAIN, 40);
	}
	public void draw(Graphics2D g2) {
		this.g2=g2;
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
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
		
		drawSubWindow(frameX,frameY,frameWidth,frameHeight);
		
		switch(subState) {
		case 0: optionsTop(frameX, frameY); break;
		case 1: options_combat(frameX, frameY); break;
		case 2: options_items(frameX, frameY); break;
		case 3: attackMode(frameX,frameY);break;
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
	public void attackMode(int frameX, int frameY) {
		int textX;
		int textY;
		String attackText;
		if(gp.ui.commandNum==0) {
			attackText = "Slice";
		}
		else if(gp.ui.commandNum==1) {
			attackText = "NutShot";
		}
		else if(gp.ui.commandNum==2) {
			attackText = "Charge";
		}
		else {
			attackText = "Heal";
		}
		//String
		String text = "AmbatukamHealth:"+gp.player.DreamyHealth;
		String text2 = "PlayerHealth:"+gp.player.PlayerHealth;
		String textUserAttack = "You used:"+attackText;
		String textDreamyAttack = "Ambatukam"+gp.player.DreamyAttack;
		//make it smaller
		g2.setFont(g2.getFont().deriveFont(70F));
		textX=getXForCenteredText(text);
		textY = frameY+gp.tileSize*2;		
		g2.drawString(text, textX, textY);
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
	
}
