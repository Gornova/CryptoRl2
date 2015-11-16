package it.crypto2.world.entities;

import org.newdawn.slick.Image;

import it.crypto2.G;
import it.marteEngine.SFX;
import it.marteEngine.entity.Entity;

public class Exit extends GameEntity {

	public Exit(int i, int j, Image img, String name) {
		super(i, j);

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
			SFX.playSound(G.EXIT_SOUND);
			G.world.nextLevel();
		}
	}

}
