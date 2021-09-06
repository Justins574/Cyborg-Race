package com.mycompany.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class Help extends Command {
	
	public Help() 
	{
		super("Help");
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String help = "Key Controls:\n"
				+ "a: accelerate \n"
				+ "b: brake \n"
				+ "l: left turn \n"
				+ "r: right turn \n"
				+ "t: tick clock \n";
				
		Dialog.show("Help", help, "Ok", null);
	}

}
