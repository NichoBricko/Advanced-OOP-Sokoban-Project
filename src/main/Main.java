package main;

import view.*;

public class Main {

	private GameWindow gameWindow;
	private GamePanel gamePanel;

	
	public static void main(String[] args) {
		
		Main main = new Main();
		main.gamePanel = new GamePanel(main);
		main.gameWindow = new GameWindow(main.gamePanel);
		main.gamePanel.setGameWindow(main.gameWindow);
	    


	}
	
	
	
	public void toggleGameWindow() {
		gameWindow.toggleVisibility();
	}
	
	public GameWindow getGameWindow() {
		return gameWindow;
	}

}