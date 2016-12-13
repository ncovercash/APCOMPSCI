import objectdraw.*;
import java.awt.*;

public class Driver extends WindowController {
	public static void main(String args[]){
		new Driver().startController(2000,1000);
	}

	public void begin() {
		// int j=100;
		// for (int i=0; i<canvas.getWidth(); i+=40) {
		// 	j+=5;
		// 	new BlinkingOrnament(i, j, 100, 100, Color.RED, Color.BLUE, Color.GREEN, canvas);
		// }
		new MovingOrnament(200, 200, 100, 100, Color.RED, Color.BLUE, Color.GREEN, canvas);
	}
}