package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public class NPC extends Cyborg{
	// energy refills when low
	// start with 0 damageLevel, but take 1 damage per collision. Cannot die, so damage has no max
	// every tick, update steering to position of player or base (-25 to 25) with new steer function (override steering)
	// create 3 NPC
	// no 4 collide functions
	
	private int sequenceNumber;
	private static int currentNumber = 1;
	
	private Point baseLoc = new Point(200,800);
	private Point playerLoc = new Point(200,200);
	
	private IStrategy curStrategy;				
	
	public NPC() {
		
		sequenceNumber = currentNumber;
		currentNumber++;
		
		setSpeed(1);
		
		switch (sequenceNumber) {		// each NPC has a different location and strategy
		
		case 1:
			setLocation(200,50);
			curStrategy = new BaseStrategy(this);
			break;
			
		case 2:
			setLocation(50,50);
			curStrategy = new AttackStrategy(this);
			break;
			
		case 3:
			setLocation(50,350);
			curStrategy = new BaseStrategy(this);
			break;
			
			
		default:
			break;
			
		}
		
		invokeStrategy();				// apply strategy once created
		
	}
	
	
	public String toString()			// string of NPC unique information
	{
		String display = "Non-Player " + super.toString() + " currentStrategy= " + getStratSting();
		
		return display;
	}	
	
	
	private String getStratSting() {				// For printing strategy name
		
		String stratName;
		
		if (curStrategy instanceof BaseStrategy ) {		
			stratName = "BaseStrategy";			
		}
		else{											
			stratName = "AttackStrategy";
		}
		
		return stratName;
	}


	public void changeStrategy() {						// swap from attack to base strategy and vice versa

		if (curStrategy instanceof BaseStrategy ) {		// swap to attack
			curStrategy = new AttackStrategy(this);			
		}
		else{											// swap to base
			curStrategy = new BaseStrategy(this);
		}
		
//		setLastBaseReached(getLastBaseReached() + 1); 	// update cyborg last base reached
//		invokeStrategy();
		
		
	}


	public void invokeStrategy() {						// update steering
		
		if (curStrategy instanceof BaseStrategy ) {		// if base, pass bassLoc as target
			curStrategy.apply(baseLoc);		
		}
		else{											// if attack, pass playerLoc as target
			curStrategy.apply(playerLoc);
		}
		
	}
	
	public int getSequenceNumber() {					// FOR TESTING PURPOSES
		
		return sequenceNumber;
	}


	public void updatePlayerLoc(Point inputPlayer) {		// update location of next base and player
		
		playerLoc.setX(inputPlayer.getX());
		playerLoc.setY(inputPlayer.getY());

		
	}


	public void updateBaseLoc(Point inputBase) {
		baseLoc.setX(inputBase.getX());
		baseLoc.setY(inputBase.getY());
		
	}
	
	
	public void collision() {			// collision with PlayerCyborg
		
		int damage =  getDamageLevel();
		int maxSpeed = getMaximumSpeed();
		
		damage = damage + 1;				// take 1 damage
		int red = getRed() - 1;			// reduce Color redness by 1
		
		if (red < 0) {
			red = 0;
		}
		
		setColor(red, 0, 0);
		
//		maxSpeed = 100 - damage;		// reduce max speed
//		if (getSpeed() > maxSpeed) {	// if current speed is greater than new max speed
//			setSpeed(maxSpeed);			// set current speed to new max speed
//		}
		
		setDamageLevel(damage);
		setMaximumSpeed(maxSpeed);
	
	}


	@Override
	public void draw(Graphics g, com.codename1.ui.geom.Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		
		g.setColor(this.getColor());
		
		int size = this.getSize();
		
		int xLoc = (int)this.getLocation().getX() + pCmpRelPrnt.getX() - (size / 2);
		int yLoc = (int)this.getLocation().getY() + pCmpRelPrnt.getY() - (size / 2);
		
		g.drawRect(xLoc, yLoc, size, size);

		
	}
	
	public void boundaryCheck(double gwHeight, double gwWidth) {				
		
		Point oldLocation = getLocation();		// store old location
		
		float H = (float) gwHeight;
		float W = (float) gwWidth;
		
		if(oldLocation.getX() >= W || oldLocation.getX() <= 0  ||
				oldLocation.getY() >= H || oldLocation.getY() <= 0) { 	// if side bounds
			
			int oldHeading = getHeading();
			
			int newHeading = oldHeading - 180;
			
			setHeading(newHeading);
				
			if(oldLocation.getX() >= W) {
				this.setLocation(oldLocation.getX() - 5, oldLocation.getY());
			}
			if(oldLocation.getX() <= 0) {
				this.setLocation(oldLocation.getX() + 5, oldLocation.getY());
			}
			
			if(oldLocation.getY() >= H) {
				this.setLocation(oldLocation.getX(), oldLocation.getY() - 5);
			}
			if(oldLocation.getY() <= 0) {
				this.setLocation(oldLocation.getX(), oldLocation.getY() + 5);
			}

		}
		
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
		// TODO Auto-generated method stub
		
		if (otherObject instanceof PlayerCyborg){
			collision();
		}
		
		else if (otherObject instanceof NPC){
			collision();
		}
		
		else if(otherObject instanceof Drone){
			collision();
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
