package supersnek;

import java.awt.Color;

import objectdraw.DrawingCanvas;
import objectdraw.FilledOval;
import objectdraw.FilledRect;

/**
 * Bullet which is fired by snake
 *
 * @author Noah Overcash (smileytechguy@smileytechguy.com)
 * @see supersnek.Snake#fireBullet()
 */
public class Bullet extends Moveable implements Tickable {
    /**
     * Recommended color of bullet (materialize yellow)
     */
    public static final Color BULLET_COLOR = new Color(0xffeb3b);

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
     *            color (suggested Bullet.BULLET_COLOR)
     * @param direction
     *            direction for bullet to move
     * @param canvas
     *            objectdraw.DrawingCanvas on which the bullet should be on
     * @param world
     *            World instance which parents the object (unused)
     */
    public Bullet(int x, int y, int w, int h, Color c, Direction direction, DrawingCanvas canvas, World world) {
        this.canvas = canvas;
        this.world = world;
        this.direction = direction;
        this.object = new FilledRect(x, y, w, h, canvas);
        this.object.setColor(new Color(0x00ffffff, true));
        if (direction == Direction.LEFT || direction == Direction.RIGHT) {
            this.innerObject = new FilledOval(x, y + h / 3, w, h / 3, canvas);
        } else {
            this.innerObject = new FilledOval(x + w / 3, y, w / 3, h, canvas);
        }
        this.innerObject.setColor(c);
    }

    /**
     * Moveable implementation Moves based on internal direction
     *
     * @see supersnek.Moveable#move()
     */
    public void move() {
        switch (this.direction) {
            case UP:
                this.object.move(0, -World.GRID_SIZE);
                this.innerObject.move(0, -World.GRID_SIZE);
                break;
            case DOWN:
                this.object.move(0, World.GRID_SIZE);
                this.innerObject.move(0, World.GRID_SIZE);
                break;
            case RIGHT:
                this.object.move(World.GRID_SIZE, 0);
                this.innerObject.move(World.GRID_SIZE, 0);
                break;
            case LEFT:
                this.object.move(-World.GRID_SIZE, 0);
                this.innerObject.move(-World.GRID_SIZE, 0);
                break;
            default:
                break;
        }
    }

    /**
     * Implements Tickable
     *
     * @see supersnek.Tickable#tick()
     */
    public void tick() {
        this.move();
    }
}
