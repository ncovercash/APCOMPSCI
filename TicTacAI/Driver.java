import objectdraw.*;

public class Driver extends WindowController {
	World world;

	public static void main(String[] args) {
		new Driver().startController(1000, 1000);
	}

	public void begin() {
		world = new World(canvas);
	}

	public void onMouseClick(Location p) {
		world.handleClick(p.getX(), p.getY());
	}
}