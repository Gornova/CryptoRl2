package it.crypto2.world.map;

import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

import it.crypto2.G;

// handle gamemap data
public class GameMap implements TileBasedMap {

	private Character[][] map;

	private int width;
	private int height;

	public GameMap(int w, int h) {
		this.width = w;
		this.height = h;
		map = new Character[w][h];
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				map[i][j] = G.W;
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public GameMap setWidth(int width) {
		this.width = width;
		return this;
	}

	public int getHeight() {
		return height;
	}

	public GameMap setHeight(int height) {
		this.height = height;
		return this;
	}

	public GameMap set(int x, int y, Character g) {
		map[x][y] = g;
		return this;
	}

	public Character get(int x, int y) {
		return map[x][y];
	}

	public boolean isFree(int tx, int ty) {
		// return !get(tx, ty).isSolid();
		return true;
	}

	public int getWidthInTiles() {
		return width;
	}

	public int getHeightInTiles() {
		return height;
	}

	public void pathFinderVisited(int x, int y) {
	}

	public boolean blocked(PathFindingContext context, int tx, int ty) {
		return !isFree(tx, ty);
	}

	public float getCost(PathFindingContext context, int tx, int ty) {
		return 0;
	}

}
