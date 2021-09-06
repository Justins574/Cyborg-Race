package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.util.MathUtil;

public class BaseStrategy implements IStrategy{
	
	private NPC n;
	
	public BaseStrategy(NPC input) {
		this.n = input;		
	}
	
	@Override
	public void apply(Point baseLoc) {
		// TODO Auto-generated method stub
		
		Point nLoc = new Point(0,0);			// NPC location
		
		nLoc.setX(n.getLocation().getX());
		nLoc.setY(n.getLocation().getY());
		
		float a = baseLoc.getX() - nLoc.getX();
		float b = baseLoc.getY() - nLoc.getY();
		
		double beta = MathUtil.atan(a/b);
		
		int desiredHead = (int) Math.toDegrees(beta);
//		System.out.println("desired heading: " + desiredHead);
		
		
		//reset steering wheel
		int oldSteer = n.getSteeringDirection();
		
		if(oldSteer < 0 ) {					// if already steering left, reset to center
			for(int i = 0; i < 1; i++){
				n.steerRight();
			}
		}
		else if(oldSteer > 0) {				// if already steering right, reset to center
			for(int i = 0; i < 1; i++){
				n.steerLeft();
			}
		}
		
		//curHead
		int curHead = n.getHeading();
		
		while(curHead < -180){
	        curHead += 360;
	    }
		while(curHead > 180){
	        curHead -= 360;
	    }
		n.setHeading(curHead);
		
		
		//adjust steering wheel if steering needed
		if (desiredHead - curHead > 10 && desiredHead - curHead < 90 ) {
			for(int i = 0; i < 1; i++){
				n.steerRight();
			}
		}
		else if (desiredHead - curHead > 10 && desiredHead - curHead > 90 ) {
			for(int i = 0; i < 1; i++){
				n.steerLeft();
			}
		}
		
		else if(desiredHead - curHead < -10 && desiredHead - curHead > -90) {	
			for(int i = 0; i < 1; i++){
				n.steerLeft();
			}
		}
		else if(desiredHead - curHead < -10 && desiredHead - curHead < -90) {	
			for(int i = 0; i < 1; i++){
				n.steerRight();
			}
		}
		
		
		System.out.println("NPC  #" + n.getSequenceNumber() + " is chasing base located at (" 
		+ baseLoc.getX() + "," + baseLoc.getY() + ")");
		
	}

}
