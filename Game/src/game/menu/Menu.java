package game.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.Game;

public class Menu {
	
	private final Font myFont = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
	
	public Menu() {
		
	}
	
	//doesnt draw in the middle idk why
	public void render(Graphics g, Game gme){
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(gme.disp.getWidth() / 2, gme.disp.getHeight() / 2, 200, 100);
		g.setColor(Color.BLACK);
		g.setFont(myFont);
		g.drawString("Play Game", gme.disp.getWidth() / 2, gme.disp.getHeight() / 2 + 50);
	}

}
