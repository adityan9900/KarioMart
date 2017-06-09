package game.entities;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public abstract class Entity {

	protected float x, y;
	protected int width, height;
	private Rectangle collisionBox;
	
	
	public Entity(float x, float y, int w, int h) {
		this.x = x;
		this.y = y;
		width = w;
		height = h;
		collisionBox = new Rectangle(new Point((int)x,(int)y),new Dimension(width,height));
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public Rectangle getCollisionBox(){
		return collisionBox;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}
