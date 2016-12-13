import objectdraw.*;
import java.awt.*;

public class StandardOrnament extends Ornament {
	public StandardOrnament(int x, int y, int w, int h, Color c1, Color c2, Color c3, DrawingCanvas c) {
		super(x, y, w, h, c1, c2, c3, c);
	}

	public StandardOrnament(Ornament o, DrawingCanvas c) {
		super(o, c);
	}

	public void run() {}
}