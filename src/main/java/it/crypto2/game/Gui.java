package it.crypto2.game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import it.crypto2.G;

public class Gui {

	private GameWorld world;
	private List<String> messages = new ArrayList<String>();
	private final static int TIME = 2000; // 2 SEC
	private int timer = 0;

	public Gui(GameWorld gameWorld) {
		this.world = gameWorld;
	}

	public void render(GameContainer container, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
		int h = container.getHeight();
		int w = container.getWidth();
		g.drawString("Turn : " + world.turn, h - 10, 10);
		g.drawString("HP " + G.playerEntity.hp, 10, h - 30);

		int mx = container.getWidth() / 2 - 100;
		int my = 0;
		int dy = 20;
		for (int i = 0; i < 3; i++) {
			if (i < messages.size()) {
				g.drawString(messages.get(i), mx, my);
				my += dy;
			}
		}
	}

	public void addMessage(String m) {
		messages.add(m);
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		// timer += delta;
		// if (timer >= TIME) {
		// timer = 0;
		if (!messages.isEmpty()) {
			messages.remove(messages.size() - 1);
		}
		// }

	}

}
