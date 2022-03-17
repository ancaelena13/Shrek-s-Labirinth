package Loader;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Clasa ce contine o metoda statica pentru incarcarea unei imagini in memorie.
 */
public class ImageLoader {
	/**
	 * Incarca o imagine intr-un obiect BufferedImage si returneaza o referinta catre acesta.
	 *
	 *@param path Calea relativa pentru localizarea fisierul imagine.
	 */
	public static BufferedImage loadImage(String path) {
		BufferedImage image = null;

		/// Avand in vedere exista situatii in care fisierul sursa sa nu poate fi accesat
		/// metoda read() arunca o excpetie ce trebuie tratata

		try {
			image = ImageIO.read(ImageLoader.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();                 /// Afiseaza informatiile necesare depanarii.
		}

		return image;
	}
}
