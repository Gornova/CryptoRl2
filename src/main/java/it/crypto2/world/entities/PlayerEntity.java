package it.crypto2.world.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import it.crypto2.G;
import it.marteEngine.ME;

public class PlayerEntity extends GameEntity {

	public boolean attacking = false;

	public int hp = 50;
	public int maxHp = 50;
	public int atk = 5;

	public boolean faceRight = true;

	private Image imgLeft;
	private Image imgRight;

	public PlayerEntity(float x, float y, Image img) {
		super(x, y);

		// set id
		name = G.PLAYER;

		// set image
		imgRight = img;
		imgLeft = img.getFlippedCopy(true, false);
		setGraphic(imgRight);

		// define labels for the key
		defineControls();

		// define collision box and type
		setHitBox(0, 0, G.WIDTH, G.HEIGHT);

		addType("player", SOLID);
		depth = 5;

	}

	private void defineControls() {
		define(ME.WALK_UP, Input.KEY_UP, Input.KEY_W);
		define(ME.WALK_DOWN, Input.KEY_DOWN, Input.KEY_S);
		define(ME.WALK_LEFT, Input.KEY_LEFT, Input.KEY_A);
		define(ME.WALK_RIGHT, Input.KEY_RIGHT, Input.KEY_D);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		super.update(container, delta);
		controller.update(delta);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		if (faceRight) {
			setGraphic(imgRight);
		} else {
			setGraphic(imgLeft);
		}
		super.render(container, g);
	}

}
