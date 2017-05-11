/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.awt.Graphics;
import objectdraw.Arc;
import objectdraw.DrawingCanvas;
import objectdraw.Location;
import objectdraw.Resizable2DInterface;

public class FilledArc
extends Arc
implements Resizable2DInterface {
    public FilledArc(Location origin, double width, double height, double startAngle, double arcAngle, DrawingCanvas canvas) {
        this(origin.getX(), origin.getY(), width, height, startAngle, arcAngle, canvas);
    }

    public FilledArc(double x, double y, double width, double height, double startAngle, double arcAngle, DrawingCanvas canvas) {
        super(x, y, width, height, startAngle, arcAngle, canvas);
    }

    public FilledArc(Location p0, Location p1, double startAngle, double arcAngle, DrawingCanvas canvas) {
        super(p0, p1, startAngle, arcAngle, canvas);
    }

    synchronized void draw(Graphics g) {
        super.draw(g);
        g.fillArc((int)Math.round(this.x), (int)Math.round(this.y), (int)Math.round(this.width), (int)Math.round(this.height), (int)this.startAngle, (int)this.arcAngle);
    }

    public String toString() {
        return "Filled" + super.toString();
    }
}

