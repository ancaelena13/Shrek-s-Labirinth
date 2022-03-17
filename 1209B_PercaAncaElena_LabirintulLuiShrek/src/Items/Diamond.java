package Items;

import java.awt.Graphics2D;

import Graphics.AssetManager;
import Map.Camera;
import Map.Tile;

/**
 * Defineste un diamant care poate fi colectat
 *
 */
public class Diamond extends Item {

	public Diamond(int x, int y) {
		super(AssetManager.getImage("diamond"), x, y);
	}

	@Override
	public void update(float detla) {

	}

	@Override
	public void render(Graphics2D g2, Camera camera) {
		g2.drawImage(getImage(), Math.round(x - camera.getxOffset()), Math.round(y - camera.getyOffset()),
				Tile.TILE_WIDTH, Tile.TILE_HEIGHT, null);

	}

}
