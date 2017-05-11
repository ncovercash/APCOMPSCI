package supersnek;

import java.awt.Color;

import objectdraw.DrawingCanvas;

/**
 * Splitter
 *
 * @author Noah Overcash (smileytechguy@smileytechguy.com)
 */
public class Splitter extends Teleport {
    /**
     * Inner color of splitter
     */
    public static final Color SPLITTER_INNER_COLOR = new Color(0xcccccc);

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
    public Splitter(int x, int y, int w, int h, Color ic, Color fc, DrawingCanvas canvas, World world) {
        super(x, y, w, h, ic, fc, canvas, world);
    }
}
