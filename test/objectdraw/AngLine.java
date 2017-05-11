/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import objectdraw.DrawingCanvas;
import objectdraw.Line;
import objectdraw.Location;

public class AngLine
extends Line {
    public AngLine(Location start, double length, double radianAngle, DrawingCanvas canvas) {
        this(start.getX(), start.getY(), length, radianAngle, canvas);
    }

    public AngLine(double startX, double startY, double length, double radianAngle, DrawingCanvas canvas) {
        super(startX, startY, startX + length * Math.cos(radianAngle), startY - length * Math.sin(radianAngle), canvas);
    }
}

