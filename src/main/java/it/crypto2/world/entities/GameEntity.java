package it.crypto2.world.entities;

import org.newdawn.slick.geom.Circle;

import it.crypto2.G;
import it.crypto2.world.entities.controller.Controller;
import it.marteEngine.entity.Entity;

public class GameEntity extends Entity {

	protected Controller controller;

	private Circle c;

	public GameEntity(float x, float y) {
		super(x, y);
	}

	public void setController(Controller c) {
		this.controller = c;
	}

	public boolean canSee(int wx, int wy) {
		PlayerEntity target = G.playerEntity;
		// + ME.world.camera.cameraX
		int tx = (int) (target.x + 160);
		int ty = (int) (target.y + 160);
		c = new Circle(tx, ty, G.sight * G.TILE_SIZE);
		if (!c.contains(x, y)) {
			return false;
		}

		// for (Point p : new Line(tx / 32, ty / 32, (int) x / 32, (int) y /
		// 32)) {
		// if (((GameWorld) world).isFloor((p.x * 32) / 32, (p.y * 32) / 32)) {
		// // // if (/*
		// // * world.find((int) p.getX(), (int) p.getY()).isGround() ||
		// // */ p.getX() == wx && p.getY() == wy)
		// continue;
		// }
		// return false;
		// }

		return true;
	}

}
