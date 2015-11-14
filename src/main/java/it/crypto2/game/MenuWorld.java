package it.crypto2.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import it.crypto2.G;
import it.crypto2.Launcher;
import it.marteEngine.ResourceManager;
import it.marteEngine.World;

public class MenuWorld extends World {

	private int sx = 450;
	private int sy = 500;
	private Rectangle start;
	private Rectangle exit;
	private Image logo;

	public MenuWorld(int id, GameContainer container) {
		super(id, container);
		start = new Rectangle(sx, sy, 150, 25);
		sy += 50;
		exit = new Rectangle(sx, sy, 150, 25);
		logo = ResourceManager.getImage(G.LOGO);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);
		g.drawImage(logo, 335, 150);
		g.draw(start);
		g.drawString("START", start.getX() + 50, start.getY());
		g.draw(exit);
		g.drawString("EXIT", exit.getX() + 50, exit.getY());

		g.drawString("Use WASD or arrows key and SPACE to control your character", 260, 650);

		g.drawString("Random tower of games - 2015 - http://randomtower.blogspot.it", 250, 740);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		super.update(container, game, delta);

		Input input = container.getInput();

		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			int mx = input.getAbsoluteMouseX();
			int my = input.getAbsoluteMouseY();
			if (start.contains(mx, my)) {
				game.enterState(Launcher.GAME_STATE);
				return;
			}
			if (exit.contains(mx, my)) {
				System.exit(0);
				return;
			}
		}
		if (input.isKeyPressed(Input.KEY_F2)) {
			if (container.isFullscreen()) {
				container.setFullscreen(false);
			} else {
				container.setFullscreen(true);
			}
		}
	}

}
