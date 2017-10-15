package it.crypto2.world.entities.controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import it.crypto2.G;
import it.crypto2.util.Line;
import it.crypto2.world.entities.GameEntity;

// just wandering around 
public class WanderController extends AbstractController {

	private Random rnd = new Random();

	public WanderController(GameEntity c) {
		super(c);
	}

	public void update(int delta) {
		if (G.PLAYER_MOVED) {
			// movements
			updateMovements();
			// update movement using tweens
			updateTween(delta);
		}
		updateMotion(delta);
	}

	private void updateMovements() {
		makeMove(chooseMove());
	}

	protected DIR chooseMove() {
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

	protected Point canSee(float x, float y) {
		Line l = new Line((int) G.playerEntity.x / 32, (int) G.playerEntity.y / 32, (int) c.x / 32, (int) c.y / 32);
		Iterator<Point> iter = l.getPoints().iterator();
		while (iter.hasNext()) {
			Point point = iter.next();
			if (!G.world.isFloor(point.x, point.y)) {
				iter.remove();
			}
		}
		return l.getPoints().get(0);
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
