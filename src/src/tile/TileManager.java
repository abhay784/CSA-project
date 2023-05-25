package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import legend.GamePanel;
import legend.utilityTool;

public class TileManager {
 GamePanel gp;
 public tile[] tile;
 public int mapTileNum[][];
 public TileManager(GamePanel gp) {
	 this.gp =gp;
	 tile = new tile[20];
	 mapTileNum= new int[gp.maxWorldCol] [gp.maxWorldRow];
	 
	 getTileImage();
	 loadMap("/maps/world.txt");
 }
  public void getTileImage() {
	  
	  setup(0, "grass",false);
	  setup(1, "wall",true);
	  setup(2, "water",true);
	  setup(3, "earth",false);
	  setup(4, "tree",true);
	  setup(5, "sand",false);
	  setup(6,"carpet_side_bottom",false);
	  setup(7,"carpet_up_left",false);
	  setup(8,"carpet_up_right",false);
	  setup(9,"carpet_corner_bottom_left",false);
	  setup(10,"carpet_corner_top_right",false);
	  setup(11,"carpet_side_top", false);
	  setup(12,"black_tile_outside_grass", true);
	  setup(13,"wood_white_side_right", false);
	  setup(14,"stone_wall_bottom", true);
	  setup(15,"stone_wall_to", true);
	  setup(16,"wood_white_corner_right", false);
	  setup(17,"stone_wall", true);
	  setup(18, "wood_wall", false);
	  
  }
  
 public void setup( int index, String imageName, boolean collision) {
	 utilityTool uTool = new utilityTool();
	 
	 try {
		 
		 tile[index] =new tile();
		 tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imageName +".png"));
		 tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
		 tile[index].collision= collision;
		 
	 }catch(IOException e) {
		 e.printStackTrace();
	 }
 }
  
  public void loadMap(String filePath) {
	  try {
		  InputStream is = getClass().getResourceAsStream(filePath);
		  BufferedReader br = new BufferedReader(new InputStreamReader(is));
		  int col =0;
		  int row =0;
		  while(col< gp.maxWorldCol && row <gp.maxWorldRow) {
			  String line = br.readLine();
			  while(col< gp.maxWorldCol) {
				  String numbers[] = line.split(" ");
				  //makes the strings into numbers
				  int num = Integer.parseInt(numbers[col]);
				  mapTileNum[col][row] = num;
				  col++;
			  }
			  if(col== gp.maxWorldCol) {
				  col=0;
				  row++;
			  }
		  }
		  br.close();
	  }catch(Exception e){
		  
	  }
  }
  public void draw(Graphics2D g2) {
	  int worldCol =0;
	  int worldRow =0;

	  
	  while(worldCol< gp.maxWorldCol && worldRow <gp.maxWorldRow) {
		  int tileNum = mapTileNum[worldCol][worldRow];
		  
		  int worldX= worldCol*gp.tileSize;
		  int worldY = worldRow * gp.tileSize;
		  
		  int screenX = worldX - gp.player.worldX + gp.player.screenX;
		  int screenY=  worldY - gp.player.worldY + gp.player.screenY;
		  
		  if(worldX +gp.tileSize >gp.player.worldX-gp.player.screenX && 
		     worldX -gp.tileSize<gp.player.worldX+gp.player.screenX &&
		     worldY +gp.tileSize>gp.player.worldY-gp.player.screenY &&
		     worldY -gp.tileSize<gp.player.worldY+gp.player.screenY) {
		  g2.drawImage(tile[tileNum].image, screenX ,screenY,gp.tileSize, gp.tileSize, null);
		  }
		  worldCol++;
		  if(worldCol==gp.maxWorldCol) {
			  worldCol=0;
			  worldRow++;
			  
		  }  
	  }
	  
  }
  
}
