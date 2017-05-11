/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import objectdraw.DrawableInterface;
import objectdraw.Location;

public interface Drawable2DInterface
extends DrawableInterface {
    public double getWidth();

    public double getHeight();

    public double getX();

    public double getY();

    public Location getLocation();

    public boolean overlaps(Drawable2DInterface var1);
}

