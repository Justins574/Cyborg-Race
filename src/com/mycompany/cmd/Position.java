package com.mycompany.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.MapView;

public class Position extends Command {
	
private MapView mv;
	
	public Position(MapView mv)
	{
		super("Position");
		this.mv = mv;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		mv.enablePositionClick();
		System.out.println("Position Button Clicked");
	}

}
