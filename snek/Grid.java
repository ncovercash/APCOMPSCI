import objectdraw.Line;
import objectdraw.DrawingCanvas;
import java.awt.Color;

public class Grid {
	public static final Color GRID_COLOR = new Color(0xcccccc);
	DrawingCanvas canvas;

	public Grid(DrawingCanvas canvas) {
		this.canvas = canvas;
		drawGrid(World.GRID_SIZE);
	}

	public void drawGrid(int size) {
		for (int x=0; x<canvas.getWidth(); x+=size) {
			Line tmpLine = new Line(x, 0, x, canvas.getHeight(), canvas);
			tmpLine.setColor(GRID_COLOR);
			tmpLine.sendToBack();
		}
		for (int y=0; y<canvas.getHeight(); y+=size) {
			Line tmpLine = new Line(0, y, canvas.getWidth(), y, canvas);
			tmpLine.setColor(GRID_COLOR);
			tmpLine.sendToBack();
		}
	}
}