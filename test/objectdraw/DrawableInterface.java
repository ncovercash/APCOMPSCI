/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.awt.Color;
import objectdraw.DrawingCanvas;
import objectdraw.Location;

public interface DrawableInterface {
    public void hide();

    public void show();

    public void addToCanvas(DrawingCanvas var1);

    public void removeFromCanvas();

    public DrawingCanvas getCanvas();

    public boolean isHidden();

    public void moveTo(Location var1);

    public void moveTo(double var1, double var3);

    public void move(double var1, double var3);

    public void setColor(Color var1);

    public Color getColor();

    public void sendForward();

    public void sendBackward();

    public void sendToFront();

    public void sendToBack();

    public boolean contains(Location var1);
}

