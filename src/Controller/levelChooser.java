package Controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import view.GamePanel;


/**
 * The levelChooser class is an implementation of the ItemListener interface.
 * This class is designed to listen for changes in the level choice (typically from a ComboBox or similar GUI element)
 * and update the game state accordingly.
 */
public class levelChooser implements ItemListener {

	private GamePanel gp;
	
	 /**
     * Creates a new levelChooser instance.
     *
     * @param gp a GamePanel instance that this ItemListener will modify when an item state is changed.
     */
	public levelChooser(GamePanel gp){
		this.gp = gp;
	}
	
	
	
	
    /**
     * This method is called when the listener has detected a state change in an item.
     * If a new item is selected, the game level is changed to the corresponding level,
     * the game panel is updated, and it is repainted.
     *
     * @param e an event which indicates that a state change occurred on a component.
     */
    @Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED) {
				gp.getMap().changeLevel(gp.getLevelChooser().getSelectedIndex()+1);
				gp.update();
				gp.repaint();
		}
		
	}
	

}
