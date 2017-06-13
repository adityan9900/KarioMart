package game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	//keys pressed
	private boolean[] keys;
	//arrow keys
	public boolean up, down, left, right;
	
	public KeyManager() {
		keys = new boolean[256];
	}
	
	//ticks every second
	public void tick() {
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
	}
	
	//key pressed
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	//key released
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	//not used
	public void keyTyped(KeyEvent e) {
		
	}
	
}
