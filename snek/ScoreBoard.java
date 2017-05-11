import objectdraw.DrawingCanvas;
import objectdraw.FilledRect;
import objectdraw.Text;
import objectdraw.Line;
import objectdraw.DrawableInterface;
import java.awt.Color;
import java.util.ArrayList;

public class ScoreBoard implements Tickable {
	public static final int NUM_COLUMNS = 2;
	public static final Color REMOVED_COLOR = new Color(0x888888);
	public static final Color REMOVED_LINE_COLOR = new Color(0xff0000);
	private World world;
	private DrawingCanvas canvas;
	private ArrayList<Snake> snakes = new ArrayList<Snake>();
	private ArrayList<DrawableInterface> tableStructureObjects = new ArrayList<DrawableInterface>();
	private ArrayList<Object[]> rowContents = new ArrayList<Object[]>();

	public ScoreBoard(DrawingCanvas canvas, World world) {
		this.canvas = canvas;
		this.world = world;
	}

	public void drawRow(int row) {
		for (int i=0; i<=NUM_COLUMNS; i++) {
			int x1 = canvas.getWidth()-(i*world.GRID_SIZE);
			int x2 = canvas.getWidth()-((i-1)*world.GRID_SIZE);
			int y1 = row*world.GRID_SIZE;
			int y2 = (row+1)*world.GRID_SIZE;
			tableStructureObjects.add(new Line(x1,y1,x2,y1,canvas));
			tableStructureObjects.add(new Line(x1,y2,x2,y2,canvas));
			tableStructureObjects.add(new Line(x1,y1,x1,y2,canvas));
			tableStructureObjects.add(new Line(x2,y1,x2,y2,canvas));
		}
	}

	public void strikeoutAll() {
		for (int i=0; i<snakes.size(); i++) {
			Object[] rc = rowContents.get(i);

			((Text)rc[1]).setColor(REMOVED_COLOR);
			Line removedLine = new Line(canvas.getWidth()-(NUM_COLUMNS*world.GRID_SIZE),(i*world.GRID_SIZE) + (world.GRID_SIZE/2),canvas.getWidth(),(i*world.GRID_SIZE) + (world.GRID_SIZE/2),canvas);
			removedLine.setColor(REMOVED_LINE_COLOR);
			tableStructureObjects.add(removedLine);
		}
	}

	public void removeAll() {
		for (int i=0; i<tableStructureObjects.size(); i++) {
			tableStructureObjects.get(i).removeFromCanvas();
		}
		for (int i=0; i<rowContents.size(); i++) {
			for (int j=0; j<rowContents.get(i).length; j++) {
				if (rowContents.get(i)[j] instanceof Removable) {
					((Removable)rowContents.get(i)[j]).remove();
				}
				if (rowContents.get(i)[j] instanceof DrawableInterface) {
					((DrawableInterface)rowContents.get(i)[j]).removeFromCanvas();
				}
			}
		}
		rowContents = new ArrayList<Object[]>();
		tableStructureObjects = new ArrayList<DrawableInterface>();
		snakes = new ArrayList<Snake>();
	}

	public void tick() {
		for (int i=0; i<snakes.size(); i++) {
			Object[] rc = rowContents.get(i);

			((Text)rc[1]).setText(snakes.get(i).getLength());
			((Text)rc[1]).moveTo(canvas.getWidth()-(1*world.GRID_SIZE) + (world.GRID_SIZE/2) - (((Text)rc[1]).getWidth()/2),
								(i*world.GRID_SIZE) + (world.GRID_SIZE/2) - (((Text)rc[1]).getHeight()/2));

			if (snakes.get(i).isRemoved()) {
				((Text)rc[1]).setColor(REMOVED_COLOR);
				Line removedLine = new Line(canvas.getWidth()-(NUM_COLUMNS*world.GRID_SIZE),(i*world.GRID_SIZE) + (world.GRID_SIZE/2),canvas.getWidth(),(i*world.GRID_SIZE) + (world.GRID_SIZE/2),canvas);
				removedLine.setColor(REMOVED_LINE_COLOR);
				tableStructureObjects.add(removedLine);
			}
		}
	}

	public void initRow(int row, Snake s) {
		// col 0 is color, col 1 is length
		rowContents.add(new Object[NUM_COLUMNS]);

		Object[] rc = rowContents.get(row);

		// column 0
		rc[0] = new SnakePiece(canvas.getWidth()-(2*world.GRID_SIZE), row*world.GRID_SIZE, s.getPrimaryColor(), canvas, world);

		// column 1
		rc[1] = new Text(s.getLength(), canvas.getWidth()-(1*world.GRID_SIZE), row*world.GRID_SIZE, canvas);
		((Text)rc[1]).setFontSize(world.GRID_SIZE*3/4);
		((Text)rc[1]).moveTo(canvas.getWidth()-(1*world.GRID_SIZE) + (world.GRID_SIZE/2) - (((Text)rc[1]).getWidth()/2),
												 (row*world.GRID_SIZE) + (world.GRID_SIZE/2) - (((Text)rc[1]).getHeight()/2));
		
	}

	public void addSnake(Snake s) {
		snakes.add(s);
		drawRow(snakes.size()-1);
		initRow(snakes.size()-1, s);
	}
}