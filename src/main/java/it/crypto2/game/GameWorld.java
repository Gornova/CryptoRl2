package it.crypto2.game;

import java.awt.Point;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Mover;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.PathFinder;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

import it.crypto2.G;
import it.crypto2.Launcher;
import it.crypto2.world.map.Generator;
import it.marteEngine.Camera;
import it.marteEngine.World;

public class GameWorld extends World implements TileBasedMap {

	int turn = 1;
	private Gui gui;

	// is floor array for quick check
	boolean floor[][];
	private boolean[][] walls;

	private PathFinder pathFinder;
	private int widthInTiles;
	private int heightInTiles;
	private boolean[][] item;

	public GameWorld(int id, GameContainer container) {
		super(id, container);

		// init world
		widthInTiles = container.getWidth() / G.TILE_SIZE;
		heightInTiles = container.getHeight() / G.TILE_SIZE;
		initWorld(widthInTiles, heightInTiles);
		// init gui
		gui = new Gui(this);
		// set camera
		// setCameraOn(G.playerEntity);
		camera = new Camera(this, G.playerEntity, container.getWidth(), container.getHeight(), 400, 400,
				new Vector2f(32, 32));
		camera.setFollow(G.playerEntity);
		G.world = this;
		pathFinder = new AStarPathFinder(this, 100, false);

		gui.addMessage("Welcome to CryptoRl 2 !");
	}

	private void initWorld(int w, int h) {
		floor = new boolean[w][h];
		walls = new boolean[w][h];
		item = new boolean[w][h];
		Generator g = new Generator(w, h);
		g.generate(this);
		// set world limit
		setWidth(w * G.TILE_SIZE * 10);
		setHeight(h * G.TILE_SIZE * 10);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);
		if (G.PLAYER_MOVED) {
			turn++;
			gui.update(container, game, delta);
		}
		G.PLAYER_MOVED = false;
		if (find(G.PLAYER) == null) {
			System.out.println("player dead, game over");
			game.enterState(Launcher.GAME_OVER_STATE);
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
		super.render(container, stateBasedGame, g);
		// render gui
		gui.render(container, stateBasedGame, g);
	}

	public void setFloor(int i, int j) {
		floor[i][j] = true;
	}

	public boolean isFloor(int i, int j) {
		return floor[i][j];
	}

	public boolean isWall(int x, int y) {
		return walls[x][y];
	}

	public void setWall(int i, int j) {
		walls[i][j] = true;
	}

	public Point getRandomPoint() {
		Random rnd = new Random();
		int tx = rnd.nextInt(widthInTiles);
		int ty = rnd.nextInt(heightInTiles);
		boolean result = false;
		while (!result) {
			if (isFloor(tx, ty)) {
				result = true;
				continue;
			}
			tx = rnd.nextInt(widthInTiles);
			ty = rnd.nextInt(heightInTiles);
		}
		return new Point(tx, ty);
	}

	public int getWidthInTiles() {
		return widthInTiles;
	}

	public int getHeightInTiles() {
		return heightInTiles;
	}

	public void pathFinderVisited(int x, int y) {
	}

	public boolean blocked(PathFindingContext context, int tx, int ty) {
		return walls[tx][ty];
	}

	public float getCost(PathFindingContext context, int tx, int ty) {
		return 0;
	}

	public Path getPath(int sx, int sy, int tx, int ty) {
		return pathFinder.findPath(new DummyMover(), sx, sy, tx, ty);
	}

	private class DummyMover implements Mover {

	}

	public boolean isItem(int x, int y) {
		return item[x][y];
	}

	public void setItem(int i, int j) {
		item[i][j] = true;
	}

	public void addMessage(String m) {
		gui.addMessage(m);

	}

}