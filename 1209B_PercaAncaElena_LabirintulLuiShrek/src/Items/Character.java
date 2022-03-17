package Items;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *  Defineste notiunea abstracta de caracter/individ/fiinta din joc.
 */
public abstract class Character extends Item {

	protected int sx, sy;
	protected int dx, dy;

	protected float defaultSpeed = 0.5f;	/*!< Viteza implicita a unu caracter.*/
	protected float timer;
	protected boolean isMoving;
	protected Rectangle bounds;

	/**
	 * Constructor
	 * 
	 * @param image imaginea
	 * @param x     coordonata pe axa OX
	 * @param y     coordonata pe axa OY
	 */
	public Character(BufferedImage image, int x, int y) {
		super(image, x, y);
	}

	/**
	 * @param delta intarzierea dintre friecare cadru
	 */
	@Override
	public void update(float delta) {
		if (isMoving) {
			timer += delta;

			x = apply(sx, dx, timer / defaultSpeed);
			y = apply(sy, dy, timer / defaultSpeed);

			if (timer >= defaultSpeed) {
				x = dx;
				y = dy;
				isMoving = false;
				timer = 0;
			}
		}
	}

	/**
	 * fn public void Move()
	 *Modifica pozitia caracterului
     */
	public void move(DIRECTION dir) {
		if (isMoving)
			return;
		sx = x;
		sy = y;
		dx = x + dir.getDx();
		dy = y + dir.getDy();

		bounds.x += dir.getDx();
		bounds.y += dir.getDy();

		timer = 0;
		isMoving = true;
	}

	/**
	 * Interpolarea liniara dintre start si end
	 * 
	 * @param start valoarea de start
	 * @param end   valoarea de sfarsit
	 * @param p     procentajul
	 * 
	 * @return valoarea interpolarii dintre start si end
	 */
	private int apply(int start, int end, float p) {
		return (int) (start + ((end - start) * Math.min(1, p)));
	}

	public Rectangle getBounds() {
		return bounds;
	}

	
}
