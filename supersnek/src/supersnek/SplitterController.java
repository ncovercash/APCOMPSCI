package supersnek;

import java.awt.Color;

import objectdraw.DrawingCanvas;

/**
 * Controls all splitters
 *
 * @author Noah Overcash (smileytechguy@smileytechguy.com)
 */
public class SplitterController extends ObjectController<Splitter> implements KeyHandler {
    /**
     * colors for splitter rims
     */
    public static final Color[] SPLITTER_COLORS = { new Color(0xe51c23), new Color(0xF44336), new Color(0xe91e63),
            new Color(0x9c27b0), new Color(0x673ab7), new Color(0x3f51b5), new Color(0x2196F3), new Color(0x03a9f4),
            new Color(0x00bcd4), new Color(0x009688), new Color(0x4CAF50), new Color(0x8bc34a), new Color(0xcddc39),
            new Color(0xffeb3b), new Color(0xffc107), new Color(0xff9800), new Color(0xff5722), new Color(0x795548),
            new Color(0x607d8b), new Color(0x9e9e9e) };
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
    public SplitterController(DrawingCanvas canvas, World world) {
        super(canvas, world);
    }

    /**
     * Generates and adds a Splitter instance to the object list
     *
     * @see supersnek.ObjectController#addItem()
     */
    public void addItem() {
        int randX1 = (int) (Math.random() * (this.canvas.getWidth() / World.GRID_SIZE)) * World.GRID_SIZE;
        int randY1 = (int) (Math.random() * (this.canvas.getHeight() / World.GRID_SIZE)) * World.GRID_SIZE;
        Color randC = SplitterController.SPLITTER_COLORS[(int) (Math.random()
                * SplitterController.SPLITTER_COLORS.length)];
        this.objects.add(new Splitter(randX1, randY1, World.GRID_SIZE, World.GRID_SIZE, Splitter.SPLITTER_INNER_COLOR,
                randC, this.canvas, this.world));
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
            case 55:
                this.addItem();
                break;
            default:
                break;
        }
    }
}
