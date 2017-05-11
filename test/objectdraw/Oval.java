/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import objectdraw.DrawingCanvas;
import objectdraw.Location;
import objectdraw.Rectangular;

abstract class Oval
extends Rectangular {
    public Oval(Location origin, double width, double height, DrawingCanvas canvas) {
        this(origin.getX(), origin.getY(), width, height, canvas);
    }

    public Oval(double x, double y, double width, double height, DrawingCanvas canvas) {
        super(x, y, width, height);
        this.addToCanvas(canvas);
    }

    public Oval(Location p0, Location p1, DrawingCanvas canvas) {
        this(p0, p1.getX() - p0.getX(), p1.getY() - p0.getY(), canvas);
    }

    public boolean contains(Location point) {
        Ellipse2D.Double shape = new Ellipse2D.Double(this.x, this.y, this.width, this.height);
        return shape.contains(point.getX(), point.getY());
    }

    public String toString() {
        return "Oval at " + this.x + "," + this.y + " width:" + this.width + " height:" + this.height + " color:" + this.getColor().toString();
    }
}

