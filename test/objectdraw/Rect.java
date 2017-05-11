/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.awt.Color;
import objectdraw.DrawingCanvas;
import objectdraw.Location;
import objectdraw.Rectangular;

abstract class Rect
extends Rectangular {
    public Rect(Location origin, double width, double height, DrawingCanvas canvas) {
        this(origin.getX(), origin.getY(), width, height, canvas);
    }

    public Rect(double x, double y, double width, double height, DrawingCanvas canvas) {
        super(x, y, width, height);
        this.addToCanvas(canvas);
    }

    public Rect(Location p0, Location p1, DrawingCanvas canvas) {
        super(p0, p1);
        this.addToCanvas(canvas);
    }

    public String toString() {
        return "Rect at " + this.x + "," + this.y + " width:" + this.width + " height:" + this.height + " color:" + this.getColor().toString();
    }
}

