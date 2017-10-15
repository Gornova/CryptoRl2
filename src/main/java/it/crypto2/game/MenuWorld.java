package it.crypto2.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import it.crypto2.G;
import it.crypto2.Launcher;
import it.crypto2.gui.Button;
import it.marteEngine.ResourceManager;
import it.marteEngine.SFX;
import it.marteEngine.World;

public class MenuWorld extends World implements MouseListener, KeyListener {

	private int sx = 450;
	private int sy = 500;
	private Image logo;
	private Button startButton;
	private Button exitButton;
	private StateBasedGame game;
	private boolean clicked;

	public MenuWorld(int id, GameContainer container) {
		super(id, container);
		logo = ResourceManager.getImage(G.LOGO);
		// start button
		startButton = new Button(ResourceManager.getImage(G.MENU_START), ResourceManager.getImage(G.MENU_START_OVER),
				sx, sy, 133, 51);
		// exit button
		exitButton = new Button(ResourceManager.getImage(G.MENU_EXIT), ResourceManager.getImage(G.MENU_EXIT_OVER),
				sx + 20, sy + 70, 92, 49);
		container.getInput().addMouseListener(this);

		SFX.playMusic(G.MUSIC1);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		super.render(container, game, g);
		g.drawImage(logo, 262, 150);
		startButton.render(container, game, g);
		exitButton.render(container, game, g);

		g.drawString("Kill enemies and find exit from labirynth", 330, 630);
		g.drawString("Use WASD or arrows key and SPACE to control your character, F2 for fullscreen", 180, 650);
		g.drawString("Random tower of games - 2016 - http://randomtower.blogspot.it", 250, 740);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if (this.game == null) {
			this.game = game;
		}
		super.update(container, game, delta);
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		if (startButton.contains(x, y) && !clicked) {
			clicked = true;
			SFX.playSound(G.SELECT_SOUND);
			game.enterState(Launcher.GAME_STATE, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}
		if (exitButton.contains(x, y) && !clicked) {
			clicked = true;
			SFX.playSound(G.SELECT_SOUND);
			System.exit(0);
		}
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		if (startButton.contains(newx, newy)) {
			startButton.setOnOver();
		} else {
			startButton.setNormal();
		}
		if (exitButton.contains(newx, newy)) {
			exitButton.setOnOver();
		} else {
			exitButton.setNormal();
		}
	}

	@Override
	public void keyPressed(int key, char c) {
		try {
			if (key == Input.KEY_F2) {
				if (container.isFullscreen()) {
					container.setFullscreen(false);
					G.fullScreen = false;
				} else {
					container.setFullscreen(true);
					G.fullScreen = true;
				}
			}
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
		clicked = false;
	}

}
