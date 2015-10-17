package it.crypto2.world.entities;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

import it.crypto2.G;
import it.marteEngine.ME;
import it.marteEngine.ResourceManager;
import it.marteEngine.entity.Entity;

public class PlayerEntity extends GameEntity {

	public boolean attacking = false;

	public boolean faceRight = true;

	private Image imgLeft;
	private Image imgRight;

	public boolean invisible = false;

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
		g.drawImage(ResourceManager.getImage(G.SHADOW), x, y);
		super.render(container, g);
		if (invisible) {
			currentImage.drawFlash(x, y, currentImage.getWidth(), currentImage.getHeight(), Color.gray);
		}

		if (ME.debugEnabled) {
			PlayerEntity target = G.playerEntity;
			int tx = (int) (target.x + G.TILE_SIZE / 2);
			int ty = (int) (target.y + G.TILE_SIZE / 2);
			Circle circle = new Circle(tx, ty, G.sight * G.TILE_SIZE);
			g.draw(circle);
		}
	}

	@Override
	public void collisionResponse(Entity other) {
		if (other instanceof EnemyEntity) {
			combat(new MutablePair<GameEntity, GameEntity>(this, (GameEntity) other));
		}
		if (other instanceof Potion) {
			((Potion) other).cure(this);
			G.world.remove(other);
		}
		if (other instanceof Torch) {
			G.world.remove(other);
			((Torch) other).extend(this);
		}
	}

	public Pair<GameEntity, GameEntity> combat(Pair<GameEntity, GameEntity> input) {
		G.world.addMessage(input.getLeft().name + " attack " + input.getRight().name);

		input.getLeft()
				.damage(input.getRight().atk > input.getLeft().dfk ? input.getRight().atk - input.getLeft().dfk : 0);
		input.getRight()
				.damage(input.getLeft().atk > input.getRight().dfk ? input.getLeft().atk - input.getRight().dfk : 0);
		return input;
	}

}
