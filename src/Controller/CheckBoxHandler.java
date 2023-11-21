package Controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


/**
 * The CheckBoxHandler class implements the ItemListener interface and
 * handles changes in state of a checkbox item.
 */
public class CheckBoxHandler implements ItemListener {

    private boolean checkboxChecked;

    
    /**
     * Creates a new CheckBoxHandler instance with the checkboxChecked flag initially set to true.
     */
    public CheckBoxHandler() {
        checkboxChecked = true;
    }

    /**
     * Called when a checkbox item's state has been changed.
     * Updates the checkboxChecked flag based on the new state of the checkbox.
     *
     * @param e The event to be processed.
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED)
            checkboxChecked = true;
        else
            checkboxChecked = false;
    }

    
    /**
     * Returns the current state of the checkbox item.
     *
     * @return The current state of the checkbox item.
     */
    public boolean isCheckboxChecked() {
        return checkboxChecked;
    }
}