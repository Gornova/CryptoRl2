package it.crypto2.world.entities.controller;

import it.crypto2.G;
import it.crypto2.world.entities.GameEntity;
import it.crypto2.world.entities.PlayerEntity;
import it.marteEngine.ME;
import it.marteEngine.entity.Entity;

public class PlayerController extends AbstractController {

	public PlayerController(GameEntity c) {
		super(c);
	}

	public void update(int delta) {
		// movements
		updateMovements();
		// update movement using tweens
		updateTween(delta);
		updateMotion(delta);
	}

	private void updateMovements() {
		if (c.pressed(G.WAIT)) {
			G.PLAYER_MOVED = true;
			return;
		}
		if (c.pressed(ME.WALK_UP)) {
			moveUp();

		} else if (c.pressed(ME.WALK_DOWN)) {
			moveDown();
		}
		if (c.pressed(ME.WALK_RIGHT)) {
			moveRight();
		} else if (c.pressed(ME.WALK_LEFT)) {
			moveLeft();
		}
	}

	public void moveLeft() {
		if (c.collide(Entity.SOLID, c.x - G.speed.x, c.y) == null) {
			// x -= G.speed.x;
			moveLeft = true;
			G.PLAYER_MOVED = true;
			((PlayerEntity) c).faceRight = false;
		}
	}

	public void moveRight() {
		if (c.collide(Entity.SOLID, c.x + G.speed.x, c.y) == null) {
			// x += G.speed.x;
			moveRight = true;
			G.PLAYER_MOVED = true;
			((PlayerEntity) c).faceRight = true;
		}
	}

	public void moveDown() {
		if (canMove(c.x, c.y + G.speed.y)) {
			// y += G.speed.y;
			moveDown = true;
			G.PLAYER_MOVED = true;
		}
	}

	public void moveUp() {
		if (c.collide(Entity.SOLID, c.x, c.y - G.speed.y) == null) {
			// y -= G.speed.y;
			moveUp = true;
			G.PLAYER_MOVED = true;
		}
	}

}
