package game.worlds;

import java.awt.Point;
import java.awt.Rectangle;

import game.entities.drivers.Drivers.Direction;

public class MapCollisions {
	
	private Rectangle[]rects = new Rectangle[7];
	private Handler handler;
	
	public MapCollisions(Handler handler){
		this.handler = handler;
		rects[0] = new Rectangle(168, 160, 104, 431);
		rects[1] = new Rectangle(136,552,40,114);
		rects[2] = new Rectangle(271, 375, 416, 120);
		rects[3] = new Rectangle(688, 416, 79, 127);
		rects[4] = new Rectangle(745,415,70,400);
		rects[5] = new Rectangle(0,870,480, handler.getWorld().getMapHeight()-870);
		rects[6] = new Rectangle(735, 0, handler.getWorld().getMapWidth() - 735, 160);
	}	
	
	private Point[] getEdges(float x, float y, int width, int height, double theta){
		Point[] edges = new Point[4];
		
		double xprime = -1d*(double)width/2d;
		double yprime = -1d*(double)height/2d;
		
		for(int i = 0;i<4;i++){
			double x2prime = xprime;
			double y2prime = yprime;
			
			x2prime = xprime*Math.cos(theta) - yprime*Math.sin(theta);
			y2prime = xprime*Math.sin(theta) + yprime*Math.cos(theta);
			
			x2prime+=x;
			y2prime+=y;
			
			theta+=Math.toRadians(90);
			
			edges[i] = new Point((int)x2prime ,(int)y2prime);
		}
		return edges;
	}
	
	public void innerMapCollision(float x, float y, int width, int height,double theta){
		
		Point edges[] = getEdges(x, y, width, height, theta);
		
		for(Rectangle r:rects){
			for(Point p:edges){
				if(r.contains(p))
					continue;
			}
		}
	}
	
	public boolean xCollide(float x, float y, double speed, Direction direction, int width, int height){
		//left boundary screen
		if((speed>0 && x<= 0 && (direction == Direction.NORTH_WEST || direction == Direction.SOUTH_WEST)) || ((speed<0) && x<= 0 && (direction == Direction.NORTH_EAST|| direction == Direction.SOUTH_EAST)))
			return false;
		//right screen collision
		else if((speed>0 && x>= handler.getWorld().getMapWidth() - width && (direction == Direction.NORTH_EAST || direction == Direction.SOUTH_EAST)) ||((speed<0) && x>= handler.getWorld().getMapWidth() - width && (direction == Direction.NORTH_WEST|| direction == Direction.SOUTH_WEST)))
			return false;
		else
			return true;
	}
	
	public boolean yCollide(float x, float y, double speed, Direction direction, int width, int height){
		//top screen collision
				if((speed<0 && y<=0 && (direction == Direction.SOUTH_WEST || direction == Direction.SOUTH_EAST)) || ((speed>0) && y<=0 && (direction == Direction.NORTH_EAST|| direction == Direction.NORTH_WEST)))
							return false;
				//bottom screen collision
				else if((speed<0 && y>=handler.getWorld().getMapHeight() - height && (direction == Direction.NORTH_WEST || direction == Direction.NORTH_EAST)) ||  ((speed>0) && y>=handler.getWorld().getMapHeight() - height && (direction == Direction.SOUTH_WEST|| direction == Direction.SOUTH_EAST)))
							return false;		
				else
					return true;
	}
}
