package Loader;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;


/**
 * Clasa ce contine o metoda statica pentru incarcarea unui fisier audio in memorie.
 */

public class AudioLoader {


	public static AudioInputStream loadAudioInputStream(String path) {
		AudioInputStream stream = null;
		try {
			stream = AudioSystem.getAudioInputStream(TextLoader.class.getResource(path));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return stream;
	}
}
