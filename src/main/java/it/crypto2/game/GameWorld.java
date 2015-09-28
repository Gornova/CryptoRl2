package it.crypto2.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import it.crypto2.G;
import it.crypto2.world.map.Generator;
import it.marteEngine.Camera;
import it.marteEngine.World;

public class GameWorld extends World {

	int turn = 1;
	private Gui gui;

	// is floor array for quick check
	boolean floor[][];

	public GameWorld(int id, GameContainer container) {
		super(id, container);

		// init world
		initWorld(container.getWidth() / G.TILE_SIZE, container.getHeight() / G.TILE_SIZE);
		// init gui
		gui = new Gui(this);
		// set camera
		// setCameraOn(G.playerEntity);
		camera = new Camera(this, G.playerEntity, container.getWidth(), container.getHeight(), 400, 400,
				new Vector2f(32, 32));
		camera.setFollow(G.playerEntity);

	}

	private void initWorld(int w, int h) {
		floor = new boolean[w][h];
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
		}
		G.PLAYER_MOVED = false;
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

}