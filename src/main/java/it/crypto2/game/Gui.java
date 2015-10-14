package it.crypto2.game;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import it.crypto2.G;
import it.crypto2.world.entities.EnemyEntity;
import it.marteEngine.Camera;
import it.marteEngine.ResourceManager;
import it.marteEngine.entity.Entity;

public class Gui {

	private GameWorld world;
	private List<String> messages = new ArrayList<String>();

	private Image barImg;
	private Rectangle info;

	private Color infoDark;

	public Gui(GameWorld gameWorld) {
		this.world = gameWorld;
		this.barImg = ResourceManager.getImage(G.HPBAR);
		this.info = new Rectangle(0, 0, 0, 0);

		this.infoDark = Color.darkGray;
		this.infoDark.a = 0.5f;
	}

	public void render(GameContainer container, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
		int h = container.getHeight();
		int w = container.getWidth();
		g.drawString("Turn : " + world.turn, h - 10, 10);

		drawHP(g, container.getHeight());

		drawMessages(container, g);

		drawMouse(container, g);
	}

	private void drawMouse(GameContainer container, Graphics g) {
		int mx = container.getInput().getAbsoluteMouseX();
		int my = container.getInput().getAbsoluteMouseY();

		Camera camera = world.camera;
		EnemyEntity e = (EnemyEntity) findEnemy(mx - camera.cameraX, my - camera.cameraY);
		if (e != null && e.canSeePlayer()) {

			// move info box
			float ix = e.x + e.width;
			float iy = e.y + e.height;
			info.setLocation(ix, iy);
			info.setWidth(300);
			info.setHeight(100);
			g.setColor(infoDark);
			g.fill(info);
			g.setColor(Color.white);
			g.drawString(e.name, ix + 5, iy + 5);
			int v = e.description.length() / 40; // 40 characters = one line
			for (int i = 0; i <= v; i++) {
				int sx = i * 40;
				int ex = i * 40 + 40;
				ex = ex > e.description.length() ? e.description.length() : ex;
				g.drawString(e.description.substring(sx, ex), ix + 5,
						iy + i * g.getFont().getHeight(e.description) + 5);
			}

		}

	}

	private Entity findEnemy(float f, float g) {
		Rectangle rect;
		for (Entity entity : world.getEntities()) {
			if (entity instanceof EnemyEntity) {
				rect = new Rectangle(entity.x, entity.y, entity.width, entity.height);
				if (rect.contains(f, g)) {
					return entity;
				}
			}
		}
		return null;
	}

	private void drawMessages(GameContainer container, Graphics g) {
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

	private void drawHP(Graphics g, int cheight) {
		// g.drawString("HP " + G.playerEntity.hp, 10, h - 30);
		// every bar is 10 hp
		int bx = 10;
		int by = cheight - 20;
		if (G.playerEntity.hp > 0) {
			int v = G.playerEntity.hp / 10;
			if (v == 0) {
				v = 1;
			}
			for (int i = 0; i < v; i++) {
				g.drawImage(barImg, bx + 8 * i, by);
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
