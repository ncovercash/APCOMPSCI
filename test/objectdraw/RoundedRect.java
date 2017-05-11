/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.awt.Color;
import java.awt.geom.RoundRectangle2D;
import objectdraw.DrawingCanvas;
import objectdraw.Location;
import objectdraw.Rectangular;

abstract class RoundedRect
extends Rectangular {
    protected double arcWidth;
    protected double arcHeight;

    public RoundedRect(double x, double y, double width, double height, double arcWidth, double arcHeight, DrawingCanvas canvas) {
        super(x, y, width, height);
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        this.addToCanvas(canvas);
    }

    public RoundedRect(Location origin, double width, double height, double arcWidth, double arcHeight, DrawingCanvas canvas) {
        this(origin.getX(), origin.getY(), width, height, arcWidth, arcHeight, canvas);
    }

    public RoundedRect(Location p0, Location p1, double arcWidth, double arcHeight, DrawingCanvas canvas) {
        super(p0, p1);
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        this.addToCanvas(canvas);
    }

    public double getArcWidth() {
        return this.arcWidth;
    }

    public double getArcHeight() {
        return this.arcHeight;
    }

    public synchronized void setArcWidth(double arcWidth) {
        this.arcWidth = arcWidth;
        this.setStateChanged();
    }

    public synchronized void setArcHeight(double arcHeight) {
        this.arcHeight = arcHeight;
        this.setStateChanged();
    }

    public boolean contains(Location point) {
        RoundRectangle2D.Double shape = new RoundRectangle2D.Double(this.x, this.y, this.width, this.height, this.arcWidth, this.arcHeight);
        return shape.contains(point.getX(), point.getY());
    }

    public String toString() {
        return "RoundedRect at " + this.x + "," + this.y + " width:" + this.width + " height:" + this.height + " arcWidth:" + this.arcWidth + " arcHeight:" + this.arcHeight + " color:" + this.getColor().toString();
    }
}

