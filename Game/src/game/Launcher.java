package game;

import display.Display;

public class Launcher {
	
	public static void main(String[] args) {
		Game game = new Game("KarioMart", 1820, 1000);
		System.out.println("Original Width 1820");
		System.out.println("Original Height 1000");
		game.start();
	}
	
}
