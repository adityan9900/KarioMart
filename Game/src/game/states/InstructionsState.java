package game.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import game.input.MouseManager;
import game.worlds.Handler;

public class InstructionsState extends States {

	private final int OFFSET = 20;
	private final int BOX_SIDE_LENGTH = 50;
	
	private State backState;
	private Rectangle back;
	private Color released,hovered,pressed;
	private MouseManager mouse;
	
	public InstructionsState(Handler h) {
		super(h);
		mouse = h.getMouseManager();
		released = new Color(180,180,180);
		hovered = new Color(160,160,160);
		pressed = new Color(140,140,140);
		back = new Rectangle(OFFSET, OFFSET, BOX_SIDE_LENGTH, BOX_SIDE_LENGTH);
	}
	
	public void tick() {
		if(!mouse.isPressed()) {
			if(mouse.inBoundary((int)back.getX(), (int)back.getY(), 
					(int)back.getX() + BOX_SIDE_LENGTH, (int)back.getY() + BOX_SIDE_LENGTH)) {
				backState = State.HOVERED;
			} else 
				backState = State.RELEASED;
		} else {
			if(mouse.inBoundary((int)back.getX(), (int)back.getY(), 
					(int)back.getX() + BOX_SIDE_LENGTH, (int)back.getY() + BOX_SIDE_LENGTH)) {
				handler.getGame().gameState = new GameState(handler);
				States.setState(handler.getGame().menuState);
				backState = State.PRESSED;
			}
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		((Graphics2D)g).fill(back);
		g.setColor(released);
		if(backState == State.RELEASED)
			((Graphics2D)g).fill(back);
		g.setColor(hovered);
		if(backState == State.HOVERED)
			((Graphics2D)g).fill(back);
		g.setColor(pressed);
		if(backState == State.PRESSED)
			((Graphics2D)g).fill(back);
		g.setColor(Color.WHITE);
		g.drawLine((int)back.getX() + BOX_SIDE_LENGTH / 5, (int)back.getY() + BOX_SIDE_LENGTH / 2, (int)back.getX() + BOX_SIDE_LENGTH / 2, (int)back.getY() + 4 * BOX_SIDE_LENGTH / 5);
		g.drawLine((int)back.getX() + BOX_SIDE_LENGTH / 5, (int)back.getY() + BOX_SIDE_LENGTH / 2, (int)back.getX() + BOX_SIDE_LENGTH / 2, (int)back.getY() + BOX_SIDE_LENGTH / 5);
		g.drawLine((int)back.getX() + BOX_SIDE_LENGTH / 5, (int)back.getY() + BOX_SIDE_LENGTH / 2, (int)back.getX() + 4 * BOX_SIDE_LENGTH / 5, (int)back.getY() + BOX_SIDE_LENGTH / 2);
	}
	
	protected void initScreen() {
		
	}
	
	private enum State{
		HOVERED,
		PRESSED,
		RELEASED;
	}
	
}
