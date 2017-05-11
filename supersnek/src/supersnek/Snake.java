package supersnek;

import java.awt.Color;
import java.util.ArrayList;

import objectdraw.DrawingCanvas;
import objectdraw.Location;
import objectdraw.Resizable2DInterface;

/**
 * Snake - contains SnakePiece, main object in the game
 *
 * @author Noah Overcash (smileytechguy@smileytechguy.com)
 */
public class Snake extends Moveable implements KeyHandler, ConstrainedToArea, Tickable, Removable {
    /**
     * Number of snakes currently alive
     */
    public static volatile int snakeCount = 0;

    /**
     * Default color for the snake head
     */
    public static final Color HEAD_COLOR = new Color(0x000000);
    /**
     * Initial size of the snake
     */
    public static final int START_SIZE = 10;
    /**
     * Number of ticks until the snake moves one step
     */
    public static final int INITIAL_TICKS_PER_MOVE = 2;
    /**
     * Determines whether the snake can overlap itself
     */
    public static final boolean OVERLAPS_ALLOWED = true;

    /**
     * Array of accepted key codes
     */
    protected int[] acceptedKeyCodes;
    /**
     * Map of accepted key codes to directions
     */
    protected Direction[] keyCodeMap;
    /**
     * Colors of the snake
     */
    protected Color[] colors;
    /**
     * Current index within the colors array
     */
    protected int colorIndex = 0;
    /**
     * tick counter for TICKS_PER_MOVE
     */
    protected int curTick = 1;
    /**
     * Amount of ammo the snake has
     */
    protected int ammo = 10;

    /**
     * ArrayList of SnakePieces
     */
    protected ArrayList<SnakePiece> body = new ArrayList<SnakePiece>();
    /**
     * Number of snake pieces which need to be created
     */
    protected int numNeededToCreate = 0;

    /**
     * Overridden for default of right
     */
    @SuppressWarnings("hiding")
    protected Direction direction = Direction.RIGHT;
    
    /**
     * Current number of ticks for a move
     */
    protected int ticksPerMove = Snake.INITIAL_TICKS_PER_MOVE;
    
    /**
     * Number of ticks until ticksPerMove resets
     * 
     * @see supersnek.Snake#ticksPerMove
     */
    protected int numToReset = -1;

    /**
     * Constructor
     *
     * @param x
     *            initial x
     * @param y
     *            intial y
     * @param size
     *            initial size
     * @param colors
     *            snake colors
     * @param acceptedKeyCodes
     *            accepted key codes
     * @param keyCodeMap
     *            what directions the keys map to
     * @param canvas
     *            canvas to draw snake on
     * @param world
     *            world the snake resides in
     */
    public Snake(int x, int y, int size, Color[] colors, int[] acceptedKeyCodes, Direction[] keyCodeMap,
            DrawingCanvas canvas, World world) {
        Snake.snakeCount++;
        this.colors = colors;
        this.acceptedKeyCodes = acceptedKeyCodes;
        this.keyCodeMap = keyCodeMap;
        this.canvas = canvas;
        this.world = world;
        this.numNeededToCreate = size - 1;
        this.initialGrow(x, y);
    }

    /**
     * Constructor
     *
     * @param x
     *            initial x
     * @param y
     *            intial y
     * @param size
     *            initial size
     * @param colors
     *            snake colors
     * @param acceptedKeyCodes
     *            accepted key codes
     * @param keyCodeMap
     *            what directions the keys map to
     * @param startDirection
     *            the direction the snake should start in
     * @param canvas
     *            canvas to draw snake on
     * @param world
     *            world the snake resides in
     */
    public Snake(int x, int y, int size, Color[] colors, int[] acceptedKeyCodes, Direction[] keyCodeMap,
            Direction startDirection, DrawingCanvas canvas, World world) {
        Snake.snakeCount++;
        this.direction = startDirection;
        this.colors = colors;
        this.acceptedKeyCodes = acceptedKeyCodes;
        this.keyCodeMap = keyCodeMap;
        this.canvas = canvas;
        this.world = world;
        this.numNeededToCreate = size - 1;
        this.initialGrow(x, y);
    }

    /**
     * Constructor
     *
     * @param x
     *            initial x
     * @param y
     *            intial y
     * @param size
     *            initial size
     * @param colors
     *            snake colors
     * @param acceptedKeyCodes
     *            accepted key codes
     * @param keyCodeMap
     *            what directions the keys map to
     * @param canvas
     *            canvas to draw snake on
     * @param world
     *            world the snake resides in
     */
    public Snake(double x, double y, int size, Color[] colors, int[] acceptedKeyCodes, Direction[] keyCodeMap,
            DrawingCanvas canvas, World world) {
        this((int) x, (int) y, size, colors, acceptedKeyCodes, keyCodeMap, canvas, world);
    }

    /**
     * Constructor (protected) for splitting
     *
     * @param body
     *            body of new snake
     * @param acceptedKeyCodes
     *            accepted key codes
     * @param keyCodeMap
     *            what directions the keys map to
     * @param parent
     *            parent snake to copy properties of
     */
    protected Snake(ArrayList<SnakePiece> body, int[] acceptedKeyCodes, Direction[] keyCodeMap, Snake parent) {
        this(body, acceptedKeyCodes, keyCodeMap, parent.colors, parent);
    }

    /**
     * Constructor (protected) for splitting
     *
     * @param body
     *            body of new snake
     * @param acceptedKeyCodes
     *            accepted key codes
     * @param keyCodeMap
     *            what directions the keys map to
     * @param colors
     *            colors of new snake
     * @param parent
     *            parent snake to copy properties of
     */
    protected Snake(ArrayList<SnakePiece> body, int[] acceptedKeyCodes, Direction[] keyCodeMap, Color[] colors,
            Snake parent) {
        Snake.snakeCount++;
        this.body = body;
        this.acceptedKeyCodes = acceptedKeyCodes;
        this.keyCodeMap = keyCodeMap;
        this.colors = colors;
        this.canvas = parent.canvas;
        this.world = parent.world;
        this.numNeededToCreate = parent.numNeededToCreate / 2;
        parent.numNeededToCreate /= 2;
        this.direction = Helpers.rotateDirection(parent.getHead().getDirection());
        this.ammo = parent.ammo / 2;
        parent.ammo /= 2;

        for (int i = 0; i < body.size(); i++) {
            body.get(i).setColor(colors[this.colorIndex++]);
            this.colorIndex %= colors.length;
        }

        body.get(0).setColor(Snake.HEAD_COLOR);
    }

    /**
     * @return main color of snake
     */
    public Color getPrimaryColor() {
        return this.colors[0];
    }

    /**
     * move head to specified location
     *
     * @param l
     *            location to move head to
     */
    public void teleportTo(Location l) {
        if (this.curTick == 1) {
            this.getHead().moveTo(l);
        }
    }

    /**
     * Check if snake should move
     *
     * @see supersnek.Tickable#tick()
     */
    public void tick() {
        if (this.curTick++ == this.ticksPerMove) {
            this.move();
        }
        if (--this.numToReset == 0) {
            this.ticksPerMove = Snake.INITIAL_TICKS_PER_MOVE;
        }
    }

    /**
     * Move the snake along, growing if needed
     */
    public void move() {
        if (this.numNeededToCreate > 0) {
            this.grow();
            this.body.get(this.body.size() - 1).setDirection(this.body.get(this.body.size() - 2).getDirection());
            this.numNeededToCreate--;
            for (int i = this.body.size() - 2; i > 0; i--) {
                this.body.get(i).setDirection(this.body.get(i - 1).getDirection());
                this.body.get(i).moveTo(this.body.get(i - 1).getLocation());
            }
        } else {
            for (int i = this.body.size() - 1; i > 0; i--) {
                this.body.get(i).setDirection(this.body.get(i - 1).getDirection());
                this.body.get(i).moveTo(this.body.get(i - 1).getLocation());
            }
        }
        this.body.get(0).setDirection(this.direction);
        this.body.get(0).move();
        this.curTick = 1;
    }

    /**
     * Grow the snake by an amount scaled by size of the snake
     *
     * @param amount
     *            amount to grow the snake
     */
    public void grow(int amount) {
        this.numNeededToCreate = (int) Math.max(amount * Math.log(2 * this.getLength() / 3 + 2) + 1,
                this.numNeededToCreate + Math.log(2 * this.getLength() / 3 + 2) + 1);
    }

    /**
     * Add one snake piece
     */
    public void grow() {
        this.grow((int) this.body.get(this.body.size() - 1).getObject().getX(),
                (int) this.body.get(this.body.size() - 1).getObject().getY());
    }

    /**
     * Add a snake piece at specified location
     *
     * @param x
     *            where to add snake piece
     * @param y
     *            where to add snake piece
     */
    public void grow(int x, int y) {
        this.body.add(new SnakePiece(x, y, this.colors[this.colorIndex++], this.canvas, this.world));
        this.colorIndex %= this.colors.length;
    }

    /**
     * Add intial snake piece
     *
     * @param x
     *            where to add snake piece
     * @param y
     *            where to add snake piece
     */
    public void initialGrow(int x, int y) {
        this.body.add(new SnakePiece(x, y, Snake.HEAD_COLOR, this.canvas, this.world));
        this.object = this.body.get(0).getObject();
    }

    /**
     * Turn snake or shoot based on the key code
     *
     * @param keyCode
     *            code of key
     * @param shiftState
     *            boolean of shift key
     * @param controlState
     *            boolean of ctrl key
     * @param altState
     *            boolean of alt/option key
     * @see supersnek.KeyHandler#handleKey(int, boolean, boolean, boolean)
     */
    public void handleKey(int keyCode, boolean shiftState, boolean controlState, boolean altState) {
        if (Helpers.inSortedArray(keyCode, this.acceptedKeyCodes) != -1) {
            if (this.direction != Direction.RIGHT
                    && this.keyCodeMap[Helpers.inSortedArray(keyCode, this.acceptedKeyCodes)] == Direction.LEFT
                    || this.direction != Direction.UP
                            && this.keyCodeMap[Helpers.inSortedArray(keyCode, this.acceptedKeyCodes)] == Direction.DOWN
                    || this.direction != Direction.LEFT
                            && this.keyCodeMap[Helpers.inSortedArray(keyCode, this.acceptedKeyCodes)] == Direction.RIGHT
                    || this.direction != Direction.DOWN
                            && this.keyCodeMap[Helpers.inSortedArray(keyCode, this.acceptedKeyCodes)] == Direction.UP) {
                this.direction = this.keyCodeMap[Helpers.inSortedArray(keyCode, this.acceptedKeyCodes)];
            }
            if (this.keyCodeMap[Helpers.inSortedArray(keyCode, this.acceptedKeyCodes)] == Direction.SHOOT) {
                this.fireBullet();
            }
        }
        if (keyCode == 51) {
            this.grow(1);
        }
        if (keyCode == 52) {
            this.remove();
        }
        if (keyCode == 53) {
            Snake newSnake = this.split(KeyController.COLEMAK_KEYS, KeyController.COLEMAK_KEYS_MAP);
            this.world.addTickable(newSnake);
            this.world.addKeyHandler(newSnake);
        }
        if (keyCode == 56) {
            this.setTicksPerMove(1, 20);
        }
    }

    /**
     * fire a bullet from snake head
     */
    public void fireBullet() {
        if (this.ammo == 0) {
            return;
        }
        double x, y;
        if (this.direction == Direction.UP || this.direction == Direction.DOWN) {
            x = this.getHead().getObject().getX();
        } else if (this.direction == Direction.RIGHT) {
            x = this.getHead().getObject().getX() + World.GRID_SIZE;
        } else {
            x = this.getHead().getObject().getX() - World.GRID_SIZE;
        }

        if (this.direction == Direction.LEFT || this.direction == Direction.RIGHT) {
            y = this.getHead().getObject().getY();
        } else if (this.direction == Direction.UP) {
            y = this.getHead().getObject().getY() - World.GRID_SIZE;
        } else {
            y = this.getHead().getObject().getY() + World.GRID_SIZE;
        }

        Bullet bullet = new Bullet((int) x, (int) y, World.GRID_SIZE, World.GRID_SIZE, Bullet.BULLET_COLOR,
                this.direction, this.canvas, this.world);

        this.world.addTickable(bullet);
        this.ammo--;
    }

    /**
     * @return if the snake is overlapping itself
     */
    public boolean overlapsSelf() {
        for (int i = 0; i < this.body.size() - 1; i++) {
            for (int j = i + 1; j < this.body.size(); j++) {
                if (this.body.get(i).overlaps(this.body.get(j))) {
                    return true && !Snake.OVERLAPS_ALLOWED;
                }
            }
        }
        return false;
    }

    /**
     * @param s
     *            snake to test overlapping
     * @return if the snake overlaps this one
     */
    public boolean overlaps(Snake s) {
        if (s == this) {
            return this.overlapsSelf();
        }
        for (int i = 0; i < s.getLength(); i++) {
            if (this.getHead().overlaps(s.body.get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param obj
     *            object to test overlaps
     * @return if the snake overlaps this object
     */
    public boolean overlaps(ContainsObject obj) {
        return this.overlaps(obj.getObject());
    }

    /**
     * @param obj
     *            to check for overlap
     * @return if the snake overlaps an object
     * @see supersnek.ContainsObject#overlaps(objectdraw.Resizable2DInterface)
     */
    public boolean overlaps(Resizable2DInterface obj) {
        for (int i = 0; i < this.body.size(); i++) {
            if (this.body.get(i).overlaps(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param a
     *            to check for overlap
     * @return index of snake body that overlaps, -1 if it doesnt overlap
     * @see supersnek.ContainsObject#overlaps(objectdraw.Resizable2DInterface)
     */
    public int overlapIndex(ContainsObject a) {
        for (int i = 0; i < this.body.size(); i++) {
            if (this.body.get(i).overlaps(a.getObject())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return if the snake is in canvas bounds
     */
    public boolean isInArea() {
        if (this.isRemoved()) {
            return false;
        }
        Resizable2DInterface head = this.body.get(0).getObject();
        return head.getX() >= 0 && head.getY() >= 0 && head.getX() < this.canvas.getWidth()
                && head.getY() < this.canvas.getHeight();
    }

    /**
     * Remove/destroy snake
     */
    public void remove() {
        Snake.snakeCount--;
        for (SnakePiece piece : this.body) {
            piece.remove();
        }
        this.removed = true;
    }

    /**
     * Get the snake head
     *
     * @return snake head
     */
    public SnakePiece getHead() {
        return this.body.get(0);
    }

    /**
     * @return if the snake has been removed (or doesn't exist in general O_o)
     */
    public boolean isRemoved() {
        if (!this.removed && this.getLength() == 0) {
            this.remove();
        }
        return this.removed;
    }

    /**
     * @return snake length
     */
    public int getLength() {
        return this.body.size();
    }

    /**
     * split snake with a specified keyset
     *
     * @param acceptedKeyCodes
     *            new keycodes
     * @param keyCodeMap
     *            new keymap
     * @return new snake
     */
    public Snake split(int[] acceptedKeyCodes, Direction[] keyCodeMap) {
        if (this.getLength() <= 1) {
            return null;
        }
        return this.split(this.getLength() / 2, acceptedKeyCodes, keyCodeMap);
    }

    /**
     * split snake with a specified keyset and color
     *
     * @param acceptedKeyCodes
     *            new keycodes
     * @param keyCodeMap
     *            new keymap
     * @param colorScheme
     *            new color scheme
     * @return new snake
     */

    public Snake split(int[] acceptedKeyCodes, Direction[] keyCodeMap, Color[] colorScheme) {
        if (this.getLength() <= 1) {
            return null;
        }
        return this.split(this.getLength() / 2, acceptedKeyCodes, keyCodeMap, colorScheme);
    }

    /**
     * split snake with a specified keyset and size
     *
     * @param size
     *            new snake size
     * @param acceptedKeyCodes
     *            new keycodes
     * @param keyCodeMap
     *            new keymap
     * @return new snake
     */
    public Snake split(int size, int[] acceptedKeyCodes, Direction[] keyCodeMap) {
        if (this.getLength() <= 1 || this.curTick != 1) {
            return null;
        }
        ArrayList<SnakePiece> newBody = new ArrayList<SnakePiece>();
        while (size < this.body.size()) {
            newBody.add(this.body.get(size));
            this.body.remove(size);
        }
        return new Snake(newBody, acceptedKeyCodes, keyCodeMap, this);
    }

    /**
     * split snake with a specified keyset, size, and color scheme
     *
     * @param size
     *            new snake size
     * @param acceptedKeyCodes
     *            new keycodes
     * @param keyCodeMap
     *            new keymap
     * @param colorScheme
     *            new colors
     * @return new snake
     */
    public Snake split(int size, int[] acceptedKeyCodes, Direction[] keyCodeMap, Color[] colorScheme) {
        if (this.getLength() <= 1 || this.curTick != 1) {
            return null;
        }
        ArrayList<SnakePiece> newBody = new ArrayList<SnakePiece>();
        while (size < this.body.size()) {
            newBody.add(this.body.get(size));
            this.body.remove(size);
        }
        return new Snake(newBody, acceptedKeyCodes, keyCodeMap, colorScheme, this);
    }

    /**
     * @return amount of ammo
     */
    public int getAmmo() {
        return this.ammo;
    }

    /**
     * increase ammo with respect to length
     */
    public void incAmmo() {
        this.ammo = (int) Math.max(Math.log(2 * this.getLength() / 3 + 2) + 1,
                this.ammo + Math.log(2 * this.getLength() / 3 + 2) + 1);
    }

    /**
     * @param to
     *            new snake length
     */
    public void trim(int to) {
        while (to < this.body.size()) {
            this.body.get(to).remove();
            this.body.remove(to);
        }
    }

    /**
     * @return the ticksPerMove
     */
    public int getTicksPerMove() {
        return this.ticksPerMove;
    }

    /**
     * @param ticksPerMove the ticksPerMove to set
     * @param numToReset the numToReset to set
     */
    public void setTicksPerMove(int ticksPerMove, int numToReset) {
        this.ticksPerMove = ticksPerMove;
        this.numToReset = Math.max(numToReset, this.numToReset+numToReset);
    }
}
