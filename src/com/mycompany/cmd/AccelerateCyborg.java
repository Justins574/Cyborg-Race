package com.mycompany.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class AccelerateCyborg extends Command{
	
	private GameWorld gw;
	
	public AccelerateCyborg(GameWorld gw)
	{	
		super("Accelerate Cyborg");
		this.gw = gw;
	}
		
	@Override
	public void actionPerformed(ActionEvent e)
	{
		gw.accelerate();
		System.out.println("Cyborg accelerated");	
	}
	
}
