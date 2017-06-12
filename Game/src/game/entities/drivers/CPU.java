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

public class CPU extends Drivers {

	private World world;
	private ArrayList<double[]> midArry;
	
	private int midIndex;
	private double i;
	private double UPDATE_PERIOD = 51.0;
	
	public CPU(World w, Handler h, float x, float y, int width, int height) {
		super(h, x + 30, y + 65, width, height);
		setAccel(DEFAULT_ACCEL/2d);
		
		setMaxV(DEFAULT_MAX_SPEED*2d);
		setTurnPwr(DEFAULT_TURN_PWR*.85);
	
		this.world = w;
		world.setPath(world.getTrackName() + "TrackMid.txt", 4);
		this.midArry = world.getPath(4); //get center path
		
		double [] b = new double [2];
		b[0] = 915.0;
		b[1] = 505.0;
		midArry.add(b);
		
		double [] a = midArry.get(0);
//		this.x = (float)a[0];
//		this.y = (float)a[1];
		this.theta = 0;
	}

	Random r = new Random();
	boolean first = true;
	public void tick() {
		
		if(first) {
			if(handler.getGame().difficulty.ordinal() == 0) UPDATE_PERIOD += 8.0;
			else if(handler.getGame().difficulty.ordinal() == 1) UPDATE_PERIOD = UPDATE_PERIOD;
			else UPDATE_PERIOD -= 8.0;
			first = false;
		}
		//System.out.println("X: " + this.x + "\tY: " + this.y);
		if(i % UPDATE_PERIOD == 0 || i % UPDATE_PERIOD == 1 || i % UPDATE_PERIOD == 2 || i % UPDATE_PERIOD == 3) midIndex ++;
			if(midIndex < midArry.size() - 1) {
				double [] a = midArry.get(midIndex);
			//	a[0] = r.nextGaussian() * 0.5 + a[0];
			//	a[1] = r.nextGaussian() * 0.5 + a[1];
				
				double dTheta;
				
				double [] b = a;
				if(midIndex < midArry.size() - 1) {
					b = midArry.get(midIndex+1);
				}
				
				double mod = i % UPDATE_PERIOD;
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
			
				
				
				/**ALL THE NEW STUFF**/
				if(Math.abs(newTheta - this.theta) < 9 * Math.PI/180.0) newTheta = this.theta;
				//forcing AI to not make huge erroneous turns
				
				//prevents sketchy shit from happening i think
				double currentTheta = this.theta;
				
			
				double currentDif = Math.abs(newTheta - currentTheta);
				if(newTheta < 0) newTheta += 2 * Math.PI;
				if(currentTheta < 0) currentTheta += 2 * Math.PI;
				double newDif = Math.abs(newTheta - currentTheta);
				
				//makes sure diffs less than 3/4 of pi/2
				if(Math.min(currentDif, newDif) < Math.PI/2.0) {
					if(newTheta > Math.PI) this.theta = newTheta - 2*Math.PI;
					else this.theta = newTheta;
				}
				
				
				
				if(this.x < 110) this.theta = r.nextGaussian() * 1.5 *Math.PI/90.0 + Math.PI;
				else if(this.x > 910) this.theta = r.nextGaussian() * 1.5 *Math.PI/90.0;
				else if(this.x > 230 && this.x < 900 && this.y > 60 && this.y < 400) this.theta = r.nextGaussian() * 1.5*Math.PI/90.0 - Math.PI/3.0;
				else if(this.x > 114 && this.x < 480 && this.y > 590 && this.y < 740) this.theta = r.nextGaussian() * 1.5*Math.PI/90.0 + Math.PI/3.0;
				else if(this.x > 510 && this.x < 732 && this.y > 595 && this.y < 879) this.theta = r.nextGaussian() * 1.5*Math.PI/90.0 + 5 * Math.PI/6.0;
			}
	
			i += 4 ;
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		//original transform
		AffineTransform origAT = g2d.getTransform(); 
		//car being painted
		BufferedImage car = Assets.blue;		
		AffineTransform rotation = new AffineTransform(); 
		//spins the car with theta, and x coord(center of car) + y coord(center of car)
		rotation.rotate(theta,x + width / 2, y + height/2);
		g2d.setTransform(rotation);
		//draws the car on the screen
		g2d.drawImage(car, (int)x, (int)y, width, height, null);
		g2d.drawString("Dom", x, y);
		//reverts the transform to original
		g2d.setTransform(origAT);
	}
}
