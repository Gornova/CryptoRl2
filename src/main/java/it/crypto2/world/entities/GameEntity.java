package it.crypto2.world.entities;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

import it.crypto2.G;
import it.crypto2.effect.Effect;
import it.crypto2.effect.Shake;
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
	public String description = "";
	public boolean item;
	private List<Effect> effects = new ArrayList<Effect>();

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

		Line l = new Line((int) target.x / 32, (int) target.y / 32, (int) x / 32, (int) y / 32);
		Vector2f actual = new Vector2f(x / 32, y / 32);
		Vector2f current;
		for (Point point : l) {
			current = new Vector2f(point.x, point.y);
			if (actual.distance(current) > G.sight) {
				return false;
			}
			if (G.world.isFloor(point.x, point.y) || G.world.isItem(point.x, point.y)
					|| point.x == x / 32 && point.y == y / 32) {
				G.world.setSaw((int) point.x, (int) point.y);
				continue;
			}
			return false;
		}

		c = new Circle(tx, ty, G.sight);
		if (c.contains(x, y)) {
			return true;
		}

		return true;
	}

	public GameEntity damage(int value) {
		this.hp -= value;
		if (hp <= 0) {
			setDead(true);
		}
		// apply visual damage status
		addEffect(new Shake(this));
		return this;
	}

	private void addEffect(Effect effect) {
		effects.add(effect);
	}

	public void setDead(boolean v) {
		this.dead = v;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if (!effects.isEmpty()) {
			Iterator<Effect> iter = effects.iterator();
			while (iter.hasNext()) {
				Effect effect = iter.next();
				effect.update(container, delta);
				if (effect.completed) {
					iter.remove();
				}
			}
			return;
		}
		super.update(container, delta);
		if (isDead()) {
			G.world.remove(this);
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		if (canSeePlayer()) {
			if (!effects.isEmpty()) {
				for (Effect effect : effects) {
					effect.render(container, g);
				}
				return;
			}
			super.render(container, g);
		}
	}

	protected boolean alreadySeenByPlayer(int tx, int ty) {
		return G.world.alreadySeenByPlayer(tx, ty);
	}

}
