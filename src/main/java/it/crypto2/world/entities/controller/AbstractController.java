package it.crypto2.world.entities.controller;

import it.crypto2.G;
import it.marteEngine.World;
import it.marteEngine.entity.Entity;
import it.marteEngine.tween.Ease;
import it.marteEngine.tween.LinearMotion;

public abstract class AbstractController implements Controller {

	protected Entity c;
	protected World world;
	// private Collider collider;

	public LinearMotion motion;

	public boolean moveLeft;

	public boolean moveRight;

	public boolean moveDown;

	public boolean moveUp;

	public static final float TIME = 3;

	public enum DIR {
		LEFT, RIGHT, UP, DOWN, NONE
	}

	public AbstractController(Entity c) {
		this.c = c;
	}

	public void setWorld(World world) {
		this.world = world;
		// this.collider = new Collider(world);
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
		if (c.collide(c.SOLID, tx, ty) == null) {
			c.x = tx;
			c.y = ty;
		}
		// checkCollision(tx, ty);
	}

	protected void checkCollision(int tx, int ty) {
		// check collision
		if (canMove(tx, ty)) {
			// check tile
			/*
			 * if (world.get(tx, ty).getGlyph() == Glyph.LAVA) { c.damage(10);
			 * world.getGui().addMessage("damage from lava"); }
			 */
			// execute gameelement action
			// world.find(tx, ty).update(c, world);
			// update position
			// c.setX(tx);
			/// c.setY(ty);
		} else {
			// collider.collide(c, world, tx, ty);
		}
	}

	public boolean canMove(float tx, float ty) {
		// check world limit
		if (tx < 0 || tx >= world.getWidth() || ty < 0 || ty >= world.getHeight()) {
			return false;
		}
		return c.collide(Entity.SOLID, tx, ty) == null ? true : false;
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

	// public boolean canSeePlayer(int x, int y) {
	// return !getWorld().getPlayer().isInvisible() &&
	// getWorld().playerCanSee(x, y);
	// }

	public void updateTween(int delta) {
		if (!moveRight && !moveLeft && !moveUp && !moveDown) {
			return;
		}
		if (motion != null) {
			return;
		}
		if (moveRight) {
			motion = new LinearMotion(c.x, c.y, c.x + G.speed.x, c.y, TIME, Ease.QUAD_IN);
			moveRight = false;
		}
		if (moveLeft) {
			motion = new LinearMotion(c.x, c.y, c.x - G.speed.x, c.y, TIME, Ease.QUAD_IN);
			moveLeft = false;
		}
		if (moveUp) {
			motion = new LinearMotion(c.x, c.y, c.x, c.y - G.speed.y, TIME, Ease.QUAD_IN);
			moveUp = false;
		}
		if (moveDown) {
			moveDown = false;
			motion = new LinearMotion(c.x, c.y, c.x, c.y + G.speed.y, TIME, Ease.QUAD_IN);
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
}
