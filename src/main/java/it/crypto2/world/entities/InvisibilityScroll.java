package it.crypto2.world.entities;

import org.newdawn.slick.Image;

import it.crypto2.G;
import it.marteEngine.entity.Entity;

public class InvisibilityScroll extends GameEntity {

	public InvisibilityScroll(int x, int y, Image img, String name) {
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
	public void collisionResponse(Entity other) {
		if (other instanceof PlayerEntity) {
			G.playerEntity.invisible = true;
			System.out.println("player is now invisible for " + G.INVISIBILITY_TIME);
			G.INVISIBILITY_TIMER = G.INVISIBILITY_TIME;
			G.world.remove(this);
			G.world.addMessage("Player fades away (invisible for 10 turns)");
		}
	}

}
