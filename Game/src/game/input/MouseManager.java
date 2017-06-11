package game.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener,MouseMotionListener{
	
	private double x;
	private double y;
	private boolean pressed = false;
	
	public boolean inBoundary(int x1, int y1, int x2, int y2){
		return(x>=x1 && x<=x2 && y>=y1 && y<=y2);
	}
	
	public void tick(){
		
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
		System.out.println(e.getX() + " " + e.getY());
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

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

}
