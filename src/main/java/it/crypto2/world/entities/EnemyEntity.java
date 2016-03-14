package it.crypto2.world.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import it.crypto2.G;
import it.marteEngine.ResourceManager;

public class EnemyEntity extends GameEntity {

	public boolean attacking = false;
	private Rectangle hbar;

	public EnemyEntity(float x, float y, Image img, String name) {
		super(x, y);

		// set id
		this.name = name;

		// set image
		setGraphic(img);

		// define collision box and type
		setHitBox(0, 0, G.WIDTH, G.HEIGHT);

		addType(name, SOLID);
		depth = 20;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		super.update(container, delta);
		controller.update(delta);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		if (canSeePlayer()) {
			g.drawImage(ResourceManager.getImage(G.SHADOW), x, y);
			super.render(container, g);
			int w = (32 * hp) / maxHp;
			if (w < 0) {
				w = 0;
			} else if (w > 32) {
				w = 32;
			}
			hbar = new Rectangle(x, y, w, 3);
			g.setColor(Color.red);
			g.fill(hbar);
			g.resetTransform();
		}
	}

	public void moveLeft() {
		if (collide(SOLID, x - G.speed.x, y) == null) {
			x -= G.speed.x;
		}
	}

	public void moveRight() {
		if (collide(SOLID, x + G.speed.x, y) == null) {
			x += G.speed.x;
		}
	}

	public void moveDown() {
		if (collide(SOLID, x, y + G.speed.y) == null) {
			y += G.speed.y;
		}
	}

	public void moveUp() {
		if (collide(SOLID, x, y - G.speed.y) == null) {
			y -= G.speed.y;
		}
	}

	@Override
	public void removedFromWorld() {
		G.world.add(new BloodEntity(x, y, ResourceManager.getImage(G.BLOOD), world));
	}

}
