package view.entity_views;

import java.awt.Graphics2D;

import model.Tile;




/**
 * The TileView class is responsible for rendering individual Tiles.
 */
public class TileView {

	
	
	 /**
     * The draw method is responsible for drawing the tile on the screen.
     *
     * @param g2        A Graphics2D object used for drawing.
     * @param tile      The Tile object to be drawn.
     * @param x         The x coordinate where the tile will be drawn.
     * @param y         The y coordinate where the tile will be drawn.
     * @param tileSize  The size of the tile.
     */
	public void draw(Graphics2D g2, Tile tile, int x, int y, int tileSize) {
	    g2.drawImage(tile.getImage(), x, y, tileSize, tileSize, null);
	}
    }
