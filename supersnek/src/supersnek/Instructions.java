package supersnek;

import java.awt.Color;
import java.util.ArrayList;

import objectdraw.DrawableInterface;
import objectdraw.DrawingCanvas;
import objectdraw.Text;

/**
 * Class which displays instructions for playing the game
 *
 * @author Noah Overcash (smileytechguy@smileytechguy.com)
 */
public class Instructions {
    /**
     * Number of rows in instruction set
     */
    public static final int NUM_ROWS = 7;
    /**
     * Number of columns in instruction set
     */
    public static final int NUM_COLS = 3;

    /**
     * Basic text instructions to list (e.g. keypress commands)
     */
    public static final String[][] TEXT_INSTRUCTIONS = { { "Esc", "Quit Game" }, { "+", "Increase speed" },
            { "-", "Decrease speed" }, { "1", "Increase food creation rate" }, { "2", "Decrease food creation rate" },
            { "3", "Grow all snakes" }, { "4", "Kill all snakes" }, { "5", "Split all snakes" },
            { "6", "Add teleporters" }, { "7", "Add splitter" }, { "8", "Speed all snakes" } };
    /**
     * Text to display under instructions
     */
    public static final String BOTTOM_TEXTS[] = { "Arrow keys or WASD/WARS to move, Space or Tab/Backspace/Q to shoot",
            "Automatic win when length of 75 is acheived" };

    /**
     * World which this object is contained in
     */
    protected World world;
    /**
     * Canvas these objects are in
     */
    protected DrawingCanvas canvas;
    /**
     * Y coordinate to start drawing instructions at
     */
    protected double startY;

    /**
     * ArrayList of objects used
     */
    protected ArrayList<Object> objects = new ArrayList<Object>();

    /**
     * Constructor
     *
     * @param startYCoordinate
     *            starting y coordinate
     * @param canvas
     *            canvas to draw instructions on
     * @param world
     *            world the instructions are part of
     */
    public Instructions(double startYCoordinate, DrawingCanvas canvas, World world) {
        int startY = (int) startYCoordinate / World.GRID_SIZE * World.GRID_SIZE;
        this.startY = startY;
        this.canvas = canvas;
        this.world = world;
        
        int[] startXs = new int[Instructions.NUM_COLS];

        int currentItem = 0;

        for (int i = 0; i < Instructions.NUM_COLS; i++) {
            startXs[i] = (i * 2 + 1) * (canvas.getWidth() / (Instructions.NUM_COLS * 2 + 1));
            startXs[i] /= World.GRID_SIZE;
            startXs[i] *= World.GRID_SIZE;
        }

        Text tmpText;
        Object tmpObject;
        int tmpInt;

        // text instructions
        for (String[] pair : Instructions.TEXT_INSTRUCTIONS) {
            tmpText = new Text(pair[0], startXs[currentItem / Instructions.NUM_ROWS],
                    startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS), canvas);
            tmpInt = 1;
            tmpText.setFontSize(1);
            while (tmpText.getWidth() < World.GRID_SIZE && tmpText.getHeight() < World.GRID_SIZE) {
                tmpText.setFontSize(++tmpInt);
            }
            tmpText.setFontSize(--tmpInt);
            tmpText.moveTo(startXs[currentItem / Instructions.NUM_ROWS] + World.GRID_SIZE / 2 - tmpText.getWidth() / 2,
                    startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS) + World.GRID_SIZE / 2
                            - tmpText.getHeight() / 2);
            this.objects.add(tmpText);

            tmpText = new Text(pair[1], startXs[currentItem / Instructions.NUM_ROWS] + 2 * World.GRID_SIZE,
                    startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS), canvas);
            tmpInt = 1;
            tmpText.setFontSize(1);
            while (tmpText.getHeight() < World.GRID_SIZE) {
                tmpText.setFontSize(++tmpInt);
            }
            tmpText.setFontSize(--tmpInt);
            tmpText.moveTo(startXs[currentItem / Instructions.NUM_ROWS] + 2 * World.GRID_SIZE,
                    startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS) + World.GRID_SIZE / 2
                            - tmpText.getHeight() / 2);
            this.objects.add(tmpText);
            
            currentItem++;
        }

        // Snake Head
        tmpObject = new SnakePiece(startXs[currentItem / Instructions.NUM_ROWS],
                (startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS)) / World.GRID_SIZE * World.GRID_SIZE,
                new Color(0x000000), canvas, world);
        this.objects.add(tmpObject);

        tmpText = new Text("Snake head", startXs[currentItem / Instructions.NUM_ROWS] + 2 * World.GRID_SIZE,
                (startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS)) / World.GRID_SIZE * World.GRID_SIZE,
                canvas);
        tmpInt = 1;
        tmpText.setFontSize(1);
        while (tmpText.getHeight() < World.GRID_SIZE) {
            tmpText.setFontSize(++tmpInt);
        }
        tmpText.setFontSize(--tmpInt);
        tmpText.moveTo(startXs[currentItem / Instructions.NUM_ROWS] + 2 * World.GRID_SIZE,
                (startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS)) / World.GRID_SIZE * World.GRID_SIZE
                        + World.GRID_SIZE / 2 - tmpText.getHeight() / 2);
        this.objects.add(tmpText);
        
        currentItem++;

        // Snake Body
        tmpObject = new SnakePiece(startXs[currentItem / Instructions.NUM_ROWS],
                (startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS)) / World.GRID_SIZE * World.GRID_SIZE,
                Helpers.getRandomColorScheme()[0], canvas, world);
        this.objects.add(tmpObject);

        tmpText = new Text("Snake body", startXs[currentItem / Instructions.NUM_ROWS] + 2 * World.GRID_SIZE,
                (startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS)) / World.GRID_SIZE * World.GRID_SIZE,
                canvas);
        tmpInt = 1;
        tmpText.setFontSize(1);
        while (tmpText.getHeight() < World.GRID_SIZE) {
            tmpText.setFontSize(++tmpInt);
        }
        tmpText.setFontSize(--tmpInt);
        tmpText.moveTo(startXs[currentItem / Instructions.NUM_ROWS] + 2 * World.GRID_SIZE,
                (startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS)) / World.GRID_SIZE * World.GRID_SIZE
                        + World.GRID_SIZE / 2 - tmpText.getHeight() / 2);
        this.objects.add(tmpText);
        
        currentItem++;

        // Bullet
        tmpObject = new Bullet(startXs[currentItem / Instructions.NUM_ROWS],
                (startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS)) / World.GRID_SIZE * World.GRID_SIZE,
                World.GRID_SIZE, World.GRID_SIZE, Bullet.BULLET_COLOR, Direction.RIGHT, canvas, world);
        this.objects.add(tmpObject);

        tmpText = new Text("Bullet", startXs[currentItem / Instructions.NUM_ROWS] + 2 * World.GRID_SIZE,
                (startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS)) / World.GRID_SIZE * World.GRID_SIZE,
                canvas);
        tmpInt = 1;
        tmpText.setFontSize(1);
        while (tmpText.getHeight() < World.GRID_SIZE) {
            tmpText.setFontSize(++tmpInt);
        }
        tmpText.setFontSize(--tmpInt);
        tmpText.moveTo(startXs[currentItem / Instructions.NUM_ROWS] + 2 * World.GRID_SIZE,
                (startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS)) / World.GRID_SIZE * World.GRID_SIZE
                        + World.GRID_SIZE / 2 - tmpText.getHeight() / 2);
        this.objects.add(tmpText);
        
        currentItem++;

        // Ammunition
        tmpObject = new BulletFood(startXs[currentItem / Instructions.NUM_ROWS],
                (startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS)) / World.GRID_SIZE * World.GRID_SIZE,
                World.GRID_SIZE, World.GRID_SIZE, Bullet.BULLET_COLOR, canvas, world);
        this.objects.add(tmpObject);

        tmpText = new Text("Ammunition", startXs[currentItem / Instructions.NUM_ROWS] + 2 * World.GRID_SIZE,
                (startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS)) / World.GRID_SIZE * World.GRID_SIZE,
                canvas);
        tmpInt = 1;
        tmpText.setFontSize(1);
        while (tmpText.getHeight() < World.GRID_SIZE) {
            tmpText.setFontSize(++tmpInt);
        }
        tmpText.setFontSize(--tmpInt);
        tmpText.moveTo(startXs[currentItem / Instructions.NUM_ROWS] + 2 * World.GRID_SIZE,
                (startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS)) / World.GRID_SIZE * World.GRID_SIZE
                        + World.GRID_SIZE / 2 - tmpText.getHeight() / 2);
        this.objects.add(tmpText);
        
        currentItem++;

        // Food
        tmpObject = new Food(startXs[currentItem / Instructions.NUM_ROWS],
                (startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS)) / World.GRID_SIZE * World.GRID_SIZE,
                World.GRID_SIZE, World.GRID_SIZE, Food.FOOD_COLOR, 1, canvas, world);
        this.objects.add(tmpObject);

        tmpText = new Text("Food", startXs[currentItem / Instructions.NUM_ROWS] + 2 * World.GRID_SIZE,
                (startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS)) / World.GRID_SIZE * World.GRID_SIZE,
                canvas);
        tmpInt = 1;
        tmpText.setFontSize(1);
        while (tmpText.getHeight() < World.GRID_SIZE) {
            tmpText.setFontSize(++tmpInt);
        }
        tmpText.setFontSize(--tmpInt);
        tmpText.moveTo(startXs[currentItem / Instructions.NUM_ROWS] + 2 * World.GRID_SIZE,
                (startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS)) / World.GRID_SIZE * World.GRID_SIZE
                        + World.GRID_SIZE / 2 - tmpText.getHeight() / 2);
        this.objects.add(tmpText);
        
        currentItem++;

        // Double Value Food
        tmpObject = new Food(startXs[currentItem / Instructions.NUM_ROWS],
                (startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS)) / World.GRID_SIZE * World.GRID_SIZE,
                World.GRID_SIZE, World.GRID_SIZE, Food.DOUBLE_FOOD_COLOR, Food.DOUBLE_FOOD_BORDER_COLOR, 2, canvas,
                world);
        this.objects.add(tmpObject);

        tmpText = new Text("Double Value Food", startXs[currentItem / Instructions.NUM_ROWS] + 2 * World.GRID_SIZE,
                (startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS)) / World.GRID_SIZE * World.GRID_SIZE,
                canvas);
        tmpInt = 1;
        tmpText.setFontSize(1);
        while (tmpText.getHeight() < World.GRID_SIZE) {
            tmpText.setFontSize(++tmpInt);
        }
        tmpText.setFontSize(--tmpInt);
        tmpText.moveTo(startXs[currentItem / Instructions.NUM_ROWS] + 2 * World.GRID_SIZE,
                (startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS)) / World.GRID_SIZE * World.GRID_SIZE
                        + World.GRID_SIZE / 2 - tmpText.getHeight() / 2);
        this.objects.add(tmpText);
        
        currentItem++;

        // Teleport
        tmpObject = new Teleport(startXs[currentItem / Instructions.NUM_ROWS],
                (startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS)) / World.GRID_SIZE * World.GRID_SIZE,
                World.GRID_SIZE, World.GRID_SIZE, Teleport.TELEPORTER_INNER_COLOR, Helpers.getRandomColorScheme()[0],
                canvas, world);
        this.objects.add(tmpObject);

        tmpText = new Text("Teleporter", startXs[currentItem / Instructions.NUM_ROWS] + 2 * World.GRID_SIZE,
                (startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS)) / World.GRID_SIZE * World.GRID_SIZE,
                canvas);
        tmpInt = 1;
        tmpText.setFontSize(1);
        while (tmpText.getHeight() < World.GRID_SIZE) {
            tmpText.setFontSize(++tmpInt);
        }
        tmpText.setFontSize(--tmpInt);
        tmpText.moveTo(startXs[currentItem / Instructions.NUM_ROWS] + 2 * World.GRID_SIZE,
                (startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS)) / World.GRID_SIZE * World.GRID_SIZE
                        + World.GRID_SIZE / 2 - tmpText.getHeight() / 2);
        this.objects.add(tmpText);
        
        currentItem++;

        // Splitter
        tmpObject = new Splitter(startXs[currentItem / Instructions.NUM_ROWS],
                (startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS)) / World.GRID_SIZE * World.GRID_SIZE,
                World.GRID_SIZE, World.GRID_SIZE, Splitter.SPLITTER_INNER_COLOR, Helpers.getRandomColorScheme()[0],
                canvas, world);
        this.objects.add(tmpObject);

        tmpText = new Text("Splitter", startXs[currentItem / Instructions.NUM_ROWS] + 2 * World.GRID_SIZE,
                (startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS)) / World.GRID_SIZE * World.GRID_SIZE,
                canvas);
        tmpInt = 1;
        tmpText.setFontSize(1);
        while (tmpText.getHeight() < World.GRID_SIZE) {
            tmpText.setFontSize(++tmpInt);
        }
        tmpText.setFontSize(--tmpInt);
        tmpText.moveTo(startXs[currentItem / Instructions.NUM_ROWS] + 2 * World.GRID_SIZE,
                (startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS)) / World.GRID_SIZE * World.GRID_SIZE
                        + World.GRID_SIZE / 2 - tmpText.getHeight() / 2);
        this.objects.add(tmpText);
        
        currentItem++;

        // Speed
        tmpObject = new Speed(startXs[currentItem / Instructions.NUM_ROWS],
                (startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS)) / World.GRID_SIZE * World.GRID_SIZE,
                World.GRID_SIZE, World.GRID_SIZE, Speed.SPEED_COLOR, canvas, world);
        this.objects.add(tmpObject);

        tmpText = new Text("Speed Powerup", startXs[currentItem / Instructions.NUM_ROWS] + 2 * World.GRID_SIZE,
                (startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS)) / World.GRID_SIZE * World.GRID_SIZE,
                canvas);
        tmpInt = 1;
        tmpText.setFontSize(1);
        while (tmpText.getHeight() < World.GRID_SIZE) {
            tmpText.setFontSize(++tmpInt);
        }
        tmpText.setFontSize(--tmpInt);
        tmpText.moveTo(startXs[currentItem / Instructions.NUM_ROWS] + 2 * World.GRID_SIZE,
                (startY + World.GRID_SIZE * (currentItem % Instructions.NUM_ROWS)) / World.GRID_SIZE * World.GRID_SIZE
                        + World.GRID_SIZE / 2 - tmpText.getHeight() / 2);
        this.objects.add(tmpText);
        
        currentItem++;

        // bottom text
        for (int i = 0; i < Instructions.BOTTOM_TEXTS.length; i++) {
            tmpText = new Text(Instructions.BOTTOM_TEXTS[i], 0,
                    (startY + World.GRID_SIZE * (i + Instructions.NUM_ROWS)) / World.GRID_SIZE * World.GRID_SIZE,
                    canvas);
            tmpInt = 1;
            tmpText.setFontSize(1);
            while (tmpText.getHeight() < World.GRID_SIZE) {
                tmpText.setFontSize(++tmpInt);
            }
            tmpText.setFontSize(--tmpInt);
            tmpText.moveTo(canvas.getWidth() / 2 - tmpText.getWidth() / 2,
                    (startY + World.GRID_SIZE * (i + Instructions.NUM_ROWS)) / World.GRID_SIZE * World.GRID_SIZE);
            this.objects.add(tmpText);
        }
    }

    /**
     * Hide instruction set
     */
    public void hide() {
        for (int i = 0; i < this.objects.size(); i++) {
            if (this.objects.get(i) instanceof ContainsObject) {
                ((ContainsObject) this.objects.get(i)).hide();
            }
            if (this.objects.get(i) instanceof DrawableInterface) {
                ((DrawableInterface) this.objects.get(i)).hide();
            }
        }
    }

    /**
     * Show instruction set
     */
    public void show() {
        for (int i = 0; i < this.objects.size(); i++) {
            if (this.objects.get(i) instanceof ContainsObject) {
                ((ContainsObject) this.objects.get(i)).show();
            }
            if (this.objects.get(i) instanceof DrawableInterface) {
                ((DrawableInterface) this.objects.get(i)).show();
            }
        }
    }
}
