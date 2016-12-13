import objectdraw.*;
import java.awt.*;

public class BlinkingOrnament extends Ornament {
	public BlinkingOrnament(int x, int y, int w, int h, Color c1, Color c2, Color c3, DrawingCanvas c) {
		super(x, y, w, h, c1, c2, c3, c);
	}

	public BlinkingOrnament(Ornament o, DrawingCanvas c) {
		super(o, c);
	}

	public void run() {
		while (active) {
			Color originalColor = getColour1();
			setColour1(getColour2());
			pause(pauseTime);
			setColour2(getColour3());
			pause(pauseTime);
			setColour3(originalColor);
			pause(pauseTime);
		}
	}
}