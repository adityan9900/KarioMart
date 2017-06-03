package game;

import display.Display;

public class Launcher {
	
	public static void main(String[] args) {
		Game game = new Game("Title", 1820, 1000);
		game.start();
	}
	
}
