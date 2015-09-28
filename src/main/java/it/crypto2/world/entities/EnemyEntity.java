package it.crypto2.world.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import it.crypto2.G;

public class EnemyEntity extends GameEntity {

	public boolean attacking = false;

	public EnemyEntity(float x, float y, Image img, String name) {
		super(x, y);

		// set id
		this.name = name;

		// set image
		setGraphic(img);

		// define collision box and type
		setHitBox(0, 0, G.WIDTH, G.HEIGHT);

		addType(name);
		depth = 20;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		super.update(container, delta);
		controller.update(delta);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		// render entity only near player
		if (canSee((int) x, (int) y)) {
			super.render(container, g);
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

}
