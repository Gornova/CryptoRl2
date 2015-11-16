package it.crypto2.world.entities;

import org.newdawn.slick.Image;

import it.crypto2.G;
import it.marteEngine.SFX;

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

	public void cure(GameEntity ge) {
		if (ge.hp + CURE_HP < ge.maxHp) {
			ge.hp += CURE_HP;
		} else {
			ge.hp = ge.maxHp;
		}
		G.world.addMessage("Player drink potion and feel a lot better (+" + CURE_HP + " hp)");
		SFX.playSound(G.ITEM_SOUND);
	}

}
