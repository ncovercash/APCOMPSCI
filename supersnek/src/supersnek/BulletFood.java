package supersnek;

import java.awt.Color;

import objectdraw.DrawingCanvas;
import objectdraw.FilledOval;

/**
 * Ammo for snake
 *
 * @author Noah Overcash (smileytechguy@smileytechguy.com)
 * @see supersnek.BulletFoodController
 */
public class BulletFood extends ContainsObject implements Removable {
    /**
     * Recommended color of bullet food (materialize yellow)
     */
    public static final Color BULLET_FOOD_COLOR = new Color(0xffea00);
    
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
     *            color (suggested BulletFood.BULLET_FOOD_COLOR)
     * @param canvas
     *            objectdraw.DrawingCanvas on which the bullet should be on
     * @param world
     *            World instance which parents the object (unused)
     */
    public BulletFood(int x, int y, int w, int h, Color c, DrawingCanvas canvas, World world) {
        this.canvas = canvas;
        this.world = world;
        this.object = new FilledOval(x, y, w, h, canvas);
        this.object.setColor(new Color(0x000000));
        this.innerObject = new FilledOval(x + 1, y + 1, w - 2, h - 2, canvas);
        this.innerObject.setColor(c);
    }
}
