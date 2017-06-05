package game.states;

import java.awt.Graphics;

import display.Display;
import game.Game;
import game.entities.drivers.Player;
import game.worlds.World;

public class GameState extends States {
	
	private Player player;
	private World world;
	
	public GameState(Game g) {
		super(g);
		world = new World("temp", 5); //Unfinished
		player = new Player(g, world.getSpawnX(), world.getSpawnY(),
							world.getPlayerWidth(), world.getPlayerHeight());
	}
	
	protected void initScreen() {
		game.width = world.getMapWidth();
		game.height = world.getMapHeight();
		game.disp.resize(world.getMapWidth(), world.getMapHeight());
	}
	
	public void tick() {
		world.tick();
//		checkCollision();
		player.tick();
	}
	public void render(Graphics g) {
		world.render(g);
		player.render(g);
	}
	
//	private void checkCollision() {
//		if(true || (player.getX() * Math.cos(player.getTheta()) - 
//								  player.getY() * Math.sin(player.getTheta())) <= 0) {
//			System.out.println((player.getX() * Math.cos(player.getTheta()) - player.getY() * Math.sin(player.getTheta())));
//		}
//	}
}
