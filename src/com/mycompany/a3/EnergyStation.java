package com.mycompany.a3;

import java.util.Random;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class EnergyStation extends Fixed{

	private int capacity;
	private Random rand = new Random();
	private Boolean replaced = false;
	
	private boolean selected = false;
	
	public EnergyStation() {
		
		setColor(0,255,0);				// Energy Stations are green

		int inputSize = 30 + rand.nextInt(20);	// Size is random between 30 and 50
		setSize(inputSize);
		
		capacity = (inputSize - 10) * 20;
		
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	
	public void collideEnergyStation() {		
	//	int fuel = capacity;				// transfer fuel from station 
		capacity = 0;						// empty the station
		
		int green = 55;			// reduce Color Greenness by 200
		setColor(0, green, 0);
		
	//	return fuel;
	}
	
	public String toString()			// string of Base unique information
	{
		String display = "EnergyStation: " + super.toString() + " capacity=" + capacity;
		

		return display;
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		// TODO Auto-generated method stub
		
		int size = this.getSize();
		
		g.setColor(this.getColor());

		int xLoc = (int)this.getLocation().getX() + pCmpRelPrnt.getX() - (size / 2);
		int yLoc = (int)this.getLocation().getY() + pCmpRelPrnt.getY() - (size / 2);
		
		g.drawArc(xLoc, yLoc, size, size, 0, 360);
		
		if (!selected) {
			g.fillArc(xLoc, yLoc, size, size, 0, 360);
		}
		
		g.drawString("      " + Integer.toString(capacity), xLoc, yLoc);
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
		
		if (otherObject instanceof Cyborg) {
			collideEnergyStation();
		}
		
	}

	public boolean checkReplacement() {
		// TODO Auto-generated method stub
		return replaced;
	}

	public void Replaced() {
		// TODO Auto-generated method stub
		replaced = true;
	}
	

	@Override
	public void setSelected(boolean inputTF) {
		selected = inputTF;
	}

	@Override
	public boolean isSelected() {
		return selected;
	}

	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrint) {
		// TODO Auto-generated method stub
		
		int px = pPtrRelPrnt.getX();
		int py = pPtrRelPrnt.getY();
		
		int xLoc = + pCmpRelPrint.getX() + (int)this.getLocation().getX();
		int yLoc = + pCmpRelPrint.getY() + (int)this.getLocation().getY();
		
		if ( (px >= xLoc - getSize() /  2) 
				&& (px <= xLoc + getSize() / 2) 
				&& (py >= yLoc - getSize() / 2) 
				&& (py <= yLoc + getSize() / 2))
		{
			System.out.println("EnergyStation Clicked");
			return true;
		}
		return false;
		
	}
	
	
	
}
