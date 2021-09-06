package com.mycompany.a3;

import java.util.Vector;

public class GameObjectCollection implements ICollection{
	
	private Vector<GameObject> gameObjects;
	
	public GameObjectCollection() {
		 gameObjects  = new Vector<GameObject>();
	}

	@Override
	public void add(GameObject gameObject) {
		gameObjects.add(gameObject);
	}

	@Override
	public IIterator getIterator() {
		return new GameObjectIterator();
	}
	
	public int getSize() {
		return gameObjects.size();
	}
	
	public void remove(GameObject gameObject) {
		// TODO Auto-generated method stub
		gameObjects.remove(gameObject);		
	}
	
	public GameObject getObjectAt(int objectIndex)
	{
		return gameObjects.get(objectIndex);
		
	}
	
	
	// private inner class
	private class GameObjectIterator implements IIterator
	{
		private int index;
		
		public GameObjectIterator()
		{
			index = -1;
		}

		@Override
		public boolean hasNext() {
			
			if(gameObjects.size() <= 0)			// if collection is empty
			{
				return false;
			}
			
			if(index == gameObjects.size() - 1)	// if at end of collection, nothing can be next
			{
				index = -1;						// reset index
				return false;
			}
			return true;
		}

		@Override
		public GameObject getNext() {
			index++;
			return gameObjects.get(index);
		}

		@Override
		public void remove(GameObject object) {
			// TODO Auto-generated method stub
			gameObjects.remove(object);
			index--;
		}

	}
}
