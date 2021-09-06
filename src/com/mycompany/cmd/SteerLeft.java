package com.mycompany.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class SteerLeft extends Command {

private GameWorld gw;
	
	public SteerLeft(GameWorld gw)
	{
		super("SteerLeft");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		gw.steerLeft();
		System.out.println("Steering wheel turned left 5 degrees");
	}
	
}
