package game.states;

import java.awt.Graphics;

import game.Game;

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
	
	protected Game game;
	
	public States(Game g) {
		game = g;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	protected abstract void initScreen();
	
}
