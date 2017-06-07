package game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import display.Display;
import game.gfx.Assets;
import game.input.KeyManager;
import game.input.MouseManager;
import game.states.GameState;
import game.states.MenuState;
import game.states.SettingState;
import game.states.States;
import game.worlds.Handler;

public class Game implements Runnable {
  
	public Display disp;
	private int width, height;
	private final int FPS = 60;

	public String title;
	
	private Thread thread; 
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics g;

	//Initialize the states
	public States gameState;
	public States menuState;
	public States settingState;
	
	//Input
	private KeyManager keyManager = new KeyManager();
	private MouseManager mouseManager = new MouseManager();
	
	//Handler
	private Handler handler;
	
	//game difficulty variable
	Difficulty difficulty = null;
	
	public Game(String t, int w, int h) {
		width = w;
		height = h;
		title = t;
	}
	
	private void init() {
		disp = new Display(title, width, height);
		disp.getFrame().addKeyListener(keyManager);
		disp.getFrame().addMouseListener(mouseManager);
		disp.getFrame().addMouseMotionListener(mouseManager);
		disp.getCanvas().addMouseListener(mouseManager);
		disp.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();
		
		handler = new Handler(this);
		
		settingState = new SettingState(handler);
		menuState = new MenuState(handler);
		gameState = new GameState(handler);
		States.setState(menuState);
	}
	
	private void tick() {
		keyManager.tick();
		if(gameState != null||menuState!=null||settingState!=null) {
			States.getState().tick();
		}
	}
	
	private void render() {
		bs = disp.getCanvas().getBufferStrategy();
		if(bs == null) {
			disp.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen!
		g.clearRect(0, 0, width, height);
		
		//Draw Here!
		
		if(gameState != null||menuState!=null||settingState!=null) {
			States.getState().render(g);
		}
		
		//End Drawing!
		bs.show();
		g.dispose();
		
	}
	public synchronized void start() {
		if(running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	public synchronized void stop() {
		if(!running) return;
		running = false;
		try{
			thread.join();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public void resize(int w, int h) {
		width = w;
		height = h;
	}
	public int getFPS() {
		return FPS;
	}
	public Display getDisplay() {
		return disp;
	}
	public KeyManager getKeyManager() {
		return keyManager;
	}
	public void setDifficulty(Difficulty d){
		this.difficulty = d;
	}
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	
	
	public void run() {
		
		init();
		double timePerTick = 1000000000 / FPS;
		double delta = 0;
		long now, lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				render();
				ticks ++;
				delta --;
			}
			if(timer >= 1000000000) {
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		stop();
	}
	public enum Difficulty{
		EASY,
		MEDIUM,
		HARD;
	}
}
