package game.states;

import java.awt.Graphics;

import game.menu.Menu;
import game.worlds.Handler;

public class MenuState extends States {

	private Menu menuScreen;
	
	private final int MENU_WIDTH = 1000;
	private final int MENU_HEIGHT = 1000;
	
	public MenuState(Handler h) {
		super(h);
		menuScreen = new Menu(h);
	}
	
	protected void initScreen() {
		handler.resize(MENU_WIDTH, MENU_HEIGHT);
	}
	
	public void tick() {
		menuScreen.tick();
	}

	public void render(Graphics g) {
		menuScreen.render(g);
	}

}
