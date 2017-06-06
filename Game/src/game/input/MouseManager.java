package game.input;

import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseManager implements MouseListener{
	
	private double x;
	private double y;
	private boolean pressed = false;
	
	public boolean inBoundary(int x1, int y1, int x2, int y2){
		return(x>=x1 && x<=x2 && y>=y1 && y<=y2);
	}
	
	public void tick(){
		 x = MouseInfo.getPointerInfo().getLocation().getX();
		 y = MouseInfo.getPointerInfo().getLocation().getY();
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public boolean isPressed() {
		return pressed;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		pressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		pressed = false;
	}

}
