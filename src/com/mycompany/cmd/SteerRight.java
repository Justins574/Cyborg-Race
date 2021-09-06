package com.mycompany.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class SteerRight extends Command {

private GameWorld gw;
	
	public SteerRight(GameWorld gw)
	{
		super("SteerRight");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		gw.steerRight();
		System.out.println("Steering wheel turned right 5 degrees");
	}
	
}
