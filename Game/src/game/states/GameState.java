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
	private final int PAUSE_SIDE_LENGTH = 100;
	private final int OFFSET = 20;
	private State pauseState;
	private Color released,hovered,pressed;
	private Rectangle pause;
	
	public GameState(Handler h) {
		super(h);
		isPressed = false;
		pauseState = State.RELEASED;
		world = new World("temp", 5); //Unfinished
		h.setWorld(world);
		released = new Color(180,180,180);
		hovered = new Color(160,160,160);
		pressed = new Color(140,140,140);
		pause = new Rectangle(OFFSET, OFFSET, PAUSE_SIDE_LENGTH, PAUSE_SIDE_LENGTH);
		mouse = h.getMouseManager();
		player = new Player(h, handler.getWorld().getSpawnX(), world.getSpawnY(),
							world.getPlayerWidth(), world.getPlayerHeight());
		cpu = new CPU(handler.getWorld(), (float)(handler.getWorld().getSpawnX()), (float)(handler.getWorld().getSpawnY()), handler.getWorld().getPlayerWidth(), handler.getWorld().getPlayerHeight());
		timer = 0; isStarted = false; isPaused = false;
	}
	
	protected void initScreen() {
		handler.resize(world.getMapWidth(), world.getMapHeight());
	}
	
	public void tick() {
		checkPause();
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
		
		drawPause(g);
		
		if((!isStarted || timer < 5 * handler.getFPS() + handler.getFPS() / 2) && !isPaused) {
			g.setFont(new Font(Font.SERIF, Font.PLAIN, 200));
			g.setColor(Color.WHITE);
			startingSequence(g);
		}
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
			g.drawLine((int)pause.getX() + 20, (int)pause.getY() + 20, (int)pause.getX() + 20, (int)pause.getY() + PAUSE_SIDE_LENGTH - 20);
			g.drawLine((int)pause.getX() + 20, (int)pause.getY() + 20, (int)pause.getX() + PAUSE_SIDE_LENGTH - 20, (int)pause.getY() + PAUSE_SIDE_LENGTH / 2);
			g.drawLine((int)pause.getX() + 20, (int)pause.getY() + PAUSE_SIDE_LENGTH - 20, (int)pause.getX() + PAUSE_SIDE_LENGTH - 20, (int)pause.getY() + PAUSE_SIDE_LENGTH / 2);
		} else {
			g.drawRect(2 * PAUSE_SIDE_LENGTH / 5, (int)pause.getY() + 20, PAUSE_SIDE_LENGTH / 5, PAUSE_SIDE_LENGTH - 40);
			g.drawRect(4 * PAUSE_SIDE_LENGTH / 5, (int)pause.getY() + 20, PAUSE_SIDE_LENGTH / 5, PAUSE_SIDE_LENGTH - 40);
		}
	}
	
	//Checks if paused button is clicked or not
	private void checkPause() {
		if(!mouse.isPressed()) {
			isPressed = false;
			if(mouse.inBoundary((int)pause.getX(), (int)pause.getY(), 
					(int)pause.getX() + PAUSE_SIDE_LENGTH, (int)pause.getY() + PAUSE_SIDE_LENGTH)) {
				pauseState = State.HOVERED;
			} else {
				pauseState = State.RELEASED;
			}
		} else {
			if(mouse.inBoundary((int)pause.getX(), (int)pause.getY(), 
					(int)pause.getX() + PAUSE_SIDE_LENGTH, (int)pause.getY() + PAUSE_SIDE_LENGTH) && !isPressed) {
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
