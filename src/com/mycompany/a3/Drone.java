package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public class Drone extends Moveable{
	
	private Random rand = new Random();
	
	public Drone() {
		
		setColor(255,0,255);				// color is purple for drones
		
		int inputSize = 20 + rand.nextInt(5);	// Drone size is random between 20 and 25
		setSize(inputSize);						
		
		setSpeed(2);						
		
		int inputHeading = rand.nextInt(359);	// Drone heading is random between 0 and 359
		setHeading(inputHeading);
		
	}
	
	public String toString()			// string of Drone unique information
	{
		String display = "Drone: " + super.toString();
		

		return display;
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
			
			
		}
		
	}

	@Override
	public void draw(Graphics g, com.codename1.ui.geom.Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		
		g.setColor(this.getColor());
		
		int size = this.getSize();

		int xLoc = (int)this.getLocation().getX() + pCmpRelPrnt.getX();
		int yLoc = (int)this.getLocation().getY() + pCmpRelPrnt.getY();
		
		int[] xArr = { xLoc, (xLoc - size), (xLoc + getSize()), xLoc };
		int[] yArr = { (yLoc + size), (yLoc - size), (yLoc - size), (yLoc + size) };
		
		int nPoints = 3;
		
		g.drawPolygon(xArr, yArr, nPoints);

	}

	@Override
	public boolean collidesWith(GameObject otherObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void handleCollision(GameObject otherObject) {
		// TODO Auto-generated method stub
		
	}
}
