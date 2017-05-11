/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.awt.Point;
import java.awt.Rectangle;
import objectdraw.Location;

class Bounds {
    private double x;
    private double y;
    private double width;
    private double height;

    public Bounds(Location location, double width, double height) {
        this(location.getX(), location.getY(), width, height);
    }

    public Bounds(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public synchronized Location getLocation() {
        return new Location(this.x, this.y);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public synchronized void setLocation(Location point) {
        this.x = point.getX();
        this.y = point.getY();
    }

    public synchronized void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public synchronized void setWidth(double width) {
        this.width = width;
    }

    public synchronized void setHeight(double height) {
        this.height = height;
    }

    public synchronized void setDimensions(double width, double height) {
        this.setWidth(width);
        this.setHeight(height);
    }

    public synchronized void translate(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    public synchronized boolean contains(Location point) {
        if (point.getX() >= this.x && point.getY() >= this.y && point.getX() <= this.x + this.width && point.getY() <= this.y + this.height) {
            return true;
        }
        return false;
    }

    public synchronized boolean intersects(Bounds b) {
        double bleft = b.x;
        double bright = b.x + b.width;
        double btop = b.y;
        double bbottom = b.y + b.height;
        boolean disjoint = this.x + this.width < bleft || bright < this.x || this.y + this.height < btop || bbottom < this.y;
        return !disjoint;
    }

    public String toString() {
        return "Bounds at " + this.getLocation().toString() + " width=" + this.width + "height=" + this.height;
    }

    public synchronized Rectangle toRectangle() {
        Rectangle result = new Rectangle(this.getLocation().toPoint());
        result.width = (int)Math.rint(this.width);
        result.height = (int)Math.rint(this.height);
        return result;
    }
}

