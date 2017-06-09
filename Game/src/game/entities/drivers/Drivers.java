package game.entities.drivers;
 
import game.entities.Entity;
import game.worlds.Handler;
 
public abstract class Drivers extends Entity {
 
	public static final double DEFAULT_ACCEL = .25;
	protected final double Tau = Math.PI*2.0;
	
	public static final int DEFAULT_WIDTH = 120;
	public static final int DEFAULT_HEIGHT = 251;
	
	protected float move;
	public static final double DEFAULT_SPEED = 1;
	public static final double DEFAULT_MAX_SPEED = 8;
	public static final double DEFAULT_TURN_PWR = 4;
	
	protected double accel, theta, speed, maxSpeed, turnPwr;
	
	protected Handler handler;
	
	public Drivers(Handler h, float x, float y, int width, int height) {
		super(x, y, width, height);
		accel = DEFAULT_ACCEL;
		speed = DEFAULT_SPEED;
		maxSpeed = DEFAULT_MAX_SPEED;
		turnPwr = DEFAULT_TURN_PWR;
		handler = h;
	}
	
	public boolean insideTrack() {
		return handler.getWorld().insideTrack(this.x, this.y);
	}
	
	public void move() {
		x += Math.sin(theta)*speed;
		y -= Math.cos(theta)*speed;
	}
	
	//GETTERS AND SETTERS
	public double getAccel() {
		return accel;
	}
 
	public void setAccel(double accel) {
		this.accel = accel;
	}
 
	public double getTheta() {
		return theta;
	}

	public void setTheta(double theta) {
		this.theta = theta;
	}
 
	public double getSpeed() {
		return speed;
	}
 
	public void setSpeed(double speed) {
		this.speed = speed;
	}
 
	public double getMaxSpeed() {
		return maxSpeed;
	}
 
	public void setMaxV(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
 
	public double getTurnPwr() {
		return turnPwr;
	}
 
	public void setTurnPwr(double turnPwr) {
		this.turnPwr = turnPwr;
	}
}
