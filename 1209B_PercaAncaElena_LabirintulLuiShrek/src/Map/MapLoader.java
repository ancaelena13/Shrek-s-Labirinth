package Map;

import java.util.ArrayList;
import java.util.Random;

import Items.Diamond;
import Items.Hero;
import Loader.TextLoader;

/**
 * Incarcarea hartii
 */
public class MapLoader {

	public static Map loadMap(String mapPath) {
		String data[] = TextLoader.loadTextFromClassFolder(mapPath).split(" ");

		int count = 0;
		int rows = Integer.parseInt(data[count++]);
		int cols = Integer.parseInt(data[count++]);

		///Dalele
		Tile[][] tiles = new Tile[rows][cols];
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++) {
				int tileId = Integer.parseInt(data[count++]);
				int type = tileId < 4 ? Tile.SOLID : Tile.NON_SOLID;

				tiles[i][j] = new Tile(tileId + "", type);
			}

		////Jucatorul
		int row = Integer.parseInt(data[count++]);
		int col = Integer.parseInt(data[count++]);
		Hero player = new Hero(col * Tile.TILE_WIDTH, row * Tile.TILE_HEIGHT);

		///Iesirea
		int exitY = Integer.parseInt(data[count++]) * Tile.TILE_HEIGHT;
		int exitX = Integer.parseInt(data[count++]) * Tile.TILE_WIDTH;

		/// Plasam cele 5 diamante "random" pe harta
		ArrayList<Diamond> diamonds = new ArrayList<Diamond>();
		for (int i = 0; i < 5; i++) {
			int x = 0;
			int y = 0;

			do {
				x = random(1, cols - 1);
				y = random(1, rows - 1);

			} while (tiles[y][x].isSolid());

			diamonds.add(new Diamond(x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT));

		}

		Map map = new Map(tiles, diamonds);
		map.setHero(player);
		map.setExit(exitX, exitY);

		return map;
	}

	private static int random(int min, int max) {
		return new Random().nextInt(max - min + 1) + min;
	}
}
