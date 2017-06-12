package game.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import game.Game;
import game.Game.Difficulty;
import game.input.MouseManager;
import game.states.States;
import game.worlds.Handler;

public class Menu {
	
	private Handler handler;
	
	private Font myFont = new Font(Font.SERIF, Font.PLAIN, 18);
	private final int boxWidth = 350;
	private final int boxHeight = 100;
	
	private State easyState = State.RELEASED;
	private State mediumState = State.RELEASED;
	private State hardState = State.RELEASED;
	private State quitState = State.RELEASED;
	private State instructionState = State.RELEASED;
	
	private Color released,hovered,pressed;
	private Rectangle diffBox;
	private Rectangle diffEasy;
	private Rectangle diffMedium;
	private Rectangle diffHard;
	private Rectangle quit;
	private Rectangle instructionBox;
	
	
	private MouseManager mouse;
	
	public Menu(Handler handler) {
		this.handler = handler;
		mouse = handler.getMouseManager();
		released = new Color(180,180,180);
		hovered = new Color(160,160,160);
		pressed = new Color(140,140,140);
		diffBox = new Rectangle(500 - boxWidth/2, 150, boxWidth, boxHeight*3+40);
		diffEasy = new Rectangle((int)diffBox.getX() + 10, (int)diffBox.getY()+10, boxWidth-20, boxHeight);
		diffMedium = new Rectangle((int)diffBox.getX() + 10, (int)diffEasy.getY()+boxHeight+10,boxWidth-20 ,boxHeight );
		diffHard = new Rectangle((int)diffBox.getX() + 10, (int)diffMedium.getY()+boxHeight+10,boxWidth-20 ,boxHeight );
		instructionBox = new Rectangle((int)(diffBox.getMaxX() + 20),(int)diffBox.getY(), (int)(1000 - diffBox.getMaxX() -20),(int)boxHeight*3);
		quit = new Rectangle((int)diffBox.getX(),150 + boxHeight*3+50,boxWidth,(int)(boxHeight*1.5));
	
	}
	
	public void tick(){
		if(mouse.isPressed()&&mouse.inBoundary((int)quit.getX()+10 , (int)quit.getY()+10 , (int)quit.getX()+boxWidth-20, (int)(quit.getY()+boxHeight*1.5-20)))
				System.exit(0);
		else if(mouse.isPressed()&&mouse.inBoundary((int)diffEasy.getMinX() , (int)diffEasy.getMinY() , (int)diffEasy.getMaxX(), (int)diffEasy.getMaxY()))
		{
			States.setState(handler.getGame().gameState);
			handler.getGame().setDifficulty(Difficulty.EASY);
		}
		else if(mouse.isPressed()&&mouse.inBoundary((int)diffMedium.getMinX() , (int)diffMedium.getMinY() , (int)diffMedium.getMaxX(), (int)diffMedium.getMaxY()))
		{
			States.setState(handler.getGame().gameState);
			handler.getGame().setDifficulty(Difficulty.MEDIUM);
		}	
		else if(mouse.isPressed() && mouse.inBoundary((int)diffHard.getMinX(), (int)diffHard.getMinY(), (int)diffHard.getMaxX(), (int)diffHard.getMaxX()))
		{
			States.setState(handler.getGame().gameState);
			handler.getGame().setDifficulty(Difficulty.HARD);
		}
		else if(mouse.isPressed() && mouse.inBoundary((int)instructionBox.getX(), (int)instructionBox.getY(), (int)instructionBox.getMaxX(), (int)instructionBox.getMaxY()))
			States.setState(handler.getGame().instructionsState);
			
		if(mouse.isPressed()==false){
			if(mouse.inBoundary((int)diffEasy.getX() , (int)diffEasy.getY() , (int)diffEasy.getX()+boxWidth, (int)diffEasy.getY()+boxHeight))
				easyState = State.HOVERED;
			else if(mouse.inBoundary((int)diffMedium.getX() , (int)diffMedium.getY() , (int)diffMedium.getX()+boxWidth, (int)diffMedium.getY()+boxHeight))
				mediumState = State.HOVERED;
			else if(mouse.inBoundary((int)diffHard.getX() , (int)diffHard.getY() , (int)diffHard.getX()+boxWidth, (int)diffHard.getY()+boxHeight))
				hardState = State.HOVERED;
			else if(mouse.inBoundary((int)quit.getX()+10 , (int)quit.getY()+10 , (int)quit.getX()+boxWidth-20, (int)(quit.getY()*1.5+boxHeight-20)))
				quitState = State.HOVERED;
			else if(mouse.inBoundary((int)instructionBox.getX() + 10, (int)instructionBox.getY() + 10, (int)instructionBox.getMaxX()-10, (int)instructionBox.getMaxY()-10))
				instructionState = State.HOVERED;
			else
				instructionState = quitState = easyState = mediumState = hardState = State.RELEASED;
		}else{
			if(mouse.inBoundary((int)diffEasy.getX() , (int)diffEasy.getY() , (int)diffEasy.getX()+boxWidth, (int)diffEasy.getY()+boxHeight))
				easyState = State.PRESSED;
			else if(mouse.inBoundary((int)diffMedium.getX() , (int)diffMedium.getY() , (int)diffMedium.getX()+boxWidth, (int)diffMedium.getY()+boxHeight))
				mediumState = State.PRESSED;
			else if(mouse.inBoundary((int)diffHard.getX() , (int)diffHard.getY() , (int)diffHard.getX()+boxWidth, (int)diffHard.getY()+boxHeight))
				hardState = State.PRESSED;
			else if(mouse.inBoundary((int)quit.getX()+10 , (int)quit.getY()+10 , (int)quit.getX()+boxWidth-20, (int)(quit.getY()+boxHeight*1.5-20)))
				quitState = State.PRESSED;
			else if(mouse.inBoundary((int)instructionBox.getX() + 10, (int)instructionBox.getY() + 10, (int)instructionBox.getMaxX()-10, (int)instructionBox.getMaxY()-10))
				instructionState = State.PRESSED;
		}
	}
	
	public void render(Graphics g){
		g.setColor(Color.DARK_GRAY);
		((Graphics2D)g).fill(quit);
		((Graphics2D)g).fill(diffBox);
		((Graphics2D)g).fill(instructionBox);
		
		//draws KarioMart on screen
		int tempWidth, tempHeight;
		myFont = myFont.deriveFont(100f);
		g.setFont(myFont);
		tempWidth = g.getFontMetrics(myFont).stringWidth("Kario Mart");
		g.drawString("Kario Mart", handler.getDisplay().getWidth()/2 - tempWidth/2 , handler.getDisplay().getHeight()/10);
		g.drawLine(handler.getDisplay().getWidth()/2 - tempWidth/2 , handler.getDisplay().getHeight()/10+20, handler.getDisplay().getWidth()/2 + tempWidth/2 , handler.getDisplay().getHeight()/10+20);
		myFont = myFont.deriveFont(50f);
		g.setFont(myFont);
		
		g.setColor(released);
		if(easyState == State.RELEASED)
			((Graphics2D)g).fill(diffEasy);
		if(mediumState == State.RELEASED)
			((Graphics2D)g).fill(diffMedium);
		if(hardState == State.RELEASED)
			((Graphics2D)g).fill(diffHard);
		if(quitState == State.RELEASED)
			((Graphics2D)g).fill(new Rectangle((int)quit.getX()+10, (int)quit.getY()+10, boxWidth-20, (int)(boxHeight*1.5-20)));
		if(instructionState == State.RELEASED)
			((Graphics2D)g).fill(new Rectangle((int)instructionBox.getX()+10, (int)instructionBox.getY()+10, (int)instructionBox.getWidth()-20, (int)(instructionBox.getHeight()-20)));
		
		
		g.setColor(hovered);
		if(easyState == State.HOVERED)
			((Graphics2D)g).fill(diffEasy);
		if(mediumState == State.HOVERED)
			((Graphics2D)g).fill(diffMedium);
		if(hardState == State.HOVERED)
			((Graphics2D)g).fill(diffHard);
		if(quitState == State.HOVERED)
			((Graphics2D)g).fill(new Rectangle((int)quit.getX()+10, (int)quit.getY()+10, boxWidth-20, (int)(boxHeight*1.5-20)));
		if(instructionState == State.HOVERED)
			((Graphics2D)g).fill(new Rectangle((int)instructionBox.getX()+10, (int)instructionBox.getY()+10, (int)instructionBox.getWidth()-20, (int)(instructionBox.getHeight()-20)));
		
		
		
		g.setColor(pressed);
		if(easyState == State.PRESSED)
			((Graphics2D)g).fill(diffEasy);
		if(mediumState == State.PRESSED)
			((Graphics2D)g).fill(diffMedium);
		if(hardState == State.PRESSED)
			((Graphics2D)g).fill(diffHard);
		if(quitState == State.PRESSED)
			((Graphics2D)g).fill(new Rectangle((int)quit.getX()+10, (int)quit.getY()+10, boxWidth-20, (int)(boxHeight*1.5-20)));
		if(instructionState == State.PRESSED)
			((Graphics2D)g).fill(new Rectangle((int)instructionBox.getX()+10, (int)instructionBox.getY()+10, (int)instructionBox.getWidth()-20, (int)(instructionBox.getHeight()-20)));
		
		g.setColor(Color.WHITE);
		tempHeight = g.getFontMetrics(myFont).getHeight();
		tempWidth = g.getFontMetrics(myFont).stringWidth("Easy");
		g.drawString("Easy", (int)diffEasy.getCenterX() - tempWidth / 2, (int)diffEasy.getCenterY() + tempHeight / 4);
		tempWidth = g.getFontMetrics(myFont).stringWidth("Medium");
		g.drawString("Medium", (int)diffMedium.getCenterX() - tempWidth / 2, (int)diffMedium.getCenterY() + tempHeight / 4);
		tempWidth = g.getFontMetrics(myFont).stringWidth("Hard");
		g.drawString("Hard", (int)diffHard.getCenterX() - tempWidth / 2, (int)diffHard.getCenterY() + tempHeight / 4);
		tempWidth = g.getFontMetrics(myFont).stringWidth("Quit");
		g.drawString("Quit", (int)quit.getCenterX()-tempWidth/2, (int)quit.getCenterY()+tempHeight/4);
		tempWidth = g.getFontMetrics(myFont).stringWidth("Instructions");
		g.drawString("Instructions", (int)instructionBox.getCenterX()-tempWidth/2, (int)instructionBox.getCenterY()+tempHeight/4);
	}
	private enum State{
		HOVERED,
		PRESSED,
		RELEASED;
	}
}