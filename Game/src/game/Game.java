package game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import display.Display;
import game.gfx.Assets;
import game.input.KeyManager;
import game.states.GameState;
import game.states.MenuState;
import game.states.SettingState;
import game.states.States;

public class Game implements Runnable {
  
	public Display disp;
	public int width, height;
	public String title;
	
	private Thread thread; 
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics g;

	//Initialize the states
	private States gameState;
	private States menuState;
	private States settingState;
	
	//Input
	private KeyManager keyManager = new KeyManager();
	
	public Game(String t, int w, int h) {
		width = w;
		height = h;
		title = t;
	}
	
	private void init() {
		disp = new Display(title, width, height);
		disp.getFrame().addKeyListener(keyManager);
		Assets.init();
		
		settingState = new SettingState(this);
		menuState = new MenuState(this);
		gameState = new GameState(this);
		States.setState(gameState);
	}
	
	private void tick() {
		keyManager.tick();
		if(gameState != null) {
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
		
		if(gameState != null) {
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
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
	public void run() {
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
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
}
