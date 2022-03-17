package Items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Map.Camera;

/**
 * Implementeaza notiunea abstracta de entitate activa din joc, "element cu care se poate interactiona: zid, diamant etc.".
 */
public abstract class Item {

	protected int x,y;
	protected BufferedImage image;
	
	/**
	 * Constructor
	 *
	 * @param image	imaginea obiectului
	 * @param x	 coordonata x
	 * @param y	 coordonata y
	 */
	public Item(BufferedImage image, int x, int y){
		this.image = image;
		this.x = x;
		this.y = y;
	}
	
	public abstract void update(float detla);
	public abstract void render(Graphics2D g2,Camera camera);


	///SETTERS si GETTERS
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	
	
}
