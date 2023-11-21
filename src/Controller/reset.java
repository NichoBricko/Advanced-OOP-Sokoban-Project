package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GamePanel;


/**
 * The reset class is an implementation of the ActionListener interface that is responsible for resetting the game state.
 * This class is designed to be attached to a GUI element (like a button), so when that element is clicked, the game will reset.
 */
public class reset implements ActionListener{

	 /**
     * A GamePanel instance that this ActionListener modifies when an action is performed.
     */
	private GamePanel gp;
	
	 /**
     * Creates a new reset instance.
     *
     * @param gp a GamePanel instance that this ActionListener will modify when an action is performed.
     */
	public reset(GamePanel gp){
		this.gp = gp;
	}
	

	 /**
     * This method is called when the listener has detected an action.
     * It reloads the current game map, updates the game panel, repaints it and prints "RESET" to the console.
     *
     * @param e an event which indicates that a component-defined action occurred.
     */
	public void actionPerformed(ActionEvent e) {
		gp.getMap().mapLoad(gp.getMap().getCurrentLevel());
		gp.update();
		gp.repaint();
		System.out.println("RESET");
	}

}
