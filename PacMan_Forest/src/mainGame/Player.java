package mainGame;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;


public class Player{
	
	
	Image playerImg,playerImg2;
	private int playerX;
	private int playerY;
	private boolean direction,rightLeft;
	
	
	
	Player(int playerX,int playerY){
		
		playerImg = new ImageIcon(Player.class.getResource("/PngCharacter.png")).getImage();
		playerImg2=new ImageIcon(Player.class.getResource("/512x512px.gif")).getImage();
		
		direction=false;
		rightLeft=false;
		
        this.playerX=playerX;
        this.playerY=playerY;

		
	}
	
	public int getPlayerX() {
		return playerX;
	}
	public void setPlayerX(int playerX) {
		this.playerX = playerX;
	}


	public int getPlayerY() {
		return playerY;
	}
	public void setPlayerY(int playerY) {
		this.playerY = playerY;
	}



	public void render(Graphics2D g2D,int drawX,int drawY) {
		if(direction==true) {
			if(rightLeft==true) {
				g2D.drawImage(playerImg2, drawX, drawY, playerImg2.getWidth(null), playerImg2.getHeight(null), null);
			}
			else if(rightLeft==false) {
				g2D.drawImage(playerImg2, drawX+playerImg2.getWidth(null), drawY, -playerImg2.getWidth(null), playerImg2.getHeight(null), null);
			}
		  }
	    else if(direction==false) {
			g2D.drawImage(playerImg, drawX, drawY,playerImg.getWidth(null),playerImg.getHeight(null) ,null);
		}
		
		
	}
	
	public boolean isDirection() {
		return direction;
	}
	public void setDirection(boolean direction) {
		this.direction = direction;
	}
	
	public boolean isRightLeft() {
		return rightLeft;
	}
	public void setRightLeft(boolean RL) {
		this.rightLeft = RL;
	}



	public void move(int x,int y) {
		playerX+=x;
		playerY+=y;
		
	}
	
	public Rectangle  getBounds() {
		return new Rectangle(playerX,playerY,playerImg2.getWidth(null),playerImg2.getHeight(null));
	}
	
	
	


}
