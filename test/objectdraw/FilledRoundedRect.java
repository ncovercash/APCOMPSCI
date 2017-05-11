/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.awt.Graphics;
import objectdraw.DrawingCanvas;
import objectdraw.Location;
import objectdraw.Resizable2DInterface;
import objectdraw.RoundedRect;

public class FilledRoundedRect
extends RoundedRect
implements Resizable2DInterface {
    public FilledRoundedRect(Location origin, double width, double height, double arcWidth, double arcHeight, DrawingCanvas canvas) {
        this(origin.getX(), origin.getY(), width, height, arcWidth, arcHeight, canvas);
    }

    public FilledRoundedRect(double x, double y, double width, double height, double arcWidth, double arcHeight, DrawingCanvas canvas) {
        super(x, y, width, height, arcWidth, arcHeight, canvas);
    }

    public FilledRoundedRect(Location p0, Location p1, double arcWidth, double arcHeight, DrawingCanvas canvas) {
        super(p0, p1, arcWidth, arcHeight, canvas);
    }

    synchronized void draw(Graphics g) {
        super.draw(g);
        g.fillRoundRect((int)Math.round(this.x), (int)Math.round(this.y), (int)Math.round(this.width), (int)Math.round(this.height), (int)this.arcWidth, (int)this.arcHeight);
    }

    public String toString() {
        return "Filled" + super.toString();
    }
}

