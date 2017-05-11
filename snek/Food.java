import objectdraw.Resizable2DInterface;
import objectdraw.FilledOval;
import objectdraw.FramedOval;
import objectdraw.DrawingCanvas;
import java.awt.Color;

public class Food extends ContainsObject implements Obstacle, Removable {
	public static final Color FOOD_COLOR = new Color(0x66bb6a);
	public static final Color DOUBLE_FOOD_COLOR = new Color(0x66bb6a);
	public static final Color DOUBLE_FOOD_BORDER_COLOR = new Color(0x000000);

	Resizable2DInterface inner;
	DrawingCanvas canvas;
	World world;
	int val;
	boolean removed = false;

	public Food(int x, int y, int w, int h, Color c, int val, DrawingCanvas canvas, World world) {
		this.canvas = canvas;
		this.world = world;
		this.val = val;
		object = new FilledOval(x, y, w, h, canvas);
		object.setColor(c);
	}

	public Food(int x, int y, int w, int h, Color c1, Color c2, int val, DrawingCanvas canvas, World world) {
		this(x, y, w, h, c2, val, canvas, world);
		inner = new FilledOval(x+w/8, y+h/8, w/4*3, h/4*3, canvas);
		inner.setColor(c1);
	}

	public boolean overlaps(Resizable2DInterface obj) {
		return object.getX() == obj.getX() &&
		       object.getY() == obj.getY();
	}

	public boolean overlaps(Obstacle obj) {
		return this.overlaps(obj.getObject());
	}

	public void remove() {
		object.removeFromCanvas();
		if (inner != null) {
			inner.removeFromCanvas();
		}
		removed = true;
	}

	public boolean isRemoved() {
		return removed;
	}

	public int getValue() {
		return val;
	}
}