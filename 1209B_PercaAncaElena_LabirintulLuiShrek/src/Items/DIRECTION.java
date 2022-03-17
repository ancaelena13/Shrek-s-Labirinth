package Items;

/**
 * Defineste directia si deplasarea x și y
 *
 */
public enum DIRECTION {

	UP(0, -32), DOWN(0, 32), RIGHT(32, 0), LEFT(-32, 0);

	private int dx, dy;

	/**
	 * Constructor
	 *
	 * @param dx	delta x
	 * @param dy	delta y
	 */
	private DIRECTION(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public int getDx() {
		return dx;
	}

	public int getDy() {
		return dy;
	}

}
