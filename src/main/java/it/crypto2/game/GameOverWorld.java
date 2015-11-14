package it.crypto2.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import it.crypto2.Launcher;
import it.marteEngine.World;

public class GameOverWorld extends World {

	public GameOverWorld(int id, GameContainer container) {
		super(id, container);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);

		int sx = 400;
		int sy = 400;
		g.drawString("GAME OVER", sx + 50, sy);

		g.drawString("Press SPACE to continue", sx, sy + 200);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);

		if (container.getInput().isKeyDown(Input.KEY_SPACE)) {
			game.enterState(Launcher.MENU_STATE);
			return;
		}
		if (container.getInput().isKeyPressed(Input.KEY_F2)) {
			if (container.isFullscreen()) {
				container.setFullscreen(false);
			} else {
				container.setFullscreen(true);
			}
		}

	}

}
