package supersnek;

import java.awt.Color;
import java.util.ArrayList;

import objectdraw.DrawableInterface;
import objectdraw.DrawingCanvas;
import objectdraw.Line;
import objectdraw.Text;

/**
 * scoreboard for the game, tracks snake's ammo and length
 *
 * @author Noah Overcash (smileytechguy@smileytechguy.com)
 */
public class ScoreBoard extends ObjectController<Snake> {
    /**
     * Number of columns on the board
     */
    public static final int NUM_COLUMNS = 3;
    /**
     * Text color of a snake which has been removed
     */
    public static final Color REMOVED_COLOR = new Color(0x888888);
    /**
     * Line color of strikeouts
     */
    public static final Color REMOVED_LINE_COLOR = new Color(0xff0000);
    /**
     * Scaling of the grid
     */
    public static final int SCALE = 2;
    
    /**
     * Objects related to table structure (lines but can be expanded)
     */
    protected ArrayList<DrawableInterface> tableStructureObjects = new ArrayList<DrawableInterface>();
    /**
     * ArrayList of arrays for each for of the table. Contains SnakePiece and
     * some Text
     */
    protected ArrayList<Object[]> rowContents = new ArrayList<Object[]>();
    /**
     * The index in rowContents which has won
     */
    protected int winningRow = -1;
    
    /**
     * Text object declaring winner
     */
    protected Text winText;
    /**
     * SnakePiece representing the winner
     */
    protected SnakePiece winPiece;
    
    /**
     * Constructor
     *
     * @param canvas
     *            canvas these objects are on
     * @param world
     *            world which these objects reside
     */
    public ScoreBoard(DrawingCanvas canvas, World world) {
        super(canvas, world);
    }
    
    /**
     * Draws a given row on the table
     *
     * @param row
     *            row to draw
     */
    public void drawRow(int row) {
        for (int i = 0; i <= ScoreBoard.NUM_COLUMNS; i++) {
            int x1 = this.canvas.getWidth() - i * World.GRID_SIZE * ScoreBoard.SCALE;
            int x2 = this.canvas.getWidth() - (i - 1) * World.GRID_SIZE * ScoreBoard.SCALE;
            int y1 = row * World.GRID_SIZE * ScoreBoard.SCALE;
            int y2 = (row + 1) * World.GRID_SIZE * ScoreBoard.SCALE;
            this.tableStructureObjects.add(new Line(x1, y1, x2, y1, this.canvas));
            this.tableStructureObjects.add(new Line(x1, y2, x2, y2, this.canvas));
            this.tableStructureObjects.add(new Line(x1, y1, x1, y2, this.canvas));
            this.tableStructureObjects.add(new Line(x2, y1, x2, y2, this.canvas));
        }
    }
    
    /**
     * Strikeout a given row
     *
     * @param row
     *            row to strike out
     */
    public void strikeout(int row) {
        if (row == this.winningRow) {
            return;
        }
        Object[] rc = this.rowContents.get(row);
        
        ((Text) rc[1]).setColor(ScoreBoard.REMOVED_COLOR);
        ((Text) rc[2]).setColor(ScoreBoard.REMOVED_COLOR);
        Line removedLine = new Line(
                this.canvas.getWidth() - ScoreBoard.NUM_COLUMNS * World.GRID_SIZE * ScoreBoard.SCALE,
                row * World.GRID_SIZE * ScoreBoard.SCALE + World.GRID_SIZE * ScoreBoard.SCALE / 2,
                this.canvas.getWidth(),
                row * World.GRID_SIZE * ScoreBoard.SCALE + World.GRID_SIZE * ScoreBoard.SCALE / 2, this.canvas);
        removedLine.setColor(ScoreBoard.REMOVED_LINE_COLOR);
        this.tableStructureObjects.add(removedLine);
    }

    /**
     * whether the scoreboard has determined a winner
     * 
     * @return whether the scoreboard has determined a winner
     */
    public boolean hasWinner() {
        return this.winningRow != -1;
    }

    /**
     * Show the winner, if one is found
     * 
     * @postcondition resets winning row so hasWinner() is false
     */
    public void declareWinner() {
        if (!this.hasWinner()) {
            return;
        }
        this.winPiece = new SnakePiece(0, 0, World.GRID_SIZE * 6, World.GRID_SIZE * 6,
                ((SnakePiece) this.rowContents.get(this.winningRow)[0]).getColor(), this.canvas, this.world);
        this.winText = new Text("wins!", 0, 0, this.canvas);
        this.winText.setFont("Helvetica");
        this.winText.setFontSize(World.GRID_SIZE * 6);
        int totalWidth = (int) (World.GRID_SIZE * 8 + this.winText.getWidth());
        this.winPiece.moveTo(this.canvas.getWidth() / 2 - totalWidth / 2, this.canvas.getHeight() / 8);
        this.winText.moveTo(this.canvas.getWidth() / 2 - totalWidth / 2 + World.GRID_SIZE * 8,
                this.canvas.getHeight() / 8);
        this.winningRow = -1;
    }
    
    /**
     * Strikeout all rows
     */
    public void strikeoutAll() {
        for (int i = 0; i < this.objects.size(); i++) {
            this.strikeout(i);
        }
    }
    
    /**
     * Clear out the table structure and contents, reset arrays
     */
    public void removeAll() {
        for (int i = 0; i < this.tableStructureObjects.size(); i++) {
            this.tableStructureObjects.get(i).removeFromCanvas();
        }
        for (int i = 0; i < this.rowContents.size(); i++) {
            for (int j = 0; j < this.rowContents.get(i).length; j++) {
                if (this.rowContents.get(i)[j] instanceof Removable) {
                    ((Removable) this.rowContents.get(i)[j]).remove();
                }
                if (this.rowContents.get(i)[j] instanceof DrawableInterface) {
                    ((DrawableInterface) this.rowContents.get(i)[j]).removeFromCanvas();
                }
            }
        }
        if (this.winText != null) {
            this.winText.hide();
            this.winPiece.hide();
        }
        this.tableStructureObjects = new ArrayList<DrawableInterface>();
        this.objects = new ArrayList<Snake>();
    }
    
    /**
     * On tick, check and update length/ammo combos
     *
     * @see supersnek.ObjectController#tick()
     */
    public void tick() {
        int numAlive = 0, rowAlive = -1;
        for (int i = 0; i < this.objects.size(); i++) {
            Object[] rc = this.rowContents.get(i);
            
            ((Text) rc[1]).setText(this.objects.get(i).getLength());
            ((Text) rc[1]).moveTo(
                    this.canvas.getWidth() - 2 * World.GRID_SIZE * ScoreBoard.SCALE
                            + World.GRID_SIZE * ScoreBoard.SCALE / 2 - ((Text) rc[1]).getWidth() / 2,
                    i * World.GRID_SIZE * ScoreBoard.SCALE + World.GRID_SIZE * ScoreBoard.SCALE / 2
                            - ((Text) rc[1]).getHeight() / 2);
            
            ((Text) rc[2]).setText(this.objects.get(i).getAmmo());
            ((Text) rc[2]).moveTo(
                    this.canvas.getWidth() - 1 * World.GRID_SIZE * ScoreBoard.SCALE
                            + World.GRID_SIZE * ScoreBoard.SCALE / 2 - ((Text) rc[2]).getWidth() / 2,
                    i * World.GRID_SIZE * ScoreBoard.SCALE + World.GRID_SIZE * ScoreBoard.SCALE / 2
                            - ((Text) rc[2]).getHeight() / 2);
            
            if (this.objects.get(i).isRemoved()) {
                this.strikeout(i);
            } else {
                numAlive++;
                rowAlive = i;
            }
        }
        if (numAlive == 1) {
            this.winningRow = rowAlive;
        }
    }
    
    /**
     * Add a snake to the table
     *
     * @param s
     *            snake
     */
    public void addItem(Snake s) {
        super.addItem(s);
        this.drawRow(this.objects.size() - 1);
        this.initRow(this.objects.size() - 1, s);
    }
    
    /**
     * Initialize row with a snake
     *
     * @param row
     *            row to initialize
     * @param s
     *            snake to initialize it with
     */
    public void initRow(int row, Snake s) {
        // col 1 is color, col 2 is length, col 3 is ammo
        this.rowContents.add(new Object[ScoreBoard.NUM_COLUMNS]);
        
        Object[] rc = this.rowContents.get(row);
        
        // column 0
        rc[0] = new SnakePiece(this.canvas.getWidth() - 3 * World.GRID_SIZE * ScoreBoard.SCALE,
                row * World.GRID_SIZE * ScoreBoard.SCALE, World.GRID_SIZE * ScoreBoard.SCALE,
                World.GRID_SIZE * ScoreBoard.SCALE, s.getPrimaryColor(), this.canvas, this.world);
        
        // column 1
        rc[1] = new Text(s.getLength(), this.canvas.getWidth() - 2 * World.GRID_SIZE * ScoreBoard.SCALE,
                row * World.GRID_SIZE * ScoreBoard.SCALE, this.canvas);
        ((Text) rc[1]).setFontSize(World.GRID_SIZE * ScoreBoard.SCALE * 3 / 4);
        ((Text) rc[1]).moveTo(
                this.canvas.getWidth() - 2 * World.GRID_SIZE * ScoreBoard.SCALE + World.GRID_SIZE * ScoreBoard.SCALE / 2
                        - ((Text) rc[1]).getWidth() / 2,
                row * World.GRID_SIZE * ScoreBoard.SCALE + World.GRID_SIZE * ScoreBoard.SCALE / 2
                        - ((Text) rc[1]).getHeight() / 2);
        
        // column 2
        rc[2] = new Text(s.getAmmo(), this.canvas.getWidth() - 1 * World.GRID_SIZE * ScoreBoard.SCALE,
                row * World.GRID_SIZE * ScoreBoard.SCALE, this.canvas);
        ((Text) rc[2]).setFontSize(World.GRID_SIZE * ScoreBoard.SCALE * 3 / 4);
        ((Text) rc[2]).moveTo(
                this.canvas.getWidth() - 1 * World.GRID_SIZE * ScoreBoard.SCALE + World.GRID_SIZE * ScoreBoard.SCALE / 2
                        - ((Text) rc[2]).getWidth() / 2,
                row * World.GRID_SIZE * ScoreBoard.SCALE + World.GRID_SIZE * ScoreBoard.SCALE / 2
                        - ((Text) rc[2]).getHeight() / 2);
    }
    
    // Inapplicable
    /**
     * Inapplicable
     *
     * @see supersnek.ObjectController#addItem()
     */
    public void addItem() {
        /* not implemented, inapplicable */ }
}
