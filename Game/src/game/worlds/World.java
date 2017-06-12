package game.worlds;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import game.gfx.ImageLoader;
import game.utils.Utils;

public class World {
	
	private ArrayList<double[]> centerPath;
	private ArrayList<double[]> centerPath2;
	private ArrayList<double[]> centerPath3;
	private ArrayList<double[]> centerPath4;
	private ArrayList<double[]> centerPath5;
	private ArrayList<double[]> innerPath;
	private ArrayList<double[]> outerPath;
	private ArrayList<double[]> checkpoints;
	private final int NUM_LAPS = 3;
	
	private BufferedImage bkg;
	private String trackName;
	private int trackWidth = 80;
	private int spawnX, spawnY, playerW, playerH;
	private int mapWidth;
	private int mapHeight;
	private int border;
	private double[] finishLine;
	
	private ArrayList spawnPoints = new ArrayList<Point>();
	
	public World(String name, int width) {
		finishLine = new double[4];
		
		centerPath = new ArrayList<double[]>();
		centerPath2 = new ArrayList<double[]>();
		centerPath3 = new ArrayList<double[]>();
		centerPath4 = new ArrayList<double[]>();
		centerPath5 = new ArrayList<double[]>();
		innerPath = new ArrayList<double[]>();
		outerPath = new ArrayList<double[]>();
		checkpoints = new ArrayList<double[]>();
		
		trackName = name;
		trackWidth = width;
		loadWorld();
		setPath(getTrackName() + "TrackInner.txt",0);
		setPath(getTrackName() + "TrackMid.txt",1);
		setPath(getTrackName() + "TrackOuter.txt",2);
		setPath(getTrackName() + "TrackMid.txt",4);
		setPath(getTrackName() + "TrackMid.txt",5);
		setPath(getTrackName() + "TrackMid.txt",6);
		initializeCheckpoints();
	}
	
	private void initializeCheckpoints() {
		for(int i = 0; i < NUM_LAPS; i ++) {
			for(int j = 0; j < centerPath.size(); j += 6) {
				checkpoints.add(centerPath.get(j));
			}
		}
		double[] finish = new double[2];
		finish[0] = finishLine[0];
		finish[1] = finishLine[1];
		checkpoints.add(finish);
		double[] finish2 = new double[2];
		finish2[0] = finishLine[2];
		finish2[1] = finishLine[3];
		checkpoints.add(finish2);
	}
	
	//will determine if car is on the track based on the current pos(x,y)
	public boolean insideTrack(float x, float y) {
		trackWidth = 85;
		ArrayList<Double> distsFromInner = new ArrayList<Double>();
		for(double [] i : innerPath) distsFromInner.add(Math.sqrt((x - i[0])*(x - i[0]) + (y - i[1])*(y - i[1])));
		
		ArrayList<Double> distsFromOuter = new ArrayList<Double>();
		for(double [] i : outerPath) distsFromOuter.add(Math.sqrt((x - i[0])*(x - i[0]) + (y - i[1])*(y - i[1])));
		
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
			Random rand = new Random();
			
			while(true) {
				currentLine = r.readLine();
				if(currentLine == null) break;
				String [] a = currentLine.split(",");
				
				int x = Integer.parseInt(a[0]);
				int y = Integer.parseInt(a[1]);
				
				double [] w = new double[2];
				if(i == 1) {
					w[0] = 1.0 * rand.nextGaussian() + x + 15;
					w[1] = 1.0 * rand.nextGaussian() + y;
				}
				
				
				
				else if(i == 4) {
					w[0] = 1.0 * rand.nextGaussian() + x - 30;
					w[1] = 1.0 * rand.nextGaussian() + y + 80 ;
				}
				
				else if(i == 5) {
					w[0] = 1.0 * rand.nextGaussian() + x + 5;
					w[1] = 1.0 * rand.nextGaussian() + y - 45;
				}
				
				else if(i == 6) {
					w[0] = 1.0 * rand.nextGaussian() + x - 20;
					w[1] = 1.0 * rand.nextGaussian() + y - 45;
				}
				
				
				else {
					w[0] = x;
					w[1] = y;
				}
				
				if(i == 0) innerPath.add(w);
				else if(i == 1) centerPath.add(w);
				else if(i == 2) outerPath.add(w);
				else if(i == 4) centerPath2.add(w);
				else if(i == 5) centerPath3.add(w);
				else if(i == 6) centerPath4.add(w);
			}
		}
		catch(Exception e) {
			System.out.println("Failure in processing!");
		}
	}
	
	
	public ArrayList<double[]> getPath(int i) {
		if(i == 0) return innerPath;
		else if(i == 1) return centerPath;
		else if(i == 2) return outerPath;
		else if(i == 4) return centerPath2;
		else if(i == 5) return centerPath3;
		else if(i == 6) return centerPath4;
		else return null;
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
		g.setColor(Color.RED);
		if(checkpoints.size() > 2) {
			g.drawOval((int)(checkpoints.get(0)[0]- trackWidth / 2), (int)(checkpoints.get(0)[1] - trackWidth / 2), trackWidth, trackWidth);
		} else if (checkpoints.size() == 2) {
			g.drawLine((int)checkpoints.get(0)[0], (int)checkpoints.get(0)[1], (int)checkpoints.get(1)[0], (int)checkpoints.get(1)[1]);
		}
	}
	public String getTrackName() {
		return trackName;
	}
	public boolean isInside(double playerX, double playerY, int playerW, int playerH) {
		if(checkpoints.size() > 2) {
			if(!isFinished()) {
				return Math.sqrt(Math.pow(playerX - checkpoints.get(0)[0], 2) + Math.pow(playerY - checkpoints.get(0)[1], 2)) < trackWidth;
			} return false;
		} else {
			int minX = (int) (playerX - playerW / 2);
			int maxX = (int) (playerX + playerW / 2);
			int maxY = (int) (playerY + playerH / 2);
			int minY = (int) (playerY - playerH / 2);
			
			if(checkpoints.get(0)[1] >= minY && checkpoints.get(0)[1] <= maxY) {
				if(minX >= checkpoints.get(0)[0] && minX <= checkpoints.get(1)[0]) return true;
				else if(maxX >= checkpoints.get(0)[0] && maxX <= checkpoints.get(1)[0]) return true;
			} return false;
		}
	}
	public void removeCheckpoint() {
		if(!isFinished() && checkpoints.size() > 2) {
			checkpoints.remove(0);
		} else {
			checkpoints.remove(0);
			checkpoints.remove(0);
		}
	}
	public boolean isFinished() {
		return checkpoints.isEmpty();
	}
	
	private void loadWorld() {
		bkg = ImageLoader.loadImage("/worldGen/" + trackName + ".png");
		String file = Utils.loadFileAsString("res/worldGen/" + trackName + ".txt");
		String[] tokens = file.split("\\s+");
		mapWidth = Utils.parseInt(tokens[0]);
		mapHeight = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		playerW = Utils.parseInt(tokens[4]);
		playerH = Utils.parseInt(tokens[5]);
		border = Utils.parseInt(tokens[6]);
		finishLine[0] = (double)Utils.parseInt(tokens[7]);
		finishLine[1] = (double)Utils.parseInt(tokens[8]);
		finishLine[2] = (double)Utils.parseInt(tokens[9]);
		finishLine[3] = (double)Utils.parseInt(tokens[10]);
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


