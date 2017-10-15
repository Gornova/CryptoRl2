package it.crypto2.gui;

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
import it.crypto2.game.GameWorld;
import it.crypto2.world.entities.EnemyEntity;
import it.crypto2.world.entities.GameEntity;
import it.marteEngine.Camera;
import it.marteEngine.ResourceManager;
import it.marteEngine.entity.Entity;

public class Gui {

	private GameWorld world;
	private List<String> messages = new ArrayList<String>();

	private Image barImg;
	private Rectangle info;

	private Color infoDark;

	private int fh = -1;
	private Image lifeImg;
	private Image turnImg;
	private Image levelImg;
	private Image explorationImg;

	public Gui(GameWorld gameWorld) {
		this.world = gameWorld;
		this.barImg = ResourceManager.getImage(G.HPBAR);
		this.lifeImg = ResourceManager.getImage(G.GUI_LIFE);
		this.turnImg = ResourceManager.getImage(G.GUI_TURN);
		this.levelImg = ResourceManager.getImage(G.GUI_LEVEL);
		this.explorationImg = ResourceManager.getImage(G.GUI_EXPLORATION);
		this.info = new Rectangle(0, 0, 0, 0);

		this.infoDark = Color.darkGray;
		this.infoDark.a = 0.5f;
	}

	public void render(GameContainer container, StateBasedGame stateBasedGame, Graphics g) throws SlickException {
		if (fh == -1) {
			fh = g.getFont().getHeight("hi");
		}
		// turn image
		g.drawImage(turnImg, 200, container.getHeight() - 37);
		g.drawString("" + G.turn, 290, container.getHeight() - 35);
		// level image
		g.drawImage(levelImg, 400, container.getHeight() - 37);
		g.drawString("" + G.currentLevel, 495, container.getHeight() - 35);
		// exploration
		int expl = world.getSawTiles() * 100 / world.getTileNumber();
		g.drawImage(explorationImg, 600, container.getHeight() - 35);
		g.drawString("" + expl + "%", 790, container.getHeight() - 35);

		drawHP(g, container.getHeight());

		drawAttackAndDefense(g);

		drawMessages(container, g);

		drawMouse(container, g);
	}

	private void drawAttackAndDefense(Graphics g) {
		g.drawImage(ResourceManager.getImage(G.SWORD), 5, 5);
		g.drawString("" + G.playerEntity.atk, 40, 5);
		g.drawImage(ResourceManager.getImage(G.SHIELD), 5, 40);
		g.drawString("" + G.playerEntity.dfk, 40, 40);
	}

	private void drawMouse(GameContainer container, Graphics g) {
		int mx = container.getInput().getAbsoluteMouseX();
		int my = container.getInput().getAbsoluteMouseY();

		Camera camera = world.camera;
		GameEntity e = (GameEntity) findEnemyOrItem(mx + camera.cameraX, my + camera.cameraY);
		if (e != null && e.canSeePlayer()) {

			// move info box
			float ix = mx;
			float iy = my;
			info.setLocation(mx, my);
			info.setWidth(400);
			info.setHeight(100);
			g.setColor(infoDark);
			g.fill(info);
			g.setColor(Color.white);
			String status = "";
			if (e.item) {
				status = e.name;
			} else {
				status = e.name + " " + e.hp + "/" + e.maxHp;
			}
			g.drawString(status, ix + 5, iy + 5);
			iy += fh;
			int v = e.description.length() / 40; // 40 characters = one line
			for (int i = 0; i <= v; i++) {
				int sx = i * 40;
				int ex = i * 40 + 40;
				ex = ex > e.description.length() ? e.description.length() : ex;
				g.drawString(e.description.substring(sx, ex), ix + 5, iy + i * fh + 5);
			}

		}

	}

	private GameEntity findEnemyOrItem(float f, float g) {
		Rectangle rect;
		for (Entity entity : world.getEntities()) {
			if (entity instanceof EnemyEntity || (entity instanceof GameEntity && ((GameEntity) entity).item)) {
				rect = new Rectangle(entity.x, entity.y, entity.width, entity.height);
				if (rect.contains(f, g)) {
					return (GameEntity) entity;
				}
			}
		}
		return null;
	}

	private void drawMessages(GameContainer container, Graphics g) {
		int mx = 250;
		int my = 0;
		int dy = 20;
		for (int i = 0; i < 5; i++) {
			if (i < messages.size()) {
				g.drawString(messages.get(i), mx, my);
				my += dy;
			}
		}
	}

	private void drawHP(Graphics g, int cheight) {
		g.drawImage(lifeImg, 10, cheight - 40);
		// every bar is 10 hp
		int bx = 75;
		int by = cheight - 35;
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
		if (!messages.isEmpty()) {
			messages.remove(messages.size() - 1);
		}
	}

}
