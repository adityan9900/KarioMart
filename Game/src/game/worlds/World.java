package game.worlds;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

import game.gfx.ImageLoader;
import game.utils.Utils;

public class World {
	
	private ArrayList<int[]> centerPath;
	private ArrayList<int[]> innerPath;
	private ArrayList<int[]> outerPath;
	
	private BufferedImage bkg;
	private String trackName;
	private int trackWidth = 80;
	private int spawnX, spawnY, playerW, playerH;
	private int mapWidth;
	private int mapHeight;
	private int border;
	
	private ArrayList spawnPoints = new ArrayList<Point>();
	
	public World(String name, int width) {
		centerPath = new ArrayList<int[]>();
		innerPath = new ArrayList<int[]>();
		outerPath = new ArrayList<int[]>();
		
		trackName = name;
		trackWidth = width;
		loadWorld();
		setPath(getTrackName() + "TrackInner.txt",0);
		setPath(getTrackName() + "TrackMid.txt",1);
		setPath(getTrackName() + "TrackOuter.txt",2);
		
	}
	
	
	//will determine if car is on the track based on the current pos(x,y)
	public boolean insideTrack(float x, float y) {
		trackWidth = 85;
		ArrayList<Double> distsFromInner = new ArrayList<Double>();
		for(int [] i : innerPath) distsFromInner.add(Math.sqrt((x - i[0])*(x - i[0]) + (y - i[1])*(y - i[1])));
		
		ArrayList<Double> distsFromOuter = new ArrayList<Double>();
		for(int [] i : outerPath) distsFromOuter.add(Math.sqrt((x - i[0])*(x - i[0]) + (y - i[1])*(y - i[1])));
		
		Collections.sort(distsFromInner);
		Collections.sort(distsFromOuter);
		
		
	//	System.out.println("distsFromInner: " + distsFromInner.get(0) + "\tdistsFromOuter: " + distsFromOuter.get(0));
		
		if(distsFromInner.get(0) < trackWidth && distsFromOuter.get(0) < trackWidth) {
		//	System.out.println("True activated");
			return true;
		}
		else return false;
		
		
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
	
	
	public ArrayList<int[]> getPath(int i) {
		if(i == 0) return innerPath;
		else if(i == 1) return centerPath;
		else return outerPath;
	}
	public void tick() {
		
	}
	
	public int getMapWidth() {
		return mapWidth;
	}
	public int getMapHeight() {
		return mapHeight;
	}
	public int getMapBorder() {
		return border;
	}
	public void render(Graphics g) {
		g.drawImage(bkg, 0, 0, null);
	}
	public String getTrackName() {
		return trackName;
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
		border = Utils.parseInt(tokens[6]);
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
