package supersnek;

import java.awt.Color;
import java.util.ArrayList;

import objectdraw.DrawingCanvas;

/**
 * Controls all teleporters
 *
 * @author Noah Overcash (smileytechguy@smileytechguy.com)
 */
public class TeleportController extends ObjectController<Teleport> implements KeyHandler {
    /**
     * colors for teleporter rims
     */
    public static final Color[] TELEPORT_COLORS = { new Color(0xe51c23), new Color(0xF44336), new Color(0xe91e63),
            new Color(0x9c27b0), new Color(0x673ab7), new Color(0x3f51b5), new Color(0x2196F3), new Color(0x03a9f4),
            new Color(0x00bcd4), new Color(0x009688), new Color(0x4CAF50), new Color(0x8bc34a), new Color(0xcddc39),
            new Color(0xffeb3b), new Color(0xffc107), new Color(0xff9800), new Color(0xff5722), new Color(0x795548),
            new Color(0x607d8b), new Color(0x9e9e9e) };
    /**
     * Probability that, on a given tick, a new object will be added
     */
    public static double probabilityToAddOnTick = 0.00;

    /**
     * Holds set 1 of teleporters
     */
    protected ArrayList<Teleport> teleports1 = new ArrayList<Teleport>();
    /**
     * Holds corresponding set of teleporters
     */
    protected ArrayList<Teleport> teleports2 = new ArrayList<Teleport>();

    /**
     * Constructor
     *
     * @param canvas
     *            canvas these objects are on
     * @param world
     *            world which these objects reside
     */
    public TeleportController(DrawingCanvas canvas, World world) {
        super(canvas, world);
    }

    /**
     * Generates and adds teleporters to the lists
     *
     * @see supersnek.ObjectController#addItem()
     */
    public void addItem() {
        int randX1 = (int) (Math.random() * (this.canvas.getWidth() / World.GRID_SIZE)) * World.GRID_SIZE;
        int randY1 = (int) (Math.random() * (this.canvas.getHeight() / World.GRID_SIZE)) * World.GRID_SIZE;
        int randX2 = (int) (Math.random() * (this.canvas.getWidth() / World.GRID_SIZE)) * World.GRID_SIZE;
        int randY2 = (int) (Math.random() * (this.canvas.getHeight() / World.GRID_SIZE)) * World.GRID_SIZE;
        Color randC = TeleportController.TELEPORT_COLORS[(int) (Math.random()
                * TeleportController.TELEPORT_COLORS.length)];
        this.teleports1.add(new Teleport(randX1, randY1, World.GRID_SIZE, World.GRID_SIZE,
                Teleport.TELEPORTER_INNER_COLOR, randC, this.canvas, this.world));
        this.teleports2.add(new Teleport(randX2, randY2, World.GRID_SIZE, World.GRID_SIZE,
                Teleport.TELEPORTER_INNER_COLOR, randC, this.canvas, this.world));
    }

    /**
     * get all teleporters (override)
     *
     * @return all teleporters in an ArrayList
     */
    public ArrayList<Teleport> getObjects() {
        ArrayList<Teleport> returnArr = new ArrayList<Teleport>(this.teleports1.size() + this.teleports2.size());
        for (Teleport t : this.teleports1) {
            returnArr.add(t);
        }
        for (Teleport t : this.teleports2) {
            returnArr.add(t);
        }
        return returnArr;
    }

    /**
     * Get associated teleporter
     *
     * @param t
     *            teleporter to find pair
     * @return associated teleporter
     */
    public Teleport getOtherTeleport(Teleport t) {
        for (int i = 0; i < this.teleports1.size(); i++) {
            if (this.teleports1.get(i) == t) {
                return this.teleports2.get(i);
            }
            if (this.teleports2.get(i) == t) {
                return this.teleports1.get(i);
            }
        }
        return null;
    }

    /**
     * Remove all teleporters
     */
    public void removeAll() {
        for (Teleport obj : this.teleports1) {
            obj.remove();
        }
        for (Teleport obj : this.teleports2) {
            obj.remove();
        }
        this.teleports1 = new ArrayList<Teleport>();
        this.teleports2 = new ArrayList<Teleport>();
    }

    /**
     * Adds splitter on specified keypress
     *
     * @param keyCode
     *            code of key
     * @param shiftState
     *            boolean of shift key
     * @param controlState
     *            boolean of ctrl key
     * @param altState
     *            boolean of alt/option key
     */
    public void handleKey(int keyCode, boolean shiftState, boolean controlState, boolean altState) {
        switch (keyCode) {
            case 54:
                this.addItem();
                break;
            default:
                break;
        }
    }
}
