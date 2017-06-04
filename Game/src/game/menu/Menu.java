package game.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Menu {
	
	public final int DEFAULT_WIDTH = 800;
	public final int DEFAULT_HEIGHT = 550;
	private final Font myFont = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
	
	public Menu(){
		
	}
	
	//doesnt draw in the middle idk why
	public void render(Graphics g){
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(DEFAULT_WIDTH/2, DEFAULT_HEIGHT/2, 200, 100);
		g.setColor(Color.BLACK);
		g.setFont(myFont);
		g.drawString("Play Game", DEFAULT_WIDTH/2, DEFAULT_HEIGHT/2 + 50);
	}

}
