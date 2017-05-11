/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.awt.Graphics;
import objectdraw.Bounds;
import objectdraw.CanvasManager;
import objectdraw.Drawable;
import objectdraw.Drawable2DInterface;
import objectdraw.DrawingCanvas;
import objectdraw.Location;

abstract class Drawable2D
extends Drawable
implements Drawable2DInterface {
    Drawable2D() {
    }

    synchronized void draw(Graphics g) {
        super.draw(g);
    }

    abstract Bounds getBounds();

    public double getX() {
        return this.getBounds().getX();
    }

    public double getY() {
        return this.getBounds().getY();
    }

    public Location getLocation() {
        return this.getBounds().getLocation();
    }

    public boolean contains(Location point) {
        return this.getBounds().contains(point);
    }

    public abstract double getWidth();

    public abstract double getHeight();

    public boolean overlaps(Drawable2DInterface item) {
        boolean disjoint;
        double itemleft = item.getX();
        double itemright = itemleft + item.getWidth();
        double itemtop = item.getY();
        double itembottom = itemtop + item.getHeight();
        boolean bl = disjoint = this.getX() + this.getWidth() < itemleft || itemright < this.getX() || this.getY() + this.getHeight() < itemtop || itembottom < this.getY();
        if (this.canvasContent != null && this.getCanvas() == item.getCanvas() && !disjoint) {
            return true;
        }
        return false;
    }
}

