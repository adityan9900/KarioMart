package game.input;

import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseManager implements MouseListener{
	
	private double x;
	private double y;
	
	public void tick(){
		 x = MouseInfo.getPointerInfo().getLocation().getX();
		 y = MouseInfo.getPointerInfo().getLocation().getY();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int currX = e.getX();
		int currY = e.getY();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

}
