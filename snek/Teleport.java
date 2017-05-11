import objectdraw.Resizable2DInterface;
import objectdraw.FilledOval;
import objectdraw.Location;
import objectdraw.FramedOval;
import objectdraw.DrawingCanvas;
import java.awt.Color;

public class Teleport extends ContainsObject implements Obstacle, Removable {
	public static final Color INNER_COLOR = new Color(0x000000);

	Resizable2DInterface inside;
	DrawingCanvas canvas;
	World world;
	boolean removed = false;

	public Teleport(int x, int y, int w, int h, Color ic, Color fc, DrawingCanvas canvas, World world) {
		this.canvas = canvas;
		this.world = world;
		object = new FilledOval(x, y, w, h, canvas);
		object.setColor(fc);
		inside = new FilledOval(x+w/8, y+h/8, w/4*3, h/4*3, canvas);
		inside.setColor(ic);
	}

	public boolean overlaps(Resizable2DInterface obj) {
		return object.getX() == obj.getX() &&
		       object.getY() == obj.getY();
	}

	public boolean overlaps(Obstacle obj) {
		return this.overlaps(obj.getObject());
	}

	public Location getLocation() {
		return object.getLocation();
	}

	public void remove() {
		object.removeFromCanvas();
		inside.removeFromCanvas();
		removed = true;
	}

	public boolean isRemoved() {
		return removed;
	}
}