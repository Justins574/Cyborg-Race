package com.mycompany.a3;

import com.codename1.charts.models.Point;

public abstract class Moveable extends GameObject{
	
	private int speed;
	private int heading;
	
	
	public Moveable()						
	{
		speed = 0;
		heading = 0;
		
	}
	
	public String toString()			// string of Cyborg unique information
	{
		String display = super.toString() + " heading=" + heading + " speed=" + speed;
		

		return display;
	}
	
	public void setSpeed(int inputSpeed) {
		speed = inputSpeed;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setHeading(int inputHeading) {
		heading = inputHeading;
	}
	
	public int getHeading() {
		return heading;
	}
	
	public void move(double gwHeight, double gwWidth) {
		
		Point oldLocation = getLocation();		// store old location
		Point newLocation = new Point(0,0);		// will store new location (initailize for now)
		
		int Angle = 90 - heading;				//  theta = 90 - heading
		
		double deltaX = 0;
		double deltaY = 0;
		
		deltaX = Math.cos(Math.toRadians(Angle)) * speed;	 	// deltaX = cos(theta)*speed
		deltaY = Math.sin(Math.toRadians(Angle)) * speed;		// deltaY = sin(theta)*speed,
		
		newLocation.setX((float)deltaX + oldLocation.getX());	// update coordinates
		newLocation.setY((float)deltaY + oldLocation.getY());
		
		float H = (float) gwHeight;
		float W = (float) gwWidth;
		
		if (newLocation.getX() > W) {		// create wall at 1000 for X
			newLocation.setX(W);
		}
		
		if (newLocation.getX() < 0) {		// create wall at 0 for X
			newLocation.setX(0);
		}
		
		if (newLocation.getY() > H) {		// create wall at 1000 for Y
			newLocation.setY(H);
		}
		
		if (newLocation.getY() < 0) {		// create wall at 0 for Y
			newLocation.setY(0);
		}
		
		
		setLocation(newLocation.getX(),newLocation.getY());		// set new location
		
		
		
	}

}
