package it.crypto2.world.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import it.crypto2.G;
import it.marteEngine.ResourceManager;
import it.marteEngine.World;

public class StaticEntity extends GameEntity {

	public boolean attacking = false;

	private Image gray;

	public StaticEntity(float x, float y, Image img, World world) {
		super(x, y);

		// set image
		setGraphic(img);

		// define collision box and type
		setHitBox(0, 0, G.WIDTH, G.HEIGHT);
		this.world = world;
		depth = 0;

		gray = ResourceManager.getImage(G.gray);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		// render entity only near player
		if (canSeePlayer()) {
			super.render(container, g);
		} else if (alreadySeenByPlayer((int) x / G.TILE_SIZE, (int) y / G.TILE_SIZE)) {
			// super.render(container, g);
			currentImage.drawFlash(x, y, currentImage.getWidth(), currentImage.getHeight(), Color.darkGray);
			// g.drawImage(gray, x, y);
		}
	}

}
