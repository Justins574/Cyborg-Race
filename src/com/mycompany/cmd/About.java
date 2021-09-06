package com.mycompany.cmd;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class About extends Command{
	
	public About() 
	{
		super("About");
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String about = "Justin Kaloti \nCSC 133 \nSili-Challenge Game \nAssignment 2 ";
		Dialog.show("About", about, "Ok", null);
	}

}
