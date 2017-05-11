import objectdraw.FilledOval;
import objectdraw.DrawingCanvas;
import java.awt.Color;

public class Splitter extends Teleport implements Obstacle, Removable {
	public static final Color INNER_COLOR = new Color(0xcccccc);

	public Splitter(int x, int y, int w, int h, Color ic, Color fc, DrawingCanvas canvas, World world) {
		super(x,y,w,h,ic,fc,canvas,world);
	}
}