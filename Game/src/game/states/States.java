package game.states;

import java.awt.Graphics;

import game.worlds.Handler;

public abstract class States {
	
	private static States currentState = null;
	
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
	
	public abstract void tick();
	public abstract void render(Graphics g);
	protected abstract void initScreen();
	
}
