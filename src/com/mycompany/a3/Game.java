package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.mycompany.cmd.About;
import com.mycompany.cmd.AccelerateCyborg;
import com.mycompany.cmd.BrakeCyborg;
import com.mycompany.cmd.ChangeStrategy;
import com.mycompany.cmd.ExitGame;
import com.mycompany.cmd.Help;
import com.mycompany.cmd.Pause;
import com.mycompany.cmd.Position;
import com.mycompany.cmd.SteerLeft;
import com.mycompany.cmd.SteerRight;
import com.mycompany.cmd.ToggleSound;

public class Game extends Form implements Runnable{
	
	private final double tickTime = 20;
	private int timeElapsed = 0;
	private UITimer timer;
	
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	
	private boolean paused = false;
	
	private buttonObj accelButton;
	private buttonObj leftButton;
	private buttonObj changeButton;
	private buttonObj brakeButton;
	private buttonObj rightButton;
	private buttonObj pauseButton;
	private buttonObj positionButton;
	
	private AccelerateCyborg accelerateCommand;
	private SteerLeft leftCommand;
	private ChangeStrategy ChangeCommand;
	private BrakeCyborg BrakeCommand;
	private SteerRight rightCommand;
	private Pause PauseCommand;
	private Position PositionCommand;
	
	
	
	public Game() {
		
		this.setLayout(new BorderLayout());
		this.setScrollable(false);
		
		gw = new GameWorld();
		mv = new MapView(); 
		sv = new ScoreView();
		
		gw.addObserver(mv); // register the map observer
		gw.addObserver(sv); // register the score observer
		
		// code here to create Command objects for each command,
		// add commands to side menu and title bar area, bind commands to keys, create
		// control containers for the buttons, add buttons to the control containers,
		// add commands to the buttons, and add control containers, MapView, and
		// ScoreView to the form
		
		SetUpCMD();
		SetUpMenu();
		
		this.addComponent(BorderLayout.CENTER, mv);
		this.addComponent(BorderLayout.NORTH, sv);
		
		gw.init();
		
		this.show();
		
		gw.createSounds();
		
		// code here to query MapView's width and height and set them as world's
		// width and height
		
		gw.setGWHeight(mv.getMapHeight());
		gw.setGWWidth(mv.getMapWidth());
		
		timer  =  new UITimer(this);
		timer.schedule((int)tickTime,true, this);
			
		
		
	}

	
	private void SetUpCMD() {
		
		SetUpWest();
		SetUpEast();
		SetUpSouth();
	
		
	}
	
	private void SetUpWest() {
		// TODO Auto-generated method stub
		
		Container WestContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		WestContainer.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.rgb(0, 0, 0)));
		WestContainer.setScrollableY(false);
		WestContainer.getAllStyles().setPadding(Component.TOP, 100);
		Label WestLabel = new Label("");
		WestContainer.add(WestLabel);
		
		accelerateCommand = new AccelerateCyborg(gw);
		accelButton = new buttonObj(accelerateCommand);
		addKeyListener('a', accelerateCommand);
		addKeyListener('e', accelerateCommand);
		WestContainer.add(accelButton);
		
		leftCommand = new SteerLeft(gw);
		leftButton = new buttonObj(leftCommand);
		addKeyListener('l', leftCommand);
		addKeyListener('f', leftCommand);
		WestContainer.add(leftButton);
		
		ChangeCommand = new ChangeStrategy(gw);
		changeButton = new buttonObj(ChangeCommand);
		WestContainer.add(changeButton);
		
		this.addComponent(BorderLayout.WEST, WestContainer);
	}


	private void SetUpEast() {
		// TODO Auto-generated method stub
		
		Container EastContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		EastContainer.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.rgb(0, 0, 0)));
		EastContainer.setScrollableY(false);
		EastContainer.getAllStyles().setPadding(Component.TOP, 100);
		Label EastLabel = new Label("");
		EastContainer.add(EastLabel);
		
		BrakeCommand = new BrakeCyborg(gw);
		brakeButton = new buttonObj(BrakeCommand);
		addKeyListener('b', BrakeCommand);
		addKeyListener('d', BrakeCommand);
		EastContainer.add(brakeButton);
		
		rightCommand = new SteerRight(gw);
		rightButton = new buttonObj(rightCommand);
		addKeyListener('r', rightCommand);
		addKeyListener('s', rightCommand);
		EastContainer.add(rightButton);
		
		this.addComponent(BorderLayout.EAST, EastContainer);
	}
	
	
	private void SetUpSouth() {
		// TODO Auto-generated method stub
		
		Container SouthContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
		SouthContainer.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.rgb(0, 0, 0)));
		SouthContainer.setScrollableY(false);
		SouthContainer.getAllStyles().setPadding(Component.LEFT, 200);
		Label SouthLabel = new Label("");
		SouthContainer.add(SouthLabel);
				
		PauseCommand = new Pause(this);
		pauseButton = new buttonObj(PauseCommand);
		SouthContainer.add(pauseButton);
		
		PositionCommand = new Position(mv);
		positionButton = new buttonObj(PositionCommand);
		positionButton.setEnabled(!positionButton.isEnabled());
		SouthContainer.add(positionButton);
			
		
		this.addComponent(BorderLayout.SOUTH, SouthContainer);
	}


	private void SetUpMenu() {
		// TODO Auto-generated method stub
		
		Toolbar menu = new Toolbar();
		this.setToolbar(menu);
		menu.setTitle("Sili-Challenge Game");
		
		AccelerateCyborg AC = new AccelerateCyborg(gw);
		menu.addCommandToSideMenu(AC);
		
		ToggleSound sound = new ToggleSound(gw);
		menu.addCommandToSideMenu(sound);
		
		About abt = new About();
		menu.addCommandToSideMenu(abt);
		
		ExitGame exit = new ExitGame();
		menu.addCommandToSideMenu(exit);
		
		Help help = new Help();
		menu.addCommandToRightBar(help);
		
		
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		gw.tick(tickTime);
		timeElapsed += tickTime;
		
	}


	public void pause() {
		// TODO Auto-generated method stub
		
		this.paused = !paused;
		
		accelButton.setEnabled(!accelButton.isEnabled());
		leftButton.setEnabled(!leftButton.isEnabled());
		brakeButton.setEnabled(!brakeButton.isEnabled());
		rightButton.setEnabled(!rightButton.isEnabled());
		positionButton.setEnabled(!positionButton.isEnabled());
		
		if(paused) {
			
			mv.enableClicking();
			
			pauseButton.setText("Play");
			timer.cancel();
			gw.getbg().pause();
			
			removeKeyListener('e', accelerateCommand);
			removeKeyListener('a', accelerateCommand);
			
			removeKeyListener('l', leftCommand);
			removeKeyListener('f', leftCommand);
			
			removeKeyListener('d', BrakeCommand);
			removeKeyListener('b', BrakeCommand);
			
			removeKeyListener('r', rightCommand);
			removeKeyListener('s', rightCommand);
		}
		else {
			
			mv.disableClicking();
			mv.removeSelection();
			
			pauseButton.setText("Pause");
			timer.schedule((int)tickTime, true, this);
			gw.getbg().play();
			
			addKeyListener('e', accelerateCommand);
			addKeyListener('a', accelerateCommand);
			
			addKeyListener('l', leftCommand);
			addKeyListener('f', leftCommand);
			
			addKeyListener('d', BrakeCommand);
			addKeyListener('b', BrakeCommand);
			
			addKeyListener('r', rightCommand);
			addKeyListener('s', rightCommand);
			
		}
		
	}


}
