package it.crypto2.effect;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import it.crypto2.world.entities.GameEntity;

public class Shake extends Effect {

	private GameEntity entity;
	private int timer = 250;
	private int xOff = 0;
	private int yOff = 0;
	private Random rnd = new Random();

	public Shake(GameEntity entity) {
		this.entity = entity;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if (timer > 0) {
			timer -= delta;
			if (timer <= 0) {
				completed = true;
			}
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		if (timer > 0) {
			xOff = rnd.nextInt(8) - 4;
			yOff = rnd.nextInt(8) - 4;
			g.drawImage(entity.getCurrentImage(), entity.x + xOff, entity.y + yOff);
		}
	}

}
