package Sounds;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;

/**
 * Defineste un fisier audio care poate fi redat
 */
public class AudioFile {

	private AudioInputStream stream;
	private Clip clip;
	private DataLine.Info info;
	
	
	/**
	 * Constructor
	 *
	 * @param stream intrarea audio
	 */
	public AudioFile(AudioInputStream stream) {
		this.stream = stream;
		this.info = new DataLine.Info(Clip.class, this.stream.getFormat());
		
		try {
			this.clip = (Clip) AudioSystem.getLine(info);
			clip.open(this.stream);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Radare audio
	 */
	public void play() {
		clip.setFramePosition(0);
		clip.start();
	}
	

	public void reset() {
		clip.setFramePosition(0);
	}
}
