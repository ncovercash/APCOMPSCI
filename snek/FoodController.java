import objectdraw.DrawingCanvas;
import java.awt.Color;
import java.util.ArrayList;

public class FoodController implements Tickable, KeyHandler, ObstacleController {
	World world;
	DrawingCanvas canvas;

	public static double foodPerTick = 0.00;
	public static double doubleFood = 0.1;
	ArrayList<Food> food = new ArrayList<Food>();

	public FoodController(DrawingCanvas canvas, World world) {
		this.canvas = canvas;
		this.world = world;
	}

	public void addFood() {
		int randX = ((int)(Math.random()*(canvas.getWidth()/world.GRID_SIZE)))*world.GRID_SIZE;
		int randY = ((int)(Math.random()*(canvas.getHeight()/world.GRID_SIZE)))*world.GRID_SIZE;
		if (Math.random() <= doubleFood) {
			food.add(new Food(randX, randY, world.GRID_SIZE, world.GRID_SIZE, Food.DOUBLE_FOOD_COLOR, Food.DOUBLE_FOOD_BORDER_COLOR, 2, canvas, world));
		} else {
			food.add(new Food(randX, randY, world.GRID_SIZE, world.GRID_SIZE, Food.FOOD_COLOR, 1, canvas, world));
		}
	}

	public void tick() {
		if (Math.random() <= foodPerTick) {
			addFood();
		}
	}

	public ArrayList<Food> getFood() {
		return food;
	}

	public ArrayList<Obstacle> getObstacles() {
		ArrayList<Obstacle> returnArr = new ArrayList<Obstacle>(food.size());
		for (Food obj : food) {
			returnArr.add(obj);
		}
		return returnArr;
	}

	public void remove(Food f) {
		for (int i=0; i<food.size(); i++) {
			if (f == food.get(i)) {
				f.remove();
				food.remove(i);
				return;
			}
		}
	}

	public void removeAll() {
		for (Food obj : food) {
			obj.remove();
		}
		food = new ArrayList<Food>();
	}

	public void handleKey(int keyCode, boolean shiftState, boolean controlState, boolean altState) {
		switch (keyCode) {
			case 49:
				foodPerTick += 0.001;
				break;
			case 50:
				foodPerTick -= 0.001;
				break;
		}
	}
}
