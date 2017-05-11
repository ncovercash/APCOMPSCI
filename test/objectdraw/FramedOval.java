/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.awt.Graphics;
import objectdraw.DrawingCanvas;
import objectdraw.Location;
import objectdraw.Oval;
import objectdraw.Resizable2DInterface;

public class FramedOval
extends Oval
implements Resizable2DInterface {
    public FramedOval(Location origin, double width, double height, DrawingCanvas canvas) {
        this(origin.getX(), origin.getY(), width, height, canvas);
    }

    public FramedOval(double x, double y, double width, double height, DrawingCanvas canvas) {
        super(x, y, width, height, canvas);
    }

    public FramedOval(Location p0, Location p1, DrawingCanvas canvas) {
        super(p0, p1, canvas);
    }

    synchronized void draw(Graphics g) {
        super.draw(g);
        g.drawOval((int)Math.round(this.x), (int)Math.round(this.y), (int)Math.round(this.width), (int)Math.round(this.height));
    }

    public String toString() {
        return "Filled" + super.toString();
    }
}

