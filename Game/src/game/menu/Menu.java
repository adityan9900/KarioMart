package game.menu;

import java.awt.Graphics;

import game.Game;

public class Menu {
	
	private int width;
	private int height;
	
	public Menu(Game g){
		width = g.width;
		height = g.height;
	}
	
	//doesnt draw in the middle idk why
	public void render(Graphics g){
		g.fillRect(width/2, height/2, width/4, height/10);
	}

}
