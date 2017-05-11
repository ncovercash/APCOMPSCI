/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import objectdraw.Drawable;
import objectdraw.Drawable1DInterface;
import objectdraw.DrawingCanvas;
import objectdraw.Location;

public class Line
extends Drawable
implements Drawable1DInterface {
    protected Location start;
    protected Location end;

    public Line(Location start, Location end, DrawingCanvas canvas) {
        this.start = new Location(start);
        this.end = new Location(end);
        this.addToCanvas(canvas);
    }

    public Line(double startx, double starty, double endx, double endy, DrawingCanvas c) {
        this(new Location(startx, starty), new Location(endx, endy), c);
    }

    synchronized void draw(Graphics g) {
        super.draw(g);
        Point start_int = this.start.toPoint();
        Point end_int = this.end.toPoint();
        g.drawLine(start_int.x, start_int.y, end_int.x, end_int.y);
    }

    public synchronized boolean contains(Location point) {
        double buffer = 2.0;
        double diffX = this.end.getX() - this.start.getX();
        double diffY = this.end.getY() - this.start.getY();
        double diffLength = Math.sqrt(diffX * diffX + diffY * diffY);
        double dx = buffer * diffX / diffLength;
        double dy = buffer * diffY / diffLength;
        int[] xpoint = new int[4];
        int[] ypoint = new int[4];
        xpoint[0] = (int)(this.end.getX() + dx + dy);
        ypoint[0] = (int)(this.end.getY() + dy - dx);
        xpoint[1] = (int)(this.end.getX() + dx - dy);
        ypoint[1] = (int)(this.end.getY() + dy + dx);
        xpoint[2] = (int)(this.start.getX() - dx - dy);
        ypoint[2] = (int)(this.start.getY() - dy + dx);
        xpoint[3] = (int)(this.start.getX() - dx + dy);
        ypoint[3] = (int)(this.start.getY() - dy - dx);
        Polygon pg = new Polygon(xpoint, ypoint, 4);
        return pg.contains(point.getX(), point.getY());
    }

    public Location getStart() {
        return this.start;
    }

    public Location getEnd() {
        return this.end;
    }

    public synchronized void setStart(Location point) {
        this.start = new Location(point);
        this.setStateChanged();
    }

    public synchronized void setStart(double x, double y) {
        this.start = new Location(x, y);
        this.setStateChanged();
    }

    public synchronized void setEnd(Location point) {
        this.end = new Location(point);
        this.setStateChanged();
    }

    public synchronized void setEnd(double x, double y) {
        this.end = new Location(x, y);
        this.setStateChanged();
    }

    public synchronized void setEndPoints(Location start, Location end) {
        this.setStart(start);
        this.setEnd(end);
    }

    public synchronized void setEndPoints(double x1, double y1, double x2, double y2) {
        this.setStart(x1, y1);
        this.setEnd(x2, y2);
    }

    public String toString() {
        return "Line from " + this.start + " to " + this.end + " color:" + this.getColor().toString();
    }

    public synchronized void moveTo(double x, double y) {
        this.move(x - this.start.getX(), y - this.start.getY());
    }

    public synchronized void move(double dx, double dy) {
        this.start.translate(dx, dy);
        this.end.translate(dx, dy);
        this.setStateChanged();
    }
}

