package game.worlds;

import java.awt.Rectangle;

public class MapCollisions {
	
	private Handler handler;
	
	public MapCollisions(Handler handler){
		this.handler = handler;
	}
	
	private Rectangle r1 = new Rectangle(168, 160, 104, 431)
	,r2 = new Rectangle(136,552,40,114)
	,r3 = new Rectangle(271, 375, 416, 120)
	,r4 = new Rectangle(688, 416, 79, 127)
	,r5
	,r6 = new Rectangle(0,870,480, handler.getWorld().getMapHeight()-870)
	,r7;
	
	
}
