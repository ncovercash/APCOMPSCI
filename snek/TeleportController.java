import objectdraw.DrawingCanvas;
import java.awt.Color;
import java.util.ArrayList;

public class TeleportController implements KeyHandler, ObstacleController {
	World world;
	DrawingCanvas canvas;

	public static final Color[] TELEPORT_COLORS = {new Color(0xe51c23), new Color(0xF44336), new Color(0xe91e63), new Color(0x9c27b0), new Color(0x673ab7), new Color(0x3f51b5), new Color(0x2196F3), new Color(0x03a9f4), new Color(0x00bcd4), new Color(0x009688), new Color(0x4CAF50), new Color(0x8bc34a), new Color(0xcddc39), new Color(0xffeb3b), new Color(0xffc107), new Color(0xff9800), new Color(0xff5722), new Color(0x795548), new Color(0x607d8b), new Color(0x9e9e9e)};

	ArrayList<Teleport> teleports1 = new ArrayList<Teleport>();
	ArrayList<Teleport> teleports2 = new ArrayList<Teleport>();

	public TeleportController(DrawingCanvas canvas, World world) {
		this.canvas = canvas;
		this.world = world;
	}

	public void addTeleporter() {
		int randX1 = ((int)(Math.random()*(canvas.getWidth()/world.GRID_SIZE)))*world.GRID_SIZE;
		int randY1 = ((int)(Math.random()*(canvas.getHeight()/world.GRID_SIZE)))*world.GRID_SIZE;
		int randX2 = ((int)(Math.random()*(canvas.getWidth()/world.GRID_SIZE)))*world.GRID_SIZE;
		int randY2 = ((int)(Math.random()*(canvas.getHeight()/world.GRID_SIZE)))*world.GRID_SIZE;
		Color randC = TELEPORT_COLORS[((int)(Math.random()*TELEPORT_COLORS.length))];
		teleports1.add(new Teleport(randX1, randY1, world.GRID_SIZE, world.GRID_SIZE, Teleport.INNER_COLOR, randC, canvas, world));
		teleports2.add(new Teleport(randX2, randY2, world.GRID_SIZE, world.GRID_SIZE, Teleport.INNER_COLOR, randC, canvas, world));
	}

	public ArrayList<Teleport> getTeleporters() {
		ArrayList<Teleport> returnArr = new ArrayList<Teleport>(teleports1.size()+teleports2.size());
		for (Teleport obj : teleports1) {
			returnArr.add(obj);
		}
		for (Teleport obj : teleports2) {
			returnArr.add(obj);
		}
		return returnArr;
	}

	public ArrayList<Obstacle> getObstacles() {
		ArrayList<Obstacle> returnArr = new ArrayList<Obstacle>(teleports1.size()+teleports2.size());
		for (Teleport obj : teleports1) {
			returnArr.add(obj);
		}
		for (Teleport obj : teleports2) {
			returnArr.add(obj);
		}
		return returnArr;
	}

	public Teleport getOtherTeleport(Teleport t) {
		for (int i=0; i<teleports1.size(); i++) {
			if (teleports1.get(i) == t) {
				return teleports2.get(i);
			}
			if (teleports2.get(i) == t) {
				return teleports1.get(i);
			}
		}
		return null;
	}

	public void removeAll() {
		for (Teleport obj : teleports1) {
			obj.remove();
		}
		for (Teleport obj : teleports2) {
			obj.remove();
		}
		teleports1 = new ArrayList<Teleport>();
		teleports2 = new ArrayList<Teleport>();
	}

	public void handleKey(int keyCode, boolean shiftState, boolean controlState, boolean altState) {
		switch (keyCode) {
			case 54:
				addTeleporter();
				break;
		}
	}
}
