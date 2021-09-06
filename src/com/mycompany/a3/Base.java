package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Base extends Fixed{
	
	private int sequenceNumber;
	private static int currentNumber = 1;
	
	private boolean selected = false;

//	private boolean baseReached;
	
	public Base() {
		
//		System.out.println("Base Contructor Visited");
		
		sequenceNumber = currentNumber;
		currentNumber++;
		
		setColor(0,0,255);				// color is blue
		setSize(30);					// set size of all bases 30
				
//		baseReached = false;			// when created, a base has not been reached
//		
//		if (sequenceNumber == 0) {
//			baseReached = true;			// cyborg starts at first base, so that base is reached
//		}
		
//		System.out.println("B4 switch" + sequenceNumber);
		
		switch (sequenceNumber) {		// each base has a different location
		
		case 1:
			setLocation(200,350);
			break;
			
		case 2:
			setLocation(200,800);
			break;
			
		case 3:
			setLocation(700,200);;
			break;
			
		case 4:
			setLocation(900,900);
			break;
			
		default:
			break;
		
		
		}
		
		
		
	}
	
	
	public String toString()			// string of Base unique information
	{
		String display = "Base: " + super.toString() + " seqNum=" + sequenceNumber;
		

		return display;
	}


	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		
		g.setColor(this.getColor());
		
		int size = this.getSize();

		int xLoc = (int)this.getLocation().getX() + pCmpRelPrnt.getX();
		int yLoc = (int)this.getLocation().getY() + pCmpRelPrnt.getY();
		
		int[] xArr = { xLoc, (xLoc - size), (xLoc + getSize()), xLoc };
		int[] yArr = { (yLoc + size), (yLoc - size), (yLoc - size), (yLoc + size) };
		
		int nPoints = 3;
		
		g.drawPolygon(xArr, yArr, nPoints);
		
		if (!selected) {
			g.fillPolygon(xArr, yArr, nPoints);	
		}
		
		g.drawString("   " +Integer.toString(sequenceNumber), xLoc, yLoc);
	}
	
	public int getNumber() {
		// TODO Auto-generated method stub
		
		return sequenceNumber;
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
			System.out.println("Base Clicked");
			return true;
		}
		return false;
	}



	
	

	
}
