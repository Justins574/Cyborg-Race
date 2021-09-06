package com.mycompany.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class ChangeStrategy extends Command {

private GameWorld gw;
	
	public ChangeStrategy(GameWorld gw)
	{	
		super("ChangeStrategy");
		this.gw = gw;
	}
		
	@Override
	public void actionPerformed(ActionEvent e)
	{
		gw.changeStrategy();
		System.out.println("Strategy Changed");
	}
}
