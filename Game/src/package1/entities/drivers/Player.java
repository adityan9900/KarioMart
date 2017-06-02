package package1.entities.drivers;
 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
 
import package1.Game;
import package1.gfx.Assets;
 
public class Player extends Drivers{
 
	private Game game;
	
	public Player(Game g, float x, float y, int width, int height) {
		super(x, y, width, height);
		game = g;
	}
 
	public void tick() {
		getInput();
		move();
	}
	
	private void getInput() {
		if(game.getKeyManager().up || game.getKeyManager().down) {
			if(game.getKeyManager().up && speed<maxSpeed) 
				speed+=accel;		//change eventually off different players speeds		//did this already
			if(game.getKeyManager().down && Math.abs(speed) < maxSpeed) 
				speed-=accel;		//here	//did this already
		} else if(!game.getKeyManager().up || !game.getKeyManager().down && speed!=0) {
			if(Math.abs(speed) <= accel) 
				speed = 0;
			else if(speed>0)
				speed-=accel;		//change here		//did it
			else if(speed<0)
				speed+= accel;		//change		//did it
		}
		if(game.getKeyManager().left || game.getKeyManager().right) {
			if(game.getKeyManager().left){ 
				if(theta<0)
					theta = theta+Tau;		//thats the turn power
				theta-= Math.toRadians(1)*turnPwr;
		}
			if(game.getKeyManager().right){ 
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
		//reverts the transform to original
		g2d.setTransform(origAT);
	}
	
}
