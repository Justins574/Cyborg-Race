package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer  {
	
	private GameWorld gw;
	private boolean paused = false;
	private boolean positionMode = false;
	
	public MapView() {
		this.getAllStyles().setBorder(Border.createLineBorder(2,ColorUtil.rgb(255, 0, 0)));
//		this.setWidth(1000);
//		this.setHeight(1000);
		
	}
	
	@Override
	public void update (Observable o, Object arg) {
		// code here to call the method in GameWorld (Observable) that output the
		// game object information to the console
		
//		System.out.println("Updating MapView");
		gw = (GameWorld) arg;
		gw.printGameMap();
		System.out.println("update");
		repaint();
		
		}

	public double getMapHeight() {
		// TODO Auto-generated method stub
		
		double height = (double) this.getHeight();
		return height;
		
	}

	public double getMapWidth() {
		// TODO Auto-generated method stub
		
		double width = (double) this.getWidth();		
		return width;
		
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		Point pCmpRelPrnt = new Point(this.getX(), this.getY());
		IIterator iterator = gw.getCollection().getIterator();
		
		while (iterator.hasNext())
		{
			GameObject currentObject = iterator.getNext();

			currentObject.draw(g, pCmpRelPrnt);

		}
	}
	
	@Override
	public void pointerPressed(int x, int y)
	{
		if(paused) {
			
			if(positionMode) {
				
				int px = x - getParent().getAbsoluteX() - getX();
				int py = y - getParent().getAbsoluteY() - getY();
				
//				int px = getX();
//				int py = getY();
				
				IIterator iterator = gw.getCollection().getIterator();
				
				while (iterator.hasNext())
				{
					GameObject currentObject = iterator.getNext();
					
					if (currentObject instanceof ISelectable)
					{
						Fixed selectedObject = (Fixed)currentObject;
						
						if(selectedObject.isSelected()) {
							selectedObject.setLocation(px, py);
							selectedObject.setSelected(false);
						}
						
					}
				}		
				positionMode = false;
			}
			else {
			
				int px = x - getParent().getAbsoluteX();
				int py = y - getParent().getAbsoluteY();
				Point pPtrRelPrnt = new Point(px, py);
				Point pCmpRelPrnt = new Point(getX(), getY());
				
				IIterator iterator = gw.getCollection().getIterator();
				
				while (iterator.hasNext()){
					
					GameObject currentObject = iterator.getNext();
					
					if (currentObject instanceof ISelectable){
						
						ISelectable selectedObject = (ISelectable)currentObject;
						
						if (selectedObject.contains(pPtrRelPrnt, pCmpRelPrnt)){
							selectedObject.setSelected(true);
						}
						else {
							selectedObject.setSelected(false);
						}
					}
				}
			}
			repaint();
		}
		System.out.println("Click Detected");
	}

	public void enableClicking() {
		// TODO Auto-generated method stub
		paused = true;
	}

	public void disableClicking() {
		// TODO Auto-generated method stub
		paused = false;
	}

	public void removeSelection() {
		// TODO Auto-generated method stub
		IIterator iterator = gw.getCollection().getIterator();
		
		while (iterator.hasNext())
		{
			GameObject currentObject = iterator.getNext();
			
			if (currentObject instanceof ISelectable)
			{
				ISelectable selectedObject = (ISelectable)currentObject;
				
				selectedObject.setSelected(false);
			}
		}
		
		repaint();
	}

	public void enablePositionClick() {
		// TODO Auto-generated method stub
		
		positionMode = true;
		
	}
}
