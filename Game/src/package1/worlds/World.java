package package1.worlds;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import package1.gfx.ImageLoader;
import package1.utils.Utils;

public class World {
	
	private ArrayList<int[]> path;
	private BufferedImage bkg;
	private String trackName;
	private int trackWidth;
	private int spawnX, spawnY, playerW, playerH;
	private int mapWidth;
	private int mapHeight;
	
	public World(String name, int width) {
		path = new ArrayList<int[]>();
		trackName = name;
		trackWidth = width;
		loadWorld();
	}
	
	public void tick() {
		
	}
	
	public int getMapWidth() {
		return mapWidth;
	}
	public int getMapHeight() {
		return mapHeight;
	}
	
	public void render(Graphics g) {
		g.drawImage(bkg, 0, 0, null);
	}
	
	private void loadWorld() {
		bkg = ImageLoader.loadImage("/worldGen/" + trackName + ".png");
		String file = Utils.loadFileAsString("res/worldGen/" + trackName + ".txt");
		String[] tokens = file.split("\\s+");
//		for(int i = 0; i < tokens.length; i ++) {
//			System.out.println(tokens[i]);
//		}
		mapWidth = Utils.parseInt(tokens[0]);
		mapHeight = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		playerW = Utils.parseInt(tokens[4]);
		playerH = Utils.parseInt(tokens[5]);
	}
	
	public int getSpawnX() {
		return spawnX;
	}
	public int getSpawnY() {
		return spawnY;
	}
	public int getPlayerWidth() {
		return playerW;
	}
	public int getPlayerHeight() {
		return playerH;
	}
}
