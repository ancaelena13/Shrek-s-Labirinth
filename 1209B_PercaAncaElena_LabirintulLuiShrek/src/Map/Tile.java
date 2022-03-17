package Map;

import java.awt.image.BufferedImage;

import Graphics.AssetManager;

/**
 * O dala a jocului
 */
public class Tile {
	public static final int TILE_WIDTH = 32, TILE_HEIGHT = 32;

	public static final int SOLID = 1;
	public static final int NON_SOLID = 2;

	private BufferedImage image;
	private int type;

	/**
	 * Constructor
	 * 
	 * @param tileId id-ul dalei
	 * @param type tipul dalei
	 */
	public Tile(String tileId, int type) {
		this.type = type;
		this.image = AssetManager.getImage(tileId);
	}

	public boolean isSolid() {
		return type == SOLID;
	}

	public BufferedImage getImage() {
		return image;
	}

}
