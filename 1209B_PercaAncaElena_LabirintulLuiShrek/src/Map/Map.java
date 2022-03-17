package Map;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Items.Diamond;
import Items.Hero;
import game.Main;
import Graphics.AssetManager;

/**
 * Implementeaza notiunea de harta a jocului.
 */
public class Map {

	private Tile[][] tiles;
	private int rows, cols;
	private Hero hero;
	private Camera camera;

	private ArrayList<Diamond> diamonds;
	private int exitX, exitY;


	/**
	 * Constructorul de initializare al clasei.

	 *@param tiles dalele
	 *@param diamonds diamantele
     */
	public Map(Tile[][] tiles, ArrayList<Diamond> diamonds) {
		this.tiles = tiles;
		this.diamonds = diamonds;

		rows = tiles.length;
		cols = tiles[0].length;

		camera = new Camera(0, 0, cols, rows);
	}

	/**
	 *Actualizarea hartii in functie de evenimente (un diamant a fost colectat)
	 */

	public void update(float delta) {
		hero.update(delta);
		camera.centerOnEntity(hero);

		checkDiamondCollision();
	}

	/**
	 * Verific daca eroul a interactionat cu un diamant,
	 * acesta dispare de pe harta si se aude acel sunet de "colectat"
	 */
	private void checkDiamondCollision() {
		int playerX = hero.getBounds().x;
		int playerY = hero.getBounds().y;

		for (int i = 0; i < diamonds.size(); i++) {
			if (diamonds.get(i).getX() == playerX && diamonds.get(i).getY() == playerY) {
				hero.diamondCollected();
				diamonds.remove(i);
				Main.soundPlayer.playSoundEffect(AssetManager.getAudioFile("pick"));
				break;
			}
		}

	}

	public void render(Graphics2D g2) {
		/// Desenez harta
		int xStart = (int) Math.max(0, camera.getxOffset() / Tile.TILE_WIDTH);
		int xEnd = (int) Math.min(cols, (camera.getxOffset() + Main.WIDTH) / Tile.TILE_WIDTH + 1);
		int yStart = (int) Math.max(0, camera.getyOffset() / Tile.TILE_HEIGHT);
		int yEnd = (int) Math.min(rows, (camera.getyOffset() + Main.HEIGHT) / Tile.TILE_HEIGHT + 1);

		for (int i = yStart; i < yEnd; i++) {
			for (int j = xStart; j < xEnd; j++) {
				g2.drawImage(tiles[i][j].getImage(), Math.round(j * Tile.TILE_WIDTH - camera.getxOffset()),
						Math.round(i * Tile.TILE_HEIGHT - camera.getyOffset()), Tile.TILE_WIDTH, Tile.TILE_HEIGHT,
						null);
			}
		}

		/// Desenez eroul
		hero.render(g2, camera);

		/// Desenez diamantele
		for (Diamond diamond : diamonds)
			diamond.render(g2, camera);

	}

	/**
	 * Verific daca se face coliziune ( IS SOLID)
	 * 
	 * @param x coordonata x
	 * @param y coordonata y
	 * 
	 * @return true daca se va face coliziunea (IS SOLID)
	 */
	public boolean isSolidTile(int x, int y) {
		int row = y / Tile.TILE_HEIGHT;
		int col = x / Tile.TILE_WIDTH;

		if (row < 0 || col < 0 || row >= rows || col >= cols)
			return true;

		return tiles[row][col].isSolid();
	}

	/**
	 * Verific daca exista iesire la x,y
	 *
	 * @param x coordonata x
	 * @param y coordonata y
	 *
	 * @return true daca exista iesire la x,y
	 */
	public boolean isExitAt(int x,int y) {
		return x == exitX && y == exitY;
	}

	/// SETTERS si GETTERS

	public void setExit(int x, int y) {
		exitX = x;
		exitY = y;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

}
