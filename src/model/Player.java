package model;

import java.io.IOException;
import view.*;
import javax.imageio.ImageIO;
import Controller.KeyHandler;

/**
 * Player is an extension of the GameObject class.
 * It is responsible for handling player's position, movement and interaction with the game world.
 */
public class Player extends GameObject{

	private GamePanel gp;
	private KeyHandler keyHandler;
	
	
	// Grid-based position
    private int gridX;
    private int gridY;
	
    /**
     * Constructor for the Player object.
     * 
     * @param gp An instance of GamePanel. It is a GUI component.
     * @param keyHandler An instance of KeyHandler which handles key events.
     */
	public Player(GamePanel gp, KeyHandler keyHandler) {
		this.gp = gp;
		this.keyHandler = keyHandler;
		
		
		SetValues();
		getPlayerImage();
	}
	
	/**
     * Reads an image file and assigns it to the player object.
     * The image represents the player on the game screen.
     */
	public void getPlayerImage() {
		try {
			
			setPlayer(ImageIO.read(getClass().getResourceAsStream("/player/player.png")));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
     * Initializes or resets the values of player's attributes.
     * Set initial positions, speed of player and pixel position calculated from the grid position.
     */
	public void SetValues() {

        //gridX = 2; // Set grid X position
        //gridY = 2; // Set grid Y position
        setX(gridX * gp.getTileSize()); // Calculate pixel position
        setY(gridY * gp.getTileSize());
        setSpeed(1);

    }
	
	 /**
     * Sets the start value of the player's position on the grid.
     * 
     * @param x x-coordinate of the player's starting position.
     * @param y y-coordinate of the player's starting position.
     */
	public void setStartValue(int x, int y) {
		this.gridX = x;
		this.gridY = y;
	}
	

    /**
     * Updates the player's position based on the direction retrieved from getDirection() method.
     */
	
	public void update() {
	    move(getDirection());
	}
	
	
	
	/**
     * Determines the direction of player's movement based on the key pressed.
     * 
     * @return String representing the direction of player's movement.
     */
	private String getDirection() {
	    if (KeyHandler.getUpPressed()) {
	        return "UP";
	    } else if (KeyHandler.getDownPressed()) {
	        return "DOWN";
	    } else if (KeyHandler.getLeftPressed()) {
	        return "LEFT";
	    } else if (KeyHandler.getRightPressed()) {
	        return "RIGHT";
	    }
	    return "";
	}
	
	
	
	
	/**
     * Updates the player's position based on the provided direction.
     * 
     * @param direction String representing the direction of player's movement.
     */
	public void update(String direction) {
	    move(direction);
	}

	
	 /**
     * Moves the player in the given direction.
     * Also handles player's interaction with game objects like crates and collidable objects.
     * 
     * @param direction String representing the direction of player's movement.
     */
	public void move(String direction) {
	    
	    int nextGridX = gridX;
	    int nextGridY = gridY;

	    // Determine the next position based on our key press
	    if ("UP".equals(direction)) {
	        nextGridY -= getSpeed();
	    } else if ("DOWN".equals(direction)) {
	        nextGridY += getSpeed();
	    } else if ("LEFT".equals(direction)) {
	        nextGridX -= getSpeed();
	    } else if ("RIGHT".equals(direction)) {
	        nextGridX += getSpeed();
	    }
	    
	    
	    

	    // Check for collision at the next grid position, no collision, move player to the next grid position
	    if (!gp.getMap().isCollidable(nextGridX, nextGridY)) {
	        
	        gridX = nextGridX;
	        gridY = nextGridY;
	    } else {
	        // Check if the tile at the next position is a crate or markedcrate
	        int crateTileNum = gp.getMap().getMapTileNum(nextGridX,nextGridY);
	        if (crateTileNum == 3 || crateTileNum == 2) {
	            // Calculate the next position for the crate
	            int nextCrateGridX = nextGridX * 2 - gridX;
	            int nextCrateGridY = nextGridY * 2 - gridY;

	            // Check if the next position for the crate is not collidable
	            if (!gp.getMap().isCollidable(nextCrateGridX, nextCrateGridY)) {
	                // check if next crate/position is a blankmarked
	                int nextCrateTileNum = gp.getMap().getMapTileNum(nextCrateGridX, nextCrateGridY);
	                if (nextCrateTileNum == 4) {
	                    // Change crate to cratemarked
	                    gp.getMap().setMapTileNum(nextCrateGridX, nextCrateGridY, 2);
	                } else {
	                    // if you move crate or the marked crate to the position as a crate again
	                    gp.getMap().setMapTileNum(nextCrateGridX,nextCrateGridY, 3);
	                }

	                // if we moved markedcrate pos will be a blankmarked
	                if (crateTileNum == 2) {
	                    gp.getMap().setMapTileNum(nextGridX, nextGridY, 4);
	                } else {
	                    // Remove the crate from its current posi
	                    gp.getMap().setMapTileNum(nextGridX,nextGridY, 0);
	                }

	                // Move the player to the next position
	                gridX = nextGridX;
	                gridY = nextGridY;
	            }
	        }
	    }

	    // Update the position of the player based on the grid position
	    setX (gridX * gp.getTileSize());
	    setY (gridY * gp.getTileSize());
	    gp.repaint();
	    
	    
	}
    
	public void setGridX(int num) {
		this.gridX = num;
	}
	
	public int getGridX() {
		return this.gridX;
	}
	
	public void setGridY(int num) {
		this.gridY = num;
	}
	
	public int getGridY() {
		return this.gridY;
	}
	
}

