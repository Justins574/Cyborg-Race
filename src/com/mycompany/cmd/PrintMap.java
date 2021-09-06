package com.mycompany.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class PrintMap extends Command{
	
private GameWorld gw;
	
	public PrintMap(GameWorld gw)
	{	
		super("Print Map");
		this.gw = gw;
	}
		
	@Override
	public void actionPerformed(ActionEvent e)
	{
		gw.printGameMap();
		System.out.println("Game map Printed");	
	}

}
