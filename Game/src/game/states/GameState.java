package game.states;

import java.awt.Graphics;

import game.entities.drivers.CPU;
import game.entities.drivers.Player;
import game.worlds.Handler;
import game.worlds.World;

public class GameState extends States {
	
	private int difficulty;
	private Player player;
	private World world;
	private CPU cpu;

	
	public GameState(Handler h) {
		super(h);
		this.difficulty = difficulty;
		world = new World("temp", 5); //Unfinished
		h.setWorld(world);
		player = new Player(h, handler.getWorld().getSpawnX(), world.getSpawnY(),
							world.getPlayerWidth(), world.getPlayerHeight());
		cpu = new CPU(handler.getWorld(), (float)(handler.getWorld().getSpawnX()), (float)(handler.getWorld().getSpawnY()), handler.getWorld().getPlayerWidth(), handler.getWorld().getPlayerHeight());
	}
	
	protected void initScreen() {
//		game.width = world.getMapWidth();
//		game.height = world.getMapHeight();
		handler.getDisplay().resize(world.getMapWidth(), world.getMapHeight());
	}
	
	public void tick() {
		world.tick();
//		checkCollision();
		player.tick();
		cpu.tick();
	}
	public void render(Graphics g) {
		world.render(g);
		player.render(g);
		cpu.render(g);
	}
	
//	private void checkCollision() {
//		if(true || (player.getX() * Math.cos(player.getTheta()) - 
//								  player.getY() * Math.sin(player.getTheta())) <= 0) {
//			System.out.println((player.getX() * Math.cos(player.getTheta()) - player.getY() * Math.sin(player.getTheta())));
//		}
//	}
}
