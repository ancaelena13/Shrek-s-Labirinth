package Loader;

import java.io.File;
import java.util.Scanner;

/**
 * Clasa ce contine o metoda statica pentru incarcarea unui fisier audio in memorie.
 */

public class TextLoader {

	public static String loadTextFileAsString(String path) {
		try (Scanner scan = new Scanner(new File(path))) {
			String file = "";
			while (scan.hasNext()) {
				file += scan.next() + " ";
			}
			return file;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		return null;
	}

	public static String loadTextFromClassFolder(String path) {
		try (Scanner scan = new Scanner(TextLoader.class.getResourceAsStream(path))) {
			String file = "";
			while (scan.hasNext()) {
				file += scan.next() + " ";
			}
			return file;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		return null;
	}

}
