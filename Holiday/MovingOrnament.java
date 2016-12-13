import objectdraw.*;
import java.awt.*;

public class MovingOrnament extends Ornament {
	int spiralSize = 400, numLoops = 5;
	Location originalCenter;
	public MovingOrnament(int x, int y, int w, int h, Color c1, Color c2, Color c3, DrawingCanvas c) {
		super(x, y, w, h, c1, c2, c3, c);
		originalCenter = new Location(x+w/2, y+h/2);
	}

	public MovingOrnament(Ornament o, DrawingCanvas c) {
		super(o, c);
		originalCenter = new Location(o.getX()+(o.getWidth()/2), o.getY()+o.getHeight()/2);
	}

	public void run() {
		while (active) {
			for (int i=0; i <= 360; i += 5) {
				this.moveTo(Math.cos(Math.toRadians(i%360)), Math.sin(Math.toRadians(i%360)))
				pause(pauseTime);
			}
		}
	}
}