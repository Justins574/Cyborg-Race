package com.mycompany.a3;
import java.util.Observable;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.ui.Dialog;


public class GameWorld extends Observable{
	
	//private Vector<GameObject> gameObjects  = new Vector<GameObject>();
	
	private GameObjectCollection gameObjects;
	
	private int playerLives;
	private int clockTime;
	
	private boolean soundSetting = true;
	
	private double gwWidth;
	private double gwHeight;
	
	private Sound crash;
	private Sound energy;
	private Sound death;
	private Music bg;
	
	private Random rand = new Random();
	
	public void init(){
		//code here to create the
		//initial game objects/setup
		System.out.println("Initializing");
		
		playerLives = 3;							// 3 lives
		clockTime = 0;								// clock starts at zero
		
		soundSetting = true;
		
		gameObjects = new GameObjectCollection();		// contains vector
		

		addObject('b');								// create 4 bases
		addObject('b');
		addObject('b');
		addObject('b');
		
		addObject('c');								// create player cyborg
		
		addObject('d');								// create 2 drones
		addObject('d');
		
		addObject('n');								// create 3 NPCs
		addObject('n');
		addObject('n');
		
		addObject('e');								// create 2 energy stations
		addObject('e');
		
		UpdateObservers();
		
	}
	
	public void exit() {
		System.out.println("Closing");
		System.exit(0);

	}
	
//	public void setGameOver(boolean gameOver) {
//		this.gameOver = gameOver;
//	}
	
	public int getPlayerLives() {
		return playerLives;
		
	}
	
	public void addObject(char ch) {
		// TODO Auto-generated method stub
		switch(ch)	{
		
		case 'n': 
			NPC n = new NPC();
			gameObjects.add(n);
			System.out.println("NPC created");	
			break;
			
		case 'c': 
			Cyborg c = PlayerCyborg.getPlayer();
			gameObjects.add(c);
			System.out.println("Player Cyborg created");	
			break;
		
		case 'b':											// create 4 bases
			Base b = new Base();
			gameObjects.add(b);
			System.out.println("Base created");	
			break;
			
		case 'd':
			Drone d = new Drone();
			gameObjects.add(d);
			System.out.println("Drone created");	
			break;
			
		case 'e':
			EnergyStation e = new EnergyStation();
			gameObjects.add(e);
			System.out.println("EnergyStation created");	
			break;
			
		default:
			break;
		}
		
	}
	
	
	public void printGameMap() {							// display Map of current world
		for(int i=0; i <  gameObjects.getSize(); i++)
		{
			System.out.println(gameObjects.getObjectAt(i));
		}
		
	}
	
	public void printGameState() {								// print state values.
		System.out.println("PlayerLives = " + playerLives);
		System.out.println("ClockTime = " + clockTime);
		System.out.println("Highest Reached Base = " + findPlayerCyborg().getLastBaseReached());
		System.out.println("Current Energy Level = " + findPlayerCyborg().getEnergyLevel());
		System.out.println("Current Damage Level = " + findPlayerCyborg().getDamageLevel());
		
	}
	
	public PlayerCyborg findPlayerCyborg() {
		
		for(int i=0; i < gameObjects.getSize(); i++){
			if(gameObjects.getObjectAt(i) instanceof PlayerCyborg)
			{				
				return (PlayerCyborg) gameObjects.getObjectAt(i);
			}
		}
		
		return null;
	}

	
	public void accelerate() {
		if (findPlayerCyborg() != null) {
			findPlayerCyborg().accelerate();
		}
		UpdateObservers();
	}
	
	public void brake() {
		if (findPlayerCyborg() != null) {
			findPlayerCyborg().brake();
		}
		UpdateObservers();
	}
	
	
	public void steerLeft() {
		if (findPlayerCyborg() != null) {
			findPlayerCyborg().steerLeft();
		}
		UpdateObservers();
	}
	
	public void steerRight() {
		if (findPlayerCyborg() != null) {
			findPlayerCyborg().steerRight();
		}
		UpdateObservers();
	}
	
	
	public void death() {
		playerLives = playerLives - 1;			// 1 life lost
		System.out.println("Cyborg died");
		System.out.println("Lives Remaining = " + playerLives);
		death.play();
		
		if(playerLives == 0) {					// final life is lost
			
			UpdateObservers();
			System.out.println("Game over, you failed!");
			Dialog.show("Game Over! R.I.P.", "Your Cyborg is dead!", "Ok", null);
			exit();
		}
		
		if (findPlayerCyborg() != null) {		// respawn cyborg
			findPlayerCyborg().respawnCyborg();
		}
		
	}
	
	public void victory() {
		
		UpdateObservers();
		System.out.println("Victory, you win! Total time: " + clockTime);
		Dialog.show("Victory!", "Congrats! You Win!", "Ok", null);
		exit();
	}

	public void setGWHeight(double mapHeight) {

		System.out.println(mapHeight);
		gwHeight = mapHeight;
	}

	public void setGWWidth(double mapWidth) {

		System.out.println(mapWidth);
		gwWidth = mapWidth;
	}

	public void changeStrategy() {
		// TODO Auto-generated method stub
		
		for(int i = 0; i < gameObjects.getSize(); i++) {					// loop through all objects
		
			if(gameObjects.getObjectAt(i) instanceof NPC)					// if NPC is found
			{
				NPC n = (NPC) gameObjects.getObjectAt(i);
				n.changeStrategy();
				
				
				Point playerLoc = new Point();								// grab player location
				playerLoc.setX(findPlayerCyborg().getLocation().getX());
				playerLoc.setY(findPlayerCyborg().getLocation().getY());
				
				int nextBase = n.getLastBaseReached() + 1;
				Point baseLoc = new Point();
				
				float x = 0;
				float y = 0;
				
				if (nextBase < 5) {
					x = (findBase(nextBase).getLocation().getX());
					y = (findBase(nextBase).getLocation().getY());
				}
				
				baseLoc.setX(x);
				baseLoc.setY(y);
				
				
				n.updatePlayerLoc(playerLoc);						// update locations
				n.updateBaseLoc(baseLoc);
				
			}
			
		}
		UpdateObservers();
	}

	public void NPCWin() {
		
		UpdateObservers();
		System.out.println("Game over, a Non-Player Cyborg wins! Total time: " + clockTime);
		Dialog.show("Game Over!", "A Non-Player Cyborg wins!", "Ok", null);
		exit();
		
	}

	public void toggleSound() {
		// TODO Auto-generated method stub
		
		soundSetting = !soundSetting;
		
		if(soundSetting) {
			bg.Unmute();
			crash.Unmute();
			energy.Unmute();
			death.Unmute();
		} 
		else {
			bg.Mute();
			crash.Mute();
			energy.Mute();
			death.Mute();
		}
		
		UpdateObservers();
	}
	
	public boolean getSoundSetting() {
		// TODO Auto-generated method stub
		return soundSetting;
	}

	public int getElapsedTime() {
		// TODO Auto-generated method stub
//		System.out.println("Time: "+ clockTime);
		return clockTime;
	}

	
	private void UpdateObservers() {
	
		this.setChanged();
		this.notifyObservers(this);

	}

	public GameObjectCollection getCollection() {
		return this.gameObjects;
		
	}
	
	public void tick(double tickTime) {												
		clockTime += tickTime;													// increment game clock
		
		for(int i = 0; i < gameObjects.getSize(); i++) {					// loop through all objects
			
			if(gameObjects.getObjectAt(i) instanceof Cyborg) {					// if cyborg is found
				
				Cyborg c = (Cyborg) gameObjects.getObjectAt(i);
				
				if(gameObjects.getObjectAt(i) instanceof NPC) {			// if NPC, make sure to invoke strategy
																		// to adjust steering if needed
					NPC n = (NPC) gameObjects.getObjectAt(i);
					
					Point playerLoc = new Point();								// grab player location 
					playerLoc.setX(findPlayerCyborg().getLocation().getX());
					playerLoc.setY(findPlayerCyborg().getLocation().getY());
					
					int nextBase = n.getLastBaseReached() + 1;
					Point baseLoc = new Point();
					
					float x = 0;
					float y = 0;
					
					if (nextBase < 5 ) {
						x = (findBase(nextBase).getLocation().getX());
						y = (findBase(nextBase).getLocation().getY());
					}
					
					baseLoc.setX(x);
					baseLoc.setY(y);
					
					
					n.updatePlayerLoc(playerLoc);						// update player location due to movement
					n.updateBaseLoc(baseLoc);
					
					n.invokeStrategy();
				}
				
				
				if(c.getEnergyLevel() != 0 &&				// if cyborg still has energy
						c.getDamageLevel() != 100 &&	    // and cyborg is not at max damage
						c.getSpeed() != 0) {				// and is not at 0 speed
					
					
					int oldHeading = c.getHeading();					// store old heading
					int newHeading = 0;									// initialize new heading
					
					int steeringDirection = c.getSteeringDirection();	// get steering direction 
					
					newHeading = oldHeading + steeringDirection;		// calculate new heading
					
					c.setHeading(newHeading);				// update heading
					
				}	
				
				c.depleteEnergy();				// deplete energy
				
				
				if(gameObjects.getObjectAt(i) instanceof NPC) {			// if NPC, check energyLevel
					NPC n = (NPC) gameObjects.getObjectAt(i);
					
					if(n.getEnergyLevel() < 50) {						// if below 50, set to 50
						n.setEnergyLevel(50);
					}
				}				
				
				
				if(c.getEnergyLevel() == 0) {		// if out of energy, dead
					death();
				}
			}//if(gameObjects.get(i) instanceof Cyborg)
			
			
			if(gameObjects.getObjectAt(i) instanceof Drone) {				// if drone is found
				
				Drone d = (Drone) gameObjects.getObjectAt(i);
				
				int oldHeading = d.getHeading();					// store old heading
				int newHeading = 0;									// initialize new heading
				
				int turn = rand.nextInt(10) - 5 ;					// drone can turn up to 5 or -5
				
				newHeading = oldHeading + turn;						// calculate new heading
				
				d.setHeading(newHeading);							// update heading
			}//if(gameObjects.get(i) instanceof Drone)
			 
			
			if(gameObjects.getObjectAt(i) instanceof Moveable) {				// if object is moveable
				
				((Moveable) gameObjects.getObjectAt(i)).move(gwHeight, gwWidth);					// then move it
				
				
				if(gameObjects.getObjectAt(i) instanceof Drone) {				// if a drone just moved
					
					Drone d = (Drone) gameObjects.getObjectAt(i);
					
					d.boundaryCheck(gwHeight, gwWidth);									// function to handle drone out of bounds
					
					d.move(gwHeight, gwWidth);								// move back into bounds
				}
				
				if(gameObjects.getObjectAt(i) instanceof NPC) {				// if an NPC just moved
					
					NPC n = (NPC) gameObjects.getObjectAt(i);
					
					n.boundaryCheck(gwHeight, gwWidth);									// function to handle NPC out of bounds
					
					n.move(gwHeight, gwWidth);								// move back into bounds
				}
				
			}//if(gameObjects.get(i) instanceof Moveable)	
			
		}//for loop through all objects
		
		CheckCollisions();
		
		UpdateObservers();
	}//public void tick()

	
	private GameObject findBase(int nextBase) {
		
		for(int i=0; i < gameObjects.getSize(); i++){
			if(gameObjects.getObjectAt(i) instanceof Base)
			{		
				Base b = (Base) gameObjects.getObjectAt(i);
				if(b.getNumber() == nextBase) {
					return b;
				}
				
			}
		}
		
		return null;
		
	}

	private void CheckCollisions() {
		
		IIterator iterator = gameObjects.getIterator();							
		
		while (iterator.hasNext()) {									// loop through all objects (object 1)
			
			GameObject object = iterator.getNext();
			
			IIterator iterator2 = gameObjects.getIterator();
			
			while(iterator2.hasNext())									// loop through all objects (object 2)
			{
				GameObject object2 = iterator2.getNext();
				if(!(object.equals(object2)))							
				{					
					if(object.collidesWith(object2))					// if object1 collides with an object from iterator
					{
						object.handleCollision(object2);
						
						
						if(object instanceof NPC) {						// check if object is NPC	
							
							NPC n = (NPC) object;
							
							if(n.getLastBaseReached() == 4) {			// if NPC reached base 4
								NPCWin();
							}		
							
							if(object2 instanceof Cyborg) {
								crash.play();
							}
							
						}
						
						if(object instanceof EnergyStation) {
							
							EnergyStation e = (EnergyStation) object;
							
							if(e.getCapacity() == 0 && e.checkReplacement() == false) {					// if an empty energy station is found, create new
								
								EnergyStation newE = new EnergyStation();
								gameObjects.add(newE);
								System.out.println("EnergyStation created");
								e.Replaced();
								energy.play();
							}	
						}
						
					}
				}
			}
		}
		
		if(findPlayerCyborg().getLastBaseReached() == 4) {	// if the final base was reached
			victory();										// you win
		}
		
		if (findPlayerCyborg() != null) {						// check if cyborg died
			if(findPlayerCyborg().getDamageLevel() >= 100) {
				death();
			}
		}
		
		UpdateObservers();
	}

	public Music getbg() {
		// TODO Auto-generated method stub
		return bg;
	}

	
	public void createSounds() {
		// TODO Auto-generated method stub
		bg = new Music("BG Beat.mp3");
		
		System.out.println("Music Loaded");
		
		energy = new Sound("energy.wav");
		crash = new Sound("crash.wav");	
		death = new Sound("death.wav");
		
		System.out.println("Sounds Loaded");
		
		UpdateObservers();
		
		bg.play();
	}

	
	
	// additional methods here to
	// manipulate world objects and
	// related game state data
}
