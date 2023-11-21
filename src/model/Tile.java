package model;

import java.awt.image.BufferedImage;

/**
 * The Tile class represents a single tile in the game map.
 * It contains an image that is displayed for the tile and a collision flag to indicate whether the tile is passable or not.
 */
public class Tile {

	 /**
     * This flag indicates whether the tile is collidable (true) or not (false).
     * Collidable tiles cannot be passed through in the game.
     * By default, a tile is not collidable.
     */
	private boolean collision = false;
	

    /**
     * This is the image that is displayed for the tile on the game map.
     */
	private BufferedImage image;
	
	public void setCollision(boolean condition) {
		this.collision = condition;
	}
	public boolean getCollision() {
		return this.collision;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	public BufferedImage getImage() {
		return this.image;
	}
}
