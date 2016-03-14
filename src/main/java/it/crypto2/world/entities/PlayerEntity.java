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
import it.marteEngine.SFX;
import it.marteEngine.entity.Entity;

public class PlayerEntity extends GameEntity {

	public boolean attacking = false;

	public boolean faceRight = true;

	private Image imgLeft;
	private Image imgRight;

	public boolean invisible = false;

	private boolean rightFree;

	private boolean leftFree;

	private boolean downFree;

	private boolean upFree;

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
		define(G.WAIT, Input.KEY_SPACE);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		super.update(container, delta);
		controller.update(delta);
		if (!isCollide(x + 32, y)) {
			rightFree = true;
		}
		if (!isCollide(x - 32, y)) {
			leftFree = true;
		}
		if (!isCollide(x, y + 32)) {
			downFree = true;
		}
		if (!isCollide(x, y - 32)) {
			upFree = true;
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		if (faceRight) {
			setGraphic(imgRight);
		} else {
			setGraphic(imgLeft);
		}
		//
		g.drawImage(ResourceManager.getImage(G.SHADOW), x, y);
		super.render(container, g);
		if (invisible) {
			currentImage.drawFlash(x, y, currentImage.getWidth(), currentImage.getHeight(), Color.gray);
		}
		// steps
		if (!isCollide(x + 32, y)) {
			g.drawImage(ResourceManager.getImage("step"), x + 32, y);
		}
		if (!isCollide(x - 32, y)) {
			g.drawImage(ResourceManager.getImage("step"), x - 32, y);
		}
		if (!isCollide(x, y + 32)) {
			g.drawImage(ResourceManager.getImage("step"), x, y + 32);
		}
		if (!isCollide(x, y - 32)) {
			g.drawImage(ResourceManager.getImage("step"), x, y - 32);
		}

		if (ME.debugEnabled) {
			PlayerEntity target = G.playerEntity;
			int tx = (int) (target.x + G.TILE_SIZE / 2);
			int ty = (int) (target.y + G.TILE_SIZE / 2);
			Circle circle = new Circle(tx, ty, G.sight * G.TILE_SIZE);
			g.draw(circle);
		}
	}

	private boolean isCollide(float tx, float ty) {
		return G.world.isWall((int) tx / 32, (int) ty / 32);
	}

	@Override
	public void collisionResponse(Entity other) {
		if (other instanceof EnemyEntity) {
			combat(new MutablePair<GameEntity, GameEntity>(this, (GameEntity) other));
		}
		if (other instanceof Potion) {
			if (hp < maxHp) {
				((Potion) other).cure(this);
				G.world.remove(other);
			} else {
				G.world.addMessage("Player is already at full health");
			}
		}
		if (other instanceof Torch) {
			G.world.remove(other);
			((Torch) other).extend(this);
		}
		if (other instanceof Trap) {
			Trap t = (Trap) other;
			t.execute(this);
		}
		if (other instanceof Sword) {
			if (atk < G.MAX_ATTACK) {
				Sword s = (Sword) other;
				G.world.remove(other);
				s.execute(this);
			} else {
				G.world.addMessage("Player have already best possible sword");
			}
		}
		if (other instanceof Shield) {
			if (dfk < G.MAX_DEFENSE) {
				Shield s = (Shield) other;
				G.world.remove(other);
				s.execute(this);
			} else {
				G.world.addMessage("Player have already best possible shield!");
			}
		}

	}

	public Pair<GameEntity, GameEntity> combat(Pair<GameEntity, GameEntity> input) {
		SFX.playSound(G.HIT_SOUND);
		G.world.addMessage(input.getLeft().name + " attack " + input.getRight().name);

		input.getLeft()
				.damage(input.getRight().atk > input.getLeft().dfk ? input.getRight().atk - input.getLeft().dfk : 0);
		input.getRight()
				.damage(input.getLeft().atk > input.getRight().dfk ? input.getLeft().atk - input.getRight().dfk : 0);
		return input;
	}

}
