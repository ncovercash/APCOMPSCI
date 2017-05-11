package supersnek;

import java.awt.Color;
import java.util.ArrayList;

import objectdraw.ActiveObject;
import objectdraw.DrawingCanvas;
import objectdraw.FilledRect;
import objectdraw.JDrawingCanvas;
import objectdraw.Location;
import objectdraw.Text;

/**
 * Main thread of the program executes here through ActiveObject
 *
 * @author Noah Overcash (smileytechguy@smileytechguy.com)
 */
public class World extends ActiveObject {
    /**
     * Primary key controls
     */
    public static final int[] PRIMARY_KEY_CONTROLS = KeyController.ARROW_KEYS;
    /**
     * Primary key controls map
     */
    public static final Direction[] PRIMARY_KEY_CONTROLS_MAP = KeyController.ARROW_KEYS_MAP;
    /**
     * Secondary key controls
     */
    public static final int[] SECONDARY_KEY_CONTROLS = KeyController.COLEMAK_KEYS;
    /**
     * Secondary key controls map
     */
    public static final Direction[] SECONDARY_KEY_CONTROLS_MAP = KeyController.COLEMAK_KEYS_MAP;

    /**
     * Size of blocks on grid for common measurement
     */
    public static final int GRID_SIZE = 32;

    /**
     * Initial amount of food
     */
    public static final int INITIAL_FOOD = 5;
    /**
     * Initial amount of teleporters
     */
    public static final int INITIAL_TELEPORTERS = 3;
    /**
     * Initial amount of splitters
     */
    public static final int INITIAL_SPLITTERS = 0;
    /**
     * Initial amount of bullet food
     */
    public static final int INITIAL_BULLET_FOOD = 5;
    /**
     * Initial amount of bullet food
     */
    public static final int INITIAL_SPEED= 5;

    /**
     * Length which will automatically cause the game to win
     */
    public static final int AUTOMATIC_WIN_LENGTH = 75;

    /**
     * Pause time between run iterations
     */
    public int pauseTime = 90;
    /**
     * If running (quits if false)
     */
    public boolean running = true;

    /**
     * scoreboard
     */
    public ScoreBoard sb;
    /**
     * notice text
     */
    protected Text notice;
    /**
     * keyboard controller
     */
    protected KeyController kc;
    /**
     * food controller
     */
    protected FoodController fc;
    /**
     * teleport controller
     */
    protected TeleportController tc;
    /**
     * splitter controller
     */
    protected SplitterController sc;
    /**
     * bullet food controller
     */
    protected BulletFoodController bfc;
    /**
     * speed controller
     */
    protected SpeedController spc;
    /**
     * world controller
     */
    protected WorldController wc;
    /**
     * grid
     */
    protected Grid grid;
    /**
     * instructions
     */
    protected Instructions instructions;
    /**
     * canvas
     */
    protected DrawingCanvas canvas;
    /**
     * tickable array to iterate and tick
     */
    protected ArrayList<Tickable> tickable = new ArrayList<Tickable>();
    /**
     * keyhandlers to notify on key press
     */
    protected ArrayList<KeyHandler> keyHandlers = new ArrayList<KeyHandler>();
    /**
     * Background of the canvas
     */
    protected FilledRect background;
    /**
     * Whether the screen has been cleared after a game
     */
    protected boolean clearedScreen;

    /**
     * Constructor
     *
     * @param canvas
     *            canvas to draw stuff on
     */
    public World(DrawingCanvas canvas) {
        this.canvas = canvas;

        this.initializeControllers();

        // make background
        this.background = new FilledRect(0, 0, canvas.getWidth(), canvas.getHeight(), canvas);
        this.background.setColor(new Color(0xbcaaa4));
        this.grid = new Grid(canvas);
        this.background.sendToBack();

        this.setNotice("Enter to start");

        this.instructions = new Instructions(this.notice.getY() + this.notice.getHeight(), canvas, this);

        this.start();
    }

    /**
     * Initialize controllers
     */
    public void initializeControllers() {
        // make controllers
        this.wc = new WorldController(this);
        this.keyHandlers.add(this.wc);
        
        this.kc = new KeyController(this, (JDrawingCanvas) this.canvas);

        this.fc = new FoodController(this.canvas, this);
        this.keyHandlers.add(this.fc);
        this.tickable.add(this.fc);

        this.bfc = new BulletFoodController(this.canvas, this);
        this.tickable.add(this.bfc);

        this.spc = new SpeedController(this.canvas, this);
        this.tickable.add(this.spc);

        this.tc = new TeleportController(this.canvas, this);
        this.keyHandlers.add(this.tc);

        this.sc = new SplitterController(this.canvas, this);
        this.keyHandlers.add(this.sc);

        this.sb = new ScoreBoard(this.canvas, this);
        this.tickable.add(this.sb);
    }

    /**
     * Create initial snake
     */
    public void addInitialSnakes() {
        this.sb.removeAll();
        Snake snake = new Snake(this.canvas.getWidth() * World.GRID_SIZE / World.GRID_SIZE - World.GRID_SIZE,
                this.canvas.getHeight() * World.GRID_SIZE / World.GRID_SIZE - World.GRID_SIZE, Snake.START_SIZE,
                Helpers.getRandomColorScheme(), World.PRIMARY_KEY_CONTROLS, World.PRIMARY_KEY_CONTROLS_MAP,
                Direction.LEFT, this.canvas, this);
        this.tickable.add(snake);
        this.keyHandlers.add(snake);
        this.sb.addItem(snake);
        snake = new Snake(0, 0, Snake.START_SIZE, Helpers.getRandomColorScheme(), World.SECONDARY_KEY_CONTROLS,
                World.SECONDARY_KEY_CONTROLS_MAP, Direction.RIGHT, this.canvas, this);
        this.tickable.add(snake);
        this.keyHandlers.add(snake);
        this.sb.addItem(snake);
    }
    
    /**
     * Create initial items
     */
    public void addInitialItems() {
        for (int i = 0; i < World.INITIAL_FOOD; i++) {
            this.fc.addItem();
        }
        for (int i = 0; i < World.INITIAL_TELEPORTERS; i++) {
            this.tc.addItem();
        }
        for (int i = 0; i < World.INITIAL_SPLITTERS; i++) {
            this.sc.addItem();
        }
        for (int i = 0; i < World.INITIAL_BULLET_FOOD; i++) {
            this.bfc.addItem();
        }
        for (int i = 0; i < World.INITIAL_SPEED; i++) {
            this.spc.addItem();
        }
    }
    
    /**
     * Start a new supersnake game
     */
    public void startGame() {
        if (!World.snakesPresent()) {
            this.instructions.hide();
            this.addInitialItems();
            this.addInitialSnakes();
            this.removeNotice();
            this.clearedScreen = false;
        }
    }
    
    /**
     * restart game
     */
    public void restartGame() {
        for (int i = 0; i < this.tickable.size(); i++) {
            if (this.tickable.get(i) instanceof Snake) {
                ((Snake) this.tickable.get(i)).remove();
            }
        }
    }
    
    /**
     * Separate thread which controls logic and checks
     *
     * @see java.lang.Thread#run()
     */
    public void run() {
        while (this.running) {
            while (this.checkForSnakes() && this.running) {
                this.tickAllObjects();
                this.checkWinningLength();
                this.checkFoodOverlaps();
                this.checkBulletFoodOverlaps();
                this.checkSpeedOverlaps();
                this.checkSnakeOverlaps();
                this.checkBulletHits();
                this.checkTeleporterOverlaps();
                this.checkSplitterOverlaps();
                this.checkBoundaries();
                this.gc();
                ActiveObject.pause(this.pauseTime);
            }
            ActiveObject.pause(this.pauseTime);
        }
        System.exit(0);
    }

    /**
     * Ticks all objects
     */
    public void tickAllObjects() {
        for (int i = 0; i < this.tickable.size(); i++) {
            this.tickable.get(i).tick();
            // check snake head overlap
            // must do in tick loop, as 2 heads adjacent will not
            // overlap after tick
            if (this.tickable.get(i) instanceof Snake && !((Removable) this.tickable.get(i)).isRemoved()) {
                for (int j = i + 1; j < this.tickable.size(); j++) {
                    if (this.tickable.get(j) instanceof Snake && !((Removable) this.tickable.get(j)).isRemoved()) {
                        if (((Snake) this.tickable.get(i)).getHead()
                                .overlaps(((Snake) this.tickable.get(j)).getHead())) {
                            this.restartGame();
                        }
                    }
                }
            }
            // check bullet overlap
            // must do in tick loop, as 2 bullets adjacent will not
            // overlap after tick
            if (this.tickable.get(i) instanceof Bullet && !((Removable) this.tickable.get(i)).isRemoved()) {
                for (int j = i + 1; j < this.tickable.size(); j++) {
                    if (this.tickable.get(j) instanceof Bullet && !((Removable) this.tickable.get(j)).isRemoved()) {
                        if (((Bullet) this.tickable.get(i)).getObject()
                                .overlaps(((Bullet) this.tickable.get(j)).getObject())) {
                            ((Bullet)this.tickable.get(i)).remove();
                            ((Bullet)this.tickable.get(j)).remove();
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Check for snakes that are beyond winning length
     */
    public void checkWinningLength() {
        for (int i = 0; i < this.tickable.size(); i++) {
            if (this.tickable.get(i) instanceof Snake) {
                if (((Snake) this.tickable.get(i)).getLength() >= World.AUTOMATIC_WIN_LENGTH) {
                    for (int j = 0; j < this.tickable.size(); j++) {
                        if (this.tickable.get(j) instanceof Snake && i != j) {
                            ((Removable) this.tickable.get(j)).remove();
                        }
                    }
                    this.sb.tick();
                    ((Removable) this.tickable.get(i)).remove();
                }
            }
        }
    }
    
    /**
     * Check for food overlapped by snake
     */
    public void checkFoodOverlaps() {
        ArrayList<Food> foodArr = this.fc.getObjects();
        for (int i = 0; i < foodArr.size(); i++) {
            for (int j = 0; j < this.tickable.size(); j++) {
                if (this.tickable.get(j) instanceof Snake && !((Removable) this.tickable.get(j)).isRemoved()
                        && ((Snake) this.tickable.get(j)).overlaps(foodArr.get(i))) {
                    ((Snake) this.tickable.get(j)).grow(foodArr.get(i).getValue());
                    this.fc.remove(foodArr.get(i));
                    this.fc.addItem();
                }
            }
        }
    }
    
    /**
     * Check for bullet food overlapped by snake
     */
    public void checkBulletFoodOverlaps() {
        // check bullet food
        ArrayList<BulletFood> bulletFoodArr = this.bfc.getObjects();
        for (int i = 0; i < bulletFoodArr.size(); i++) {
            for (int j = 0; j < this.tickable.size(); j++) {
                if (this.tickable.get(j) instanceof Snake && !((Removable) this.tickable.get(j)).isRemoved()) {
                    if (((Snake) this.tickable.get(j)).overlaps(bulletFoodArr.get(i))) {
                        ((Snake) this.tickable.get(j)).incAmmo();
                        this.bfc.remove(bulletFoodArr.get(i));
                        this.bfc.addItem();
                    }
                }
            }
        }
    }
    
    /**
     * Check for speed overlapped by snake
     */
    public void checkSpeedOverlaps() {
        ArrayList<Speed> speedArr = this.spc.getObjects();
        for (int i = 0; i < speedArr.size(); i++) {
            for (int j = 0; j < this.tickable.size(); j++) {
                if (this.tickable.get(j) instanceof Snake && !((Removable) this.tickable.get(j)).isRemoved()) {
                    if (((Snake) this.tickable.get(j)).overlaps(speedArr.get(i))) {
                        ((Snake) this.tickable.get(j)).setTicksPerMove(1, 20);
                        this.spc.remove(speedArr.get(i));
                        this.spc.addItem();
                    }
                }
            }
        }
    }
    
    /**
     * Check for overlapping snakes
     */
    public void checkSnakeOverlaps() {
        for (int i = 0; i < this.tickable.size(); i++) {
            if (this.tickable.get(i) instanceof Snake && !((Removable) this.tickable.get(i)).isRemoved()) {
                for (int j = i; j < this.tickable.size(); j++) {
                    if (this.tickable.get(j) instanceof Snake && !((Removable) this.tickable.get(j)).isRemoved()) {
                        if (((Snake) this.tickable.get(i)).overlaps((Snake) this.tickable.get(j))) {
                            ((Removable) this.tickable.get(i)).remove();
                        } else if (((Snake) this.tickable.get(j)).overlaps((Snake) this.tickable.get(i))) {
                            ((Removable) this.tickable.get(j)).remove();
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Check for bullets hitting snakes
     */
    public void checkBulletHits() {
        for (int i = 0; i < this.tickable.size(); i++) {
            if (this.tickable.get(i) instanceof Snake && !((Removable) this.tickable.get(i)).isRemoved()) {
                for (int j = 0; j < this.tickable.size(); j++) {
                    if (this.tickable.get(j) instanceof Bullet) {
                        if (((Snake) this.tickable.get(i)).overlapIndex((Bullet) this.tickable.get(j)) != -1) {
                            ((Snake) this.tickable.get(i))
                                    .trim(((Snake) this.tickable.get(i)).overlapIndex((Bullet) this.tickable.get(j)));
                            ((Bullet) this.tickable.get(j)).remove();
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Check for snakes entering teleporters
     */
    public void checkTeleporterOverlaps() {
        ArrayList<Teleport> teleportArr = this.tc.getObjects();
        for (int j = 0; j < this.tickable.size(); j++) {
            for (int i = 0; i < teleportArr.size(); i++) {
                if (this.tickable.get(j) instanceof Snake && !((Removable) this.tickable.get(j)).isRemoved()) {
                    if (((Snake) this.tickable.get(j)).getHead().overlaps(teleportArr.get(i))) {
                        ((Snake) this.tickable.get(j))
                                .teleportTo(this.tc.getOtherTeleport(teleportArr.get(i)).getLocation());
                        break;
                    }
                }
            }
        }
    }
    
    /**
     * Check for snakes hitting splitters
     */
    public void checkSplitterOverlaps() {
        ArrayList<Splitter> splitterArr = this.sc.getObjects();
        for (int j = 0; j < this.tickable.size(); j++) {
            for (int i = 0; i < splitterArr.size(); i++) {
                if (this.tickable.get(j) instanceof Snake && !((Removable) this.tickable.get(j)).isRemoved()) {
                    if (((Snake) this.tickable.get(j)).getHead().overlaps(splitterArr.get(i))) {
                        Snake newSnake = ((Snake) this.tickable.get(j)).split(World.PRIMARY_KEY_CONTROLS,
                                World.PRIMARY_KEY_CONTROLS_MAP, Helpers.getRandomColorScheme());
                        if (newSnake != null) {
                            this.sc.remove(splitterArr.get(i));
                            this.sc.addItem();
                            this.tickable.add(newSnake);
                            this.keyHandlers.add(newSnake);
                            this.sb.addItem(newSnake);
                        }
                        break;
                    }
                }
            }
        }
    }
    
    /**
     * Check for things out of bounds
     */
    public void checkBoundaries() {
        for (int i = 0; i < this.tickable.size(); i++) {
            if (this.tickable.get(i) instanceof ConstrainedToArea) {
                if (!((ConstrainedToArea) this.tickable.get(i)).isInArea()
                        && !((Removable) this.tickable.get(i)).isRemoved()) {
                    if (this.tickable.get(i) instanceof Removable) {
                        ((Removable) this.tickable.get(i)).remove();
                    }
                }
            }
        }
    }
    
    /**
     * Remove removed objects
     */
    public void gc() {
        for (int i = 0; i < this.tickable.size(); i++) {
            if (this.tickable.get(i) instanceof Removable) {
                if (((Removable) this.tickable.get(i)).isRemoved()) {
                    if (this.tickable.get(i) instanceof KeyHandler) {
                        for (int j = 0; j < this.keyHandlers.size(); j++) {
                            if (this.keyHandlers.get(j) == this.tickable.get(i)) {
                                this.keyHandlers.remove(j);
                                break;
                            }
                        }
                    }
                    this.tickable.remove(i);
                    i--;
                }
            } else if (this.tickable.get(i) == null) {
                this.tickable.remove(i);
                i--;
            }
        }
    }
    
    /**
     * @return if there are any snakes alive
     */
    public static boolean snakesPresent() {
        return Snake.snakeCount > 0;
    }
    
    /**
     * Check for snakes and clear screen if not
     *
     * @return if snakes are alive
     */
    public boolean checkForSnakes() {
        if (!World.snakesPresent() || this.sb.hasWinner()) {
            if (this.clearedScreen) {
                return false;
            }
            for (int i = 0; i < this.tickable.size(); i++) {
                if (this.tickable.get(i) instanceof Removable && !((Removable)this.tickable.get(i)).isRemoved()) {
                    System.out.println(this.tickable.get(i));
                    ((Removable)this.tickable.get(i)).remove();
                }
            }
            this.gc();
            for (int i = 0; i < this.keyHandlers.size(); i++) {
                if (this.keyHandlers.get(i) instanceof Snake && !((Removable)this.tickable.get(i)).isRemoved()) {
                    ((Removable)this.keyHandlers.get(i)).remove();
                    System.out.println(this.keyHandlers.remove(i--));
                }
            }
            this.fc.removeAll();
            this.tc.removeAll();
            this.sc.removeAll();
            this.bfc.removeAll();
            this.spc.removeAll();
            this.sb.strikeoutAll();
            if (this.sb.hasWinner()) {
                this.sb.declareWinner();
            }
            this.setNotice("Enter to start");
            this.clearedScreen=true;
            return false;
        }
        return true;
    }
    
    /**
     * Set the large splash notice
     *
     * @param str
     *            new text to set notice to
     */
    public void setNotice(String str) {
        if (this.notice == null) {
            this.notice = new Text(str, 0, 0, this.canvas);
            this.notice.setFont("Helvetica");
            int size = this.canvas.getHeight() / 3;
            this.notice.setFontSize(size);
            while (this.notice.getWidth() > this.canvas.getWidth()) {
                this.notice.setFontSize(size -= 2);
            }
            this.notice.moveTo(this.canvas.getWidth() / 2 - this.notice.getWidth() / 2,
                    this.canvas.getHeight() / 2 - this.notice.getHeight() / 2);
        } else {
            this.notice.removeFromCanvas();
            this.notice = null;
            this.setNotice(str);
        }
    }
    
    /**
     * Hide the large notice
     */
    public void removeNotice() {
        if (this.notice != null) {
            this.notice.removeFromCanvas();
            this.notice = null;
        }
    }
    
    /**
     * Add tickable item to internal array
     *
     * @param t
     *            Tickable item
     */
    public void addTickable(Tickable t) {
        this.tickable.add(t);
    }

    /**
     * Add keyhandler item to internal array
     *
     * @param k
     *            KeyHandler item
     */
    public void addKeyHandler(KeyHandler k) {
        this.keyHandlers.add(k);
    }

    /**
     * @return the keyHandlers
     */
    public ArrayList<KeyHandler> getKeyHandlers() {
        return this.keyHandlers;
    }
    
    // WindowController overrides
    /**
     * On mouse click override
     *
     * @param l
     *            location of click
     */
    public void onMouseClick(Location l) {
        if (!World.snakesPresent()) {
            this.startGame();
        }
    }
}
