package it.crypto2.world.entities;

import org.newdawn.slick.Image;

import it.crypto2.G;
import it.marteEngine.SFX;

public class Torch extends GameEntity {

	public Torch(int x, int y, Image img, String name) {
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

	public void extend(GameEntity ge) {
		if (G.sight + 3 < G.MAX_SIGHT) {
			G.sight += 3;
		} else {
			G.sight = G.MAX_SIGHT;
		}
		G.world.addMessage("Player take a torch and darkness fade away (+3 sight) ");
		SFX.playSound(G.ITEM_SOUND);
	}

}
