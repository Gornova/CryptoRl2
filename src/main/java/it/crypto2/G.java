package it.crypto2;

import org.newdawn.slick.geom.Vector2f;

import it.crypto2.game.GameWorld;
import it.crypto2.world.entities.PlayerEntity;

// Globals
public final class G {

	public static final String PLAYER = "player";

	public static final int HEIGHT = 32;
	public static final int WIDTH = 32;

	public static final String STAND_DOWN = "stand_down";
	public static final String STAND_UP = "stand_up";
	public static final String STAND_RIGHT = "stand_right";
	public static final String STAND_LEFT = "stand_left";

	// speed for game entities
	public static final Vector2f speed = new Vector2f(32, 32);

	public static final String FLOOR = "floor";

	public static final String ELF = "elf";

	public static final Character F = '.';

	public static final int TILE_SIZE = 32;

	public static final Character W = '#';

	public static final String WALL = "wall";

	public static final String SPIDER = "spider";

	public static final String STYGIANBIRD = "stygianBird";

	public static final String ABERRATION = "aberration";

	public static final String POTION = "potion";

	public static final String TORCH = "torch";

	public static boolean PLAYER_MOVED = false;

	public static PlayerEntity playerEntity = null;

	public static final int MAX_SIGHT = 8;

	public static final String LOGO = "logo";

	public static final String gray = "gray";

	public static final String EXIT = "exit";

	public static final int MAX_LEVEL = 2;

	public static int sight = 5;

	public static GameWorld world;

	public static int currentLevel = 1;

	public static final String HPBAR = "hpbar";

	public static final String INVISIBILITY_SCROLL = "invisibilityScroll";

	public static final int INVISIBILITY_TIME = 10;

	public static int INVISIBILITY_TIMER = 0;

}
