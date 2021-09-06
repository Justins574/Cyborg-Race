package com.mycompany.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;


public class Pause extends Command{

private Game g;
	
	public Pause(Game g)
	{
		super("Pause");
		this.g = g;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		g.pause();
	}
	
}
