package it.crypto2.world.entities.controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.Path.Step;

import it.crypto2.G;
import it.crypto2.util.Line;
import it.crypto2.world.entities.GameEntity;

// just wandering around 
public class RunWithLowHealthController extends WanderController {

	private Random rnd = new Random();

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
			} else if (canSee(c.x, c.y)) {
				System.out.println("I can see player");
				Line l = new Line((int) G.playerEntity.x / 32, (int) G.playerEntity.y / 32, (int) c.x / 32,
						(int) c.y / 32);
				Iterator<Point> iter = l.getPoints().iterator();
				while (iter.hasNext()) {
					Point point = iter.next();
					if (!G.world.isFloor(point.x, point.y)) {
						iter.remove();
					}
				}
				Point p = l.getPoints().get(0);
				makeMove(p);
			} else {
				// movements
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
		} else if (dx > 0) {
			if (canMove(c.x - 1, c.y)) {
				moveLeft = true;
			}
		} else if (dy < 0) {
			if (canMove(c.x, c.y + 1)) {
				moveDown = true;
			}
		} else if (dy > 0) {
			if (canMove(c.x, c.y - 11)) {
				moveUp = true;
			}
		}

	}

	private boolean canSee(float x, float y) {
		Line l = new Line((int) x / 32, (int) y / 32, (int) G.playerEntity.x / 32, (int) G.playerEntity.y / 32);
		for (Point p : l) {
			if (!G.world.isFloor(p.x, p.y)) {
				return false;
			}
		}
		return true;
	}

	private void updateMovements() {
		makeMove(chooseMove());
	}

	private DIR chooseMove() {
		// get data from world using radius
		// note: no diagonal movements!
		// eliminate move that lead into a wall
		// TODO: if near player. attack!
		List<DIR> values = new ArrayList<DIR>();
		int cx = (int) c.x;
		int cy = (int) c.y;
		values = randomMove(values, cx, cy);
		// pick one random
		if (values.size() > 0) {
			DIR value = values.get(rnd.nextInt(values.size()));
			return value;
		}

		return DIR.NONE;
	}

	private List<DIR> randomMove(List<DIR> values, int cx, int cy) {
		if (canMove(cx - 1, cy)) {
			values.add(DIR.LEFT);
		}
		if (canMove(cx + 1, cy)) {
			values.add(DIR.RIGHT);
		}
		if (canMove(cx, cy - 1)) {
			values.add(DIR.UP);
		}
		if (canMove(cx, cy + 1)) {
			values.add(DIR.DOWN);
		}
		return values;
	}

}
