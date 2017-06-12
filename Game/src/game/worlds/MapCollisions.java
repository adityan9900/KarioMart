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
		rects[2] = new Rectangle(250, 375, 416, 120);
		rects[3] = new Rectangle(670, 416, 79, 127);
		rects[4] = new Rectangle(745,415,70,400);
		rects[5] = new Rectangle(0,870,480, handler.getWorld().getMapHeight()-870);
		rects[6] = new Rectangle(735, -15, handler.getWorld().getMapWidth() - 700, 160);
	}	
	
	public boolean[] getHit(float x, float y, int width, int height, Direction direction,double speed ,double theta){
		boolean[] xyMove = {true,true};
		for(Rectangle r:rects){
			
			int xOrigin =(int) x+width/2;
			int yOrigin =(int) y+height/2;
			
			if(r.contains(x,y)){		//there is a collision at top left
				if(direction == Direction.NORTH_WEST){
					if(speed>0 && yOrigin <=r.getY() + r.getHeight() && yOrigin>=r.getY())
						xyMove[0] = false;
					if(speed>0 && xOrigin <=r.getX() + r.getWidth() && xOrigin>=r.getX())
						xyMove[1] = false;	
				}else if(direction == Direction.SOUTH_EAST){
					if(speed<0 && yOrigin <=r.getY() + r.getHeight() && yOrigin>=r.getY())
						xyMove[0] = false;
					if(speed<0 && xOrigin <=r.getX() + r.getWidth() && xOrigin>=r.getX())
						xyMove[1] = false;	
				}
				}
			if(r.contains(x+width, y)){		//collision topright
				if(direction == Direction.NORTH_EAST){
					if(speed>0 && yOrigin <=r.getY() + r.getHeight() && yOrigin>=r.getY())
						xyMove[0] = false;
					if(speed>0 && xOrigin <=r.getX() + r.getWidth() && xOrigin>=r.getX())
						xyMove[1] = false;
				}else if(direction == Direction.SOUTH_WEST){
					if(speed<0 && yOrigin <=r.getY() + r.getHeight() && yOrigin>=r.getY())
						xyMove[0] = false;
					if(speed<0 && xOrigin <=r.getX() + r.getWidth() && xOrigin>=r.getX())
						xyMove[1] = false;
				}
			}
			if(r.contains(x+width, y+height)){		//collision bottom right
				if(direction == Direction.SOUTH_EAST){
					if(speed>0 && yOrigin <=r.getY() + r.getHeight() && yOrigin>=r.getY())
						xyMove[0] = false;
					if(speed>0 && xOrigin <=r.getX() + r.getWidth() && xOrigin>=r.getX())
						xyMove[1] = false;
				}else if(direction == Direction.NORTH_WEST){
					if(speed<0 && yOrigin <=r.getY() + r.getHeight() && yOrigin>=r.getY())
						xyMove[0] = false;
					if(speed<0 && xOrigin <=r.getX() + r.getWidth() && xOrigin>=r.getX())
						xyMove[1] = false;
				}
			}
			if(r.contains(x, y+height)){		//collision bottom left
				if(direction == Direction.SOUTH_WEST){
					if(speed>0 && yOrigin <=r.getY() + r.getHeight() && yOrigin>=r.getY())
						xyMove[0] = false;
					if(speed>0 && xOrigin <=r.getX() + r.getWidth() && xOrigin>=r.getX())
						xyMove[1] = false;
				}else if(direction == Direction.NORTH_EAST){
					if(speed<0 && yOrigin <=r.getY() + r.getHeight() && yOrigin>=r.getY())
						xyMove[0] = false;
					if(speed<0 && xOrigin <=r.getX() + r.getWidth() && xOrigin>=r.getX())
						xyMove[1] = false;
				}
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
