package it.crypto2.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import it.crypto2.G;
import it.crypto2.Launcher;
import it.marteEngine.ResourceManager;
import it.marteEngine.World;

public class GameOverWorld extends World {

	private Image img;

	public GameOverWorld(int id, GameContainer container) {
		super(id, container);
		this.img = ResourceManager.getImage(G.GUI_GAME_OVER);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);

		g.drawImage(img, 250, 200);
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
