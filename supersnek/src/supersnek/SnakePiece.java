package supersnek;

import java.awt.Color;

import objectdraw.DrawingCanvas;
import objectdraw.FilledRoundedRect;
import objectdraw.Location;

/**
 * SnakePiece - part of the snake
 *
 * @author Noah Overcash (smileytechguy@smileytechguy.com)
 */
public class SnakePiece extends Moveable {
    /**
     * Constructor
     *
     * @param x
     *            x of the snakepiece
     * @param y
     *            y of the snakepiece
     * @param w
     *            width of the snakepiece
     * @param h
     *            height of the snakepiece
     * @param color
     *            color of the snakepiece
     * @param canvas
     *            canvas to put the snakepiece on
     * @param world
     *            world which the snakepiece is part of
     */
    public SnakePiece(int x, int y, int w, int h, Color color, DrawingCanvas canvas, World world) {
        this.canvas = canvas;
        this.world = world;

        this.object = new FilledRoundedRect(x, y, w, h, w * 2 / 3, h * 2 / 3, canvas);
        this.object.setColor(new Color(0x000000));
        this.innerObject = new FilledRoundedRect(x + 1, y + 1, w - 2, h - 2, w * 2 / 3 - 1, h * 2 / 3 - 1, canvas);
        this.innerObject.setColor(color);
    }

    /**
     * Constructor
     *
     * @param x
     *            x of the snakepiece
     * @param y
     *            y of the snakepiece
     * @param color
     *            color of the snakepiece
     * @param canvas
     *            canvas to put the snakepiece on
     * @param world
     *            world which the snakepiece is part of
     */
    public SnakePiece(int x, int y, Color color, DrawingCanvas canvas, World world) {
        this(x, y, World.GRID_SIZE, World.GRID_SIZE, color, canvas, world);
    }

    /**
     * Move the piece
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
     * Move the snake piece to a location
     *
     * @param l
     *            location
     */
    public void moveTo(Location l) {
        this.object.moveTo(l);
        Location newL = new Location(l);
        newL.translate(1, 1);
        this.innerObject.moveTo(newL);
    }

    /**
     * Move the snake piece to a coordinate
     *
     * @param x
     *            x coordinate
     * @param y
     *            y coordinate
     */
    public void moveTo(int x, int y) {
        this.object.moveTo(x, y);
        this.innerObject.moveTo(x + 1, y + 1);
    }

    /**
     * Change piece color
     *
     * @param c
     *            Color to set the piece to
     */
    public void setColor(Color c) {
        this.innerObject.setColor(c);
    }

    /**
     * Return piece color
     *
     * @return color of piece
     */
    public Color getColor() {
        return this.innerObject.getColor();
    }
}
