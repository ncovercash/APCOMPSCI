import objectdraw.Location;
import objectdraw.Resizable2DInterface;
import objectdraw.DrawingCanvas;
import objectdraw.FilledRoundedRect;
import java.awt.Color;

public class SnakePiece extends Moveable implements Obstacle, Removable {
	Color color;
	DrawingCanvas canvas;
	World world;
	boolean removed = false;

	public SnakePiece(int x, int y, Color color, DrawingCanvas canvas, World world) {
		this.color = color;
		this.canvas = canvas;
		this.world = world;

		object = new FilledRoundedRect(x, y, world.GRID_SIZE, world.GRID_SIZE, world.GRID_SIZE*2/3, world.GRID_SIZE*2/3, canvas);
		object.setColor(color);
	}

	public void move() {
		switch (direction) {
			case UP:
				object.move(0, -world.GRID_SIZE);
				break;
			case DOWN:
				object.move(0, world.GRID_SIZE);
				break;
			case RIGHT:
				object.move(world.GRID_SIZE, 0);
				break;
			case LEFT:
				object.move(-world.GRID_SIZE, 0);
				break;
		}
	}

	public void moveTo(Location l) {
		object.moveTo(l);
	}

	public void moveTo(int x, int y) {
		object.moveTo(x,y);
	}

	public Location getLocation() {
		return object.getLocation();
	}

	public boolean overlaps(Obstacle a) {
		return this.overlaps(a.getObject());
	}

	public boolean overlaps(Resizable2DInterface a) {
		return a.getX() == this.getObject().getX() &&
			   a.getY() == this.getObject().getY();
	}

	public void remove() {
		object.removeFromCanvas();
		removed = true;
	}

	public boolean isRemoved() {
		return removed;
	}
}
