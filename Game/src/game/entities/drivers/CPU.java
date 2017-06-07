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
			
		    if(i % 30 == 0) midIndex ++;
			if(midIndex < midArry.size() - 1) {
				int [] a = midArry.get(midIndex);
		
				double dTheta;
				
				int [] b = a;
				if(midIndex < midArry.size() - 1) {
					b = midArry.get(midIndex+1);
				}
				
				int mod = i % 30;
				double currentX = a[0] + mod * (b[0] - a[0]) / 30.0;
				double currentY = a[1] + mod * (b[1] - a[1]) / 30.0;
				
				double newX = currentX + mod * (b[0] - a[0]) / 30.0;
				double newY = currentY + mod * (b[1] - a[1]) / 30.0;
				
				if(newY - currentY == 0) dTheta = 0;
				else dTheta = Math.atan((newX - currentX)/(newY - currentY));
				

				this.x = (int)currentX;
				this.y = (int)currentY;
			
				if(newX - currentX < 0 && newY - currentY < 0) this.theta = -dTheta;
				else if(newX - currentX > 0 && newY - currentY > 0) this.theta = Math.PI - dTheta;
				else if(newX - currentX < 0 && newY - currentY > 0) this.theta = -Math.PI - dTheta;
				else if(dTheta == 0) this.theta = this.theta;
				else this.theta = -dTheta;
			
				
				System.out.println("theta: " + this.theta + "\tdX: " + (newX - currentX) + "\tdY: " + (newY - currentY));
				//this.theta *= 0.6; //dampening, will check effects
				
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
