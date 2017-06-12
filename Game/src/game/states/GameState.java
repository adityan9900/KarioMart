package game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import game.entities.drivers.CPU;
import game.entities.drivers.Player;
import game.input.MouseManager;
import game.worlds.Handler;
import game.worlds.World;

public class GameState extends States {
	
	private Player player;
	private World world;
	private CPU cpu;
	private int timer;
	private boolean isStarted;
	private MouseManager mouse;
	private boolean isPaused, isPressed;
	private final int BOX_SIDE_LENGTH = 50;
	private final int OFFSET = 20;
	private State pauseState, backState;
	private Color released,hovered,pressed;
	private Rectangle pause, back;
	
	public GameState(Handler h) {
		super(h);
		isPressed = false;
		pauseState = State.RELEASED;
		backState = State.RELEASED;
		world = new World("temp", 5); //Unfinished
		h.setWorld(world);
		released = new Color(180,180,180);
		hovered = new Color(160,160,160);
		pressed = new Color(140,140,140);
		pause = new Rectangle(2 * OFFSET + BOX_SIDE_LENGTH, OFFSET, BOX_SIDE_LENGTH, BOX_SIDE_LENGTH);
		back = new Rectangle(OFFSET, OFFSET, BOX_SIDE_LENGTH, BOX_SIDE_LENGTH);
		mouse = h.getMouseManager();
		player = new Player(h, handler.getWorld().getSpawnX(), world.getSpawnY(),
							world.getPlayerWidth(), world.getPlayerHeight());
		cpu = new CPU(handler.getWorld(), handler, (float)(handler.getWorld().getSpawnX()), (float)(handler.getWorld().getSpawnY()), handler.getWorld().getPlayerWidth(), handler.getWorld().getPlayerHeight());
		timer = 0; isStarted = false; isPaused = false;
	}
	
	protected void initScreen() {
		handler.resize(world.getMapWidth(), world.getMapHeight());
	}
	
	public void tick() {
		checkPause();
		checkBack();
		if(isStarted) {
			world.tick();
			player.tick();
			cpu.tick();
		}
		if(timer < 5 * handler.getFPS() + handler.getFPS() / 2 && !isPaused) timer ++;
		if(timer == 5 * handler.getFPS()) isStarted = true;
		
	}
	public void render(Graphics g) {
		world.render(g);
		player.render(g);
		cpu.render(g);
		
		//Set font
		g.setFont(new Font(Font.SERIF, Font.PLAIN, 200));
		
		drawPause(g);
		drawBack(g);
		
		if((!isStarted || timer < 5 * handler.getFPS() + handler.getFPS() / 2) && !isPaused) {
			g.setColor(Color.WHITE);
			startingSequence(g);
		}
	}
	//draws everything for the back button
	private void drawBack(Graphics g) {
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
	
	//draws everything for the pause button
	private void drawPause(Graphics g) {
		//Pause button
		g.setColor(Color.DARK_GRAY);
		((Graphics2D)g).fill(pause);
		g.setColor(released);
		if(pauseState == State.RELEASED)
			((Graphics2D)g).fill(pause);
		g.setColor(hovered);
		if(pauseState == State.HOVERED)
			((Graphics2D)g).fill(pause);
		g.setColor(pressed);
		if(pauseState == State.PRESSED)
			((Graphics2D)g).fill(pause);
		g.setColor(Color.WHITE);
		if(isPaused) {
			g.drawLine((int)pause.getX() + BOX_SIDE_LENGTH / 5, (int)pause.getY() + BOX_SIDE_LENGTH / 5, (int)pause.getX() + BOX_SIDE_LENGTH / 5, (int)pause.getY() + 4 * BOX_SIDE_LENGTH / 5);
			g.drawLine((int)pause.getX() + BOX_SIDE_LENGTH / 5, (int)pause.getY() + BOX_SIDE_LENGTH / 5, (int)pause.getX() + 4 * BOX_SIDE_LENGTH / 5, (int)pause.getY() + BOX_SIDE_LENGTH / 2);
			g.drawLine((int)pause.getX() + BOX_SIDE_LENGTH / 5, (int)pause.getY() + 4 * BOX_SIDE_LENGTH / 5, (int)pause.getX() + 4 * BOX_SIDE_LENGTH / 5, (int)pause.getY() + BOX_SIDE_LENGTH / 2);
		} else {
			g.drawRect(BOX_SIDE_LENGTH / 5 + (int)pause.getX(), (int)pause.getY() + BOX_SIDE_LENGTH / 5, BOX_SIDE_LENGTH / 5, 3 * BOX_SIDE_LENGTH / 5);
			g.drawRect(3 * BOX_SIDE_LENGTH / 5 + (int)pause.getX(), (int)pause.getY() + BOX_SIDE_LENGTH/ 5, BOX_SIDE_LENGTH / 5, 3 * BOX_SIDE_LENGTH / 5);
		}
	}
	
	//Checks the position of the back button
	private void checkBack() {
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
	
	//Checks if paused button is clicked or not
	private void checkPause() {
		if(!mouse.isPressed()) {
			isPressed = false;
			if(mouse.inBoundary((int)pause.getX(), (int)pause.getY(), 
					(int)pause.getX() + BOX_SIDE_LENGTH, (int)pause.getY() + BOX_SIDE_LENGTH)) {
				pauseState = State.HOVERED;
			} else {
				pauseState = State.RELEASED;
			}
		} else {
			if(mouse.inBoundary((int)pause.getX(), (int)pause.getY(), 
					(int)pause.getX() + BOX_SIDE_LENGTH, (int)pause.getY() + BOX_SIDE_LENGTH) && !isPressed) {
				pauseState = State.PRESSED;
				isPressed = true;
				if(isPaused) {
					isPaused = false;
				} else {
					isStarted = false;
					timer = 0;
					isPaused = true;
				}
			}
		}
	}
	
	//Starting sequence number appear on the screen
	private void startingSequence(Graphics g) {
		int stringW = g.getFontMetrics().stringWidth("" + (5 - timer / handler.getFPS()));
		int stringH = g.getFontMetrics().getHeight();
		if(5 - timer / handler.getFPS() != 0)
			g.drawString("" + (5 - timer / handler.getFPS()), handler.getWidth() / 2 - stringW / 2, handler.getHeight() / 2 + stringH / 4);
		else {
			stringW = g.getFontMetrics().stringWidth("GO!");
			g.drawString("GO!", handler.getWidth() / 2 - stringW / 2, handler.getHeight() / 2 + stringH / 4);
		}
	}
	
	private enum State{
		HOVERED,
		PRESSED,
		RELEASED;
	}
}
