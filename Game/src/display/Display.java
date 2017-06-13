
package display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	//frame
	private JFrame frame;
	//title string
	private String title;
	//width height
	private int width, height;
	private Canvas canvas;
	
	public Display(String t, int w, int h) {
		title = t;
		width = w;
		height = h;
		createDisplay();
	}
	//gets width
	public int getWidth(){
		return width;
	}
	//gets height
	public int getHeight(){
		return height;
	}
	//resizes canvas too
	public void resize(int w, int h) {
		width = w; height = h;
		frame.setSize(new Dimension(w, h));
		canvas.setSize(new Dimension(w, h));
		canvas.setPreferredSize(new Dimension(w, h));
		canvas.setMaximumSize(new Dimension(w, h));
		canvas.setMinimumSize(new Dimension(w, h));
		frame.pack();
	}
	//creates the display
	private void createDisplay() {
		frame = new JFrame(title);
		frame.setSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();
	}
	//returns the canvas
	public Canvas getCanvas() {
		return canvas;
	}
	//returns the frame
	public JFrame getFrame() {
		return frame;
	}
}
