package it.crypto2.effect;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import it.crypto2.world.entities.GameEntity;

public class Damage extends Effect {

	private int damageTimer = 125;
	private Color damageColor = new Color(250, 0, 0, 0.2f);
	private GameEntity entity;

	public Damage(GameEntity entity) {
		this.entity = entity;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if (damageTimer > 0) {
			damageTimer -= delta;
			if (damageTimer <= 0) {
				completed = true;
			}
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		if (damageTimer > 0) {
			Image flash = entity.getCurrentImage().copy();
			flash.drawFlash(entity.x, entity.y, entity.getCurrentImage().getWidth(),
					entity.getCurrentImage().getHeight(), damageColor);
		}
	}

}
