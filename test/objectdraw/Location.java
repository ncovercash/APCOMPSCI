/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.awt.Point;
import java.io.Serializable;

public class Location
implements Serializable {
    private double x;
    private double y;

    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Location(Location point) {
        this(point.x, point.y);
    }

    public Location(Point point) {
        this(point.x, point.y);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public synchronized double distanceTo(Location point) {
        return Math.sqrt(Math.pow(point.x - this.x, 2.0) + Math.pow(point.y - this.y, 2.0));
    }

    public synchronized Point toPoint() {
        return new Point((int)Math.rint(this.x), (int)Math.rint(this.y));
    }

    public synchronized void translate(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    public String toString() {
        return "Location[" + this.x + "," + this.y + "]";
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Location)) {
            return false;
        }
        if (this.x == ((Location)obj).getX() && this.y == ((Location)obj).getY()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return new Double(1000000.0 * this.x + this.y).hashCode();
    }
}

