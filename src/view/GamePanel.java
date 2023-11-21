package view;


import java.awt.*;

import javax.swing.*;

import model.Player;
import model.Map;
import Controller.*;
import main.*;

import view.entity_views.MapView;
import view.entity_views.PlayerView;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * The GamePanel class represents the game's main panel and contains all the 
 * other necessary components such as Player, Map, GameWindow, and various controls.
 */
public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	//screen settings.
	//We are using "final int" because we do not want these values to change at all, and we want he map or tiles to always stay the same
	final int orginalTileSize = 16; // 16x16 tile
	final int scale = 3;
	private int tileSize = orginalTileSize * scale;//48x48 tiles
	private int maxScreenCol = 16;
	private int maxScreenRow = 12;
	private int screenWidth = tileSize * maxScreenCol;//768 pixel
	private int screenHeight = tileSize * maxScreenRow;//576 pixel

	private List<Observer> arrayList;

	//Creating
	private ConsoleView consoleView;
	private MapView mapView;
    private PlayerView playerView; 
    
    private CheckBoxHandler checkboxHandler;
    private JCheckBox checkBoxConsoleView;

    private ButtonControlHandler buttonControlHandler;
    
    private JButton optionsButton;
    
    private reset reset;
    private JPanel sideMenu;
    private JButton resetButton;
    private JComboBox<String> levelChooser;
    private levelChooser levelChooserHandler;

    private JDialog dialog;
    private JButton toggleButton;
    
    private GameWindow gameWindow;
    
    private String[] levels;
    
	private Map tileM = new Map(this);
	KeyHandler keyHandler; //initiate keyHandler. Can also remove the keyhandler from "GamePanel" and set it here. TIP FOR LATER
	KeyHandler keyH = new KeyHandler(this);
	private Player player = new Player(this,keyH);
	
	private Main main;
	
    /**
     * Constructs a new GamePanel with a given instance of Main.
     *
     * @param main An instance of Main to be used within this GamePanel.
     */
	//Initialize
	public GamePanel(Main main) {
		
		this.main = main;
		this.gameWindow = main.getGameWindow();
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(new Color(222, 214, 173));
		this.setDoubleBuffered(true);
		sideMenu = new JPanel(new GridLayout(1,0, 0, 10));
		
		//RESET BUTTTON
		this.reset = new reset(this);
		resetButton = new JButton();
		resetButton.setText("RESET");
		resetButton.addActionListener(reset);
		resetButton.setFocusable(false);
		sideMenu.add(resetButton);
		
		//CHECKBOX
		this.checkboxHandler = new CheckBoxHandler();
		checkBoxConsoleView = new JCheckBox("Console View", true);
		checkBoxConsoleView.setFocusable(false);
		checkBoxConsoleView.addItemListener(checkboxHandler);
		sideMenu.add(checkBoxConsoleView);
		
		//DROP DOWN MENU LEVELCHOOSER
		levelChooserHandler = new levelChooser(this);
		levelChooser = new JComboBox<>(tileM.amountsOfLevels(tileM.getLevelAmount()));
		levelChooser.setFocusable(false);
		levelChooser.addItemListener(levelChooserHandler);
		sideMenu.add(levelChooser);
		
		//OPTION BUTTON FOR GUI TOGGLE
		optionsButton = new JButton("Options");
		optionsButton.setFocusable(false);
		optionsButton.addActionListener(e -> showOptionsDialog());
		sideMenu.add(optionsButton);
		
		buttonControlHandler = new ButtonControlHandler(player, this);

		JFrame buttonFrame = createButtonFrame(buttonControlHandler);

		JCheckBox buttonControlCheckbox = new JCheckBox("Button Control", false);
		buttonControlCheckbox.addItemListener(new ItemListener() {
		    public void itemStateChanged(ItemEvent e) {
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		            buttonFrame.setVisible(true);
		        } else {
		            buttonFrame.setVisible(false);
		        }
		        SwingUtilities.invokeLater(() -> GamePanel.this.requestFocusInWindow());
		        //the requestFocusInWindow method is needed to retrieve key inputs once the window is closed
		    }
		});

		sideMenu.add(buttonControlCheckbox);
		this.add(sideMenu, BorderLayout.EAST);
		
		this.add(sideMenu, BorderLayout.EAST);
        
		keyHandler = new KeyHandler(this);
		this.addKeyListener(keyHandler);// So we can recongize the key inputs from gamepanel
		this.setFocusable(true); // gamepanel can be focused to receive key inputs
		
		this.arrayList = new ArrayList<Observer>();
		consoleView = new ConsoleView(tileM, this.player);
		arrayList.add(consoleView);
		
		mapView = new MapView(tileM, this);
        playerView = new PlayerView(player, this);
	}
	
	/**
     * Gets the KeyHandler instance used in this GamePanel.
     *
     * @return The KeyHandler instance used in this GamePanel.
     */
	public KeyHandler getKeyHandler() {
	    return this.keyH;
	}
	
	/**
     * Displays the options dialog.
     */
	private void showOptionsDialog() {
	    JFrame frame = new JFrame();
	    dialog = new JDialog(frame , "Options", false);
	    dialog.addKeyListener(this.getKeyHandler().getDialogKeyListener());
	    dialog.setLayout(new FlowLayout());
	    dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	    
	    dialog.addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {
	            if (gameWindow.isWindowVisible()) { //this prevents the game from running in the background if dialog is closed
	                dialog.dispose();  //only closes upon 'x' if gameWindow is visible.
	            }
	        }
	    });
	    
	    toggleButton = new JButton("Toggle Visibility");
	    toggleButton.setFocusable(false);
	    toggleButton.addActionListener(e -> toggleGameWindowVisibility());
	    dialog.add(toggleButton);
	    dialog.setSize(300,200);
	    dialog.setVisible(true);
	}
	
	public void notifyObs() {
		for(Observer obs : arrayList) {
			obs.obsUpdate(player.getGridX(), player.getGridY(), getCheckBox());
		}
	}
	
	/**
     * Creates a JFrame with buttons for player control.
     *
     * @param buttonControlHandler The handler for button controls.
     * @return The created JFrame.
     */
	private JFrame createButtonFrame(ButtonControlHandler buttonControlHandler) {
	    JFrame buttonFrame = new JFrame("Controls");
	    buttonFrame.setSize(250, 200);
	    buttonFrame.setLayout(new GridBagLayout());

	    GridBagConstraints gbc = new GridBagConstraints();

	    JButton upButton = buttonControlHandler.createButton("UP");
	    JButton downButton = buttonControlHandler.createButton("DOWN");
	    JButton leftButton = buttonControlHandler.createButton("LEFT");
	    JButton rightButton = buttonControlHandler.createButton("RIGHT");

	    gbc.gridx = 1;
	    gbc.gridy = 0;
	    buttonFrame.add(upButton, gbc);

	    gbc.gridx = 1;
	    gbc.gridy = 2;
	    buttonFrame.add(downButton, gbc);

	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    buttonFrame.add(leftButton, gbc);

	    gbc.gridx = 2;
	    gbc.gridy = 1;
	    buttonFrame.add(rightButton, gbc);

	    buttonFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

	    return buttonFrame;
	}	
	
	/**
     * Sets the GameWindow to be used by this GamePanel.
     *
     * @param gameWindow The GameWindow to be used.
     */
	public void setGameWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }	
	
	/**
     * Toggles the visibility of the GameWindow.
     */
	public void toggleGameWindowVisibility() {
        main.toggleGameWindow(); 
        boolean isGameWindowVisible = gameWindow.isWindowVisible();
        if(isGameWindowVisible) {
            dialog.setVisible(false);
        }
    }

    /**
     * Updates the screen dimensions based on the provided maxScreenCol and maxScreenRow parameters.
     *
     * @param maxScreenCol The maximum number of columns to be displayed on the screen.
     * @param maxScreenRow The maximum number of rows to be displayed on the screen.
     */

	public void levelChange(int maxScreenCol, int maxScreenRow) {
		this.maxScreenCol = maxScreenCol;
		this.maxScreenRow = maxScreenRow;
		this.tileSize = this.screenWidth/maxScreenCol;
		
		//this.screenWidth = tileSize * maxScreenCol;//768 pixel
		this.screenHeight = tileSize * maxScreenRow;//576 pixel
	}
	
	/**
     * Handles the game state when a level is completed.
     *
     * @param gamePanel The current gamePanel instance.
     */
	public void levelComplete(GamePanel gamePanel) {
		tileM.changeLevel(gamePanel.tileM.getLevel()+1);
		levelChooser.setSelectedIndex(gamePanel.tileM.getLevel()-1);
		update();
	}
		
	/**
     * Updates the game state including player movement and other observer based views.
     */
	public void update() {		
		player.update();
		notifyObs();
	}

	/**
     * Paints the components of the game on the panel.
     *
     * @param g The Graphics context in which to paint.
     */
	public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        mapView.draw(g2);
        playerView.draw(g2);
    }
	public Map getMap() {
		return this.tileM;
	}
	public Player getPlayer() {
		return this.player;
	}
	public JComboBox<String> getLevelChooser(){
		return this.levelChooser;
	}
	public int getMaxScreenCol() {
		return this.maxScreenCol;
	}
	public int getMaxScreenRow() {
		return this.maxScreenRow;
	}
	public void setMaxScreenCol(int col) {
		this.maxScreenCol = col;
	}
	public void setMaxScreenRow(int row) {
		this.maxScreenCol = row;
	}
	public int getTileSize() {
		return this.tileSize;
	}
	public boolean getCheckBox() {
		return checkBoxConsoleView.isSelected();
	}
}