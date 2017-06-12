package game.worlds;

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
	
	public boolean[] getHit(float x, float y, int width, int height, Direction direction, double speed){
		boolean[] xyMove = {true,true};
		for(Rectangle r:rects){
			//if the car is touching the inner boundaries
			if(r.contains(x,y)){
					if(r.getX() <= x && x<=r.getX()+r.getWidth() && speed>0)
						xyMove[1] = false;
					if(r.getY()<=y && y<=r.getY()+r.getHeight())
						xyMove[0] = false;
			}
		}			
		return xyMove;
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
