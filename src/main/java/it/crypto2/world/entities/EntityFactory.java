package it.crypto2.world.entities;

import java.util.Random;

import it.crypto2.G;
import it.crypto2.game.GameWorld;
import it.crypto2.world.entities.controller.AttackOnSightController;
import it.crypto2.world.entities.controller.Controller;
import it.crypto2.world.entities.controller.PlayerController;
import it.crypto2.world.entities.controller.WanderController;
import it.marteEngine.ResourceManager;
import it.marteEngine.World;
import it.marteEngine.entity.Entity;

public class EntityFactory {

	public static Entity buildPlayer(World world, int x, int y) {
		PlayerEntity e = new PlayerEntity(x * G.TILE_SIZE, y * G.TILE_SIZE, ResourceManager.getImage(G.ELF));
		e.hp = 50;
		e.maxHp = 50;
		e.atk = 5;
		e.addType(G.SPIDER, G.ABERRATION, G.STYGIANBIRD);
		Controller c = new PlayerController(e);
		c.setWorld(world);
		e.setController(c);
		return e;
	}

	public static Entity buildSpider(World world, int x, int y) {
		EnemyEntity e = new EnemyEntity(x * G.TILE_SIZE, y * G.TILE_SIZE, ResourceManager.getImage(G.SPIDER), G.SPIDER);
		e.description = "spiders are one of the most common animals in subterrain world. Could attack if provoked";
		e.hp = 15;
		e.maxHp = 15;
		e.atk = 5;

		Controller c = new WanderController(e);
		c.setWorld(world);
		e.setController(c);
		return e;
	}

	public static Entity buildStygianBird(World world, int x, int y) {
		EnemyEntity e = new EnemyEntity(x * G.TILE_SIZE, y * G.TILE_SIZE, ResourceManager.getImage(G.STYGIANBIRD),
				G.STYGIANBIRD);
		e.description = "stygian bird are small birds with no eyes. It is weak and cowardly";
		e.hp = 8;
		e.maxHp = 40;
		e.atk = 10;

		Controller c = new WanderController(e);
		c.setWorld(world);
		e.setController(c);
		return e;
	}

	public static Entity buildAberration(World world, int x, int y) {
		EnemyEntity e = new EnemyEntity(x * G.TILE_SIZE, y * G.TILE_SIZE, ResourceManager.getImage(G.ABERRATION),
				G.ABERRATION);
		e.description = "aberration is a monster that corrupt and consume everything. It is colossal and very dangerous";
		e.hp = 50;
		e.maxHp = 50;
		e.atk = 5;

		Controller c = new AttackOnSightController(e);
		c.setWorld(world);
		e.setController(c);
		return e;
	}

	public static Entity buildRandomMonster(World world, int x, int y) {
		Random rnd = new Random();
		switch (rnd.nextInt(3)) {
		case 0:
			return buildSpider(world, x, y);
		case 1:
			return buildStygianBird(world, x, y);
		case 2:
			return buildAberration(world, x, y);

		default:
			return buildStygianBird(world, x, y);
		}
	}

	public static Entity buildPotion(World world, int x, int y) {
		Potion e = new Potion(x * G.TILE_SIZE, y * G.TILE_SIZE, ResourceManager.getImage(G.POTION), G.POTION);
		return e;
	}

	public static Entity buildTorch(World world, int x, int y) {
		Torch e = new Torch(x * G.TILE_SIZE, y * G.TILE_SIZE, ResourceManager.getImage(G.TORCH), G.TORCH);
		return e;
	}

	public static Entity buildRandomItem(World world, int x, int y) {
		Random rnd = new Random();
		switch (rnd.nextInt(3)) {
		case 0:
			return buildPotion(world, x, y);
		case 1:
			return buildTorch(world, x, y);
		case 21:
			return buildInvisibilityScroll(world, x, y);
		default:
			return buildPotion(world, x, y);
		}
	}

	private static Entity buildInvisibilityScroll(World world, int x, int y) {
		InvisibilityScroll e = new InvisibilityScroll(x * G.TILE_SIZE, y * G.TILE_SIZE,
				ResourceManager.getImage(G.INVISIBILITY_SCROLL), G.INVISIBILITY_SCROLL);
		return e;
	}

	public static Entity buildExit(GameWorld world, int x, int y) {
		Exit e = new Exit(x * G.TILE_SIZE, y * G.TILE_SIZE, ResourceManager.getImage(G.EXIT), G.EXIT);
		return e;
	}

}
