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

	public static final int MAX_LEVEL = 5;

	public static int sight = 5;

	public static GameWorld world;

	public static int currentLevel = 1;

	public static final String HPBAR = "hpbar";

	public static final String INVISIBILITY_SCROLL = "invisibility scroll";

	public static final int INVISIBILITY_TIME = 10;

	public static final String SHADOW = "shadow";

	public static final String WAIT = "wait";

	public static final String BLOOD = "blood";

	public static final String TRAP = "trap";

	public static final String MENU_START = "menu_start";

	public static final String MENU_EXIT = "menu_exit";

	public static final String MENU_START_OVER = "menu_start_over";

	public static final String MENU_EXIT_OVER = "menu_exit_over";

	public static final String RAT = "rat";

	public static final String SNAKE = "snake";

	public static final String GUI_LIFE = "gui_life";

	public static final String GUI_TURN = "gui_turn";

	public static final String GUI_LEVEL = "gui_level";

	public static final String GUI_EXPLORATION = "gui_exploration";

	public static final String STEP_SOUND = "step";

	public static final String HIT_SOUND = "hit";

	public static final String SELECT_SOUND = "select";

	public static final String ITEM_SOUND = "item";

	public static final String EXIT_SOUND = "exitSound";

	public static final String GUI_WIN = "gui_win";

	public static final String GUI_GAME_OVER = "gui_game_over";

	public static int INVISIBILITY_TIMER = 0;

	public static int turn = 1;

	public static int BLOCK_TIMER = 0;

	public static boolean fullScreen;

}
