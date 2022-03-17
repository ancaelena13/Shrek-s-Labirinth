package Graphics;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import Sounds.AudioFile;
import Loader.AudioLoader;
import Loader.ImageLoader;

/**
 * Clasa incarca fiecare element grafic necesar jocului.
 * Game assets include tot ce este folosit in joc: imagini, sunete etc.
 */
public class AssetManager {

	private static HashMap<String, AudioFile> allSounds = new HashMap<String, AudioFile>();
	private static HashMap<String, BufferedImage> allImages = new HashMap<String, BufferedImage>();
	private static HashMap<String, BufferedImage[]> allAnimations = new HashMap<String, BufferedImage[]>();

	public static void load() {
		loadImages();
		loadAnimations();
		loadAudio();
	}

	private static void loadImages() {
		SpriteSheet tileSheet = new SpriteSheet("/tilesheet.png");

		///Dale
		int count = 1;
		for (int i = 0; i < 4; i++)
			allImages.put("" + count++, tileSheet.crop(32 * i, 0, 32, 32));

		for (int i = 0; i < 3; i++)
			allImages.put("" + count++, tileSheet.crop(32 * i, 32, 32, 32));
		
		///Diamant
		allImages.put("diamond", ImageLoader.loadImage("/diamant.png"));
		
		///Meniu
		allImages.put("Menu", ImageLoader.loadImage("/Menu.jpg"));
		
		///Poveste
		allImages.put("poveste", ImageLoader.loadImage("/Poveste.jpg"));
		
		///Reguli
		allImages.put("reguli", ImageLoader.loadImage("/Reguli.jpg"));
	}

	private static void loadAnimations() {

		SpriteSheet playerSheet = new SpriteSheet("/playersheet.png");

		///Sprite erou
		BufferedImage[] heroLeftFrames = new BufferedImage[4];
		BufferedImage[] heroRightFrames = new BufferedImage[4];
		BufferedImage[] heroUpFrames = new BufferedImage[4];
		BufferedImage[] heroDownFrames = new BufferedImage[4];

		for (int i = 0; i < 4; i++) {
			heroDownFrames[i] = playerSheet.crop(32 * i, 0, 32, 48);
			heroLeftFrames[i] = playerSheet.crop(32 * i, 48 * 1, 32, 48);
			heroRightFrames[i] = playerSheet.crop(32 * i, 48 * 2, 32, 48);
			heroUpFrames[i] = playerSheet.crop(32 * i, 48 * 3, 32, 48);
		}

		allAnimations.put("heroDown", heroDownFrames);
		allAnimations.put("heroUp", heroUpFrames);
		allAnimations.put("heroLeft", heroLeftFrames);
		allAnimations.put("heroRight", heroRightFrames);

	}
	
	private static void loadAudio() {
		allSounds.put("tick", new AudioFile(AudioLoader.loadAudioInputStream("/tick.wav")));
		allSounds.put("pick", new AudioFile(AudioLoader.loadAudioInputStream("/pick.wav")));
	}
	
	public static AudioFile getAudioFile(String key) {
		return allSounds.get(key);
	}

	public static BufferedImage getImage(String key) {
		return allImages.get(key);
	}

	public static BufferedImage[] getFrames(String key) {
		return allAnimations.get(key);
	}

}
