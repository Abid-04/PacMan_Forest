package mainGame;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

public class Enemies {

	Panel panel;

    private Image enemy1;
    Point pos;
    ArrayList<Point>positions=new ArrayList<>();
    ArrayList<Integer>moves=new ArrayList<>();
    ArrayList<Integer>boundries=new ArrayList<>();
    
    Rectangle rec1,rec2,rec3,rec4,rec5,rec6;

    private String[][]map;
    private int worldX;
    private int worldY;
    int tileSize=54;
    private int leftBound;
    private int rightBound;
    
    Random random;

    public Enemies(int rightBound,String[][]map,int tileSize) {
    	
    	//images of enemy
        enemy1 = new ImageIcon(Enemies.class.getResource("/ghost.png")).getImage();
 
        random=new Random();
        
        boundries.add(0);
        boundries.add(0);
        boundries.add(0);
        boundries.add(0);
        boundries.add(0);
        boundries.add(0);

        moves.add(7);
        moves.add(6);
        moves.add(-5);
        moves.add(8);
        moves.add(-8);
        moves.add(-5);

        //this.leftBound = leftBound;
        this.tileSize=tileSize;
        this.map=map;
        this.rightBound = rightBound;
    }

	//Method of enemy's moving only in X-axis.
    public void enemyMove(int playerX,int playerY) {
    	for(int i=0; i<positions.size(); i++) {	
    		
    		int enemyX=positions.get(i).x;
    		int enemyY=positions.get(i).y;
    		
    		int newX = positions.get(i).x;
    		int newY = positions.get(i).y;

    		int detectionRadius=4*tileSize;

    		boolean detected=Math.abs(enemyX-playerX)<=detectionRadius && Math.abs(enemyY-playerY)<=detectionRadius;
    		
    		if(detected) {
    			
    			int speed=4;
    			
    			if(enemyX > playerX) newX -= speed;
    			if(enemyX < playerX) newX += speed;
    			if(enemyY > playerY) newY -= speed;
    			if(enemyY < playerY) newY += speed;

    			if(canMove(newX, enemyY)) {
    			    positions.get(i).x = newX;
    			}

    			if(canMove(positions.get(i).x, newY)) {
    			    positions.get(i).y = newY;
    			}    			
    			
    		}

    		else {

                int nextX=enemyX+moves.get(i);
                if(nextX>=boundries.get(i) && nextX<=rightBound && canMove(nextX,enemyY)) {
                    positions.get(i).x=nextX;
                } else {
                    moves.set(i,-moves.get(i));
                }
        	}
    		}

    	}
    
    private boolean canMove(int x,int y) {
        int width=enemy1.getWidth(null), height=enemy1.getHeight(null);
        if(x<0 || y<0 || x+width>map[0].length*tileSize || y+height>map.length*tileSize) return false;
        for(int row=y/tileSize;row<=(y+height-1)/tileSize;row++) {
            for(int col=x/tileSize;col<=(x+width-1)/tileSize;col++) {
                String tile=map[row][col];
                if(!tile.equals("1") && !tile.equals("2")) return false;
            }
        }
        return true;
    }

    public Point randomPosition() {
        ArrayList<Point> available=new ArrayList<>();
        for(int row=0;row<map.length;row++) {
            for(int col=0;col<map[row].length;col++) {
                int x=col*tileSize, y=row*tileSize;
                if(x<=rightBound && canMove(x,y)) available.add(new Point(x,y));
            }
        }
        if(available.isEmpty()) throw new IllegalStateException("Map has no enemy spawn tiles");
        return available.get(random.nextInt(available.size()));
    }

    public void enemyCor(int x,int y) {
        positions.clear();
        for(int i=0;i<boundries.size();i++) positions.add(randomPosition());
    }

    public void render(Graphics2D g2D, int cameraX, int cameraY) {	
    	for(int i=0; i<positions.size(); i++) {
    		g2D.drawImage(enemy1, positions.get(i).x-cameraX,positions.get(i).y-cameraY, null);
    	}	
    }

    public Rectangle getBounds(int index) {
    	pos=positions.get(index);

    	return new Rectangle(pos.x,pos.y,enemy1.getWidth(null),enemy1.getHeight(null));
    }

    public int getWorldX() { 
    	return worldX; 
    	}
    public void setWorldX(int x) {
    	this.worldX = x; 
    	}

    public int getWorldY() {
    	return worldY; 
    	}
    public void setWorldY(int y) {
    	this.worldY = y; 
    	}

    public Image getEnemy1() {
		return enemy1;
	}
	public void setEnemy1(Image enemy1) {
		this.enemy1 = enemy1;
	}

}
