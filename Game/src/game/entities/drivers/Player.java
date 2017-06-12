package game.entities.drivers;
 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.gfx.Assets;
import game.worlds.Handler;
import game.worlds.MapCollisions;
 
public class Player extends Drivers{
 	
	public Player(Handler h, float x, float y, int width, int height) {
		super(h, x, y, width, height);
	}
 
	public void tick() {
		getInput();
		checkCollision();
		move();
	}
	
	private void checkCollision(){
		MapCollisions collisions = new MapCollisions(handler);
		moveX = collisions.xCollide(x, y, speed, getDirection(), width, height);
		moveY = collisions.yCollide(x, y, speed, getDirection(), width, height);
		boolean[] temp = collisions.getHit(x, y, width, height, getDirection(), speed);
		if(moveX)
			moveX = temp[0];
		if(moveY)
			moveY = temp[1];
	}
	
	private void getInput() {
		if(handler.getKeyManager().up || handler.getKeyManager().down) {
			if(handler.getKeyManager().up && speed<maxSpeed) 
				speed+=accel;	
			if(handler.getKeyManager().down && Math.abs(speed) < maxSpeed) 
				speed-=accel;
		} else if(!handler.getKeyManager().up || !handler.getKeyManager().down && speed!=0) {
			if(Math.abs(speed) <= accel) 
				speed = 0;
			else if(speed>0)
				speed-=accel;
			else if(speed<0)
				speed += accel;
		}
		if(Math.abs(speed) > maxSpeed) {
			if(Math.abs(speed) <= accel) 
				speed = 0;
			else if(speed>0)
				speed-=accel;		//change here		//did it
			else if(speed<0)
				speed += accel;		//change		//did it
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
