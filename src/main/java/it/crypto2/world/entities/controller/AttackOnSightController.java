package it.crypto2.world.entities.controller;

import java.awt.Point;

import it.crypto2.G;
import it.crypto2.world.entities.GameEntity;

// just wandering around 
public class AttackOnSightController extends WanderController {

	public AttackOnSightController(GameEntity c) {
		super(c);
	}

	public void update(int delta) {
		if (G.PLAYER_MOVED) {
			Point p = canSee(c.x, c.y);
			if (p != null && !G.playerEntity.invisible) {
				if (c.name.equals(G.ABERRATION)) {
					G.world.addMessage(c.name + " roars towards player");
				}
				makeMove(p);
			} else {
				updateMovements();
			}
			// update movement using tweens
			updateTween(delta);
		}
		updateMotion(delta);
	}

	private void makeMove(Point p) {
		int dx = (int) (c.x - p.x * 32);
		int dy = (int) (c.y - p.y * 32);
		if (dx < 0) {
			if (canMove(c.x + 1, c.y)) {
				moveRight = true;
			}
		} else if (dx > 0 && canMove(c.x - 1, c.y)) {
			moveLeft = true;
		} else if (dy < 0 && canMove(c.x, c.y + 1)) {
			moveDown = true;
		} else if (dy > 0 && canMove(c.x, c.y - 11)) {
			moveUp = true;
		}
	}

	private void updateMovements() {
		makeMove(chooseMove());
	}

}
