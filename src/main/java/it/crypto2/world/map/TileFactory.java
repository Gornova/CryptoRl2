package it.crypto2.world.map;

import it.crypto2.G;
import it.crypto2.world.entities.StaticEntity;
import it.marteEngine.ResourceManager;
import it.marteEngine.World;
import it.marteEngine.entity.Entity;

public class TileFactory {

	public static StaticEntity buildFloor(int x, int y, World world) {
		StaticEntity ge = new StaticEntity(x * G.TILE_SIZE, y * G.TILE_SIZE, ResourceManager.getImage(G.FLOOR), world);
		ge.addType(G.FLOOR);
		ge.collidable = false;
		ge.depth = 1;
		return ge;
	}

	public static StaticEntity buildWall(int x, int y, World world) {
		StaticEntity ge = new StaticEntity(x * G.TILE_SIZE, y * G.TILE_SIZE, ResourceManager.getImage(G.WALL), world);
		ge.addType(G.WALL, Entity.SOLID);
		ge.depth = 1;
		return ge;

	}

}
