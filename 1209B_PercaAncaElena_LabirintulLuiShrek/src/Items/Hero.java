package Items;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import Graphics.AssetManager;
import Map.Camera;
import Map.Tile;

/**
 * Implementeaza notiunea de erou  (caracterul controlat de jucator).

 */
public class Hero extends Character {

	private HashMap<DIRECTION, Animation> walkAnimation;
	private DIRECTION dir = DIRECTION.RIGHT;
	private int numDiamonds;

	/**
	 * Constructor
	 *
	 * @param x Pozitia initiala pe axa OX a eroului.
	 * @param y Pozitia initiala pe axa OY a eroului.
	 */
	public Hero(int x, int y) {
		super(null, 0, 0);
		loadAnimations();

		bounds = new Rectangle(x,y,Tile.TILE_WIDTH,Tile.TILE_HEIGHT	);
		this.x = x;
		this.y = bounds.y - 14;

		defaultSpeed = 0.5f;
	}

	/**
	 * Actualizeaza pozitia si imaginea eroului.
     */
	@Override
	public void update(float delta) {
		super.update(delta);
	}

	@Override
	public void render(Graphics2D g2, Camera camera) {
		g2.drawImage(getImage(), Math.round(x - camera.getxOffset()), Math.round(y - camera.getyOffset()), null);
	}

	/**
	 * Misc eroul si stabilesc totodata si directia in care se face miscarea
	 */
	@Override
	public void move(DIRECTION dir) {
		if (isMoving)
			return;
		super.move(dir);
		this.dir = dir;
	}

	/**
	 * Incrementez numarul de diamante colectate
	 */
	public void diamondCollected() {
		numDiamonds = getNumDiamonds() + 1;

	}

	/**
	 * Returnez imaginea in functie de starea eroului: merge sau sta
	 */
	public BufferedImage getImage() {
		if (isMoving)
			return walkAnimation.get(dir).getCurrentFrame(timer);

		return walkAnimation.get(dir).getFrames()[0];
	}

	/**
	 * Incarc toate miscarile eroului
	 */
	private void loadAnimations() {
		/// Cadre
		walkAnimation = new HashMap<DIRECTION, Animation>();

		walkAnimation.put(DIRECTION.RIGHT, new Animation(AssetManager.getFrames("heroRight"), defaultSpeed / 4));
		walkAnimation.put(DIRECTION.LEFT, new Animation(AssetManager.getFrames("heroLeft"), defaultSpeed / 4));
		walkAnimation.put(DIRECTION.UP, new Animation(AssetManager.getFrames("heroUp"), defaultSpeed / 4));
		walkAnimation.put(DIRECTION.DOWN, new Animation(AssetManager.getFrames("heroDown"), defaultSpeed / 4));

	}

	///SETTERS si GETTERS
	
	public int getNumDiamonds() {
		return numDiamonds;
	}

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

}
