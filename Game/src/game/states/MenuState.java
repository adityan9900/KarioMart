package game.states;

import java.awt.Graphics;

import game.Game;
import game.menu.Menu;

public class MenuState extends States {

	private Menu menuScreen;
	
	private final int MENU_WIDTH = 1000;
	private final int MENU_HEIGHT = 1000;
	
	public MenuState(Game g) {
		super(g);
		menuScreen = new Menu(g);
	}
	
	protected void initScreen() {
		game.disp.resize(MENU_WIDTH, MENU_HEIGHT);
	}
	
	public void tick() {
		menuScreen.tick();
	}

	public void render(Graphics g) {
		menuScreen.render(g);
	}

}
