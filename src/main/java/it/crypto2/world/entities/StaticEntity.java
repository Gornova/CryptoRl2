package it.crypto2.world.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import it.crypto2.G;
import it.marteEngine.World;

public class StaticEntity extends GameEntity {

	public boolean attacking = false;

	public StaticEntity(float x, float y, Image img, World world) {
		super(x, y);

		// set image
		setGraphic(img);

		// define collision box and type
		setHitBox(0, 0, G.WIDTH, G.HEIGHT);
		this.world = world;
		depth = 0;
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		// render entity only near player
		if (canSee((int) x, (int) y)) {
			super.render(container, g);
		}
	}

	// private boolean nearPlayer() {
	// Entity e = G.playerEntity;
	// int d = (int) (Math.abs(x - e.x) + Math.abs(y - e.y));
	// if (d < G.TILE_SIZE * 8) {
	// return true;
	// }
	// return false;
	// }

}
