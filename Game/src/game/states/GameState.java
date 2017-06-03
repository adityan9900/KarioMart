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
		g.width = world.getMapWidth();
		g.height = world.getMapHeight();
		g.disp.resize(world.getMapWidth(), world.getMapHeight());
		player = new Player(g, world.getSpawnX(), world.getSpawnY(),
							world.getPlayerWidth(), world.getPlayerHeight());
	}
	
	public void tick() {
		world.tick();
		player.tick();
	}
	public void render(Graphics g) {
		world.render(g);
		player.render(g);
	}
}
