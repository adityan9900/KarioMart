package game.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import game.Game;

public class Menu {
	
	private Game gameCopy;
	
	private final Font myFont = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
	private int buttonWidth;
	private int buttonHeight;
	private final int MARGIN = 20;
	
	public Menu(Game g) {
		gameCopy = g;
	}
	
	//doesnt draw in the middle idk why
	public void render(Graphics g){
		//Button width and height relative to screen size
		buttonWidth = ((gameCopy.disp.getWidth() - 4 * MARGIN) / 3);
		buttonHeight = ((gameCopy.disp.getHeight() - MARGIN) / 6);
		
		//Font specifications
		g.setFont(myFont);
		FontMetrics metric = g.getFontMetrics();
		int textHeight = metric.getHeight(), advance;
		
		//EASY
		g.setColor(Color.RED);
		g.fillRect(MARGIN, MARGIN, buttonWidth, buttonHeight);
		g.setColor(Color.BLACK);
		advance = metric.stringWidth("EASY");
		g.drawString("EASY", MARGIN + buttonWidth / 2 - advance / 2, MARGIN + buttonHeight / 2 + textHeight / 2);
		
		//MEDIUM
		g.setColor(Color.RED);
		g.fillRect(2 * MARGIN + buttonWidth, MARGIN, buttonWidth, buttonHeight);
		g.setColor(Color.BLACK);
		advance = metric.stringWidth("MEDIUM");
		g.drawString("MEDIUM", 2 * MARGIN + 3 * buttonWidth / 2 - advance / 2, MARGIN + buttonHeight / 2 + textHeight / 2);
		
		g.setColor(Color.RED);
		g.fillRect(3 * MARGIN + 2 * buttonWidth, MARGIN, buttonWidth, buttonHeight);
	}
}
