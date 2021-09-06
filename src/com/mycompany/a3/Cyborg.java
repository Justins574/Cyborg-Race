package com.mycompany.a3;

public abstract class Cyborg extends Moveable implements ISteerable {
	
	private int steeringDirection;
	private int maximumSpeed;
	private int energyLevel;
	private int energyConsumptionRate;
	private int damageLevel;
	private int lastBaseReached;
	
	public Cyborg() {
		
		steeringDirection = 0;
		maximumSpeed = 5;
		energyLevel = 1000;
		energyConsumptionRate = 1;			
		damageLevel = 0;					// upper bound 100
		lastBaseReached = 1;
		
		setSize(40);					// set size of cyborg to 40
		setColor(255,0,0);				// color is red
		setLocation(200,350);			// spawn cyborg at first base
		
	}
	
	
	@Override
	public void steerLeft() {
		steeringDirection = steeringDirection - 5;
		
		if(steeringDirection < -45) {
			steeringDirection = -45;
		}
		
	}
	
	@Override
	public void steerRight() {
		steeringDirection = steeringDirection + 5;
		
		if(steeringDirection > 45) {
			steeringDirection = 45;
		}
	}
	
	public int getSteeringDirection() {
		return steeringDirection;
	}
	
	
	public String toString()			// string of Cyborg unique information
	{
		String display = "Cyborg: " + super.toString() + " maxSpeed=" + maximumSpeed + " steeringDirection=" 
				+ steeringDirection + " energyLevel=" + energyLevel + " damageLevel=" + damageLevel ;
		

		return display;
	}	
	
	public int getLastBaseReached() {
		
		return lastBaseReached;
	}
	
	public void setLastBaseReached(int inputBase) {
		lastBaseReached = inputBase;
	}
	
	
	public int getEnergyLevel() {
		
		return energyLevel;
	}
	
	public void setEnergyLevel(int inputLevel) {
		energyLevel = inputLevel;
	}
	
	public int getDamageLevel() {
		
		return damageLevel;
	}
	
	public void setDamageLevel(int inputDamage) {
		damageLevel = inputDamage;
	}
	
	public int getMaximumSpeed() {		
		
		return maximumSpeed;
	}
	
	public void setMaximumSpeed(int inputMax) {
		maximumSpeed = inputMax;
	}
	
	public void accelerate() {			// accelerate by 10
		
		int tempSpeed = getSpeed();
		tempSpeed = tempSpeed + 1;
		
		if (tempSpeed > maximumSpeed) {
			tempSpeed = maximumSpeed;
		}
		
		setSpeed(tempSpeed);
	}
	
	public void brake() {				// brake by 10
		
		int tempSpeed = getSpeed();
		tempSpeed = tempSpeed - 1;
		
		if (tempSpeed < 0) {
			tempSpeed = 0;
		}
		
		setSpeed(tempSpeed);
	}
	
	
	public void depleteEnergy() {
		energyLevel = energyLevel - energyConsumptionRate;
	}
	
	
	public void respawnCyborg() {						
		
		steeringDirection = 0;
		maximumSpeed = 5;
		energyLevel = 1000;
		energyConsumptionRate = 1;
		damageLevel = 0;					// upper bound 100
		
		setSize(40);					// set size of cyborg to 40
		setColor(255,0,0);				// color is red
		setLocation(200,350);			// spawn cyborg at first base
	}
	
	public void collideBase(int x) {				// base reached
		int baseNum = getLastBaseReached();
		
		if (x == baseNum + 1) {				// if reached base is next in sequence
			setLastBaseReached(baseNum + 1);
		}
	}
	
	public void collideEnergyStation(int fuel) {				// energy station reached
		setEnergyLevel(getEnergyLevel() + fuel);					// add fuel from energy station
		
	}
	
	
}
