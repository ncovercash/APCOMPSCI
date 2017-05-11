package supersnek;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.KeyListener;

import objectdraw.Location;
import objectdraw.WindowController;

/**
 * This class is used because it contains a main() method, and starts the
 * WindowController, and contains forwarding events from WindowController
 *
 * @author Noah Overcash (smileytechguy@smileytechguy.com)
 * @see objectdraw.WindowController
 */
public class Driver extends WindowController {
    /**
     * Contains the world which truly runs the program Used to forward events
     * from WindowController and KeyListener
     *
     * @see objectdraw.WindowController
     * @see KeyListener
     */
    private World w;

    /**
     * Method which is initially called when the controller is started,
     * initializes world
     *
     * @see objectdraw.WindowController#begin()
     */
    public void begin() {
        this.w = new World(this.canvas);
        Frame window = (Frame) javax.swing.SwingUtilities.getWindowAncestor((Component) this.canvas);
        window.setTitle("supersnek");
    }

    // WindowController overrides
    /**
     * Overrides onMouseClick for game start
     *
     * @see World#onMouseClick(objectdraw.Location)
     * @see objectdraw.WindowController#onMouseClick(objectdraw.Location)
     */
    public void onMouseClick(Location l) {
        this.w.onMouseClick(l);
    }

    /**
     * Standard main method Determines window size based on parameters
     *
     * @param args
     *            optional screen size parameters
     */
    public static void main(String[] args) {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        // set window size based on args
        if (args.length == 0) {
            new Driver().startController(1920, 1024 + 22);
        } else if (args.length == 1) {
            new Driver().startController(Integer.parseInt(args[0]) / World.GRID_SIZE * World.GRID_SIZE,
                    Integer.parseInt(args[0]) / World.GRID_SIZE * World.GRID_SIZE + 22);
        } else {
            new Driver().startController(Integer.parseInt(args[0]) / World.GRID_SIZE * World.GRID_SIZE,
                    Integer.parseInt(args[1]) / World.GRID_SIZE * World.GRID_SIZE + 22);
        }
    }
}
