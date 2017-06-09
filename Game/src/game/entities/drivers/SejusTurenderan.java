package game.entities.drivers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import game.gfx.Assets;
import game.worlds.Handler;

public class SejusTurenderan extends Drivers{

	public SejusTurenderan(Handler h, float x, float y, int width, int height) {
		super(h, x, y, width, height);
		setAccel(DEFAULT_ACCEL/2d);
		setMaxV(DEFAULT_MAX_SPEED*2d);
		setTurnPwr(DEFAULT_TURN_PWR*.85);
	}

	public void tick() {
		//car ai is going here
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
