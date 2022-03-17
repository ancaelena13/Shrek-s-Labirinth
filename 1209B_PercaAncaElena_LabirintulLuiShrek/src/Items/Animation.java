package Items;

import java.awt.image.BufferedImage;

/**
 * Defineste o animatie care contine o lista de cadre
 */
public class Animation {

	private BufferedImage[] frames;
	private int index;
	private float frameDelay;

	/**
	 * Constructor
	 * 
	 * @param frames	cadrele animatiei
	 * @param frameDelay	intarzierea intre fiecare cadru
	 */
	public Animation(BufferedImage[] frames, float frameDelay) {
		this.frames = frames;
		index = 0;
		this.frameDelay = frameDelay;
	}

	/**
	 * Obtinem cadrul in functie de timpul trecut
	 * 
	 * @param stateTime	timpul trecut
	 * @return	cadrul curent
	 */
	public BufferedImage getCurrentFrame(float stateTime) {
		int i = (int) (stateTime / frameDelay);
		if (i >= frames.length)
			i = frames.length - 1;
		return frames[i];
	}

	//STTERS si GETTERS
	
	public void setFrameDelay(float frameDelay) {
		this.frameDelay = frameDelay;
	}

	public int getIndex() {
		return index;
	}

	public BufferedImage getCurrentFrame() {
		return frames[index];
	}

	public BufferedImage[] getFrames() {
		return this.frames;
	}

	public boolean isAtLastIndex() {
		return index == frames.length - 1;
	}

	public boolean isComplete() {
		return isAtLastIndex();
	}

	public float getFrameDelaly() {
		return frameDelay;
	}

}
