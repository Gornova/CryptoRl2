package it.crypto2.world.entities;

import java.awt.Point;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

import it.crypto2.G;
import it.crypto2.util.Line;
import it.crypto2.world.entities.controller.Controller;
import it.marteEngine.entity.Entity;

public class GameEntity extends Entity {

	protected Controller controller;

	private Circle c;

	public int hp = 0;
	public int maxHp = 0;
	public int atk = 5;
	public int dfk = 0;

	private boolean dead;

	public boolean isDead() {
		return dead;
	}

	public GameEntity(float x, float y) {
		super(x, y);
	}

	public void setController(Controller c) {
		this.controller = c;
	}

	public boolean canSeePlayer() {
		PlayerEntity target = G.playerEntity;
		int tx = (int) (target.x + G.TILE_SIZE / 2);
		int ty = (int) (target.y + G.TILE_SIZE / 2);
		c = new Circle(tx, ty, G.sight);
		if (c.contains(x, y)) {
			return true;
		}

		Line l = new Line((int) target.x / 32, (int) target.y / 32, (int) x / 32, (int) y / 32);
		boolean wallFound = false;
		Vector2f actual = new Vector2f(x / 32, y / 32);
		Vector2f current;
		for (Point point : l) {
			current = new Vector2f(point.x, point.y);
			if (actual.distance(current) > G.sight) {
				return false;
			}
			if (G.world.isFloor(point.x, point.y) || G.world.isItem(point.x, point.y)) {
				continue;
			}
			if (!wallFound && G.world.isWall(point.x, point.y)) {
				wallFound = true;
			} else if (wallFound && G.world.isWall(point.x, point.y)) {
				return false;
			}
		}

		return true;
	}

	public GameEntity damage(int value) {
		this.hp -= value;
		if (hp <= 0) {
			setDead();
		}
		return this;
	}

	private void setDead() {
		this.dead = true;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		super.update(container, delta);
		if (isDead()) {
			G.world.remove(this);
		}
	}

}
