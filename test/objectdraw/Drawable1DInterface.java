/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import objectdraw.DrawableInterface;
import objectdraw.Location;

public interface Drawable1DInterface
extends DrawableInterface {
    public Location getStart();

    public Location getEnd();

    public void setStart(Location var1);

    public void setStart(double var1, double var3);

    public void setEnd(Location var1);

    public void setEnd(double var1, double var3);

    public void setEndPoints(Location var1, Location var2);

    public void setEndPoints(double var1, double var3, double var5, double var7);
}

