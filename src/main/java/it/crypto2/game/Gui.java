package it.crypto2.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import it.crypto2.G;

public class Gui {

	private GameWorld world;

	public Gui(GameWorld gameWorld) {
		this.world = gameWorld;
	}

	public void render(GameContainer container, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
		int h = container.getHeight();
		int w = container.getWidth();
		g.drawString("Turn : " + world.turn, h - 10, 10);
		g.drawString("HP " + G.playerEntity.hp, 10, h - 30);
	}

}
