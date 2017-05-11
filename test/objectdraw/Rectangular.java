/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import objectdraw.Bounds;
import objectdraw.Location;
import objectdraw.Resizable2D;
import objectdraw.Resizable2DInterface;

abstract class Rectangular
extends Resizable2D
implements Resizable2DInterface {
    protected double x;
    protected double y;
    protected double width;
    protected double height;

    public Rectangular(Location origin, double width, double height) {
        this(origin.getX(), origin.getY(), width, height);
    }

    public Rectangular(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rectangular(Location p0, Location p1) {
        this.width = p1.getX() - p0.getX();
        this.height = p1.getY() - p0.getY();
        if (this.width < 0.0) {
            this.x = p1.getX();
            if (this.height < 0.0) {
                this.y = p1.getY();
                this.height = - this.height;
            } else {
                this.y = p0.getY();
            }
            this.width = - this.width;
        } else {
            this.x = p0.getX();
            if (this.height < 0.0) {
                this.y = p1.getY();
                this.height = - this.height;
            } else {
                this.y = p0.getY();
            }
        }
    }

    Bounds getBounds() {
        return new Bounds(this.x, this.y, this.width, this.height);
    }

    void setBounds(Bounds b) {
        this.x = b.getX();
        this.y = b.getY();
        this.width = b.getWidth();
        this.height = b.getHeight();
        this.setStateChanged();
    }

    public void setSize(double width, double height) {
        this.width = width;
        this.height = height;
        this.setStateChanged();
    }

    public synchronized void setWidth(double width) {
        this.width = width;
        this.setStateChanged();
    }

    public synchronized void setHeight(double height) {
        this.height = height;
        this.setStateChanged();
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }
}

