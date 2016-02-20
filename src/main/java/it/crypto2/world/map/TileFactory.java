package it.crypto2.world.map;

import java.util.Random;

import org.newdawn.slick.Image;

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

	public static StaticEntity buildWall(int x, int y, World world) {
		StaticEntity ge = new StaticEntity(x * G.TILE_SIZE, y * G.TILE_SIZE, ResourceManager.getImage(G.WALL), world);
		ge.addType(G.WALL, Entity.SOLID);
		ge.depth = 1;
		return ge;
	}

	private static Image getRandomFloor() {
		int n = rnd.nextInt(13);
		return ResourceManager.getImage(G.FLOOR + n);
	}

}
