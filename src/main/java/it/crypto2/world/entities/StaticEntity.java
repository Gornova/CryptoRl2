package it.crypto2.world.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import it.crypto2.G;
import it.marteEngine.World;

public class StaticEntity extends GameEntity {

	public boolean attacking = false;

	private int timer = 0;

	private boolean fadeOut;

	private Color black = new Color(Color.darkGray);

	public StaticEntity(float x, float y, Image img, World world) {
		super(x, y);

		// set image
		setGraphic(img);

		// define collision box and type
		setHitBox(0, 0, G.WIDTH, G.HEIGHT);
		this.world = world;
		depth = 0;

		// gray = ResourceManager.getImage(G.gray);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		// render entity only near player
		if (canSeePlayer()) {
			super.render(container, g);
			fadeOut = false;
		} else if (alreadySeenByPlayer((int) x / G.TILE_SIZE, (int) y / G.TILE_SIZE)) {
			fadeOut = true;
		} else {
			fadeOut = false;
		}
		if (fadeOut) {
			currentImage.drawFlash(x, y, currentImage.getWidth(), currentImage.getHeight(), black);
		}
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		super.update(container, delta);
		if (fadeOut) {
			timer += delta;
			black.darker(0.05f);
			if (timer > 1000) {
				fadeOut = false;
				timer = 0;
				black = new Color(Color.darkGray);
			}
		}
	}

}
