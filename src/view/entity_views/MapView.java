package view.entity_views;

import java.awt.Graphics2D;
import model.*;
import view.GamePanel;

/**
 * The MapView class is responsible for rendering the Map on the GamePanel.
 */
public class MapView {

	private Map map;
    private GamePanel gamePanel;
    private TileView tileView;

    
    /**
     * Constructor for MapView class.
     *
     * @param map        The Map to be rendered.
     * @param gamePanel  The GamePanel where the Map will be drawn.
     */
    public MapView(Map map, GamePanel gamePanel) {
        this.map = map;
        this.gamePanel = gamePanel;
        this.tileView = new TileView();
        this.map.mapLoad(this.map.getCurrentLevel());
        this.gamePanel.update();
        this.gamePanel.repaint();
    }

    
    /**
     * The draw method is responsible for drawing the map on the GamePanel.
     *
     * @param g2 A Graphics2D object used for drawing.
     */
    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        map.setCurrentNum(0);
        while(col < gamePanel.getMaxScreenCol() && row < gamePanel.getMaxScreenRow()) {
            int tileNum = map.getMapTileNum(col,row);
            
            Tile tile = map.getTile(tileNum);
            if(tileNum == 2) {map.setCurrentNum(map.getCurrentNum()+1);}
            if (map.getMapTileNum(col,row) == 5) {map.setMapTileNum(col,row, 0);}
            tileView.draw(g2, tile, x, y, gamePanel.getTileSize()); // This part is the snippet, review it for later (renders the tiles)
            col++;
            x += gamePanel.getTileSize();

            if(col == gamePanel.getMaxScreenCol()) {
                col = 0;
                x = 0;
                row++;
                y += gamePanel.getTileSize();
            }
        }
        map.checkIfWin();
    }
}