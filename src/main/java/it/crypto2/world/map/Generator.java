package it.crypto2.world.map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.geom.Vector2f;

import it.crypto2.G;
import it.crypto2.game.GameWorld;
import it.crypto2.world.entities.EntityFactory;
import it.crypto2.world.entities.PlayerEntity;
import it.marteEngine.World;
import it.marteEngine.entity.Entity;

public class Generator {

	private Point location;
	private Random rnd = new Random();
	private int tiles = 0;
	private char lastDir;

	// public static final int FLOOR = 1;
	private GameMap map;
	private int w;
	private int h;
	private Point playerStartingPoint = null;
	private boolean[][] monsters;
	private boolean[][] item;
	private boolean[][] floor;
	private boolean[][] trap;

	public Point getPlayerStartingPoint() {
		return playerStartingPoint;
	}

	public Generator(int w, int h) {
		map = new GameMap(w, h);
		this.w = w;
		this.h = h;
		floor = new boolean[w][h];
		monsters = new boolean[w][h];
		item = new boolean[w][h];
		trap = new boolean[w][h];
	}

	public void set(Point location, int t) {
		if (location.x > 0 && location.y > 0 && location.x < w - 1 && location.y < h - 1) {
			map.set(location.x, location.y, G.F);
		}
	}

	public boolean isFloor(int i, int j) {
		// return map.get(i, j) != null && map.get(i, j).isGround();
		return true;
	}

	/*
	 * public static void main(String[] args) throws IOException { double
	 * percentage = 0.75; int width = 90; int height = 35; int n = 1;
	 * 
	 * while (n < 100) { Generator generator = new Generator(width, height);
	 * generator.generate(width, height, percentage);
	 * 
	 * // draw image BufferedImage image = new BufferedImage(width, height,
	 * BufferedImage.TYPE_INT_RGB); image.createGraphics(); Graphics2D g =
	 * (Graphics2D) image.getGraphics(); for (int i = 0; i < width; i++) { for
	 * (int j = 0; j < height; j++) { if (generator.isFloor(i, j)) {
	 * g.setColor(Color.white); g.drawLine(i, j, i, j); g.setColor(Color.black);
	 * } } } image.flush(); ImageIO.write(image, "png", new File(n + ".png"));
	 * n++; }
	 * 
	 * }
	 */

	private GameMap generate(int w, int h, double percentage) {
		location = randomStart(w, h, 5);
		//// System.out.println("Start from: " + location);

		int targetTiles = (int) (w * h * percentage);
		////// System.out.println("target tiles :" + targetTiles);

		lastDir = getRandomDir(' ');
		while (tiles < targetTiles) {
			tiles++;
			set(location, G.F);
			chooseLocation(w, h);
		}
		// removeIsland(w, h);

		return map;
	}

	// remove blocks 1x1 alone
	/*
	 * private void removeIsland(int w, int h) { for (int i = 1; i < w - 1; i++)
	 * { for (int j = 1; j - 1 < h; j++) { if (map[i - 1][j - 1] == FLOOR &&
	 * map[i][j - 1] == FLOOR && map[i + 1][j - 1] == FLOOR && map[i - 1][j] ==
	 * FLOOR && map[i + 1][j] == FLOOR && map[i - 1][j + 1] == FLOOR && map[i][j
	 * + 1] == FLOOR && map[i][j + 1] == FLOOR) { //System.out.println(
	 * "Island found"); map[i][j] = FLOOR; } } } }
	 */

	private void chooseLocation(int w, int h) {
		char currentDir = getRandomDir(lastDir);
		int test = 0;
		while (test < 100 && (!isValid(currentDir, w, h) || isFloor(currentDir, w, h))) {
			currentDir = getRandomDir(lastDir);
			test++;
		}
		// System.out.println("current " + currentDir);
		// qui
		location = apply(location, currentDir);
		// ---
		lastDir = currentDir;

	}

	private boolean isFloor(char d, int w, int h) {
		if (!isValid(d, w, h)) {
			return false;
		}
		Point next = new Point(location);
		switch (d) {
		case 'U':
			next.y -= 1;
			break;
		case 'D':
			next.y += 1;
			break;
		case 'L':
			next.x -= 1;
			break;
		case 'R':
			next.x += 1;
			break;
		default:
			break;
		}
		return map.get(next.x, next.y) != null && map.get(next.x, next.y) == G.F;
		// return map[next.x][next.y] == FLOOR;

	}

	private Point apply(Point l, char d) {
		switch (d) {
		case 'U':
			l.y -= 1;
			break;
		case 'D':
			l.y += 1;
			break;
		case 'L':
			l.x -= 1;
			break;
		case 'R':
			l.x += 1;
			break;
		default:
			break;
		}
		return l;
	}

	private boolean isValid(char d, int w, int h) {
		Point next = new Point(location);
		switch (d) {
		case 'U':
			next.y -= 1;
			break;
		case 'D':
			next.y += 1;
			break;
		case 'L':
			next.x -= 1;
			break;
		case 'R':
			next.x += 1;
			break;
		default:
			break;
		}
		// must be inside map size, minus and plus one, to give dungeon a border
		// of 1
		return next.x > 1 && next.y > 1 && next.x < w - 1 && next.y < h - 1;
	}

	// get random start near center +- of value
	private Point randomStart(int w, int h, int v) {
		int cx = w / 2;
		int cy = h / 2;
		int cv = rnd.nextInt(v);
		boolean px = rnd.nextBoolean();
		boolean py = rnd.nextBoolean();
		int x = 0;
		int y = 0;
		x = px ? cx + cv : cx - cv;
		y = py ? cy + cv : cy - cv;
		return new Point(x, y);
	}

	// return a random dir
	//
	// 0,U,0
	// L,0,R
	// 0,D,0
	//
	// where U = move up
	// L = move left
	// D = move down
	// R = move right
	//
	// direction must be not opposite to other direction, for example, next dir
	// must not be U if before there is a down
	private char getRandomDir(Character lastDir) {
		ArrayList<Character> dirs = new ArrayList<Character>();
		dirs.add('U');
		dirs.add('L');
		dirs.add('R');
		dirs.add('D');
		dirs.remove(lastDir);
		dirs.remove(getOpposite(lastDir));
		return (char) dirs.get(rnd.nextInt(dirs.size()));
	}

	private Character getOpposite(Character d) {
		char r = ' ';
		switch (d) {
		case 'U':
			r = 'D';
			break;
		case 'D':
			r = 'U';
			break;
		case 'L':
			r = 'R';
			break;
		case 'R':
			r = 'L';
			break;
		default:
			break;
		}
		return r;
	}

	public GameWorld generate(GameWorld world) {
		double percentage = 0.75;
		int creatures = 0;
		int maximumCreatures = 5;
		int items = 0;
		int maximumItems = 8;
		double cper = 0.15;
		double pper = 0.05; // TODO: add random
		double bper = 0.01;
		int trapnumber = 0;
		generate(w, h, percentage);
		int c = 0;
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				if (map.get(i, j) != null) {
					if (map.get(i, j) == G.F) {
						c++;
						world.add(TileFactory.buildFloor(i, j, world));
						world.setFloor(i, j);
						floor[i][j] = true;
						if (c == 2) {
							playerStartingPoint = new Point(i, j);
							G.playerEntity = (PlayerEntity) EntityFactory.buildPlayer(world, i, j);
							world.add(G.playerEntity);
						} else if (rnd.nextDouble() < cper && creatures < maximumCreatures
								&& !nearCreature(world, i, j)) {
							creatures++;
							Entity monster = EntityFactory.buildRandomMonster(world, i, j, G.currentLevel);
							world.add(monster);
							monsters[i][j] = true;
							// world.set(i, j, TileFactory.buildFloor(i, j));
						} else if (rnd.nextDouble() < pper && items < maximumItems && !nearItem(world, i, j)) {
							items++;
							world.add(EntityFactory.buildRandomItem(world, i, j));
							world.setItem(i, j);
							item[i][j] = true;
						} else if (rnd.nextDouble() < bper && trapnumber < 10 && !nearTrap(world, i, j)) {
							trap[i][j] = true;
							trapnumber++;
							world.add(EntityFactory.buildTrap(world, i, j));
						}

					} else if (map.get(i, j) == G.W) {
						world.add(TileFactory.buildWall(i, j, world, map));
						world.setWall(i, j);
						floor[i][j] = false;
					}
				} else {
					// world.add(TileFactory.buildWall(i, j));
				}
			}
		}
		// place player
		// build starting point for player

		// place exit
		Point p = null;
		while (p == null) {
			p = getRandomPoint();
			if (nearPlayer(p.x, p.y)) {
				p = null;
			}
		}
		world.add(EntityFactory.buildExit(world, p.x, p.y));

		// GameElement exit = TileFactory.buildExit((int) p.getX(), (int)
		// p.getY());
		// world.set((int) p.getX(), (int) p.getY(), exit);
		// // System.out.println("Placed Exit");
		//
		// // remove player
		// world.getElements().remove(player);
		// int tx = getPlayerStartingPoint().x + 1;
		// int ty = getPlayerStartingPoint().y;
		// world.add(EntityFactory.buildTrap(world, tx, ty));

		return world;
	}

	private boolean nearTrap(GameWorld world, int i, int j) {
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				if (trap[x][y]) {
					if (near(i, j, x, y, 10)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean nearPlayer(int x, int y) {
		return near(playerStartingPoint.x, playerStartingPoint.y, x, y, 10);
	}

	private Point getRandomPoint() {
		// get randompoint using floor
		int f = 0;
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				if (floor[i][j])
					f++;
			}

		}
		// in f number of free floor
		Random rnd = new Random();
		int s = rnd.nextInt(f);
		int k = 0;
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				if (floor[i][j]) {
					k++;
				}
				if (k == s) {
					return new Point(i, j);
				}
			}

		}
		return null;
	}

	private boolean nearItem(GameWorld world, int i, int j) {
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				if (item[x][y]) {
					if (near(i, j, x, y, 10)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	// private boolean nearItem(WorldInterface world, int i, int j) {
	// if (world.getElements() == null) {
	// return false;
	// }
	// for (GameElement ge : world.getElements()) {
	// if (ge instanceof Item) {
	// if (near(i, j, ge, 50)) {
	// return true;
	// }
	// }
	// }
	// return false;
	// }
	//
	private boolean nearCreature(World world, int i, int j) {
		if (playerStartingPoint != null && near(i, j, playerStartingPoint.x, playerStartingPoint.y, 10)) {
			return true;
		}
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				if (monsters[x][y]) {
					if (near(i, j, x, y, 10)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean near(int i, int j, int x, int y, int v) {
		Vector2f p = new Vector2f(i, j);
		Vector2f o = new Vector2f(x, y);
		if (Math.abs((p.distance(o))) < v) {
			return true;
		}
		return false;
	}
}
