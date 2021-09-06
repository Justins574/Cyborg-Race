package com.mycompany.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class ExitGame extends Command{
	
	public ExitGame()
	{	
		super("Exit Game");
	}
		
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (Dialog.show("Exit Game", "Whoa, are you sure?", "Yes", "No"))
		{
			System.out.println("Closing");
			System.exit(0);
		}
	}
	
}
