package it.crypto2.world.entities.controller;

import java.awt.Point;

import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.Path.Step;

import it.crypto2.G;
import it.crypto2.world.entities.GameEntity;

// just wandering around 
public class RunWithLowHealthController extends WanderController {

	public RunWithLowHealthController(GameEntity c) {
		super(c);
	}

	public void update(int delta) {
		if (G.PLAYER_MOVED) {
			if (lowHealth()) {
				Point p = G.world.getRandomPoint();
				System.out.println("Low health!! Run from player to " + p.toString());
				Path path = G.world.getPath(p.x, p.y, (int) c.x / 32, (int) c.y / 32);
				if (path != null && path.getLength() > 0) {
					Step step = path.getStep(1);
					makeMove(new Point(step.getX() * 32, step.getY() * 32));

				}
			} else {
				Point p = canSee(c.x, c.y);
				if (p != null) {
					makeMove(p);
				} else {
					// movements
					updateMovements();
				}
			}
			// update movement using tweens
			updateTween(delta);
		}
		updateMotion(delta);
	}

	private void makeMove(Point p) {
		int dx = (int) (c.x - p.x * 32);
		int dy = (int) (c.y - p.y * 32);
		if (dx < 0 && canMove(c.x + 1, c.y)) {
			moveRight = true;
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
