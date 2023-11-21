package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import model.*;
import view.*;
import Controller.*;
import main.Main;

@RunWith(JUnit4.class)
public class PlayerTest {
    private GamePanel gamePanel;
    private KeyHandler keyHandler;
    private Player player;

    @Before
    public void setup() {
    	Main main = new Main();

        GamePanel gamePanel = new GamePanel(main); 
        Map map = new Map(gamePanel);
        player = new Player(gamePanel, keyHandler);
    }

    @Test
    public void testPlayerInitialization() {
        // Verify that the player object is initialized correctly
        Assert.assertEquals(0, player.getGridX());
        Assert.assertEquals(0, player.getGridY());
        Assert.assertEquals(0, player.getX());
        Assert.assertEquals(0, player.getY());
        Assert.assertEquals(1, player.getSpeed());
    }

    @Test
    public void testSetStartValue() {
        // Test setting the start value of the player's position
        player.setStartValue(3, 2);
        Assert.assertEquals(3, player.getGridX());
        Assert.assertEquals(2, player.getGridY());
    }

    @Test
    public void testMovePlayer() {
        // Set up initial position
        player.setStartValue(3, 2);

        // Test moving the player up
        player.move("UP");
        Assert.assertEquals(3, player.getGridX());
        Assert.assertEquals(2, player.getGridY());

        // Test moving the player down
        player.move("DOWN");
        Assert.assertEquals(3, player.getGridX());
        Assert.assertEquals(2, player.getGridY());

        // Test moving the player left
        player.move("LEFT");
        Assert.assertEquals(2, player.getGridX());
        Assert.assertEquals(2, player.getGridY());

        // Test moving the player right
        player.move("RIGHT");
        Assert.assertEquals(3, player.getGridX());
        Assert.assertEquals(2, player.getGridY());
    }
}
