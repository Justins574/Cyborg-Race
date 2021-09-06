package com.mycompany.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class BrakeCyborg extends Command {

	private GameWorld gw;
	
	public BrakeCyborg(GameWorld gw)
	{
		super("Brake Cyborg");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		gw.brake();
		System.out.println("Cyborg braked");
	}
	
	
}
