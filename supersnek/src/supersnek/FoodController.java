package supersnek;

import objectdraw.DrawingCanvas;

/**
 * Controls all food
 *
 * @author Noah Overcash (smileytechguy@smileytechguy.com)
 */
public class FoodController extends ObjectController<Food> implements KeyHandler {
    /**
     * Probability that, on a given tick, a new object will be added
     */
    public static double probabilityToAddOnTick = 0.00;
    /**
     * Probability that, when an object is added, the food will have double
     * value
     */
    public static double doubleFood = 0.1;

    /**
     * Constructor
     *
     * @param canvas
     *            canvas these objects are on
     * @param world
     *            world which these objects reside
     */
    public FoodController(DrawingCanvas canvas, World world) {
        super(canvas, world);
    }

    /**
     * Generates and adds a Food instance to the object list
     *
     * @see supersnek.ObjectController#addItem()
     */
    public void addItem() {
        int randX = (int) (Math.random() * (this.canvas.getWidth() / World.GRID_SIZE)) * World.GRID_SIZE;
        int randY = (int) (Math.random() * (this.canvas.getHeight() / World.GRID_SIZE)) * World.GRID_SIZE;
        if (Math.random() <= FoodController.doubleFood) {
            this.objects.add(new Food(randX, randY, World.GRID_SIZE, World.GRID_SIZE, Food.DOUBLE_FOOD_COLOR,
                    Food.DOUBLE_FOOD_BORDER_COLOR, 2, this.canvas, this.world));
        } else {
            this.objects.add(new Food(randX, randY, World.GRID_SIZE, World.GRID_SIZE, Food.FOOD_COLOR, 1, this.canvas,
                    this.world));
        }
    }

    /**
     * Handles keys 1 and 2 to increase and decrease food rate
     *
     * @param keyCode
     *            code of key
     * @param shiftState
     *            boolean of shift key
     * @param controlState
     *            boolean of ctrl key
     * @param altState
     *            boolean of alt/option key
     * @see supersnek.KeyHandler#handleKey(int, boolean, boolean, boolean)
     */
    public void handleKey(int keyCode, boolean shiftState, boolean controlState, boolean altState) {
        switch (keyCode) {
            case 49:
                FoodController.probabilityToAddOnTick += 0.001;
                break;
            case 50:
                FoodController.probabilityToAddOnTick -= 0.001;
                break;
            default:
                break;
        }
    }
}
