package package1.gfx;

import java.awt.image.BufferedImage;

public class Assets {

	public static BufferedImage purp, yel, green, blue, black, grey, orange, red;
	public static final int G_WIDTH = 120, G_HEIGHT = 251;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/SpriteSheet.png"));
		purp = sheet.crop(0, 0, G_WIDTH, G_HEIGHT);
		yel = sheet.crop(G_WIDTH, 0, G_WIDTH, G_HEIGHT);
		green = sheet.crop(2 * G_WIDTH, 0, G_WIDTH, G_HEIGHT);
		blue = sheet.crop(3 * G_WIDTH, 0, G_WIDTH, G_HEIGHT);
		black = sheet.crop(0, G_HEIGHT, G_WIDTH, G_HEIGHT);
		grey = sheet.crop(G_WIDTH, G_HEIGHT, G_WIDTH, G_HEIGHT);
		orange = sheet.crop(2 * G_WIDTH, G_HEIGHT, G_WIDTH, G_HEIGHT);
		red = sheet.crop(3 * G_WIDTH, G_HEIGHT, G_WIDTH, G_HEIGHT);
	}
	
}
