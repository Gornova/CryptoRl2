package it.crypto2.world.entities;

import it.crypto2.G;
import it.crypto2.world.entities.controller.Controller;
import it.crypto2.world.entities.controller.PlayerController;
import it.crypto2.world.entities.controller.WanderController;
import it.marteEngine.ResourceManager;
import it.marteEngine.World;
import it.marteEngine.entity.Entity;

public class EntityFactory {

	public static Entity buildPlayer(World world, int x, int y) {
		PlayerEntity e = new PlayerEntity(x * G.TILE_SIZE, y * G.TILE_SIZE, ResourceManager.getImage(G.ELF));
		Controller c = new PlayerController(e);
		c.setWorld(world);
		e.setController(c);
		return e;
	}

	public static Entity buildSpider(World world, int x, int y) {
		EnemyEntity e = new EnemyEntity(x * G.TILE_SIZE, y * G.TILE_SIZE, ResourceManager.getImage(G.SPIDER), G.SPIDER);
		Controller c = new WanderController(e);
		c.setWorld(world);
		e.setController(c);
		return e;
	}

	public static Entity buildRandomMonster(World world, int x, int y) {
		return buildSpider(world, x, y);
	}

}
