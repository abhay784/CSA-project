package legend;

import entity.entity;

public class CollisionChecker {
	
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp =gp;
	}
	
	//check collisions for tiles and monsters
	public void checkTile(entity entity) {
		int entityLeftWorld = entity.worldX + entity.solidArea.x;
		int entityRightWorld = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorld = entity.worldY + entity.solidArea.y;
		int entityBottomWorld = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorld/gp.tileSize;
		int entityRightCol = entityRightWorld/gp.tileSize;
		int entityTopRow= entityTopWorld/gp.tileSize;
		int entityBottomRow=entityBottomWorld/gp.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
		case"up":
			entityTopRow = (entityTopWorld -entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn=true;
			}
			break;
		case"down":
			entityBottomRow = (entityBottomWorld + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn=true;
			}
			break;
		case"left":
			entityLeftCol = (entityLeftWorld -entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn=true;
			}
			break;
		case"right":
			entityRightCol = (entityRightWorld + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn=true;
			}
			break;
		}
	}

}
