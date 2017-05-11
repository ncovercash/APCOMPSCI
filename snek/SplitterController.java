import objectdraw.DrawingCanvas;
import java.awt.Color;
import java.util.ArrayList;

public class SplitterController implements KeyHandler, ObstacleController {
	World world;
	DrawingCanvas canvas;

	public static final Color[] SPLITTER_COLORS = {new Color(0xe51c23), new Color(0xF44336), new Color(0xe91e63), new Color(0x9c27b0), new Color(0x673ab7), new Color(0x3f51b5), new Color(0x2196F3), new Color(0x03a9f4), new Color(0x00bcd4), new Color(0x009688), new Color(0x4CAF50), new Color(0x8bc34a), new Color(0xcddc39), new Color(0xffeb3b), new Color(0xffc107), new Color(0xff9800), new Color(0xff5722), new Color(0x795548), new Color(0x607d8b), new Color(0x9e9e9e)};

	ArrayList<Splitter> splitters = new ArrayList<Splitter>();

	public SplitterController(DrawingCanvas canvas, World world) {
		this.canvas = canvas;
		this.world = world;
	}

	public void addSplitter() {
		int randX1 = ((int)(Math.random()*(canvas.getWidth()/world.GRID_SIZE)))*world.GRID_SIZE;
		int randY1 = ((int)(Math.random()*(canvas.getHeight()/world.GRID_SIZE)))*world.GRID_SIZE;
		Color randC = SPLITTER_COLORS[((int)(Math.random()*SPLITTER_COLORS.length))];
		splitters.add(new Splitter(randX1, randY1, world.GRID_SIZE, world.GRID_SIZE, Splitter.INNER_COLOR, randC, canvas, world));
	}

	public ArrayList<Splitter> getSplitters() {
		ArrayList<Splitter> returnArr = new ArrayList<Splitter>(splitters.size());
		for (Splitter obj : splitters) {
			returnArr.add(obj);
		}
		return returnArr;
	}

	public ArrayList<Obstacle> getObstacles() {
		ArrayList<Obstacle> returnArr = new ArrayList<Obstacle>(splitters.size());
		for (Splitter obj : splitters) {
			returnArr.add(obj);
		}
		return returnArr;
	}

	public void remove(Splitter s1) {
		for (int i=0; i<splitters.size(); i++) {
			if (s1 == splitters.get(i)) {
				s1.remove();
				splitters.remove(i);
				break;
			}
		}
	}

	public void removeAll() {
		for (Splitter obj : splitters) {
			obj.remove();
		}
		splitters = new ArrayList<Splitter>();
	}

	public void handleKey(int keyCode, boolean shiftState, boolean controlState, boolean altState) {
		switch (keyCode) {
			case 55:
				addSplitter();
				break;
		}
	}
}
