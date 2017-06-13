package game.states;

import java.awt.Graphics;

import game.menu.Menu;
import game.worlds.Handler;

public class MenuState extends States {

	private Menu menuScreen;
	
	private final int MENU_WIDTH = 1000;
	private final int MENU_HEIGHT = 1000;
	
	//creates menu
	public MenuState(Handler h) {
		super(h);
		menuScreen = new Menu(h);
	}
	
	//resizes screen
	protected void initScreen() {
		handler.resize(MENU_WIDTH, MENU_HEIGHT);
	}
	
	//updates variables
	public void tick() {
		menuScreen.tick();
	}

	//renders screen
	public void render(Graphics g) {
		menuScreen.render(g);
	}

}
