package Sounds;

/**
 * Redare sunete
 *
 */
public class SoundPlayer{

	private AudioFile soundEffect;

	public void playSoundEffect(AudioFile soundEffect) {
		if (soundEffect != null) {
			if (this.soundEffect != null)
				this.soundEffect.reset();
			this.soundEffect = soundEffect;
			this.soundEffect.play();

		}

	}

}
