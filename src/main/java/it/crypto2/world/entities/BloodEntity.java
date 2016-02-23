package it.crypto2.world.entities;

import org.newdawn.slick.Image;

import it.crypto2.G;
import it.marteEngine.World;

public class BloodEntity extends GameEntity {

	public BloodEntity(float x, float y, Image img, World world) {
		super(x, y);

		// set id
		this.name = "blood";

		// set image
		setGraphic(img);

		// define collision box and type
		setHitBox(0, 0, G.WIDTH, G.HEIGHT);

		addType(name, G.BLOOD);
		depth = 1;
	}

}
