package model;


//THIS IS MAP STUFF


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.imageio.ImageIO;

import view.GamePanel;



/**
 * The Map class represents the game world or map.
 * It is responsible for loading map layouts from files, checking win conditions, and managing collisions.
 */
public class Map {

	private GamePanel gp;
	private Tile[] tile;
	private int mapTileNum[][];
	
	private int winningNum;
	private int currentNum;
	private int currentConsoleNum;
	private boolean won = false;
	private int level = 1;
	private int levelamount = 4;
	
	private String levelFile ="/maps/Map1.txt";
	
	private int rowLevel;
	private int colLevel;
	public String[] inArray;
	
	private Scanner scanIn;
	private String inputLine;

	


	/**
     * Constructor for the Map object.
     * 
     * @param gp An instance of GamePanel. It is a GUI component.
     */
	public Map (GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[10];
		mapTileNum = new int [gp.getMaxScreenCol()] [gp.getMaxScreenRow()];
		getLevelColAndRow(this.levelFile);
		getTileImage();
		mapLoad(this.levelFile);
	}
	
	
	
	
	 /**
     * Changes the dimensions of the mapTileNum array based on the screen size.
     * 
     * @param maxScreenCol Maximum columns based on the screen size.
     * @param maxScreenRow Maximum rows based on the screen size.
     */
	public void changeArrayLenght(int maxScreenCol, int maxScreenRow) {
		int[][] current = new int [maxScreenCol] [maxScreenRow];
		this.mapTileNum = current;
	}
	
	
	/**
     * Changes the current level of the game.
     * 
     * @param level The level number to be set.
     */
	public void changeLevel(int level) {
		if(level > levelamount) {level = 1;};
		this.level = level;
		setLevelLayout(level);
		getLevelColAndRow(this.levelFile);
		gp.levelChange(this.colLevel, this.rowLevel); 
		
		changeArrayLenght(this.colLevel, this.rowLevel);
		mapLoad(levelFile);
		this.won = false;
	}

	
	
	 /**
     * Sets the layout file for the current level.
     * 
     * @param currentLevel The level number for which the layout needs to be set.
     */
	public void setLevelLayout(int currentLevel) {
		this.levelFile = "/maps/Map"+ currentLevel + ".txt";
		
	}
	
	  /**
     * Generates a list of level names.
     * 
     * @param x The number of levels.
     * @return Array of level names.
     */
	public String[] amountsOfLevels(int x) {
		String[] levels = new String[x];
		
		for(int i = 0; i < x; i++) {
			levels[i] = "Level " +(i+1);
		}
		return levels;
	}
	
	
	 /**
     * Retrieves the number of rows and columns in the map of the current level.
     * 
     * @param levelFile The file path of the level.
     */
	public void getLevelColAndRow(String levelFile) {
		this.rowLevel = 0;
		this.colLevel = 0;
		String[] inArray;
		try {
			InputStream is = getClass().getResourceAsStream(levelFile);
			scanIn = new Scanner(new InputStreamReader(is));
			while(scanIn.hasNextLine()) {
				inputLine = scanIn.nextLine();
				inArray = inputLine.split(" ");
				this.rowLevel++;
				if (inArray.length > this.colLevel) {
					this.colLevel = inArray.length;
            
					}
				}
				
			scanIn.close();
		}
		
		catch(Exception e) {
			System.out.println("File not found: " + levelFile);
	        e.printStackTrace();
		}
	}
	
	/**
     * Checks if the winning condition of the game is met.
     * 
     * @return True if the game is won, else false.
     */
	public boolean checkIfWin() {
		if(currentNum == winningNum || currentConsoleNum == winningNum) {
			won = true;
			return true;
		}
		return false;
	}
	
	
	 /**
     * Gets the layout file of the current level.
     * 
     * @return The file path of the current level.
     */
	public String getCurrentLevel() {
		return this.levelFile;
	}
	
	
	/**
     * Loads tile images and sets collision property for respective tiles.
     */
	public void getTileImage() {
		
		try {
		
			tile[0] = new Tile();
			tile[0].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/blank.png")));
			
			tile[1] = new Tile();
			tile[1].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png")));
			
			tile[2] = new Tile();
			tile[2].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/cratemarked.png")));
			
			tile[3] = new Tile();
			tile[3].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/crate.png")));
			
			tile[4] = new Tile();
			tile[4].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/blankmarked.png")));
			
			//look at this <-
			tile[5] = new Tile();
			tile[5].setImage(ImageIO.read(getClass().getResourceAsStream("/player/player.png")));
			
		
			//For collision
			tile[1].setCollision(true); // wall
			tile[3].setCollision(true); // crate
			tile[2].setCollision(true); // cratemarked
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	 /**
     * Checks if a tile is collidable.
     * 
     * @param col The column number of the tile.
     * @param row The row number of the tile.
     * @return True if the tile is collidable, else false.
     */
	public boolean isCollidable(int col, int row) {
	    int tileNum = mapTileNum[col][row];
	    return tile[tileNum].getCollision();
	}
	
	
	
	/**
     * Loads a map layout from the current level file and populates mapTileNum with tile numbers.
     * 
     * @param currentLevel The file path of the current level.
     */
	public void mapLoad(String currentLevel) {
		
		try {
	
			InputStream is = getClass().getResourceAsStream(currentLevel);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			int col = 0 ;
			int row = 0;
			winningNum = 0;
			currentNum = 0;
			while(col < gp.getMaxScreenCol() && row < gp.getMaxScreenRow()) {
				String line = br.readLine();    // reads textfiles.
				while(col < gp.getMaxScreenCol()) {
					String numbers [] = line.split(" "); //Splits this string around matches of the given regular expression
					
					int num = Integer.parseInt(numbers[col]);
					if (num == 2 || num == 4 ) {winningNum++;}
					if (num == 5) {
						gp.getPlayer().setStartValue(col, row);
						num = 0;}
					mapTileNum[col][row] = num;
					col++;
				}
				if (col == gp.getMaxScreenCol()) {
					col = 0;
					row ++;
				}
			}
			br.close();
		}catch(Exception e) {

		}
	}
	/**
	 * 
	 * @param Get and set functions
	 * 
	 */
	public void setMapTileNum(int col, int row, int value) {
		this.mapTileNum[col][row] = value;
	}
	
	public int getMapTileNum(int col, int row) {
		return this.mapTileNum[col][row];
	}
	
	public int getRowLevel() {
		return this.rowLevel;
	}
	
	public int getColLevel() {
		return this.colLevel;
	}
	
	public int getCurrentConsoleNum() {
		return this.currentConsoleNum;
	}
	
	public void setCurrentConsoleNum(int num) {
		this.currentConsoleNum = num;
	}
	
	public int getLevelAmount() {
		return this.levelamount;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public int getCurrentNum() {
		return this.currentNum;
	}
	public void setCurrentNum(int num) {
		this.currentNum = num;
	}
	public Tile getTile(int num) {
		return this.tile[num];
	}

}