package game.entities.drivers;
 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import game.gfx.Assets;
import game.worlds.Handler;
 
public class Player extends Drivers{
 
	private Handler handler;
	private Point[] collisionEdges;
	
	public Player(Handler h, float x, float y, int width, int height) {
		super(x, y, width, height);
		handler = h;
	}
 
	public void tick() {
		collisionEdges = new Point[4];
		System.out.println(x+ " , " + y);
		getInput();
		getEdges();
		checkCollision();
		move();
	}
	
	private void getEdges(){
		for(int i = 0;i<collisionEdges.length;i++){
			Point temp;
			double tempX = (x*Math.cos(theta + (double)90*i) - y*Math.sin(theta + (double)90*i));
			double tempY = x*Math.sin(theta + (double)90*i) + y*Math.cos(theta + (double)90*i);
			temp = new Point((int)tempX,(int)tempY);
			collisionEdges[i] = temp;
		}
	}
	
	private void checkCollision() {
		if(x <= 0 + handler.getWorld().getMapBorder() || x >= handler.getWorld().getMapWidth() - handler.getWorld().getMapBorder() - 15) {
			speed *= -1;
		}
		if(y <= 0 + handler.getWorld().getMapBorder() || y >= handler.getWorld().getMapWidth() - handler.getWorld().getMapBorder() - 15) {
			speed *= -0.51;
		}
	}
	
	private void getInput() {
		if(handler.getKeyManager().up || handler.getKeyManager().down) {
			if(handler.getKeyManager().up && speed<maxSpeed) 
				speed+=accel;		//change eventually off different players speeds		//did this already
			if(handler.getKeyManager().down && Math.abs(speed) < maxSpeed) 
				speed-=accel;		//here	//did this already
		} else if(!handler.getKeyManager().up || !handler.getKeyManager().down && speed!=0) {
			if(Math.abs(speed) <= accel) 
				speed = 0;
			else if(speed>0)
				speed-=accel;		//change here		//did it
			else if(speed<0)
				speed+= accel;		//change		//did it
		}
		if(handler.getKeyManager().left || handler.getKeyManager().right) {
			if(handler.getKeyManager().left){ 
				if(theta<0)
					theta = theta+Tau;		//thats the turn power
				theta-= Math.toRadians(1)*turnPwr;
		}
			if(handler.getKeyManager().right){ 
				if(theta>Tau)
					theta = theta-Tau;
				theta+=Math.toRadians(1)*turnPwr;
			}
		}
 
	}
 
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		//original transform
		AffineTransform origAT = g2d.getTransform(); 
		//car being painted
		BufferedImage car = Assets.black;		
		AffineTransform rot = new AffineTransform(); 
		//spins the car with theta, and x coord(center of car) + y coord(center of car)
		rot.rotate(theta,x + width / 2, y + height/2);
		    
		g2d.setTransform(rot);
		    
		//draws the car on the screen
		g2d.drawImage(car, (int)x, (int)y, width, height, null);
		g2d.drawString("Player", x, y);
		//reverts the transform to original
		g2d.setTransform(origAT);
	}
	
}
