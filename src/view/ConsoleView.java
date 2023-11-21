package view;

import model.Map;
import model.Player;

/**
 * The ConsoleView class provides a console-based view of the game.
 */
public class ConsoleView implements Observer {

    private Map map;
    private Player player;
    


    /**
     * Creates a new ConsoleView instance that associates a Map instance and a Player instance.
     * 
     * @param map The Map instance to associate with this ConsoleView.
     * @param player The Player instance to associate with this ConsoleView.
     */
    public ConsoleView(Map map, Player player) {
        this.map = map;
        this.player = player;
        
    }

    
    
    /**
     * Updates the console view of the game with the new position of the player and the
     * state of the game board, given a checkbox status.
     * 
     * @param playergridX The x-coordinate of the player on the game grid.
     * @param playergridY The y-coordinate of the player on the game grid.
     * @param checkBox A boolean value indicating whether a checkbox is selected.
     */
    private void update(int playergridX, int playergridY, boolean checkBox) {
    		map.setCurrentConsoleNum(0);
    		if(checkBox == true) {
		        System.out.println("---- ConsoleView ----");
		        for (int row = 0; row < map.getRowLevel(); row++) {
		            for (int col = 0; col < map.getColLevel(); col++) {
		                if (col == player.getGridX() && row == player.getGridY()) {
		                    System.out.print("X ");
		                } else {
		                    System.out.print(map.getMapTileNum(col, row) + " ");
		                }
		                if(map.getMapTileNum(col,row) == 2) {map.setCurrentConsoleNum(map.getCurrentConsoleNum()+1);}
		            }
		            System.out.println();
		        }
		        System.out.println("------------------------");
		        System.out.println("0 = Ground, 1 = Wall, 2 = Marked Crate, 3 = Crate, 4 = Marked Ground, X = player");
		        System.out.println("Player position: (" + player.getGridX() + ", " + player.getGridY() + ")");
		        
	    	}
    }



	@Override
	public void obsUpdate(int a, int b, boolean c) {
		update(a, b, c);
		
		
	}
    
	 
	
}