package Controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import view.GamePanel;


/**
 * The KeyHandler class is an implementation of the KeyListener interface.
 * This class is designed to respond to the keyboard events including key press and release.
 */
public class KeyHandler implements KeyListener {

	private static boolean upPressed, downPressed, leftPressed, rightPressed;
	private GamePanel gamePanel;
	private boolean keyProcessed;

	
	 /**
     * Creates a new KeyHandler instance.
     *
     * @param gamePanel a GamePanel instance that this KeyListener will modify when a keyboard event is detected.
     */
	public KeyHandler(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		keyProcessed = false;

	}

	/**
     * Called when a key is typed.
     * @param e The event to be processed.
     */
	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
     * Called when a key is pressed.
     * Detects the event of arrow keys or WASD keys being pressed and changes the game state accordingly.
     * @param e The event to be processed.
     */
	@Override
	public void keyPressed(KeyEvent e) {
		
		if (keyProcessed == true) 
			return;
		int pressed = e.getKeyCode();
		
		if(pressed == KeyEvent.VK_W || pressed == KeyEvent.VK_UP) {
			upPressed = true;
		}

		if(pressed == KeyEvent.VK_S || pressed == KeyEvent.VK_DOWN) {
			downPressed = true;
		}

		if(pressed == KeyEvent.VK_D || pressed == KeyEvent.VK_RIGHT) {
			rightPressed = true;
		}

		if(pressed == KeyEvent.VK_A || pressed == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}
		
		keyProcessed = true;
		gamePanel.update();
		
	}


	/**
     * Called when a key is released.
     * Detects the event of arrow keys or WASD keys being released and changes the game state accordingly.
     * If the game level is complete after releasing a key, calls the levelComplete method.
     * @param e The event to be processed.
     */
    @Override
	public void keyReleased(KeyEvent e) {
		int pressed = e.getKeyCode();
		
		if(pressed == KeyEvent.VK_W || pressed == KeyEvent.VK_UP) {
			upPressed = false;
		}

		if(pressed == KeyEvent.VK_S || pressed == KeyEvent.VK_DOWN) {
			downPressed = false;
		}

		if(pressed == KeyEvent.VK_D || pressed == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}

		if(pressed == KeyEvent.VK_A || pressed == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}
		
		 keyProcessed = false;
		 if(gamePanel.getMap().checkIfWin()) {
			 gamePanel.levelComplete(gamePanel);
			 gamePanel.update();
		 }
	}
    
	 /**
     * Returns a new instance of KeyAdapter that delegates calls to the appropriate methods of this class.
     * @return a new instance of KeyAdapter.
     */
	public KeyListener getDialogKeyListener() {
	    return new KeyAdapter() {
	        public void keyPressed(KeyEvent e) {
	            KeyHandler.this.keyPressed(e);
	        }

	        public void keyReleased(KeyEvent e) {
	            KeyHandler.this.keyReleased(e);
	        }

	        public void keyTyped(KeyEvent e) {
	            KeyHandler.this.keyTyped(e);
	        }
	    };
	}
	
	
	public static boolean getUpPressed() {
		return upPressed;
	}
	public static boolean getDownPressed() {
		return downPressed;
	}
	public static boolean getLeftPressed() {
		return leftPressed;
	}
	public static boolean getRightPressed() {
		return rightPressed;
	}

}


