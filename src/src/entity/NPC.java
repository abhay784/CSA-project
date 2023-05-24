package entity;

import java.io.IOException;

import javax.imageio.ImageIO;

import legend.GamePanel;

public class NPC extends entity{
	public NPC(GamePanel gp)
	{
		super(gp);
		
		
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

}
