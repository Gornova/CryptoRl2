package it.crypto2.world.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import it.crypto2.G;
import it.marteEngine.ME;
import it.marteEngine.SFX;

public class Trap extends GameEntity {

	public static final int TRAP_DAMAGE = 10;
	private static final String DAMAGE = "damage";
	private static final String BLOCK = "block";
	private static final int BLOCK_TIMER = 3;
	private boolean visible = false;
	private boolean activated = false;
	private String type;

	public Trap(int x, int y, Image img, String name) {
		super(x, y);

		// set id
		this.name = name;

		// set image
		setGraphic(img);

		// define collision box and type
		setHitBox(0, 0, G.WIDTH, G.HEIGHT);

		addType(name, G.TRAP);
		depth = 20;

		if (Math.random() < 0.5) {
			type = DAMAGE;
		} else {
			type = BLOCK;
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		if (visible) {
			super.render(container, g);
		}
		if (ME.debugEnabled) {
			super.render(container, g);
		}
	}

	public void execute(PlayerEntity player) {
		visible = true;
		if (!activated) {
			if (type.equals(DAMAGE)) {
				G.world.addMessage("You activate a trap! (-" + Trap.TRAP_DAMAGE + " hp)");
				player.damage(TRAP_DAMAGE);
			}
			if (type.equals(BLOCK)) {
				G.world.addMessage("You are blocked for " + Trap.BLOCK_TIMER + " turns)");
				G.BLOCK_TIMER = BLOCK_TIMER;
			}
			activated = true;
			SFX.playSound(G.HIT_SOUND);
			collidable = false;
		}
	}

}
