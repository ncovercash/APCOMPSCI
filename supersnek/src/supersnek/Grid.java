package supersnek;

import java.awt.Color;

import objectdraw.DrawingCanvas;
import objectdraw.Line;

/**
 * Draws a grid on the canvas in the background
 *
 * @author Noah Overcash (smileytechguy@smileytechguy.com)
 */
public class Grid {
    /**
     * Default color for the grid
     */
    public static final Color GRID_COLOR = new Color(0xcccccc);
    /**
     * canvas to draw grid on
     */
    protected DrawingCanvas canvas;

    /**
     * Constructor - draws grid
     *
     * @param canvas
     *            canvas to place grid on
     */
    public Grid(DrawingCanvas canvas) {
        this.canvas = canvas;
        this.drawGrid(World.GRID_SIZE);
    }

    /**
     * Draws grid
     *
     * @param spacing
     *            spacing between grid lines
     */
    public void drawGrid(int spacing) {
        for (int x = 0; x < this.canvas.getWidth(); x += spacing) {
            Line tmpLine = new Line(x, 0, x, this.canvas.getHeight(), this.canvas);
            tmpLine.setColor(Grid.GRID_COLOR);
            tmpLine.sendToBack();
        }
        for (int y = 0; y < this.canvas.getHeight(); y += spacing) {
            Line tmpLine = new Line(0, y, this.canvas.getWidth(), y, this.canvas);
            tmpLine.setColor(Grid.GRID_COLOR);
            tmpLine.sendToBack();
        }
    }
}
