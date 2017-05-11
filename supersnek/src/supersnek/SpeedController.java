package supersnek;

import objectdraw.DrawingCanvas;

/**
 * Controls all speed powerups
 *
 * @author Noah Overcash (smileytechguy@smileytechguy.com)
 */
public class SpeedController extends ObjectController<Speed> {
    /**
     * Probability that, on a given tick, a new object will be added
     */
    public static double probabilityToAddOnTick = 0.00;

    /**
     * Constructor
     *
     * @param canvas
     *            canvas these objects are on
     * @param world
     *            world which these objects reside
     */
    public SpeedController(DrawingCanvas canvas, World world) {
        super(canvas, world);
    }

    /**
     * Generates and adds a Speed instance to the object list
     *
     * @see supersnek.ObjectController#addItem()
     */
    public void addItem() {
        int randX = (int) (Math.random() * (this.canvas.getWidth() / World.GRID_SIZE)) * World.GRID_SIZE;
        int randY = (int) (Math.random() * (this.canvas.getHeight() / World.GRID_SIZE)) * World.GRID_SIZE;
        this.addItem(new Speed(randX, randY, World.GRID_SIZE, World.GRID_SIZE, Speed.SPEED_COLOR,
                this.canvas, this.world));
    }
}
