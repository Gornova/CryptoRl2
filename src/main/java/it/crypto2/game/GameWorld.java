package it.crypto2.game;

import java.awt.Point;
import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Mover;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.PathFinder;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

import it.crypto2.G;
import it.crypto2.Launcher;
import it.crypto2.gui.Gui;
import it.crypto2.world.entities.StaticEntity;
import it.crypto2.world.map.Generator;
import it.marteEngine.Camera;
import it.marteEngine.ResourceManager;
import it.marteEngine.SFX;
import it.marteEngine.World;
import it.marteEngine.entity.Entity;

public class GameWorld extends World implements TileBasedMap {

	private Gui gui;
	// is floor array for quick check
	boolean floor[][];
	private boolean[][] walls;
	private PathFinder pathFinder;
	private int widthInTiles;
	private int heightInTiles;
	private boolean[][] item;
	private boolean[][] saw;
	private boolean win;
	private boolean nextLevel;
	private StateBasedGame game;
	private boolean pressEscapeState;
	// private LightMap lightMap;
	private Image[][] tiles;
	private Image alphaMap;
	private Image screen;

	public GameWorld(int id, GameContainer container) {
		super(id, container);
		// init world
		widthInTiles = container.getWidth() / G.TILE_SIZE;
		heightInTiles = container.getHeight() / G.TILE_SIZE;
		try {
			screen = new Image(container.getWidth() * 2, container.getHeight() * 2);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initWorld(int w, int h) {
		floor = new boolean[w][h];
		walls = new boolean[w][h];
		item = new boolean[w][h];
		saw = new boolean[w][h];
		tiles = new Image[w][h];
		clear();
		Generator g = new Generator(w, h);
		g.generate(this);
		// set world limit
		setWidth(w * G.TILE_SIZE * 10);
		setHeight(h * G.TILE_SIZE * 10);

		// init gui
		gui = new Gui(this);
		// set camera
		// setCameraOn(G.playerEntity);
		camera = new Camera(this, G.playerEntity, container.getWidth(), container.getHeight(), 400, 400,
				new Vector2f(32, 32));
		camera.setFollow(G.playerEntity);
		G.world = this;
		pathFinder = new AStarPathFinder(this, 100, false);

		if (G.currentLevel == 1) {
			gui.addMessage("Welcome to CryptoRl 2 !");
		}

		// add light at player position
		// lighting.addLight(new Light(G.playerEntity.x, G.playerEntity.y,
		// 0.5f));
		// lightMap = new LightMap(0, 0, 32);
		// lightMap.addLight(new Light(G.playerEntity.x, G.playerEntity.y, 200,
		// Color.white));
		alphaMap = ResourceManager.getImage("light");
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if (this.game == null) {
			this.game = game;
		}
		if (win) {
			// win!
			game.enterState(Launcher.WIN_STATE, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
			win = false;
			G.currentLevel = 1;
			G.turn = 1;
			return;
		}
		if (nextLevel) {
			nextLevel = false;
			G.currentLevel++;
			initWorld(widthInTiles, heightInTiles);
			return;
		}
		if (G.BLOCK_TIMER > 0) {
			if (container.getInput().isKeyPressed(Input.KEY_SPACE)) {
				G.BLOCK_TIMER--;
			}
			return;
		} else {
			super.update(container, game, delta);
		}
		if (G.PLAYER_MOVED) {
			SFX.playSound(G.STEP_SOUND);
			if (G.playerEntity.invisible) {
				if (G.INVISIBILITY_TIMER > 0) {
					G.INVISIBILITY_TIMER -= 1;
				} else {
					G.playerEntity.invisible = false;
				}
			}
			G.turn++;
			gui.update(container, game, delta);
		}
		G.PLAYER_MOVED = false;
		if (find(G.PLAYER) == null) {
			System.out.println("player dead, game over");
			game.enterState(Launcher.GAME_OVER_STATE, new FadeOutTransition(Color.black),
					new FadeInTransition(Color.black));
		}
		if (container.getInput().isKeyPressed(Input.KEY_F2)) {
			if (container.isFullscreen()) {
				container.setFullscreen(false);
			} else {
				container.setFullscreen(true);
			}
		}
		// lightMap.update(container, delta);
	}

	@Override
	public void render(GameContainer container, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
		// TODO render lights http://www.java-gaming.org/index.php?topic=26729.0
		Graphics g2 = screen.getGraphics();

		// normal rendering
		super.render(container, stateBasedGame, g2);

		g2.resetTransform();
		g2.clearAlphaMap();
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		g2.setDrawMode(Graphics.MODE_ALPHA_MAP);
		alphaMap.drawCentered(G.playerEntity.x, G.playerEntity.y);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		g2.setDrawMode(Graphics.MODE_ALPHA_BLEND);

		// g.translate(-camera.cameraX, -camera.cameraY);
		g.drawImage(screen, 0, 0);
		g.resetTransform();

		// render gui
		gui.render(container, stateBasedGame, g);

		if (pressEscapeState) {
			g.drawImage(ResourceManager.getImage("escConfirm"), container.getWidth() / 2 - 120,
					container.getHeight() / 2);
		}
		// lightMap.render(container, g);
		// g.drawImage(lights, 0, 0);
		// RENDERING EVERITHING

	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
		initWorld(widthInTiles, heightInTiles);
		if (G.fullScreen) {
			container.setFullscreen(true);
		} else {
			container.setFullscreen(false);
		}
		SFX.playMusic(G.MUSIC1);
		pressEscapeState = false;

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

	public boolean alreadySeenByPlayer(int x, int y) {
		return saw[x][y];
	}

	public void setSaw(int x, int y) {
		saw[x][y] = true;
	}

	public void nextLevel() {
		if (G.currentLevel >= G.MAX_LEVEL) {
			win = true;
			return;
		} else {
			nextLevel = true;
		}
	}

	public int getSawTiles() {
		int t = 0;
		for (int i = 0; i < widthInTiles; i++) {
			for (int j = 0; j < heightInTiles; j++) {
				if (saw[i][j] && floor[i][j]) {
					t++;
				}
			}
		}
		return t;
	}

	public int getTileNumber() {
		int t = 0;
		for (int i = 0; i < widthInTiles; i++) {
			for (int j = 0; j < heightInTiles; j++) {
				if (floor[i][j]) {
					t++;
				}
			}
		}
		return t;
	}

	@Override
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_ESCAPE) {
			if (!pressEscapeState) {
				pressEscapeState = true;
			} else {
				game.enterState(Launcher.MENU_STATE, new FadeOutTransition(Color.black),
						new FadeInTransition(Color.black));
			}
		} else {
			pressEscapeState = false;
		}
	}

	@Override
	public void add(Entity e, int... flags) {
		super.add(e, flags);
		if (e instanceof StaticEntity) {
			tiles[(int) (e.x / 32)][(int) (e.y / 32)] = e.currentImage;
		}
	}

}