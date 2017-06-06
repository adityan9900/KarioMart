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
		
		world.setPath("Track_Mid.txt", 1);
		this.midArry = world.getPath(1); //get center path
	}
	
	
	
	public void tick() {
	
//		System.out.println("i % 10 results: " + (i%10 == 0));
			if(i % 10 == 0 && midIndex < midArry.size()-1) {
				int [] a = midArry.get(midIndex++);
				this.x = a[0];
				this.y = a[1];
				
				double dTheta;
				
				int [] b = a;
				if(midIndex < midArry.size()-1) {
					b = midArry.get(midIndex);
				}
				
				
				if(b[1] - a[1] == 0) dTheta = 0;
				else dTheta = Math.atan((b[0] - a[0])/(b[1] - a[1]));
				

			
				if(b[0] - a[0] < 0 && b[1] - a[1] < 0) this.theta = -dTheta;
				else if(b[0] - a[0] > 0 && b[1] - a[1] > 0) this.theta = Math.PI - dTheta;
				else if(b[0] - a[0] < 0 && b[1] - a[1] > 0) this.theta = -Math.PI - dTheta;
				else this.theta = -dTheta;
			
				
			}
			
		//	System.out.println("i = " + i);
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
