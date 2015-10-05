package it.crypto2.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import it.marteEngine.World;

public class GameOverWorld extends World {

	public GameOverWorld(int id, GameContainer container) {
		super(id, container);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);

		g.drawString("GAME OVER", container.getWidth() / 2, container.getHeight() / 2);
	}

}
