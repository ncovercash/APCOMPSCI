package supersnek;

import java.awt.Color;

import objectdraw.DrawingCanvas;
import objectdraw.FilledOval;

/**
 * Teleporter
 *
 * @author Noah Overcash (smileytechguy@smileytechguy.com)
 */
public class Teleport extends ContainsObject {
    /**
     * Inner color of teleporter
     */
    public static final Color TELEPORTER_INNER_COLOR = new Color(0x000000);

    /**
     * Constructor
     *
     * @param x
     *            x coordinate
     * @param y
     *            y coordinate
     * @param w
     *            width
     * @param h
     *            height
     * @param ic
     *            inner color
     * @param fc
     *            outer color
     * @param canvas
     *            canvas to place the teleport on
     * @param world
     *            world the teleporter is in
     */
    public Teleport(int x, int y, int w, int h, Color ic, Color fc, DrawingCanvas canvas, World world) {
        this.canvas = canvas;
        this.world = world;
        this.object = new FilledOval(x, y, w, h, canvas);
        this.object.setColor(fc);
        this.innerObject = new FilledOval(x + w / 8, y + h / 8, w / 4 * 3, h / 4 * 3, canvas);
        this.innerObject.setColor(ic);
    }
}
