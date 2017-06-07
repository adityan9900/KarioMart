package game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.entities.drivers.CPU;
import game.entities.drivers.Player;
import game.worlds.Handler;
import game.worlds.World;

public class GameState extends States {
	
	private Player player;
	private World world;
	private CPU cpu;
	private int timer;
	private boolean isStarted;
	
	public GameState(Handler h) {
		super(h);
		world = new World("temp", 5); //Unfinished
		h.setWorld(world);
		player = new Player(h, handler.getWorld().getSpawnX(), world.getSpawnY(),
							world.getPlayerWidth(), world.getPlayerHeight());
		cpu = new CPU(handler.getWorld(), (float)(handler.getWorld().getSpawnX()), (float)(handler.getWorld().getSpawnY()), handler.getWorld().getPlayerWidth(), handler.getWorld().getPlayerHeight());
		timer = 0; isStarted = false;
	}
	
	protected void initScreen() {
		handler.resize(world.getMapWidth(), world.getMapHeight());
	}
	
	public void tick() {
		if(isStarted) {
			world.tick();
			player.tick();
			cpu.tick();
		}
		if(timer < 5 * handler.getFPS() + handler.getFPS() / 2) timer ++;
		if(timer == 5 * handler.getFPS()) isStarted = true;
	}
	public void render(Graphics g) {
		world.render(g);
		player.render(g);
		cpu.render(g);
		if(!isStarted || timer < 5 * handler.getFPS() + handler.getFPS() / 2) {
			g.setFont(new Font(Font.SERIF, Font.PLAIN, 200));
			g.setColor(Color.WHITE);
			startingSequence(g);
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
}
