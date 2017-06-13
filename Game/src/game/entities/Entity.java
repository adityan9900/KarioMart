package game.entities;

import java.awt.Graphics;

public abstract class Entity {

	//x y position
	protected float x, y;
	//car heights and widths
	protected int width, height;
	
	
	public Entity(float x, float y, int w, int h) {
		this.x = x;
		this.y = y;
		width = w;
		height = h;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);

	//gets x
	public float getX() {
		return x;
	}

	//sets x
	public void setX(float x) {
		this.x = x;
	}

	//gets y
	public float getY() {
		return y;
	}

	//sets y
	public void setY(float y) {
		this.y = y;
	}

	//gets width
	public int getWidth() {
		return width;
	}

	//sets width
	public void setWidth(int width) {
		this.width = width;
	}

	//gets height
	public int getHeight() {
		return height;
	}

	//sets height
	public void setHeight(int height) {
		this.height = height;
	}
	
}
