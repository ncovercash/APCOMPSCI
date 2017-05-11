/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import objectdraw.Bounds;
import objectdraw.Drawable2D;
import objectdraw.Location;

abstract class Resizable2D
extends Drawable2D {
    Resizable2D() {
    }

    public synchronized void moveTo(Location point) {
        Bounds b = this.getBounds();
        b.setLocation(point);
        this.setBounds(b);
        this.setStateChanged();
    }

    public synchronized void move(double dx, double dy) {
        Bounds b = this.getBounds();
        b.translate(dx, dy);
        this.setBounds(b);
        this.setStateChanged();
    }

    public abstract void setSize(double var1, double var3);

    public abstract void setWidth(double var1);

    public abstract void setHeight(double var1);

    abstract void setBounds(Bounds var1);
}

