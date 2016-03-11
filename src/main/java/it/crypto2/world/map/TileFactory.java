package it.crypto2.world.map;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import it.crypto2.G;
import it.crypto2.world.entities.StaticEntity;
import it.marteEngine.ResourceManager;
import it.marteEngine.World;
import it.marteEngine.entity.Entity;

public class TileFactory {

	private static Random rnd = new Random();

	public static StaticEntity buildFloor(int x, int y, World world) {
		StaticEntity ge = new StaticEntity(x * G.TILE_SIZE, y * G.TILE_SIZE, getRandomFloor(), world);
		ge.addType(G.FLOOR);
		ge.collidable = false;
		ge.depth = 1;
		return ge;
	}

	public static StaticEntity buildWall(int x, int y, World world, GameMap map) {
		StaticEntity ge = new StaticEntity(x * G.TILE_SIZE, y * G.TILE_SIZE, getWallImage(map, x, y), world);
		ge.addType(G.WALL, Entity.SOLID);
		ge.depth = 1;
		return ge;
	}

	private static Image getWallImage(GameMap map, int x, int y) {
		SpriteSheet sheet = ResourceManager.getSpriteSheet("walls");
		Image tile;

		boolean above = isWall(map, x, y - 1);
		boolean left = isWall(map, x - 1, y);
		boolean right = isWall(map, x + 1, y);
		boolean below = isWall(map, x, y + 1);

		int index = calculateTileIndex(above, below, left, right);
		if (index < 10) {
			tile = sheet.getSprite(index, 0);
		} else {
			tile = sheet.getSprite(index - 10, 1);
		}
		return tile;
	}

	// TODO: random floors
	private static Image getRandomFloor() {
		int n = rnd.nextInt(6);
		return ResourceManager.getSpriteSheet("walls").getSprite(n, 2);
	}

	private static boolean isWall(GameMap map, int x, int y) {
		if (x >= 0 && y >= 0 && x < map.getWidth() && y < map.getHeight()) {
			return map.get(x, y) == G.W;
		}
		return false;
	}

	private static boolean isFloor(GameMap map, int x, int y) {
		if (x >= 0 && y >= 0 && x < map.getWidth() && y < map.getHeight()) {
			return map.get(x, y) == G.F;
		}
		return false;
	}

	private static int calculateTileIndex(boolean above, boolean below, boolean left, boolean right) {
		int sum = 0;
		if (above)
			sum += 1;
		if (left)
			sum += 2;
		if (below)
			sum += 4;
		if (right)
			sum += 8;
		return sum;
	}

}
