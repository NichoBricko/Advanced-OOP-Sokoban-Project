package model;

import java.awt.image.BufferedImage;

//SUPERCLASS
/**
 * GameObject is a base class that represents objects in the game.
 * It contains basic attributes common to all game objects, like position, speed and various images.
 */
public class GameObject {

private int x;
private int y;
private int speed;


private BufferedImage player;
	
public int getX() {
	return this.x;
}

public int getY() {
	return this.y;
}
public int getSpeed() {
	return this.speed;
}

public void setX(int x) {
	this.x = x;
}
public void setY(int y) {
	this.y = y;
}
public void setSpeed(int speed) {
	this.speed = speed;
}

public void setPlayer(BufferedImage img) {
	this.player = img;
}
public BufferedImage getPlayer() {
	return this.player;
}


}
