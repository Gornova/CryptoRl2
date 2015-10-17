package it.crypto2.world.entities;

import java.awt.Point;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

import it.crypto2.G;
import it.crypto2.util.Line;
import it.crypto2.world.entities.controller.Controller;
import it.marteEngine.entity.Entity;

public class GameEntity extends Entity {

	private static final String DAMAGE_EFFECT = "damageEffect";

	protected Controller controller;

	private Circle c;

	public int hp = 0;
	public int maxHp = 0;
	public int atk = 5;
	public int dfk = 0;

	private boolean dead;

	public String description = "";

	public boolean item;

	private boolean damageEffect;

	private int damageTimer;

	private Color damageColor;

	public boolean isDead() {
		return dead;
	}

	public GameEntity(float x, float y) {
		super(x, y);
		this.damageColor = new Color(250, 0, 0, 0.2f);
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
			if ((G.world.isFloor(point.x, point.y) || G.world.isItem(point.x, point.y))
					|| (point.x == x / 32 && point.y == y / 32)) {
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
		damageEffect = true;
		return this;
	}

	public void setDead(boolean v) {
		this.dead = v;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		super.update(container, delta);
		if (damageEffect) {
			damageEffect = false;
			damageTimer = 250;
		}
		if (damageTimer > 0) {
			damageTimer -= delta;
			if (damageTimer <= 0) {
				damageTimer = 0;
			}
		}
		if (isDead()) {
			G.world.remove(this);
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		if (canSeePlayer()) {
			super.render(container, g);
			if (damageTimer > 0) {
				Image flash = currentImage.copy();
				flash.drawFlash(x, y, currentImage.getWidth(), currentImage.getHeight(), damageColor);
			}
		}
	}

	protected boolean alreadySeenByPlayer(int tx, int ty) {
		return G.world.alreadySeenByPlayer(tx, ty);
	}

}
