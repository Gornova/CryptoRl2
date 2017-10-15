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
		e.description = "spiders are one of the most common animals in subterrain world. Spiders attack only if provoked";
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
		e.description = "stygian birds are small birds with no eyes. They are weak and cowardly";
		e.hp = 8;
		e.maxHp = 8;
		e.atk = 10;

		Controller c = new WanderController(e);
		c.setWorld(world);
		e.setController(c);
		return e;
	}

	public static Entity buildAberration(World world, int x, int y) {
		EnemyEntity e = new EnemyEntity(x * G.TILE_SIZE, y * G.TILE_SIZE, ResourceManager.getImage(G.ABERRATION),
				G.ABERRATION);
		e.description = "aberration are monsters that corrupts and consumes everything. They are colossal and always hungry";
		e.hp = 50;
		e.maxHp = 50;
		e.atk = 5;
		e.dfk = 5;

		Controller c = new AttackOnSightController(e);
		c.setWorld(world);
		e.setController(c);
		return e;
	}

	public static Entity buildRat(World world, int x, int y) {
		EnemyEntity e = new EnemyEntity(x * G.TILE_SIZE, y * G.TILE_SIZE, ResourceManager.getImage(G.RAT), G.RAT);
		e.description = "rat is fast and always try to eat everything";
		e.hp = 10;
		e.maxHp = 10;
		e.atk = 5;

		Controller c = new WanderController(e);
		c.setWorld(world);
		e.setController(c);
		return e;
	}

	public static Entity buildSnake(World world, int x, int y) {
		EnemyEntity e = new EnemyEntity(x * G.TILE_SIZE, y * G.TILE_SIZE, ResourceManager.getImage(G.SNAKE), G.SNAKE);
		e.description = "snake is a dangerouse animal in any situation";
		e.hp = 15;
		e.maxHp = 15;
		e.atk = 10;

		Controller c = new WanderController(e);
		c.setWorld(world);
		e.setController(c);
		return e;
	}

	public static Entity buildFungus(World world, int x, int y) {
		EnemyEntity e = new EnemyEntity(x * G.TILE_SIZE, y * G.TILE_SIZE, ResourceManager.getImage(G.FUNGUS), G.FUNGUS);
		e.description = "fungus are maleodorante creatures with low defense, but very dungerous";
		e.hp = 25;
		e.maxHp = 25;
		e.atk = 5;

		Controller c = new AttackOnSightController(e);
		c.setWorld(world);
		e.setController(c);
		return e;
	}

	public static Entity buildShade(World world, int x, int y) {
		EnemyEntity e = new EnemyEntity(x * G.TILE_SIZE, y * G.TILE_SIZE, ResourceManager.getImage(G.SHADE), G.SHADE);
		e.description = "shade are made of darkness and mistery, don't provoke a Shade!";
		e.hp = 100;
		e.maxHp = 100;
		e.atk = 10;

		Controller c = new WanderController(e);
		c.setWorld(world);
		e.setController(c);
		return e;
	}

	public static Entity buildRing(World world, int x, int y) {
		EnemyEntity e = new EnemyEntity(x * G.TILE_SIZE, y * G.TILE_SIZE, ResourceManager.getImage(G.RING), G.RING);
		e.description = "more a magical trap than a living thing...";
		e.hp = 25;
		e.maxHp = 25;
		e.atk = 15;

		Controller c = new AttackOnSightController(e);
		c.setWorld(world);
		e.setController(c);
		return e;
	}

	public static Entity buildFormian(World world, int x, int y) {
		EnemyEntity e = new EnemyEntity(x * G.TILE_SIZE, y * G.TILE_SIZE, ResourceManager.getImage(G.FORMIAN),
				G.FORMIAN);
		e.description = "a formian is an intelligent and dungerous ant like creature";
		e.hp = 125;
		e.maxHp = 125;
		e.atk = 20;

		Controller c = new WanderController(e);
		c.setWorld(world);
		e.setController(c);
		return e;
	}

	public static Entity buildRedFlame(World world, int x, int y) {
		EnemyEntity e = new EnemyEntity(x * G.TILE_SIZE, y * G.TILE_SIZE, ResourceManager.getImage(G.REDFLAME),
				G.REDFLAME);
		e.description = "a red cold flame that consumes everything";
		e.hp = 25;
		e.maxHp = 25;
		e.atk = 20;

		Controller c = new WanderController(e);
		c.setWorld(world);
		e.setController(c);
		return e;
	}

	public static Entity buildBlueFlame(World world, int x, int y) {
		EnemyEntity e = new EnemyEntity(x * G.TILE_SIZE, y * G.TILE_SIZE, ResourceManager.getImage(G.BLUEFLAME),
				G.BLUEFLAME);
		e.description = "an impossible blue flame, impossible to extinguish";
		e.hp = 200;
		e.maxHp = 200;
		e.atk = 15;

		Controller c = new WanderController(e);
		c.setWorld(world);
		e.setController(c);
		return e;
	}

	public static Entity buildRandomMonster(World world, int x, int y, int level) {
		Random rnd = new Random();
		int n = 0;
		if (level == 1) {
			n = 2;
			switch (rnd.nextInt(n)) {
			case 0:
				return buildSpider(world, x, y);
			case 1:
				return buildStygianBird(world, x, y);
			default:
				return buildSnake(world, x, y);
			}
		} else if (level == 2) {
			n = 4;
			switch (rnd.nextInt(n)) {
			case 0:
				return buildSpider(world, x, y);
			case 1:
				return buildStygianBird(world, x, y);
			case 2:
				return buildRat(world, x, y);
			case 3:
				return buildSnake(world, x, y);
			default:
				return buildSnake(world, x, y);
			}
		} else if (level == 3) {
			n = 7;
			switch (rnd.nextInt(n)) {
			case 0:
				return buildSpider(world, x, y);
			case 1:
				return buildStygianBird(world, x, y);
			case 2:
				return buildAberration(world, x, y);
			case 3:
				return buildRat(world, x, y);
			case 4:
				return buildSnake(world, x, y);
			case 5:
				return buildFungus(world, x, y);
			case 6:
				return buildShade(world, x, y);
			default:
				return buildSnake(world, x, y);
			}
		} else if (level == 4 || level == 5) {
			n = 9;
			switch (rnd.nextInt(n)) {
			case 0:
				return buildSpider(world, x, y);
			case 1:
				return buildStygianBird(world, x, y);
			case 2:
				return buildAberration(world, x, y);
			case 3:
				return buildRat(world, x, y);
			case 4:
				return buildSnake(world, x, y);
			case 5:
				return buildFungus(world, x, y);
			case 6:
				return buildShade(world, x, y);
			case 7:
				return buildRing(world, x, y);
			case 8:
				return buildFormian(world, x, y);
			default:
				return buildSnake(world, x, y);
			}
		}
		return buildSnake(world, x, y);
	}

	public static Entity buildPotion(World world, int x, int y) {
		Potion e = new Potion(x * G.TILE_SIZE, y * G.TILE_SIZE, ResourceManager.getImage(G.POTION), G.POTION);
		e.item = true;
		e.description = "potions are always useful to recovery from wounds";
		return e;
	}

	public static Entity buildTorch(World world, int x, int y) {
		Torch e = new Torch(x * G.TILE_SIZE, y * G.TILE_SIZE, ResourceManager.getImage(G.TORCH), G.TORCH);
		e.item = true;
		e.description = "torchs are most useful items in a world of darkness";
		return e;
	}

	private static Entity buildInvisibilityScroll(int x, int y) {
		InvisibilityScroll e = new InvisibilityScroll(x * G.TILE_SIZE, y * G.TILE_SIZE,
				ResourceManager.getImage(G.INVISIBILITY_SCROLL), G.INVISIBILITY_SCROLL);
		e.item = true;
		e.description = "invisibily scrolls are powerful magic artifact used to vanish from powerful enemies";
		return e;
	}

	public static Entity buildSword(World world, int x, int y) {
		Sword e = new Sword(x * G.TILE_SIZE, y * G.TILE_SIZE, ResourceManager.getImage(G.SWORD), G.SWORD);
		e.item = true;
		e.description = "a better sword.. to survive a little more!";
		return e;
	}

	public static Entity buildShield(World world, int x, int y) {
		Shield e = new Shield(x * G.TILE_SIZE, y * G.TILE_SIZE, ResourceManager.getImage(G.SHIELD), G.SHIELD);
		e.item = true;
		e.description = "a good shield is always useful!";
		return e;
	}

	public static Entity buildRandomItem(World world, int x, int y) {
		Random rnd = new Random();
		switch (rnd.nextInt(6)) {
		case 0:
			return buildPotion(world, x, y);
		case 1:
			return buildTorch(world, x, y);
		case 2:
			return buildInvisibilityScroll(x, y);
		case 3:
			return buildTrap(world, x, y);
		case 4:
			return buildSword(world, x, y);
		case 5:
			return buildShield(world, x, y);

		default:
			return buildTrap(world, x, y);
		}
	}

	public static Entity buildTrap(World world, int x, int y) {
		Trap e = new Trap(x * G.TILE_SIZE, y * G.TILE_SIZE, ResourceManager.getImage(G.TRAP), G.TRAP);
		e.depth = 4;
		return e;
	}

	public static Entity buildExit(GameWorld world, int x, int y) {
		Exit e = new Exit(x * G.TILE_SIZE, y * G.TILE_SIZE, ResourceManager.getImage(G.EXIT), G.EXIT);
		return e;
	}

}
