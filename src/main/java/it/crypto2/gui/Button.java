package it.crypto2.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import it.marteEngine.ME;

public class Button {

	private Rectangle rect;
	private Image current;
	private Image normal;
	private Image over;

	public Button(Image normal, Image over, int sx, int sy, int w, int h) {
		this.current = normal;
		this.normal = normal;
		this.over = over;
		this.rect = new Rectangle(sx, sy, w, h);
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(current, rect.getX(), rect.getY());
		if (ME.debugEnabled) {
			g.draw(rect);
		}

	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
	}

	public boolean contains(int x, int y) {
		return rect.contains(x, y);
	}

	public void setOnOver() {
		this.current = over;
	}

	public void setNormal() {
		this.current = normal;
	}

}
