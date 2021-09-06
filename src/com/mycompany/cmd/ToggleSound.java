package com.mycompany.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class ToggleSound extends Command{
	
private GameWorld gw;
	
	public ToggleSound(GameWorld gw)
	{	
		super("Toggle Sound");
		this.gw = gw;
	}
		
	@Override
	public void actionPerformed(ActionEvent e)
	{
		gw.toggleSound();
		System.out.println("Sound Toggled");
	}

}
