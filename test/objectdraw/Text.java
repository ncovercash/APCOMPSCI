/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import objectdraw.Bounds;
import objectdraw.CanvasManager;
import objectdraw.Drawable2D;
import objectdraw.Drawable2DInterface;
import objectdraw.DrawingCanvas;
import objectdraw.Location;

public class Text
extends Drawable2D
implements Drawable2DInterface {
    protected Location origin;
    protected double width;
    protected double height;
    protected double baseHeight;
    protected Font font;
    protected String text;
    protected DrawingCanvas canvas;
    protected Graphics g;
    protected FontMetrics fm;

    public Text(Object text, Location origin, DrawingCanvas canvas) {
        if (text == null) {
            throw new NullPointerException("Object to be displayed is undefined in Text construction");
        }
        if (origin == null) {
            throw new NullPointerException("Location specified in Text construction is undefined");
        }
        if (canvas == null) {
            throw new NullPointerException("Canvas parameter is undefined in Text construction");
        }
        this.canvas = canvas;
        this.text = text.toString();
        this.origin = new Location(origin);
        this.addToCanvas(canvas);
    }

    public Text(Object text, double x, double y, DrawingCanvas canvas) {
        this(text, new Location(x, y), canvas);
    }

    public Text(boolean text, Location origin, DrawingCanvas canvas) {
        this("" + text, origin, canvas);
    }

    public Text(boolean text, double x, double y, DrawingCanvas canvas) {
        this("" + text, new Location(x, y), canvas);
    }

    public Text(char text, Location origin, DrawingCanvas canvas) {
        this("" + text, origin, canvas);
    }

    public Text(char text, double x, double y, DrawingCanvas canvas) {
        this("" + text, new Location(x, y), canvas);
    }

    public Text(long text, Location origin, DrawingCanvas canvas) {
        this("" + text, origin, canvas);
    }

    public Text(long text, double x, double y, DrawingCanvas canvas) {
        this("" + text, new Location(x, y), canvas);
    }

    public Text(double text, Location origin, DrawingCanvas canvas) {
        this("" + text, origin, canvas);
    }

    public Text(double text, double x, double y, DrawingCanvas canvas) {
        this("" + text, new Location(x, y), canvas);
    }

    synchronized void draw(Graphics g) {
        super.draw(g);
        this.g = g;
        Font defaultFont = g.getFont();
        if (this.font == null) {
            this.font = defaultFont;
        }
        g.setFont(this.font);
        Point loc = this.origin.toPoint();
        this.fm = g.getFontMetrics(this.getFont());
        double baseHeight = this.fm.getMaxAscent();
        g.drawString(this.text, loc.x, loc.y + (int)Math.rint(baseHeight));
        g.setFont(defaultFont);
    }

    public synchronized void addToCanvas(DrawingCanvas canvas) {
        this.canvas = canvas;
        this.g = null;
        this.fm = null;
        super.addToCanvas(canvas);
    }

    private void calculateSize() {
        if (this.canvas != null) {
            if (this.g == null) {
                this.g = this.canvas.getGraphics();
            }
            if (this.g != null) {
                if (this.fm == null) {
                    this.fm = this.g.getFontMetrics(this.getFont());
                }
                if (this.fm != null) {
                    this.width = this.fm.stringWidth(this.text);
                    this.baseHeight = this.fm.getMaxAscent();
                    this.height = this.baseHeight + (double)this.fm.getMaxDescent();
                }
            }
        }
    }

    public double getWidth() {
        this.calculateSize();
        return this.width;
    }

    public double getHeight() {
        this.calculateSize();
        return this.height;
    }

    Bounds getBounds() {
        double x = this.origin.getX();
        double y = this.origin.getY();
        this.calculateSize();
        Bounds bounds = new Bounds(x, y, this.width, this.height);
        return bounds;
    }

    public synchronized void moveTo(Location point) {
        this.origin = new Location(point);
        this.setStateChanged();
    }

    public synchronized void move(double dx, double dy) {
        this.origin.translate(dx, dy);
        this.setStateChanged();
    }

    public Font getFont() {
        if (this.font == null && this.canvas != null) {
            if (this.g == null) {
                this.g = this.canvas.getGraphics();
            }
            if (this.g != null) {
                this.font = this.g.getFont();
            }
        }
        return this.font;
    }

    public synchronized void setText(String text) {
        this.text = text;
        this.canvasContent.setStateChanged();
    }

    public synchronized void setText(long text) {
        this.setText(String.valueOf(text));
    }

    public synchronized void setText(boolean text) {
        this.setText(String.valueOf(text));
    }

    public synchronized void setText(char text) {
        this.setText(String.valueOf(text));
    }

    public synchronized void setText(double text) {
        this.setText(String.valueOf(text));
    }

    public synchronized void setText(Object text) {
        if (text == null) {
            throw new NullPointerException("Parameter to setText is undefined");
        }
        this.setText(text.toString());
    }

    public String getText() {
        return this.text;
    }

    public synchronized void setFont(Font f) {
        this.font = f;
        this.fm = null;
        this.setStateChanged();
    }

    public synchronized void setFont(String fname) {
        this.setFont(new Font(fname, this.getFont().getStyle(), this.getFont().getSize()));
    }

    public synchronized void setFontSize(int size) {
        this.setFont(new Font(this.getFont().getName(), this.getFont().getStyle(), size));
    }

    public synchronized void setItalic() {
        this.setItalic(true);
    }

    public synchronized void setItalic(boolean bool) {
        if (bool) {
            if (this.getFont().getStyle() == 1) {
                this.setFont(new Font(this.getFont().getName(), 3, this.getFont().getSize()));
            } else {
                this.setFont(new Font(this.getFont().getName(), 2, this.getFont().getSize()));
            }
        } else if (this.getFont().getStyle() == 3) {
            this.setFont(new Font(this.getFont().getName(), 1, this.getFont().getSize()));
        } else {
            this.setFont(new Font(this.getFont().getName(), 0, this.getFont().getSize()));
        }
    }

    public synchronized void setBold() {
        this.setBold(true);
    }

    public synchronized void setBold(boolean bool) {
        if (bool) {
            if (this.getFont().getStyle() == 2) {
                this.setFont(new Font(this.getFont().getName(), 3, this.getFont().getSize()));
            } else {
                this.setFont(new Font(this.getFont().getName(), 1, this.getFont().getSize()));
            }
        } else if (this.getFont().getStyle() == 3) {
            this.setFont(new Font(this.getFont().getName(), 2, this.getFont().getSize()));
        } else {
            this.setFont(new Font(this.getFont().getName(), 0, this.getFont().getSize()));
        }
    }

    public synchronized void setPlain() {
        this.setFont(new Font(this.getFont().getName(), 0, this.getFont().getSize()));
    }

    public String toString() {
        return "Text at " + this.origin + " text:" + this.text + " font:" + this.getFont().toString() + " color:" + this.getColor().toString();
    }
}

