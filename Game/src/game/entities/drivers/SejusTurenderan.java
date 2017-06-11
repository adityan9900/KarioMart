package game.entities.drivers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import game.gfx.Assets;
import game.worlds.Handler;
import game.worlds.World;

public class SejusTurenderan extends Drivers{

	private World world;
	private ArrayList<double[]> midArry;
	
	private int midIndex;
	private int i;
	private final int UPDATE_PERIOD = 10;
	
	public SejusTurenderan(World w, Handler h, float x, float y, int width, int height) {
		super(h, x, y, width, height);
		setAccel(DEFAULT_ACCEL/2d);
		setMaxV(DEFAULT_MAX_SPEED*2d);
		setTurnPwr(DEFAULT_TURN_PWR*.85);
	
		this.world = w;
		world.setPath(world.getTrackName() + "TrackMid.txt", 1);
		this.midArry = world.getPath(1); //get center path
		
		
		double [] a = midArry.get(0);
		this.x = (float)a[0];
		this.y = (float)a[1];
		this.theta = 0;
	}

	Random r = new Random();
	public void tick() {

		    if(i % UPDATE_PERIOD == 0) midIndex ++;
			if(midIndex < midArry.size() - 1) {
				double [] a = midArry.get(midIndex);
				a[0] = r.nextGaussian() * 0.75 + a[0];
				a[1] = r.nextGaussian() * 0.75 + a[1];
				
				double dTheta;
				
				double [] b = a;
				if(midIndex < midArry.size() - 1) {
					b = midArry.get(midIndex+1);
				}
				
				int mod = i % UPDATE_PERIOD;
				double currentX = a[0] + mod * (b[0] - a[0]) / UPDATE_PERIOD;
				double currentY = a[1] + mod * (b[1] - a[1]) / UPDATE_PERIOD;
				
				double newX = currentX + mod * (b[0] - a[0]) / UPDATE_PERIOD;
				double newY = currentY + mod * (b[1] - a[1]) / UPDATE_PERIOD;
				
	//			if(newY - currentY == 0) currentX = r.nextGaussian() * 1.5 + currentX;
	//			else if(newX - currentX == 0) currentY = r.nextGaussian() * 1.5 + currentY;
				
				
				if(newY - currentY == 0) dTheta = 0;
				else dTheta = Math.atan((newX - currentX)/(newY - currentY));
				
				this.x = (float)currentX;
				this.y = (float)currentY;
				
				
				double newTheta;
				if(newX - currentX < 0 && newY - currentY < 0) newTheta = -dTheta;
				else if(newX - currentX > 0 && newY - currentY > 0) newTheta = Math.PI - dTheta;
				else if(newX - currentX < 0 && newY - currentY > 0) newTheta = -Math.PI - dTheta;
				else if(dTheta == 0) newTheta = this.theta;
				else newTheta = -dTheta;
			
				
				//forcing AI to not make huge erroneous turns
				if(Math.abs(newTheta - this.theta) < Math.PI) this.theta = newTheta;
				else {
					System.out.println("THETA: " + this.theta + "\tNEW THETA: " + newTheta);
				}
			//	System.out.println("theta: " + this.theta + "\tdX: " + (newX - currentX) + "\tdY: " + (newY - currentY));
				//this.theta *= 0.6; //dampening, will check effects
				
			}
			
		//	System.out.println("i = " + i);
			i ++;
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		//original transform
		AffineTransform origAT = g2d.getTransform(); 
		//car being painted
		BufferedImage car = Assets.SejusTurenderan;		
		AffineTransform rotation = new AffineTransform(); 
		//spins the car with theta, and x coord(center of car) + y coord(center of car)
		rotation.rotate(theta,x + width / 2, y + height/2);
		g2d.setTransform(rotation);
		//draws the car on the screen
		g2d.drawImage(car, (int)x, (int)y, width, height, null);
		g2d.drawString("Player", x, y);
		//reverts the transform to original
		g2d.setTransform(origAT);
	}
	
}