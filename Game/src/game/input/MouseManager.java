package game.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener,MouseMotionListener{
	
	//x position
	private double x;
	//y position
	private double y;
	//if its presesd down
	private boolean pressed = false;
	
	//checks if click is in the boundary of a container
	public boolean inBoundary(int x1, int y1, int x2, int y2){
		return(x>=x1 && x<=x2 && y>=y1 && y<=y2);
	}
	
	//returns x
	public double getX() {
		return x;
	}

	//returns y
	public double getY() {
		return y;
	}

	//if pressed down
	public boolean isPressed() {
		return pressed;
	}
	
	//NONE IN USE
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
	
	//if its not pressed
	@Override
	public void mouseReleased(MouseEvent e) {
		pressed = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	//gets position
	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

}
