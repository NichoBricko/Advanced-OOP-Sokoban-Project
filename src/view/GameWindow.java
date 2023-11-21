package view;

import javax.swing.JFrame;

/**
 * The GameWindow class represents the window for the game. It contains the GamePanel.
 */
public class GameWindow {
	
	private JFrame window;
	
	
	/**
	 * Creates a new GameWindow instance that contains the GamePanel.
	 * 
	 * @param gamePanel The GamePanel instance to display in this GameWindow.
	 */
	public GameWindow(GamePanel gamePanel) {	
		window = new JFrame();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setTitle("SOKOBAN_V6");
		//GamePanel gamePanel = new GamePanel(); //This is my "GamePanel" and should be named such in later stages.
		window.add(gamePanel);
		window.setLocationRelativeTo(null);
		
		window.pack();
		window.setVisible(true);// this shit should be last otherwise it bugs out, do not know why.
	}
	
	/**
	 * Toggles the visibility of the GameWindow. If the GameWindow is currently visible, it will be hidden,
	 * and vice versa.
	 */
	public void toggleVisibility() {
		window.setVisible(!window.isVisible());
	}
	
	/**
	 * Checks whether the GameWindow is currently visible.
	 * 
	 * @return A boolean value indicating whether the GameWindow is currently visible.
	 */
	public boolean isWindowVisible() {
		return window.isVisible(); 
	}
}

