/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.awt.Color;
import java.awt.geom.Arc2D;
import objectdraw.DrawingCanvas;
import objectdraw.Location;
import objectdraw.Rectangular;

abstract class Arc
extends Rectangular {
    protected double startAngle = 0.0;
    protected double arcAngle = 0.0;

    public Arc(Location origin, double width, double height, double startAngle, double arcAngle, DrawingCanvas c) {
        super(origin, width, height);
        this.startAngle = startAngle;
        this.arcAngle = arcAngle;
        this.addToCanvas(c);
    }

    public Arc(double x, double y, double width, double height, double startAngle, double arcAngle, DrawingCanvas c) {
        this(new Location(x, y), width, height, startAngle, arcAngle, c);
    }

    public Arc(Location p0, Location p1, double startAngle, double arcAngle, DrawingCanvas c) {
        super(p0, p1);
        this.startAngle = startAngle;
        this.arcAngle = arcAngle;
        this.addToCanvas(c);
    }

    public double getStartAngle() {
        return this.startAngle;
    }

    public double getArcAngle() {
        return this.arcAngle;
    }

    public synchronized void setStartAngle(double startAngle) {
        this.startAngle = startAngle;
        this.setStateChanged();
    }

    public synchronized void setArcAngle(double arcAngle) {
        this.arcAngle = arcAngle;
        this.setStateChanged();
    }

    public boolean contains(Location point) {
        Arc2D.Double shape = new Arc2D.Double(this.x, this.y, this.width, this.height, this.startAngle, this.arcAngle, 2);
        return shape.contains(point.getX(), point.getY());
    }

    public String toString() {
        return "Arc at " + this.x + "," + this.y + " width:" + this.width + " height:" + this.height + " startangle:" + this.startAngle + " arcangle:" + this.arcAngle + " color:" + this.getColor().toString();
    }
}

