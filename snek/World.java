import objectdraw.DrawingCanvas;
import objectdraw.ActiveObject;
import objectdraw.Location;
import objectdraw.Text;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class World extends ActiveObject {
	public static final int GRID_SIZE = 32;
	public static final int MIN_PAUSE_TIME = 20;
	public static final int MAX_PAUSE_TIME = 300;

	public static final int INITIAL_FOOD = 5;
	public static final int INITIAL_TELEPORTERS = 3;
	public static final int INITIAL_SPLITTERS = 2;

	public static final Color[] RED_SCHEME = {new Color(0xFF5252), new Color(0xFF1744), new Color(0xFF8A80), new Color(0xD50000)};
	public static final Color[] PINK_SCHEME = {new Color(0xff4081), new Color(0xf50057), new Color(0xff80ab), new Color(0xc51162)};
	public static final Color[] PURPLE_SCHEME = {new Color(0xe040fb), new Color(0xd500f9), new Color(0xea80fc), new Color(0xaa00ff)};
	public static final Color[] DEEP_PURPLE_SCHEME = {new Color(0x7c4dff), new Color(0x651fff), new Color(0xb388ff), new Color(0x6200ea)};
	public static final Color[] INDIGO_SCHEME = {new Color(0x536dfe), new Color(0x3d5afe), new Color(0x8c9eff), new Color(0x304ffe)};
	public static final Color[] BLUE_SCHEME = {new Color(0x448AFF), new Color(0x2979FF), new Color(0x82B1FF), new Color(0x2962FF)};
	public static final Color[] LIGHT_BLUE_SCHEME = {new Color(0x40c4ff), new Color(0x00b0ff), new Color(0x80d8ff), new Color(0x0091ea)};
	public static final Color[] CYAN_SCHEME = {new Color(0x18ffff), new Color(0x00e5ff), new Color(0x84ffff), new Color(0x00b8d4)};
	public static final Color[] TEAL_SCHEME = {new Color(0x64ffda), new Color(0x1de9b6), new Color(0xa7ffeb), new Color(0x00bfa5)};
	public static final Color[] GREEN_SCHEME = {new Color(0x69F0AE), new Color(0x00E676), new Color(0xB9F6CA), new Color(0x00C853)};
	public static final Color[] LIGHT_GREEN_SCHEME = {new Color(0xb2ff59), new Color(0x76ff03), new Color(0xccff90), new Color(0x64dd17)};
	public static final Color[] LIME_SCHEME = {new Color(0xeeff41), new Color(0xc6ff00), new Color(0xf4ff81), new Color(0xaeea00)};
	public static final Color[] YELLOW_SCHEME = {new Color(0xffff00), new Color(0xffea00), new Color(0xffff8d), new Color(0xffd600)};
	public static final Color[] AMBER_SCHEME = {new Color(0xffd740), new Color(0xffc400), new Color(0xffe57f), new Color(0xffab00)};
	public static final Color[] ORANGE_SCHEME = {new Color(0xffab40), new Color(0xff9100), new Color(0xffd180), new Color(0xff6d00)};

	public static final Color[][] ALL_SCHEMES = {RED_SCHEME, PINK_SCHEME, PURPLE_SCHEME, DEEP_PURPLE_SCHEME, INDIGO_SCHEME, BLUE_SCHEME, LIGHT_BLUE_SCHEME, CYAN_SCHEME, TEAL_SCHEME, GREEN_SCHEME, LIGHT_GREEN_SCHEME, LIME_SCHEME, YELLOW_SCHEME, AMBER_SCHEME, ORANGE_SCHEME};
	public static final Color[] RAINBOW_SCHEME = {RED_SCHEME[0], PINK_SCHEME[1], PURPLE_SCHEME[2], DEEP_PURPLE_SCHEME[3], INDIGO_SCHEME[0], BLUE_SCHEME[1], LIGHT_BLUE_SCHEME[2], CYAN_SCHEME[3], TEAL_SCHEME[0], GREEN_SCHEME[1], LIGHT_GREEN_SCHEME[2], LIME_SCHEME[3], YELLOW_SCHEME[0], AMBER_SCHEME[1], ORANGE_SCHEME[2], RED_SCHEME[1], PINK_SCHEME[2], PURPLE_SCHEME[3], DEEP_PURPLE_SCHEME[0], INDIGO_SCHEME[1], BLUE_SCHEME[2], LIGHT_BLUE_SCHEME[3], CYAN_SCHEME[0], TEAL_SCHEME[1], GREEN_SCHEME[2], LIGHT_GREEN_SCHEME[3], LIME_SCHEME[0], YELLOW_SCHEME[1], AMBER_SCHEME[2], ORANGE_SCHEME[3], RED_SCHEME[2], PINK_SCHEME[3], PURPLE_SCHEME[0], DEEP_PURPLE_SCHEME[1], INDIGO_SCHEME[2], BLUE_SCHEME[3], LIGHT_BLUE_SCHEME[0], CYAN_SCHEME[1], TEAL_SCHEME[2], GREEN_SCHEME[3], LIGHT_GREEN_SCHEME[0], LIME_SCHEME[1], YELLOW_SCHEME[2], AMBER_SCHEME[3], ORANGE_SCHEME[0], RED_SCHEME[3], PINK_SCHEME[0], PURPLE_SCHEME[1], DEEP_PURPLE_SCHEME[2], INDIGO_SCHEME[3], BLUE_SCHEME[0], LIGHT_BLUE_SCHEME[1], CYAN_SCHEME[2], TEAL_SCHEME[3], GREEN_SCHEME[0], LIGHT_GREEN_SCHEME[1], LIME_SCHEME[2], YELLOW_SCHEME[3], AMBER_SCHEME[0], ORANGE_SCHEME[1]};

	public static final int[] ARROW_KEYS = {37,38,39,40};
	public static final Direction[] ARROW_KEYS_ORDER = {Direction.LEFT, Direction.UP, Direction.RIGHT, Direction.DOWN};
	public static final int[] WASD_KEYS = {65,68,83,87};
	public static final Direction[] WASD_KEYS_ORDER = {Direction.LEFT, Direction.RIGHT, Direction.DOWN, Direction.UP};
	public static final int[] COLEMAK_KEYS = {65,82,83,87};
	public static final Direction[] COLEMAK_KEYS_ORDER = {Direction.LEFT, Direction.DOWN, Direction.RIGHT, Direction.UP};
	public static final int[] NUMBER_KEYS = {49,50,51,52};
	public static final Direction[] NUMBER_KEYS_ORDER = {Direction.LEFT, Direction.RIGHT, Direction.DOWN, Direction.UP};

	public int pauseTime = 50;
	public boolean running = true;

	public ScoreBoard sb;

	private Text notice;
	private FoodController fc;
	private TeleportController tc;
	private SplitterController sc;
	private WorldController wc;
	private DrawingCanvas canvas;
	private ArrayList<Tickable> tickable = new ArrayList<Tickable>();
	private ArrayList<KeyHandler> keyHandlers = new ArrayList<KeyHandler>();

	public World(DrawingCanvas canvas) {
		this.canvas = canvas;

		// make key controller
		wc = new WorldController(this);
		keyHandlers.add(wc);

		fc = new FoodController(canvas, this);
		keyHandlers.add(fc);
		tickable.add(fc);

		tc = new TeleportController(canvas, this);
		keyHandlers.add(tc);

		sc = new SplitterController(canvas, this);
		keyHandlers.add(sc);

		sb = new ScoreBoard(canvas, this);
		tickable.add(sb);

		new Grid(canvas);

		setNotice("Click to start");

		start();
	}

	int iiiii=0;

	public void run() {
		while (running) {
			while (checkForSnakes() && running) {
				// tick all objects
				for (int i=0; i<tickable.size(); i++) {
					tickable.get(i).tick();
				}

				// check food
				ArrayList<Food> foodArr = fc.getFood();
				for (int i=0; i<foodArr.size(); i++) {
					for (int j=0; j<tickable.size(); j++) {
						if (tickable.get(j) instanceof Snake) {
							if (((Snake)tickable.get(j)).overlaps(foodArr.get(i))) {
								((Snake)tickable.get(j)).grow(foodArr.get(i).getValue());
								fc.remove(foodArr.get(i));
								fc.addFood();
							}
						}
					}
				}

				// check snake overlaps
				for (int i=0; i<tickable.size(); i++) {
					if (tickable.get(i) instanceof Snake) {
						for (int j=i; j<tickable.size(); j++) {
							if (tickable.get(j) instanceof Snake) {
								if (((Snake)tickable.get(i)).overlaps((Snake)tickable.get(j))) {
									((Removable)tickable.get(i)).remove();
								} else if (((Snake)tickable.get(j)).overlaps((Snake)tickable.get(i))) {
									((Removable)tickable.get(j)).remove();
								}
							}
						}
					}
				}

				// check teleports
				ArrayList<Teleport> teleportArr = tc.getTeleporters();
				for (int j=0; j<tickable.size(); j++) {
					for (int i=0; i<teleportArr.size(); i++) {
						if (tickable.get(j) instanceof Snake) {
							if (((Snake)tickable.get(j)).getHead().overlaps(teleportArr.get(i))) {
								((Snake)tickable.get(j)).teleportTo(tc.getOtherTeleport(teleportArr.get(i)).getLocation());
								break;
							}
						}
					}
				}

				// check splitters
				ArrayList<Splitter> splitterArr = sc.getSplitters();
				for (int j=0; j<tickable.size(); j++) {
					for (int i=0; i<splitterArr.size(); i++) {
						if (tickable.get(j) instanceof Snake) {
							if (((Snake)tickable.get(j)).getHead().overlaps(splitterArr.get(i))) {
								Snake newSnake = ((Snake)tickable.get(j)).split(ARROW_KEYS, ARROW_KEYS_ORDER, Helpers.getRandomColorScheme());
								if (newSnake != null) {
									sc.remove(splitterArr.get(i));
									sc.addSplitter();
									tickable.add(newSnake);
									keyHandlers.add(newSnake);
									sb.addSnake(newSnake);
								}
								break;
							}
						}
					}
				}

				// check bounds
				for (int i=0; i<tickable.size(); i++) {
					if (tickable.get(i) instanceof ConstrainedToArea) {
						if (!((ConstrainedToArea)tickable.get(i)).isInArea()) {
							if (tickable.get(i) instanceof Removable) {
								((Removable)tickable.get(i)).remove();
							}
						}
					}
				}

				// gc
				for (int i=0; i<tickable.size(); i++) {
					if (tickable.get(i) instanceof Removable) {
						if (((Removable)tickable.get(i)).isRemoved()) {
							tickable.remove(i);
							i--;
						}
					}
				}

				pause(pauseTime);
			}
			pause(pauseTime);
		}
		System.exit(0);
	}

	public void addInitialSnake(double x, double y, int size) {
		sb.removeAll();
		x = ((int)x)/GRID_SIZE*GRID_SIZE;
		y = ((int)y)/GRID_SIZE*GRID_SIZE;
		Snake initialSnake = new Snake(x, y, size, Helpers.getRandomColorScheme(), ARROW_KEYS, ARROW_KEYS_ORDER, canvas, this);
		tickable.add(initialSnake);
		keyHandlers.add(initialSnake);
		sb.addSnake(initialSnake);
		for (int i=0; i<INITIAL_FOOD; i++) {
			fc.addFood();
		}
		for (int i=0; i<INITIAL_TELEPORTERS; i++) {
			tc.addTeleporter();
		}
		for (int i=0; i<INITIAL_SPLITTERS; i++) {
			sc.addSplitter();
		}
	}

	public void addExtraSnake(double x, double y, int size) {
		x = ((int)x)/GRID_SIZE*GRID_SIZE;
		y = ((int)y)/GRID_SIZE*GRID_SIZE;
		Snake initialSnake = new Snake(x, y, size, Helpers.getRandomColorScheme(), COLEMAK_KEYS, COLEMAK_KEYS_ORDER, canvas, this);
		tickable.add(initialSnake);
		keyHandlers.add(initialSnake);
		sb.addSnake(initialSnake);
		for (int i=0; i<INITIAL_FOOD; i++) {
			fc.addFood();
		}
		for (int i=0; i<INITIAL_TELEPORTERS; i++) {
			tc.addTeleporter();
		}
		for (int i=0; i<INITIAL_SPLITTERS; i++) {
			sc.addSplitter();
		}
	}

	public void addTickable(Tickable t) {
		tickable.add(t);
	}

	public void addKeyHandler(KeyHandler k) {
		keyHandlers.add(k);
	}

	public boolean snakesPresent() {
		return Snake.snakeCount > 0;
	}

	public boolean checkForSnakes() {
		if (!snakesPresent()) {
			fc.removeAll();
			tc.removeAll();
			sc.removeAll();
			sb.strikeoutAll();
			setNotice("Click to start");
			return false;
		}
		return true;
	}

	public void setNotice(String str) {
		if (notice == null) {
			notice = new Text(str,0,0,canvas);
			int size = canvas.getHeight()/3;
			notice.setFontSize(size);
			while (notice.getWidth() > canvas.getWidth()) {
				notice.setFontSize(size -= 2);
			}
			notice.moveTo(canvas.getWidth()/2-notice.getWidth()/2, canvas.getHeight()/2-notice.getHeight()/2);
		} else {
			notice.removeFromCanvas();
			notice = new Text(str,0,0,canvas);
			int size = canvas.getHeight()/3;
			notice.setFontSize(size);
			while (notice.getWidth() > canvas.getWidth()) {
				notice.setFontSize(size -= 2);
			}
			notice.moveTo(canvas.getWidth()/2-notice.getWidth()/2, canvas.getHeight()/2-notice.getHeight()/2);
		}
	}

	public void removeNotice() {
		if (notice != null) {
			notice.removeFromCanvas();
			notice = null;
		}
	}

	// WindowController overrides
	public void onMouseClick(Location l) {
		if (!snakesPresent()) {
			addInitialSnake(l.getX(), l.getY(), Snake.START_SIZE);
			removeNotice();
		} else {
			addExtraSnake(l.getX(), l.getY(), Snake.START_SIZE);
		}
	}
	public void onMouseMove(Location l) {}
	public void onMouseDrag(Location l) {}
	public void onMousePress(Location l) {}
	public void onMouseRelease(Location l) {}
	public void onMouseEnter(Location l) {}
	public void onMouseExit(Location l) {}

	// KeyListener implementation
	public void keyPressed(KeyEvent e) {
		for (int i=0; i<keyHandlers.size(); i++) {
			keyHandlers.get(i).handleKey(e.getKeyCode(), e.isShiftDown(), e.isControlDown(), e.isAltDown());
		}
	}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}
