package view.entity_views;

import java.awt.Graphics2D;
import model.Player;
import view.GamePanel;

/**
 * The PlayerView class is responsible for rendering the Player on the GamePanel.
 */
public class PlayerView {

    private Player player;
    private GamePanel gamePanel;

    
    /**
     * Constructor for PlayerView class.
     *
     * @param player     The Player to be rendered.
     * @param gamePanel  The GamePanel where the Player will be drawn.
     */
    public PlayerView(Player player, GamePanel gamePanel) {
        this.player = player;
        this.gamePanel = gamePanel;
    }

    
    /**
     * The draw method is responsible for drawing the player on the GamePanel.
     *
     * @param g2 A Graphics2D object used for drawing.
     */
    public void draw(Graphics2D g2) {
        g2.drawImage(player.getPlayer(), player.getX(), player.getY(), gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }
}