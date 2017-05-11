import objectdraw.WindowController;
import objectdraw.Location;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Driver extends WindowController implements KeyListener {
	private World w;

	public void begin() {
		System.out.println("Keys (may cause unexpected errors)");
		System.out.println(" key |    result");
		System.out.println("--------------------");
		System.out.println("  q  |     quit");
		System.out.println("  +  |   inc speed");
		System.out.println("  -  |   dec speed");
		System.out.println("  1  |   inc food");
		System.out.println("  2  |   dec food");
		System.out.println("  3  |  grow snakes");
		System.out.println("  4  |  kill snakes");
		System.out.println("  5  | split snakes");
		System.out.println("  6  | add teleport");
		System.out.println("  7  | add splitter");
		w = new World(canvas);
		canvas.addKeyListener(this);
	}

	// WindowController overrides
	public void onMouseClick(Location l) { w.onMouseClick(l); }
	public void onMouseMove(Location l) { w.onMouseMove(l); }
	public void onMouseDrag(Location l) { w.onMouseDrag(l); }
	public void onMousePress(Location l) { w.onMousePress(l); }
	public void onMouseRelease(Location l) { w.onMouseRelease(l); }
	public void onMouseEnter(Location l) { w.onMouseEnter(l); }
	public void onMouseExit(Location l) { w.onMouseExit(l); }

	// KeyListener implementation
	public void keyPressed(KeyEvent e) { w.keyPressed(e); }
	public void keyReleased(KeyEvent e) { w.keyReleased(e); }
	public void keyTyped(KeyEvent e) { w.keyTyped(e); }

	public static void main(String[] args) {
		// set window size based on args
		if (args.length == 0) {
			new Driver().startController(2016, 1024+44);
		} else if (args.length == 1) {
			new Driver().startController(Integer.parseInt(args[0])/World.GRID_SIZE*World.GRID_SIZE, Integer.parseInt(args[0])/World.GRID_SIZE*World.GRID_SIZE+44);
		} else {
			new Driver().startController(Integer.parseInt(args[0])/World.GRID_SIZE*World.GRID_SIZE, Integer.parseInt(args[1])/World.GRID_SIZE*World.GRID_SIZE+44);
		}
	}
}