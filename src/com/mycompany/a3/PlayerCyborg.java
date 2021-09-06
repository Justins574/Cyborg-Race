package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class PlayerCyborg extends Cyborg {
	// Move functions from Cyborg to here if NPC does not need the functions
	
	private static PlayerCyborg player;
	
	private PlayerCyborg() {}
	
	
	public static PlayerCyborg getPlayer() {
		if (player == null)
			player = new PlayerCyborg();
		return player;
	}
	
	public String toString()			// string of Player Cyborg unique information
	{
		String display = "Player " + super.toString();
		
		return display;
	}	
	
	public void collideCyborg() {			// collision with Cyborg
		
		int damage =  getDamageLevel();
		int maxSpeed = getMaximumSpeed();
		
		damage = damage + 2;				// take damage
		int red = getRed() - 2;			// reduce Color redness
		setColor(red, 0, 0);
		
		if (100 - damage > 80) {
			maxSpeed = 5;
			
		} else if (100 - damage > 60) {
			maxSpeed = 4;
			
		}else if (100 - damage > 40) {
			maxSpeed = 3;
			
		}else if (100 - damage > 20) {
			maxSpeed = 2;
			
		}else {
			maxSpeed = 1;
			
		}
		if (getSpeed() > maxSpeed) {	// if current speed is greater than new max speed
			setSpeed(maxSpeed);			// set current speed to new max speed
		}
		
		setDamageLevel(damage);
		setMaximumSpeed(maxSpeed);
	}
	
	
	public void collideDrone() {			// collision with Drone
		int damage =  getDamageLevel();
		int maxSpeed = getMaximumSpeed();
		
		damage = damage + 2;				// take damage
		int red = getRed() - 2;			// reduce Color redness
		setColor(red, 0, 0);
		
		if (100 - damage > 80) {
			maxSpeed = 5;
			
		} else if (100 - damage > 60) {
			maxSpeed = 4;
			
		}else if (100 - damage > 40) {
			maxSpeed = 3;
			
		}else if (100 - damage > 20) {
			maxSpeed = 2;
			
		}else {
			maxSpeed = 1;
			
		}
		
		
		if (getSpeed() > maxSpeed) {	// if current speed is greater than new max speed
			setSpeed(maxSpeed);			// set current speed to new max speed
		}
		
		setDamageLevel(damage);
		setMaximumSpeed(maxSpeed);
	}
	
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {

		g.setColor(this.getColor());
		
		int size = this.getSize();
		
		int xLoc = (int)this.getLocation().getX() + pCmpRelPrnt.getX();
		int yLoc = (int)this.getLocation().getY() + pCmpRelPrnt.getY();
		
		g.drawRect(xLoc, yLoc, size, size);
		g.fillRect(xLoc, yLoc, size, size);		
	}


	@Override
	public boolean collidesWith(GameObject otherObject) {
		// TODO Auto-generated method stub
		
		boolean collision = false;
		
		double thisX = this.getLocation().getX();
		double thisY = this.getLocation().getY();
		
		double otherX = otherObject.getLocation().getX();
		double otherY = otherObject.getLocation().getY();
		
		double dx = thisX - otherX;
		double dy = thisY - otherY;
		
		double distance = (dx * dx + dy * dy);
		
		int thisRadius= this.getSize() / 2;
		
		int otherRadius= otherObject.getSize() / 2;
		
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius);
		
		if (distance <= radiiSqr){ 
			collision = true; 
		}
		
		return collision;
	}


	@Override
	public void handleCollision(GameObject otherObject) {
		
		if (otherObject instanceof NPC) {
			collideCyborg();
		}
		
		else if(otherObject instanceof Drone){
			collideDrone();
		}
		
		else if(otherObject instanceof EnergyStation){
			
			EnergyStation E = (EnergyStation) otherObject;
			collideEnergyStation(E.getCapacity());
		}
		
		else if(otherObject instanceof Base){
			
			Base B = (Base) otherObject;
			collideBase(B.getNumber());
		}
		
		
		
	}
	

}
