package supersnek;

import java.awt.Color;

import objectdraw.DrawingCanvas;
import objectdraw.FilledOval;

/**
 * Food for snake
 *
 * @author Noah Overcash (smileytechguy@smileytechguy.com)
 * @see supersnek.FoodController
 */
public class Food extends ContainsObject implements Removable {
    /**
     * Recommended color for food
     */
    public static final Color FOOD_COLOR = new Color(0x66bb6a);
    /**
     * Recommended color for food of double value
     */
    public static final Color DOUBLE_FOOD_COLOR = new Color(0x66bb6a);
    /**
     * Recommended color for food border of double value
     */
    public static final Color DOUBLE_FOOD_BORDER_COLOR = new Color(0x000000);

    /**
     * value of object
     */
    protected int val;

    /**
     * Constructor
     *
     * @param x
     *            x coordinate
     * @param y
     *            y coordinate
     * @param w
     *            width (suggested World.GRID_SIZE)
     * @param h
     *            height (suggested World.GRID_SIZE)
     * @param c
     *            color (suggested Food.FOOD_COLOR)
     * @param val
     *            value (worth) of object
     * @param canvas
     *            objectdraw.DrawingCanvas on which the object should be on
     * @param world
     *            World instance which parents the object (unused)
     */
    public Food(int x, int y, int w, int h, Color c, int val, DrawingCanvas canvas, World world) {
        this.canvas = canvas;
        this.world = world;
        this.object = new FilledOval(x, y, w, h, canvas);
        this.object.setColor(new Color(0x000000));
        this.val = val;
        this.innerObject = new FilledOval(x + 1, y + 1, w - 2, h - 2, canvas);
        this.innerObject.setColor(c);
    }

    /**
     * Constructor
     *
     * @param x
     *            x coordinate
     * @param y
     *            y coordinate
     * @param w
     *            width (suggested World.GRID_SIZE)
     * @param h
     *            height (suggested World.GRID_SIZE)
     * @param c1
     *            foreground color (suggested Food.DOUBLE_FOOD_COLOR)
     * @param c2
     *            background color (suggested Food.DOUBLE_FOOD_BORDER_COLOR)
     * @param val
     *            value (worth) of object
     * @param canvas
     *            objectdraw.DrawingCanvas on which the object should be on
     * @param world
     *            World instance which parents the object (unused)
     */
    public Food(int x, int y, int w, int h, Color c1, Color c2, int val, DrawingCanvas canvas, World world) {
        this.canvas = canvas;
        this.world = world;
        this.object = new FilledOval(x, y, w, h, canvas);
        this.object.setColor(c2);
        this.val = val;
        this.innerObject = new FilledOval(x + w / 8, y + h / 8, w / 4 * 3, h / 4 * 3, canvas);
        this.innerObject.setColor(c1);
    }

    /**
     * @return value of object
     */
    public int getValue() {
        return this.val;
    }
}
