package it.crypto2.world.entities;

import org.newdawn.slick.Image;

import it.crypto2.G;
import it.marteEngine.SFX;

public class Sword extends GameEntity {

	public Sword(float x, float y, Image img, String name) {
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

	public void execute(GameEntity ge) {
		ge.atk++;
		G.world.addMessage("Player take a better sword and feel more powerful! (+1 ATK)");
		SFX.playSound(G.ITEM_SOUND);
	}

}
