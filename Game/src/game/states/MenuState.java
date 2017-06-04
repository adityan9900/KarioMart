package game.states;

import java.awt.Graphics;

import game.Game;
import game.menu.Menu;

public class MenuState extends States {

	private Menu menuScreen;
	
	public MenuState(Game g) {
		super(g);
		menuScreen = new Menu(g);
	}
	
	public void tick() {
		
	}

	public void render(Graphics g) {
		menuScreen.render(g);
	}

}
