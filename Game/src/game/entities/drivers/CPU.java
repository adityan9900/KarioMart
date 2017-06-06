package game.entities.drivers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.Game;
import game.gfx.Assets;
import game.worlds.World;

public class CPU extends Drivers {
	
	private World world;
	private ArrayList<int[]> midArry;
	private int midIndex;
	private int i;
	
	
	public CPU(World w, float x, float y, int width, int height) {
		super(x, y, width, height);
		this.world = w;
		
		world.setPath("DUMMY STRING", 1);
		this.midArry = world.getPath(1); //get center path
	}
	
	
	
	public void tick() {
		
		if(i % 60 == 0 && midIndex < midArry.size()) {
			int [] a = midArry.get(midIndex++);
			this.x = a[0];
			this.y = a[1];
		}
		i ++;
	}
	
	//Copied from different class
	public void render(Graphics g) {
		
		
		Graphics2D g2d = (Graphics2D)g;

		//original transform
		AffineTransform origAT = g2d.getTransform();
 
		//car being painted
		BufferedImage car = Assets.blue;		
 
		AffineTransform rot = new AffineTransform();
		//spins the car with theta, and x coord(center of car) + y coord(center of car)
 
		rot.rotate(theta,x + width / 2, y + height/2);
		g2d.setTransform(rot); 
 
		//draws the car on the screen
		g2d.drawImage(car, (int)x, (int)y, width, height, null);
		g2d.drawString("ai", x, y);
 
		//reverts the transform to original
		g2d.setTransform(origAT);
 
 
	}
}
