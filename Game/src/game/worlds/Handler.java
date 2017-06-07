package game.worlds;

import display.Display;
import game.Game;
import game.input.KeyManager;
import game.input.MouseManager;

public class Handler {

	private Game game;
	private World world;
	
	public Handler(Game game) {
		this.game = game;
	}
	
	public Display getDisplay() {
		return game.getDisplay();
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	public void resize(int w, int h) {
		game.resize(w, h);
		game.getDisplay().resize(w, h);
	}
	public int getFPS() {
		return game.getFPS();
	}
	public int getHeight() {
		return game.getHeight();
	}
	public int getWidth() {
		return game.getWidth();
	}
	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	public String getTrackName() {
		return world.getTrackName();
	}
}
