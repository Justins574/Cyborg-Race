package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

public class ScoreView extends Container implements Observer {
	
	private Label elapsedTime;
	private Label playerLives;
	private Label lastBaseReached;
	private Label energyLevel;
	private Label damageLevel;
	private Label sound;
	
	public ScoreView() {
		
		this.setLayout(new FlowLayout(LEFT));
		
		setUpTime();
		setUpLives();
		setUpLastBase();
		setUpEnergy();
		setUpDamage();
		setUpSound();
		
	}
	
	private void setUpSound() {
		Label soundTxt = new Label("Sound:");
		soundTxt.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		sound = new Label("ON");
		sound.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		sound.getAllStyles().setPadding(RIGHT, 5);
		this.add(soundTxt);
		this.add(sound);
		
	}

	private void setUpDamage() {
		Label damageTxt = new Label("Current Damage Level: ");
		damageTxt.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		damageLevel = new Label("0");
		damageLevel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		damageLevel.getAllStyles().setPadding(RIGHT, 20);
		this.add(damageTxt);
		this.add(damageLevel);
		
	}

	private void setUpEnergy() {
		Label energyTxt = new Label("Current Energy Level: ");
		energyTxt.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		energyLevel = new Label("100");
		energyLevel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		energyLevel.getAllStyles().setPadding(RIGHT, 5);
		this.add(energyTxt);
		this.add(energyLevel);
		
	}

	private void setUpLastBase() {
		Label baseTxt = new Label("Highest Reached Base: ");
		baseTxt.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		lastBaseReached = new Label("1");
		lastBaseReached.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		lastBaseReached.getAllStyles().setPadding(RIGHT, 5);
		this.add(baseTxt);
		this.add(lastBaseReached);
		
	}

	private void setUpLives() {
		Label lifeTxt = new Label("PlayerLives: ");
		lifeTxt.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		playerLives = new Label("3");
		playerLives.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		playerLives.getAllStyles().setPadding(RIGHT, 5);
		this.add(lifeTxt);
		this.add(playerLives);
		
	}	
		
	private void setUpTime() {
		Label timeTxt = new Label("ClockTime: ");
		timeTxt.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		elapsedTime = new Label("0");
		elapsedTime.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		elapsedTime.getAllStyles().setPadding(RIGHT, 5);
		this.add(timeTxt);
		this.add(elapsedTime);
		
	}

	@Override
	public void update (Observable o, Object arg) {
		// code here to update labels from the game/player-cyborg state data
		
		System.out.println("Updating ScoreView");

		GameWorld gw = (GameWorld) arg;
		
		elapsedTime.setText(""+Integer.toString(gw.getElapsedTime()));
		playerLives.setText(""+Integer.toString(gw.getPlayerLives()));
		lastBaseReached.setText(""+Integer.toString(gw.findPlayerCyborg().getLastBaseReached()));
		energyLevel.setText(""+Integer.toString(gw.findPlayerCyborg().getEnergyLevel()));
		damageLevel.setText(""+Integer.toString(gw.findPlayerCyborg().getDamageLevel()));
		
		if(gw.getSoundSetting())
		{
			sound.setText("ON");
		}
		else
		{
			sound.setText("OFF");
		}
		
		this.repaint();
	}
	
}
