package game.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import game.Game;
import game.input.MouseManager;

public class Menu {
	
	private Game gameCopy;
	
	private Font myFont = new Font(Font.SERIF, Font.PLAIN, 18);
	private final int boxWidth = 350;
	private final int boxHeight = 100;
	
	private State easyState = State.RELEASED;
	private State mediumState = State.RELEASED;
	private State hardState = State.RELEASED;
	
	private Color released,hovered,pressed;
	private Rectangle diffBox;
	private Rectangle diffEasy;
	private Rectangle diffMedium;
	private Rectangle diffHard;
	
	private MouseManager mouse = new MouseManager();
	
	public Menu(Game g) {
		gameCopy = g;
		released = new Color(180,180,180);
		hovered = new Color(160,160,160);
		pressed = new Color(140,140,140);
		diffBox = new Rectangle(500 - boxWidth/2, 150, boxWidth, boxHeight*3+40);
		diffEasy = new Rectangle((int)diffBox.getX() + 10, (int)diffBox.getY()+10, boxWidth-20, boxHeight);
		diffMedium = new Rectangle((int)diffBox.getX() + 10, (int)diffEasy.getY()+boxHeight+10,boxWidth-20 ,boxHeight );
		diffHard = new Rectangle((int)diffBox.getX() + 10, (int)diffMedium.getY()+boxHeight+10,boxWidth-20 ,boxHeight );
	}
	
	public void tick(){
		mouse.tick();
		if(mouse.isPressed()==false){
			if(mouse.inBoundary((int)diffEasy.getX() , (int)diffEasy.getY() , (int)diffEasy.getX()+boxWidth, (int)diffEasy.getY()+boxHeight))
				easyState = State.HOVERED;
			else if(mouse.inBoundary((int)diffMedium.getX() , (int)diffMedium.getY() , (int)diffMedium.getX()+boxWidth, (int)diffMedium.getY()+boxHeight))
				mediumState = State.HOVERED;
			else if(mouse.inBoundary((int)diffHard.getX() , (int)diffHard.getY() , (int)diffHard.getX()+boxWidth, (int)diffHard.getY()+boxHeight))
				hardState = State.HOVERED;
			else
				easyState = mediumState = hardState = State.RELEASED;
		}else{
			if(mouse.inBoundary((int)diffEasy.getX() , (int)diffEasy.getY() , (int)diffEasy.getX()+boxWidth, (int)diffEasy.getY()+boxHeight))
				easyState = State.PRESSED;
			else if(mouse.inBoundary((int)diffMedium.getX() , (int)diffMedium.getY() , (int)diffMedium.getX()+boxWidth, (int)diffMedium.getY()+boxHeight))
				mediumState = State.PRESSED;
			else if(mouse.inBoundary((int)diffHard.getX() , (int)diffHard.getY() , (int)diffHard.getX()+boxWidth, (int)diffHard.getY()+boxHeight))
				hardState = State.PRESSED;
		}
	}
	
	public void render(Graphics g){
		//draws KarioMart on screen
		int tempWidth;
		myFont = myFont.deriveFont(100f);
		g.setFont(myFont);
		tempWidth = g.getFontMetrics(myFont).stringWidth("Kario Mart");
		g.drawString("Kario Mart", gameCopy.disp.getWidth()/2 - tempWidth/2 , gameCopy.disp.getHeight()/10);
		g.drawLine(gameCopy.disp.getWidth()/2 - tempWidth/2 , gameCopy.disp.getHeight()/10+20, gameCopy.disp.getWidth()/2 + tempWidth/2 , gameCopy.disp.getHeight()/10+20);
		myFont = myFont.deriveFont(20f);
		
		g.setColor(released);
		if(easyState == State.RELEASED)
			((Graphics2D)g).fill(diffEasy);
		if(mediumState == State.RELEASED)
			((Graphics2D)g).fill(diffMedium);
		if(hardState == State.RELEASED)
			((Graphics2D)g).fill(diffHard);
		
		g.setColor(hovered);
		if(easyState == State.HOVERED)
			((Graphics2D)g).fill(diffEasy);
		if(mediumState == State.HOVERED)
			((Graphics2D)g).fill(diffMedium);
		if(hardState == State.HOVERED)
			((Graphics2D)g).fill(diffHard);
		
		g.setColor(pressed);
		if(easyState == State.PRESSED)
			((Graphics2D)g).fill(diffEasy);
		if(mediumState == State.PRESSED)
			((Graphics2D)g).fill(diffMedium);
		if(hardState == State.PRESSED)
			((Graphics2D)g).fill(diffHard);
			
		
		/*
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
*/	
		
	
	}
	private enum State{
		HOVERED,
		PRESSED,
		RELEASED;
	}
}
