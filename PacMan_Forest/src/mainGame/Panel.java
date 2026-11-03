package mainGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class Panel extends JPanel implements ActionListener{

	//Variables
	Player player;
	Enemies enemy;
	Timer timer;
	String [][] map;
	Rectangle rewardRec;
	Random random;
	JLabel label;
	
	boolean treeR,treeL,treeU,treeD;
	boolean no=false;
	
	boolean reward1;
	
	int movement=5;
	int fillRectW=100;
	//Score
	int score=0;
	
	int tilePX,tilePY;
	int tileSize=54;
	int cameraX=0;
	int cameraY=0;
	
	//Screen W/H
	int screenWidth=1538;
	int screenHeight=900;
	
	
	int mapPixelWidth=0;
	int mapPixelHeight=0;
	
	Action right,left,up,down;
	
	//Reward X/Y coordinates 
	int x;
	int y;
	
	
	//Images
	Image grass,water,tree,bridge,reward,reward2,reward3,reward4;
	String [] numbers;
	ArrayList<Boolean> booleans=new ArrayList<>();
	ArrayList<Image> images=new ArrayList<>();
	ArrayList<Point> positions=new ArrayList();
	
	Panel(){
		this.setBounds(0,0,screenWidth,screenHeight);
		this.setBackground(new Color(190,149,220));
		
		
		//Initializing the images
		grass=new ImageIcon(Panel.class.getResource("/grassland.png")).getImage();
		water=new ImageIcon(Panel.class.getResource("/sea.png")).getImage();
		tree=new ImageIcon(Panel.class.getResource("/tree1-ezgif.com-resize.png")).getImage();
		bridge=new ImageIcon(Panel.class.getResource("/retro.png")).getImage();
		reward=new ImageIcon(Panel.class.getResource("/reward.png")).getImage();
		reward2=new ImageIcon(Panel.class.getResource("/reward.png")).getImage();
		reward3=new ImageIcon(Panel.class.getResource("/ghost.png")).getImage();
		reward4=new ImageIcon(Panel.class.getResource("/reward.png")).getImage();
		
		//Tree's booleans to check whether the player is next to it or not.
	    treeR=false;
		treeL=false;
		treeU=false;
		treeD=false;
		
		
		images.add(reward);
		images.add(reward2);
		images.add(reward3);
		images.add(reward4);
		
		
		
		//Game world's map. Through this map, we can place different pictures based on the numbers.
		map=new String[][] {
				{"1","1","1","1","1","1","1","1","1","0","0","0","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"},
				{"1","1","1","1","1","1","1","1","3","0","0","0","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"},
				{"1","1","1","1","1","1","1","1","3","0","0","0","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"},
				{"1","1","1","1","1","1","1","1","1","0","0","0","2","2","0","0","0","0","1","1","1","1","1","1","1","1","1","1","1","1","1","1","3","1","1","1","1","1","1","1","1","1","1","1","1","1","1"},
				{"1","1","1","1","1","1","1","1","1","0","0","0","2","2","0","0","0","0","1","1","1","1","3","1","1","1","1","1","1","1","3","1","1","1","1","1","1","3","1","1","3","1","1","1","1","1","1"},
				{"1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","0","0","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"},
				{"1","1","1","1","1","1","1","1","1","1","3","1","1","1","1","1","0","0","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"},
				{"1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","2","2","1","1","3","1","3","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"},
				{"1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","2","2","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","3","1","1","1","1"},
				{"1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","3","0","0","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","3","1","1","1","1","1","1","1"},
				{"1","1","1","3","1","1","1","1","3","1","1","1","1","1","1","1","0","0","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"},
				{"1","1","1","1","1","3","1","1","1","1","1","1","1","1","1","1","0","0","1","1","1","1","1","1","1","1","1","1","3","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"},
				{"1","1","1","3","1","1","1","1","1","1","1","1","1","1","1","1","2","2","1","3","1","3","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"},
				{"1","1","1","1","1","1","1","1","1","3","1","1","1","1","1","1","2","2","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","3","1","1","1","1","1","1","1","1"},
				{"1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","0","0","1","1","1","1","1","1","3","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"},
				{"1","1","1","1","1","1","1","1","1","0","0","0","1","1","1","1","0","0","1","1","1","1","1","1","1","1","1","1","3","1","1","1","3","1","1","1","1","1","1","1","3","1","3","1","1","1","1"},
				{"1","1","1","1","1","1","1","1","1","0","0","0","1","1","1","1","0","0","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"},
				{"1","1","1","1","1","1","1","1","1","0","0","0","0","0","0","0","0","0","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"},
				{"1","1","1","3","1","1","1","1","1","0","0","0","0","0","0","0","0","0","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","3","1","1","1","1","1","1","1","1","1"},
				{"1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","0","0","1","1","1","1","1","1","3","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"},
				{"1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","0","0","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"},
				{"1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","0","0","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","3","1","1","1","1","3","1","1","1","1","1","1","1"},
				{"1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","0","0","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"},
				{"1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","0","0","1","1","1","1","1","1","3","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"},
				{"1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","0","0","1","1","1","1","1","1","1","1","1","1","1","1","1","3","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"},
				{"1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","0","0","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"},
				{"1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","0","0","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"},
				{"1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","0","0","1","1","1","1","1","1","3","1","1","1","1","1","1","1","3","1","1","1","1","3","1","1","1","1","3","1","1","1","1"},
				{"1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","0","0","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"}
		};
		
		
		
		
		//Random
		random=new Random();
		
		//Label tag for score
		label=new JLabel();
		label.setText("Score: "+score);
		label.setBounds(10, 50, 150, 50);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Times New Roman",Font.BOLD,25));
		
		
		player=new Player(500,650);
		enemy=new Enemies(30*tileSize,map,tileSize);
		
		
		timer=new Timer(18,this);
		timer.start();
		
		int tileWidth=grass.getWidth(null);
		int tileHeight=grass.getHeight(null);

		//map world's width and height 
		mapPixelWidth=map[0].length*tileWidth;
		mapPixelHeight=map.length*tileHeight;
		
	
		
		//KeyBindings to control the movements(right, left, up, down)
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed RIGHT"), "rightPressed");
		this.getActionMap().put("rightPressed", new RightPressed());
		this.getInputMap().put(KeyStroke.getKeyStroke("released RIGHT"), "rightReleased");
		this.getActionMap().put("rightReleased", new RightReleased());
		this.getInputMap().put(KeyStroke.getKeyStroke("pressed LEFT"), "leftPressed");
		this.getActionMap().put("leftPressed", new LeftPressed());
		this.getInputMap().put(KeyStroke.getKeyStroke("released LEFT"), "leftReleased");
		this.getActionMap().put("leftReleased", new LeftReleased());
        this.getInputMap().put(KeyStroke.getKeyStroke("pressed UP"), "upPressed");
        this.getActionMap().put("upPressed", new UpPressed());
        this.getInputMap().put(KeyStroke.getKeyStroke("released UP"), "upReleased");
        this.getActionMap().put("upReleased", new UpReleased());
        this.getInputMap().put(KeyStroke.getKeyStroke("pressed DOWN"), "downPressed");
        this.getActionMap().put("downPressed", new DownPressed());
        this.getInputMap().put(KeyStroke.getKeyStroke("released DOWN"), "downReleased");
        this.getActionMap().put("downReleased", new DownReleased());
        
        this.add(label);
        
        //For loop for rewards random placement.
        for(int i=0; i<images.size(); i++) {
			 x=random.nextInt(mapPixelWidth);
			 y=random.nextInt(mapPixelHeight);
			positions.add(new Point(x,y));
		}
        //enemyCor is called from enemy class, it has the same FOR LOOP with the same logic.
        enemy.enemyCor(mapPixelWidth, mapPixelHeight);
        
	}
	
	//Paint component method
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D=(Graphics2D) g;

		//Through this loop, we can put the pictures in the map.(we say for example, wherever 1 is located-
		//in the map, put grass's image in there.). The same method applies for others too.
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++) {
				if(map[i][j].equals("1")) {
					int worldX=j*grass.getWidth(null);
					int worldY=i*grass.getHeight(null);
					
					int screenX=worldX-cameraX;
					int screen=worldX-cameraX;
					
					int screenY=worldY-cameraY;
					g2D.drawImage(grass, screenX, screenY, null);
				}
				else if(map[i][j].equals("0")) {
					int worldX=j*water.getWidth(null);
					int worldY=i*water.getHeight(null);
					
					int screenX=worldX-cameraX;
					int screenY=worldY-cameraY;
					g2D.drawImage(water, screenX, screenY, null);
					
				}
				else if(map[i][j].equals("3")) {
					int worldX=j*grass.getWidth(null);
					int worldY=i*grass.getHeight(null);
					
					int screenX=worldX-cameraX;
					int screenY=worldY-cameraY;
					
					g2D.drawImage(grass, screenX, screenY, null);
					
					worldX=j*tree.getWidth(null);
				    worldY=i*tree.getHeight(null);
					
					screenX=worldX-cameraX;
					screenY=worldY-cameraY;
					
					g2D.drawImage(tree, screenX, screenY, null);
				}
				else if(map[i][j].equals("2")) {
					int worldX=j*bridge.getWidth(null);
					int worldY=i*bridge.getHeight(null);
					
					
					int screenX=worldX-cameraX;
					int screenY=worldY-cameraY;
					
					g2D.drawImage(bridge, screenX, screenY, null);	
				}	
			}
		}

		
		
		
		
		//Placing the reward in map's world. 
		for(Point p:positions) {
			g2D.drawImage(reward, p.x-cameraX, p.y-cameraY, label);
		}
        if(fillRectW>50) {
        	g2D.setColor(new Color(0, 100, 0));
        }
        else if(fillRectW<50&&fillRectW>15) {
        	g2D.setColor(new Color(255,255,204));
        }
        else if(fillRectW<15) {
        	g2D.setColor(Color.RED);
        }
        
        //drawing health bar
		g2D.fillRect(10, 10, fillRectW, 30);
		g2D.setColor(Color.WHITE);
		g2D.drawRect(10, 10, 101,31 );
		g2D.drawString(String.valueOf(fillRectW),40,30);
		
		g2D.setFont(new Font("New Time Romans",Font.BOLD,16));
		g2D.setColor(new Color(80, 250, 150));
		
		for(int t=0; t<booleans.size(); t++) {
			if(booleans.get(t)==true) {
				g2D.drawString("You can't pass through a tree or water", 10, 80);
			}
		}
		
		
		int playerScreenX=player.getPlayerX()-cameraX;
		int playerScreenY=player.getPlayerY()-cameraY;
		
		
		
		player.render(g2D,playerScreenX,playerScreenY);
		enemy.render(g2D, cameraX, cameraY);
        
		//Player 
		tilePX=player.getPlayerX()/tileSize;
		tilePY=player.getPlayerY()/tileSize;
		
	
		//repaint();
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==timer) {
			enemy.enemyMove(player.getPlayerX(),player.getPlayerY());
		}
		//Reward rectangle object
		for (int i = 0; i < images.size(); i++) {
		    rewardRec = new Rectangle(
		        positions.get(i).x,
		        positions.get(i).y,
		        images.get(i).getWidth(null),
		        images.get(i).getHeight(null)
		    );

		    if (player.getBounds().intersects(rewardRec)) {

		        boolean validTile = false;
		        int tileX = 0;
		        int tileY = 0;

		        while (!validTile) {
		            tileX = random.nextInt(map[0].length); // columns
		            tileY = random.nextInt(map.length);    // rows

		            String tile = map[tileY][tileX]; // IMPORTANT: y first, x second

		            // allow ONLY grass ("1") or bridge ("2")
		            if (!tile.equals("0") && !tile.equals("3")) {
		                validTile = true;
		            }
		        }

		        // convert tile position → pixel position
		        positions.get(i).x = tileX * tileSize;
		        positions.get(i).y = tileY * tileSize;

		        score++;
		        label.setText("Score: " + score);
		        enemy.moves.add(random.nextInt(3,9));
				enemy.boundries.add(random.nextInt(0,4));
				enemy.positions.add(new Point(cameraX,cameraY));
		    }
		}

				
				
				/*positions.get(i).x=random.nextInt(10,1600);
				positions.get(i).y=random.nextInt(10,900);
				score+=1;
				label.setText("Score: "+score);*/
				
			
		
			
		
					
		       
		        
		        //Player
		        		if(treeR==false) {
				        	if(tilePX - 1 >= 0&&map[tilePY][tilePX-1].equals("3")||tilePX - 1 >= 0&&map[tilePY][tilePX-1].equals("0")) {
								treeR=true;
							}	
				        }
						if(tilePX - 1 >= 0&&!map[tilePY][tilePX-1].equals("3")&&tilePX - 1 >= 0&&!map[tilePY][tilePX-1].equals("0")) {
							treeR=false;
						}
						
						if(treeL==false) {
					        	if(tilePX + 1 >= 0&&map[tilePY][tilePX+1].equals("3")||tilePX + 1 >= 0&&map[tilePY][tilePX+1].equals("0")) {
									treeL=true;
								}	
					        }
					    if(tilePX + 1 >= 0&&!map[tilePY][tilePX+1].equals("3")&&tilePX + 1 >= 0&&!map[tilePY][tilePX+1].equals("0")) {
							treeL=false;
							}
					    
					    if(treeU==false) {
				        	if(tilePY - 1 >= 0&&map[tilePY-1][tilePX].equals("3")||tilePY - 1 >= 0&&map[tilePY-1][tilePX].equals("0")) {
								treeU=true;
							}	
				        }
						if(tilePY - 1 >= 0&&!map[tilePY-1][tilePX].equals("3")&&tilePY - 1 >= 0&&!map[tilePY-1][tilePX].equals("0")) {
							treeU=false;
						}
						
						if(treeD==false) {
					        	if(tilePY + 1 >= 0&&map[tilePY+1][tilePX].equals("3")||tilePY + 1 >= 0&&map[tilePY+1][tilePX].equals("0")) {
									treeD=true;
								}	
					        }
						if(tilePY + 1 >= 0&&!map[tilePY+1][tilePX].equals("3")&&tilePY + 1 >= 0&&!map[tilePY+1][tilePX].equals("0")) {
							treeD=false;
							}
		       
		        
					
				booleans=new ArrayList();
				booleans.add(treeR);
				booleans.add(treeL);
				booleans.add(treeU);
				booleans.add(treeD);
					
					
		
	 //Player intersection with enemy and reward
	 for(int k=0; k<enemy.positions.size(); k++) {
		 if(player.getBounds().intersects(enemy.getBounds(k))) {
		      fillRectW-=1;
		 }		
		 
	 }		
	 
	 
		
		
		//Checking if the health bar is 0;
		if(fillRectW<=0) {
			timer.stop();
			int answer=JOptionPane.showConfirmDialog(null, "Would you like to play again?", "Game over", JOptionPane.YES_NO_OPTION);
			
			if(answer==JOptionPane.NO_OPTION) {
				timer.stop();
				System.exit(0);
			}
			else if(answer==JOptionPane.YES_OPTION) {
				timer.start();
				player.setPlayerX(500);
				player.setPlayerY(600);
				fillRectW=100;
			}
		}
		
		//Assigning the values to camera coordinates
		cameraX=player.getPlayerX()-screenWidth/2;
		cameraY=player.getPlayerY()-screenHeight/2;
		
		if(cameraX<0) {
			cameraX=0;
		}
		if(cameraY<0) {
			cameraY=0;
		}
		
		if(cameraX>mapPixelWidth-screenWidth) {
			cameraX=mapPixelWidth-screenWidth;
		}
		if(cameraY>mapPixelHeight-screenHeight) {
			cameraY=mapPixelHeight-screenHeight;
		}
		
		
		//making some rules so that the player doesn't get out of the Map's world.
		if(player.getPlayerX()<0) {
			player.setPlayerX(0);
		}
		
		if(player.getPlayerY()<0) {
			player.setPlayerY(0);
		}
		if(player.getPlayerX()>mapPixelWidth-player.playerImg.getWidth(null)) {
			player.setPlayerX(mapPixelWidth-player.playerImg.getWidth(null));
		}
		if(player.getPlayerY()>mapPixelHeight-player.playerImg.getHeight(null)) {
			player.setPlayerY(mapPixelHeight-player.playerImg.getHeight(null));
		}
		
		
		
		repaint();	
	}
	
	public boolean canMove(int futureX ,int futureY) {
		
		int tileX=futureX/tileSize;
		int tileY=futureY/tileSize;
		
		if(tileX<0 || tileX>=map[0].length-1 || tileY<0 || tileY>=map.length-1) {
			return false;
		}
		
		String tile=map[tileY][tileX];
		    return tile.equals("1") || tile.equals("2");
	}
	
	
	
	
	
	
	//KeyBindings' abstract methods for movements(right, left, up, down)
	public class RightPressed extends AbstractAction{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int futureX=player.getPlayerX()+tileSize;
			
			if(canMove(futureX,player.getPlayerY())) {
				player.setPlayerX(futureX);
			}
			player.setDirection(true);	
			player.setRightLeft(true);
			repaint();
			
		}
	}
	public class RightReleased extends AbstractAction{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			player.setDirection(false);	
			repaint();
			
		}
	}
	
	
	public class LeftPressed extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			int futureX=player.getPlayerX()-tileSize;
			
			if(canMove(futureX,player.getPlayerY())) {
				player.setPlayerX(futureX);
			}
			player.setDirection(true);
			player.setRightLeft(false);
			repaint();
		}	
	}
	public class LeftReleased extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			player.setDirection(false);
			repaint();
		}	
	}
	
	
	public class UpPressed extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
            int futureY=player.getPlayerY()-tileSize;
			
			if(canMove(player.getPlayerX(),futureY)){
				player.setPlayerY(futureY);
			}
			player.setDirection(true);
			repaint();
		}	
	}
	public class UpReleased extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			player.setDirection(false);
			repaint();
		}	
	}

	
	public class DownPressed extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int futureY=player.getPlayerY()+tileSize;
			
			if(canMove(player.getPlayerX(),futureY)){
				player.setPlayerY(futureY);
			}
			player.setDirection(true);
			repaint();
		}
	}
	public class DownReleased extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			player.setDirection(false);
			repaint();
		}
	}
}
