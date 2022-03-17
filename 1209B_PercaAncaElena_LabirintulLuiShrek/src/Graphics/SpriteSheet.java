package Graphics;

import java.awt.image.BufferedImage;

import Loader.ImageLoader;

/**
 * Sprite sheet
 */
public class SpriteSheet {

	private BufferedImage sheet;

	public SpriteSheet(String path) {
		this.sheet = (ImageLoader.loadImage(path));
	}

	///Returneaza un obiect BufferedImage ce contine o subimage (dala).
	public BufferedImage crop(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}

	public BufferedImage getImage() {
		return sheet;
	}

}
