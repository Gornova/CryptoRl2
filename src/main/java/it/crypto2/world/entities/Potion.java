package it.crypto2.world.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import it.crypto2.G;

public class Potion extends GameEntity {

	private static final int CURE_HP = 10;

	public Potion(float x, float y, Image img, String name) {
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
	public void render(GameContainer container, Graphics g) throws SlickException {
		if (canSeePlayer()) {
			super.render(container, g);
		}
	}

	public void cure(GameEntity ge) {
		if (ge.hp + CURE_HP < ge.maxHp) {
			ge.hp += CURE_HP;
		} else {
			ge.hp = maxHp;
		}
		G.world.addMessage("You feel a lot better!");

	}

}
