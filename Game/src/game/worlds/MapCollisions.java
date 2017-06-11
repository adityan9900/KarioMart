package game.worlds;

import java.awt.Rectangle;

import game.entities.drivers.Drivers.Direction;

public class MapCollisions {
	
	private Rectangle r1,r2,r3,r4,r5,r6,r7;
	private Handler handler;
	
	public MapCollisions(Handler handler){
		this.handler = handler;
		r1 = new Rectangle(168, 160, 104, 431);
		r2 = new Rectangle(136,552,40,114);
		r3 = new Rectangle(271, 375, 416, 120);
		r4 = new Rectangle(688, 416, 79, 127);
		r5 = new Rectangle(745,415,70,400);
		r6 = new Rectangle(0,870,480, handler.getWorld().getMapHeight()-870);
		r7 = new Rectangle(735, 0, handler.getWorld().getMapWidth() - 735, 160);
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
