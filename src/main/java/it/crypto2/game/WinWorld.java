package it.crypto2.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import it.crypto2.Launcher;
import it.marteEngine.World;

public class WinWorld extends World {

	public WinWorld(int id, GameContainer container) {
		super(id, container);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);

		g.drawString("YOU WIN", container.getWidth() / 2, container.getHeight() / 2);

		g.drawString("Press SPACE to continue", 450, 600);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);

		if (container.getInput().isKeyPressed(Input.KEY_SPACE)) {
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
