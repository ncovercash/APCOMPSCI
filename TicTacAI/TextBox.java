import objectdraw.*;
import java.awt.Color;

public class TextBox {
	final objectdraw.DrawingCanvas canvas;

	final String prefix;

	FilledRoundedRect box;
	Text scoreText;

	String score="";

	public TextBox(int x, int y, double w, double h, Color c, String prefix, objectdraw.DrawingCanvas canvas) {
		this.canvas = canvas;
		this.prefix = prefix;

		box = new FilledRoundedRect(x, y, w, h, h/3, h/3, canvas);
		box.setColor(c);

		scoreText = new Text(prefix + score, x+w/10, y+h/10, canvas);

		fillObjectWithText(box, scoreText);

		centerObjectInObject(scoreText, box);
	}

	public String getText() {
		return score;
	}

	public void setText(String newScore) {
		score = newScore;

		scoreText.setText(prefix+score);

		fillObjectWithText(box, scoreText);

		centerObjectInObject(scoreText, box);
	}

	public boolean contains(Location p) {
		return box.contains(p);
	}

	public boolean contains(double x, double y) {
		return box.contains(new Location(x, y));
	}

	private void fillObjectWithText(Drawable2DInterface obj, Text text) {
		if (text.getText().length() >= 1) {
			int fontSize=1;
			text.setFontSize(1);
			while (text.getWidth() < obj.getWidth()*.7 || text.getHeight() < obj.getHeight()*.7) {
				text.setFontSize(++fontSize);
			}
			text.setFontSize(--fontSize);
			text.setFontSize(--fontSize);
		}
	}

	private void centerObjectInObject(Drawable2DInterface innerObj, Drawable2DInterface outerObj) {
		innerObj.moveTo(outerObj.getX()+(outerObj.getWidth()/2)-(innerObj.getWidth()/2), outerObj.getY()+(outerObj.getHeight()/2)-(innerObj.getHeight()/2));
	}
}