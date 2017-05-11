import objectdraw.Resizable2DInterface;
import objectdraw.DrawingCanvas;
import objectdraw.Location;
import java.util.ArrayList;
import java.awt.Color;

public class Snake implements KeyHandler, Tickable, Obstacle, ConstrainedToArea, Removable {
	public static volatile int snakeCount = 0;
	public static final Color HEAD_COLOR = new Color(0x000000);
	public static final int START_SIZE = 10;
	public static final int TICKS_PER_MOVE = 2;
	public static final boolean OVERLAPS_ALLOWED = false;
	int[] acceptedKeyCodes;
	Direction[] keyCodeMap;
	DrawingCanvas canvas;
	World world;
	Direction curDirection = Direction.RIGHT;
	Color[] colors;
	int colorIndex = 0;
	boolean removed = false;
	int curTick = 1;

	ArrayList<SnakePiece> body = new ArrayList<SnakePiece>();
	int numNeededToCreate=0;

	public Snake(int x, int y, int size, Color[] colors, int[] acceptedKeyCodes, Direction[] keyCodeMap, DrawingCanvas canvas, World world) {
		snakeCount++;
		this.colors = colors;
		this.acceptedKeyCodes = acceptedKeyCodes;
		this.keyCodeMap = keyCodeMap;
		this.canvas = canvas;
		this.world = world;
		this.numNeededToCreate = size-1;
		this.initialGrow(x, y);
	}

	public Snake(double x, double y, int size, Color[] colors, int[] acceptedKeyCodes, Direction[] keyCodeMap, DrawingCanvas canvas, World world) {
		this((int)x, (int)y, size, colors, acceptedKeyCodes, keyCodeMap, canvas, world);
	}

	private Snake(ArrayList<SnakePiece> body, int[] acceptedKeyCodes, Direction[] keyCodeMap, Snake parent) {
		this(body, acceptedKeyCodes, keyCodeMap, parent.colors, parent);
	}

	private Snake(ArrayList<SnakePiece> body, int[] acceptedKeyCodes, Direction[] keyCodeMap, Color[] colors, Snake parent) {
		snakeCount++;
		this.body = body;
		this.acceptedKeyCodes = acceptedKeyCodes;
		this.keyCodeMap = keyCodeMap;
		this.colors = colors;
		this.canvas = parent.canvas;
		this.world = parent.world;
		this.numNeededToCreate = parent.numNeededToCreate/2;
		parent.numNeededToCreate /= 2;
		this.curDirection = Helpers.rotateDirection(parent.getHead().getDirection());

		for (int i=0; i<body.size(); i++) {
			body.get(i).getObject().setColor(colors[colorIndex++]);
			colorIndex %= colors.length;
		}

		body.get(0).getObject().setColor(HEAD_COLOR);
	}

	public Color getPrimaryColor() {
		return colors[0];
	}

	public void teleportTo(Location l) {
		if (curTick == 1) {
			getHead().getObject().moveTo(l);
		}
	}

	public void tick() {
		if (curTick++ == TICKS_PER_MOVE) {
			if (numNeededToCreate > 0) {
				this.grow();
				body.get(body.size()-1).setDirection(body.get(body.size()-2).getDirection());
				numNeededToCreate--;
				for (int i=body.size()-2; i>0; i--) {
					body.get(i).setDirection(body.get(i-1).getDirection());
					body.get(i).moveTo(body.get(i-1).getLocation());
				}
			} else {
				for (int i=body.size()-1; i>0; i--) {
					body.get(i).setDirection(body.get(i-1).getDirection());
					body.get(i).moveTo(body.get(i-1).getLocation());
				}
			}
			body.get(0).setDirection(curDirection);
			body.get(0).move();
			curTick = 1;
		}
	}

	public void grow(int amount) {
		numNeededToCreate += amount;
	}

	public void grow() {
		grow((int)body.get(body.size()-1).getObject().getX(),(int)body.get(body.size()-1).getObject().getY());
	}

	public void grow(int x, int y) {
		body.add(new SnakePiece(x, y,colors[colorIndex++], canvas, world));
		colorIndex %= colors.length;
	}

	public void initialGrow(int x, int y) {
		body.add(new SnakePiece(x, y,HEAD_COLOR, canvas, world));
	}

	public void handleKey(int keyCode, boolean shiftState, boolean controlState, boolean altState) {
		if (Helpers.inSortedArray(keyCode, acceptedKeyCodes) != -1) {
			if (curDirection != Direction.RIGHT && keyCodeMap[Helpers.inSortedArray(keyCode, acceptedKeyCodes)] == Direction.LEFT ||
				curDirection != Direction.UP    && keyCodeMap[Helpers.inSortedArray(keyCode, acceptedKeyCodes)] == Direction.DOWN ||
				curDirection != Direction.LEFT  && keyCodeMap[Helpers.inSortedArray(keyCode, acceptedKeyCodes)] == Direction.RIGHT ||
				curDirection != Direction.DOWN  && keyCodeMap[Helpers.inSortedArray(keyCode, acceptedKeyCodes)] == Direction.UP) {
				curDirection = keyCodeMap[Helpers.inSortedArray(keyCode, acceptedKeyCodes)];
			}
		}
		if (keyCode == 51) {
			grow(1);
		}
		if (keyCode == 52) {
			remove();
		}
		if (keyCode == 53) {
			Snake newSnake = split(World.COLEMAK_KEYS, World.COLEMAK_KEYS_ORDER);
			world.addTickable(newSnake);
			world.addKeyHandler(newSnake);
		}
	}

	public boolean overlapsSelf() {
		for (int i=0; i<body.size()-1; i++) {
			for (int j=i+1; j<body.size(); j++) {
				if (body.get(i).overlaps(body.get(j))) {
					return true && !OVERLAPS_ALLOWED;
				}
			}
		}
		return false;
	}

	public boolean overlaps(Snake s) {
		if (s == this) {
			return overlapsSelf();
		}
		for (int i=0; i<s.getLength(); i++) {
			if (this.getHead().overlaps(s.body.get(i))) {
				return true;
			}
		}
		return false;
	}

	public boolean overlaps(Obstacle a) {
		if (a == this) {
			return overlapsSelf();
		}
		if (a instanceof Snake) {
			return overlaps((Snake) a);
		}
		return this.overlaps(a.getObject());
	}

	public boolean overlaps(Resizable2DInterface a) {
		for (Obstacle obj : body) {
			if (obj.overlaps(a)) {
				return true;
			}
		}
		return false;
	}

	public Resizable2DInterface getObject() {
		return body.get(0).getObject();
	}

	public boolean isInArea() {
		Resizable2DInterface head = body.get(0).getObject();
		return head.getX() >= 0 && head.getY() >= 0 &&
		       head.getX() < canvas.getWidth() &&
		       head.getY() < canvas.getHeight();
	}

	public void remove() {
		snakeCount--;
		for (SnakePiece piece : body) {
			piece.remove();
		}
		removed = true;
	}

	public SnakePiece getHead() {
		return body.get(0);
	}

	public boolean isRemoved() {
		return removed;
	}

	public int getLength() {
		return body.size();
	}

	public Snake split(int[] acceptedKeyCodes, Direction[] keyCodeMap) {
		if (getLength() <= 1) {
			return null;
		}
		return split(getLength()/2, acceptedKeyCodes, keyCodeMap);
	}

	public Snake split(int[] acceptedKeyCodes, Direction[] keyCodeMap, Color[] colorScheme) {
		if (getLength() <= 1) {
			return null;
		}
		return split(getLength()/2, acceptedKeyCodes, keyCodeMap, colorScheme);
	}

	public Snake split(int size, int[] acceptedKeyCodes, Direction[] keyCodeMap) {
		if (getLength() <= 1 || curTick != 1) {
			return null;
		}
		ArrayList<SnakePiece> newBody = new ArrayList<SnakePiece>();
		while (size<body.size()) {
			newBody.add(body.get(size));
			body.remove(size);
		}
		return new Snake(newBody, acceptedKeyCodes, keyCodeMap, this);
	}

	public Snake split(int size, int[] acceptedKeyCodes, Direction[] keyCodeMap, Color[] colorScheme) {
		if (getLength() <= 1 || curTick != 1) {
			return null;
		}
		ArrayList<SnakePiece> newBody = new ArrayList<SnakePiece>();
		while (size<body.size()) {
			newBody.add(body.get(size));
			body.remove(size);
		}
		return new Snake(newBody, acceptedKeyCodes, keyCodeMap, colorScheme, this);
	}
}
