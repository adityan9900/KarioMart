package game.states;

import java.awt.Graphics;

import game.worlds.Handler;

public abstract class States {
	
	///state of the game
	private static States currentState = null;
	
	//sets state
	public static void setState(States state) {
		currentState = state;
		currentState.initScreen();
	}
	public static States getState() {
		return currentState;
	}
	
	//CLASS
	
	protected Handler handler;
	
	public States(Handler h) {
		handler = h;
	}
	
	//methods used
	public abstract void tick();
	public abstract void render(Graphics g);
	protected abstract void initScreen();
	
}
