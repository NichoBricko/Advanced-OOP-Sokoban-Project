package tests;


import model.*;
import view.*;
import main.Main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MapTest {
    @Test
    public void testIsCollidable() {
    	Main main = new Main();

        GamePanel gamePanel = new GamePanel(main); 
        Map map = new Map(gamePanel);

        // Test that the map's collidable tiles behave as expected
        // (replace with the grid coordinates of a wall or crate in our map)
        assertTrue(map.isCollidable(4, 2));  // Wall or crate

        // (replace  with the grid coordinates of a blank space in our map)
        assertFalse(map.isCollidable(3, 2));  // Blank space
    }
}