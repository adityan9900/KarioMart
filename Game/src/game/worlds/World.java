package game.worlds;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import game.gfx.ImageLoader;
import game.utils.Utils;

public class World {
	
	private ArrayList<int[]> centerPath;
	private ArrayList<int[]> innerPath;
	private ArrayList<int[]> outerPath;
	
	private BufferedImage bkg;
	private String trackName;
	private int trackWidth;
	private int spawnX, spawnY, playerW, playerH;
	private int mapWidth;
	private int mapHeight;
	
	public World(String name, int width) {
		centerPath = new ArrayList<int[]>();
		innerPath = new ArrayList<int[]>();
		outerPath = new ArrayList<int[]>();
		
		trackName = name;
		trackWidth = width;
		loadWorld();
	}
	
	//i = 0 --> inner, i = 1 --> center, i = 2 --> outer
	public void setPath(String csv, int i) {
		try {
			BufferedReader r = new BufferedReader(new FileReader(csv));
			String currentLine = "";
			while(true) {
				currentLine = r.readLine();
				if(currentLine == null) break;
				String [] a = currentLine.split(",");
				
				int x = Integer.parseInt(a[0]);
				int y = Integer.parseInt(a[1]);
				
				int [] w = new int[2];
				w[0] = x;
				w[1] = y;
				
				if(i == 0) innerPath.add(w);
				else if(i == 1) centerPath.add(w);
				else outerPath.add(w);
			}
		}
		catch(Exception e) {
			System.out.println("Failure in processing!");
		}
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
