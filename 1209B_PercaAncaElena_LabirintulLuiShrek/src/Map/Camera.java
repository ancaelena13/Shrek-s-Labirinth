package Map;

import Items.Character;
import game.Main;

/**
 * Ne permite sa vizualizam doar o bucata din harta
 * atunci cand aceasta e mai mare decat fereastra jocului
 */
public class Camera {

	private float xOffset;
	private float yOffset;

	private int mapWidth, mapHeight;

	public Camera(float xOffset, float yOffset, int mapWidth, int mapHeight) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.mapWidth = mapWidth * Tile.TILE_WIDTH;
		this.mapHeight = mapHeight * Tile.TILE_HEIGHT;
	}

	public void centerOnEntity(Character e) {
		xOffset = e.getX() - Main.WIDTH / 2 + 0.0f;
		yOffset = e.getY() - Main.HEIGHT / 2 + 0.0f;

		if (xOffset < 0)
			xOffset = 0;
		else if (xOffset > (mapWidth - Main.WIDTH))
			xOffset = mapWidth - Main.WIDTH;

		if (yOffset < 0)
			yOffset = 0;
		else if (yOffset > mapHeight - Main.HEIGHT)
			yOffset = mapHeight - Main.HEIGHT;
	}

	///SETTERS si GETTERS
	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

	public int getMapHeight() {
		return mapHeight;
	}

	public void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight * Tile.TILE_HEIGHT;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth * Tile.TILE_WIDTH;
	}
}
