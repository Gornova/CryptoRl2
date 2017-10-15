package it.crypto2.world.entities.controller;

import it.crypto2.G;
import it.crypto2.world.entities.GameEntity;
import it.marteEngine.World;
import it.marteEngine.entity.Entity;
import it.marteEngine.tween.Ease;
import it.marteEngine.tween.LinearMotion;

public abstract class AbstractController implements Controller {

	protected GameEntity c;
	protected World world;

	public LinearMotion motion;

	public boolean moveLeft;

	public boolean moveRight;

	public boolean moveDown;

	public boolean moveUp;

	public static final float TIME = 3;

	public enum DIR {
		LEFT, RIGHT, UP, DOWN, NONE
	}

	public AbstractController(GameEntity c) {
		this.c = c;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public World getWorld() {
		return world;
	}

	public Entity getEntity() {
		return c;
	}

	protected void move(int i, int j) {
		int tx = (int) (c.x + i * G.TILE_SIZE);
		int ty = (int) (c.y + j * G.TILE_SIZE);
		// check world limit
		if (tx < 0 || tx >= world.getWidth() || ty < 0 || ty >= world.getHeight()) {
			return;
		}
		// check collision
		if (c.collide(Entity.SOLID, tx, ty) == null || c.collide(G.TRAP, tx, ty) == null) {
			c.x = tx;
			c.y = ty;
		}
	}

	public boolean canMove(float tx, float ty) {
		// check world limit
		if (tx < 0 || tx >= world.getWidth() || ty < 0 || ty >= world.getHeight()) {
			return false;
		}
		return c.collide(Entity.SOLID, tx, ty) == null && c.collide(G.TRAP, tx, ty) == null
				&& c.collideWith(G.playerEntity, tx, ty) == null ? true : false;
	}

	protected void makeMove(DIR dir) {
		System.out.println("Move dir: " + dir.toString());
		switch (dir) {
		case UP:
			// move(0, -1);
			moveUp = true;
			break;
		case RIGHT:
			// move(1, 0);
			moveRight = true;
			break;
		case DOWN:
			// move(0, 1);
			moveDown = true;
			break;
		case LEFT:
			// move(-1, 0);
			moveLeft = true;
			break;
		default:
			break;
		}
	}

	public void updateTween(int delta) {
		if (!moveRight && !moveLeft && !moveUp && !moveDown) {
			return;
		}
		float tx = 0;
		float ty = 0;

		if (motion != null) {
			return;
		}
		if (moveRight) {
			tx = c.x + G.speed.x;
			ty = c.y;
			if (canMove(tx, ty)) {
				motion = new LinearMotion(c.x, c.y, tx, ty, TIME, Ease.QUAD_IN);
			}
			moveRight = false;
		}
		if (moveLeft) {
			tx = c.x - G.speed.x;
			ty = c.y;
			if (canMove(tx, ty)) {
				motion = new LinearMotion(c.x, c.y, tx, c.y, TIME, Ease.QUAD_IN);
			}
			moveLeft = false;
		}
		if (moveUp) {
			tx = c.x;
			ty = c.y - G.speed.y;
			if (canMove(tx, ty)) {
				motion = new LinearMotion(c.x, c.y, tx, ty, TIME, Ease.QUAD_IN);
			}
			moveUp = false;
		}
		if (moveDown) {
			tx = c.x;
			ty = c.y + G.speed.y;
			if (canMove(tx, ty)) {
				motion = new LinearMotion(c.x, c.y, tx, ty, TIME, Ease.QUAD_IN);
			}
			moveDown = false;
		}
	}

	public void updateMotion(int delta) {
		if (motion != null) {
			motion.update(delta);
			c.setPosition(motion.getPosition());
			if (motion.isFinished()) {
				motion = null;
			}
		}
	}

	// under 25%
	public boolean lowHealth() {
		return (c.hp <= (c.maxHp * 25 / 100));
	}

}
