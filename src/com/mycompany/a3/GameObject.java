package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil; 
import com.codename1.charts.models.Point;

import java.util.Random;

public abstract class GameObject implements IDrawable, ICollider {
	
	private Random rand = new Random();
	
	private int size;
	private int rgb;					// color
	private Point location;
	
	
	public GameObject()
	{
//		System.out.println("GameObject Contructor Visited");
		
		float x = rand.nextInt(1000);
		float y = rand.nextInt(1000);
		
		
		
		location = new Point(x,y);
	}
	
	public void setLocation(float x, float y) {				// update location
		location.setX(x);
		location.setY(y);
	}
	
	public Point getLocation() {
		return location;
	}
	
	
	public void setSize(int inputSize) {
		size = inputSize;
	}
	
	public int getRed() {									// get red color value
		return ColorUtil.red(rgb);
	}
	
	public int getGreen() {									// get green color value
		return ColorUtil.green(rgb);
	}
	
	
	public void setColor(int R, int G, int B)
	{
		rgb = ColorUtil.rgb(R,G,B);
	}
	
	public String toString()				// string of all common game object information
	{
		String display = " loc=(" + location.getX() + "," + location.getY() 
				+ ") col=[" + ColorUtil.red(rgb) + "," + ColorUtil.green(rgb) + "," + ColorUtil.blue(rgb) + "]" 
				+ " size=" + size;
				;
		return display;
		
	}
	
	public int getColor()
	{	
		return rgb;
	}
	
	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}
	
}
