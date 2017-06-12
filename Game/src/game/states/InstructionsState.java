package game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import game.entities.drivers.Player;
import game.input.MouseManager;
import game.worlds.Handler;

public class InstructionsState extends States {

	private final int OFFSET = 20;
	private final int BOX_SIDE_LENGTH = 50;
	
	private State backState;
	private Rectangle back;
	private Color released,hovered,pressed;
	private MouseManager mouse;
	
	private Player practicePlayer1;
	private Player practicePlayer2;
	
	public InstructionsState(Handler h) {
		super(h);
		practicePlayer1 = new Player(h, 200, 400, 100, 210);
		practicePlayer2 = new Player(h, OFFSET, 400, 100, 210);
		practicePlayer1.setAccel(0);
		practicePlayer2.setTurnPwr(0);
		mouse = h.getMouseManager();
		released = new Color(180,180,180);
		hovered = new Color(160,160,160);
		pressed = new Color(140,140,140);
		back = new Rectangle(OFFSET / 2, OFFSET, BOX_SIDE_LENGTH, BOX_SIDE_LENGTH);
	}
	
	public void tick() {
		practicePlayer1.setSpeed(0);
		practicePlayer1.tick();
		if(practicePlayer2.getY() <= 300 && practicePlayer2.getSpeed() > 0) {
			practicePlayer2.setSpeed(-0.1 * practicePlayer2.getSpeed());
			practicePlayer2.setY(300);
		}
		 else if (practicePlayer2.getY() >= 500 && practicePlayer2.getSpeed() < 0) {
			practicePlayer2.setSpeed(-0.1 * practicePlayer2.getSpeed());
			practicePlayer2.setY(500);
		 }
		practicePlayer2.tick();
		if(!mouse.isPressed()) {
			if(mouse.inBoundary((int)back.getX(), (int)back.getY(), 
					(int)back.getX() + BOX_SIDE_LENGTH, (int)back.getY() + BOX_SIDE_LENGTH)) {
				backState = State.HOVERED;
			} else 
				backState = State.RELEASED;
		} else {
			if(mouse.inBoundary((int)back.getX(), (int)back.getY(), 
					(int)back.getX() + BOX_SIDE_LENGTH, (int)back.getY() + BOX_SIDE_LENGTH)) {
				States.setState(handler.getGame().menuState);
				backState = State.PRESSED;
			}
		}
	}

	public void render(Graphics g) {
		drawKey(g);
		drawInstructions(g);
		g.setColor(Color.GRAY);
		int stringH = g.getFontMetrics().getHeight();
		g.drawRect(5, 300 - OFFSET - stringH / 4 , 3 * practicePlayer2.getWidth() + 5 *  OFFSET - 10, practicePlayer2.getHeight() + 280 + 2 * OFFSET);
		g.setColor(Color.RED);
		practicePlayer1.render(g);
		practicePlayer2.render(g);
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
	
	private void drawInstructions(Graphics g) {
		Font myFont = new Font(Font.SERIF, Font.PLAIN, 70);
		g.setFont(myFont);
		g.setColor(Color.DARK_GRAY);
		g.drawString("Rules", 450, 120);
		myFont = myFont.deriveFont(20f);
		g.setFont(myFont);
		int stringH = g.getFontMetrics().getHeight();
		g.drawString("Objective: clear all checkpoints", 450, 160);
		g.drawString("by driving through them and finish", 450, 160 + stringH);
		g.drawString("faster than the CPU controlled cars", 450, 160 + 2 * stringH);
		
		g.drawString("*NOTE*", 450, 160 + 4 * stringH);
		g.drawString("Cars move faster while on the track", 450, 160 + 5 * stringH);
	}
	
	private void drawKey(Graphics g) {
		Font myFont = new Font(Font.SERIF, Font.PLAIN, 30);
		g.setFont(myFont);
		int stringH = g.getFontMetrics().getHeight();
		g.drawRect(OFFSET / 2, 2 * OFFSET + 2 * stringH / 2, 300, 120);
		g.drawString("Key", OFFSET, 2 * OFFSET + BOX_SIDE_LENGTH + stringH / 2);
		g.setColor(Color.RED);
		g.drawOval(OFFSET, 3 * OFFSET + BOX_SIDE_LENGTH + stringH / 2, BOX_SIDE_LENGTH, BOX_SIDE_LENGTH);
		g.drawString(" = Checkpoint", 2 * OFFSET + BOX_SIDE_LENGTH, (int) (3 * OFFSET + 1.75 * BOX_SIDE_LENGTH + stringH / 2));
		
		//wrong but W/E works the same
		myFont = myFont.deriveFont(20f);
		g.setFont(myFont);
		g.drawString("Use the up and down keys to move", 10, 750);
		g.drawString("and the left and right keys to turn!", 10, 750 + stringH);
	}
	
	protected void initScreen() {
		handler.resize(750, 850);
	}
	
	private enum State{
		HOVERED,
		PRESSED,
		RELEASED;
	}
	
}
