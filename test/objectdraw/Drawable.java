/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import objectdraw.CanvasManager;
import objectdraw.DrawableInterface;
import objectdraw.DrawingCanvas;
import objectdraw.Location;

abstract class Drawable
implements Serializable,
DrawableInterface {
    protected transient CanvasManager canvasContent;
    protected Color color = Color.black;
    protected boolean shown = true;

    public void addToCanvas(DrawingCanvas c) {
        c.getCanvasContent().addToCanvas(this);
    }

    synchronized void setCanvasContent(CanvasManager cc) {
        if (this.canvasContent != null) {
            throw new IllegalStateException("Drawable object is already on a canvas");
        }
        this.canvasContent = cc;
    }

    public void removeFromCanvas() {
        if (this.canvasContent != null) {
            this.canvasContent.removeFromCanvas(this);
        } else {
            throw new IllegalStateException("Drawable object is not on canvas");
        }
    }

    protected void clearCanvasContent() {
        this.canvasContent = null;
    }

    public DrawingCanvas getCanvas() {
        return this.canvasContent != null ? this.canvasContent.getCanvas() : null;
    }

    protected void setStateChanged() {
        if (this.canvasContent != null) {
            this.canvasContent.setStateChanged();
        }
    }

    synchronized void draw(Graphics g) {
        g.setColor(this.color);
    }

    public synchronized void hide() {
        this.shown = false;
        this.setStateChanged();
    }

    public synchronized void show() {
        this.shown = true;
        this.setStateChanged();
    }

    public boolean isHidden() {
        return !this.shown;
    }

    public void moveTo(Location point) {
        this.moveTo(point.getX(), point.getY());
    }

    public synchronized void moveTo(double x, double y) {
        this.moveTo(new Location(x, y));
    }

    public abstract void move(double var1, double var3);

    public abstract boolean contains(Location var1);

    public synchronized void setColor(Color c) {
        this.color = c;
        this.setStateChanged();
    }

    public Color getColor() {
        return this.color;
    }

    public void sendForward() {
        if (this.canvasContent != null) {
            this.canvasContent.sendObjectForward(this);
        } else {
            throw new IllegalStateException("Drawble object not currently displayed on canvas");
        }
    }

    public void sendBackward() {
        if (this.canvasContent != null) {
            this.canvasContent.sendObjectBackward(this);
        } else {
            throw new IllegalStateException("Drawble object not currently displayed on canvas");
        }
    }

    public void sendToFront() {
        if (this.canvasContent != null) {
            this.canvasContent.sendObjectToFront(this);
        } else {
            throw new IllegalStateException("Drawble object not currently displayed on canvas");
        }
    }

    public void sendToBack() {
        if (this.canvasContent != null) {
            this.canvasContent.sendObjectToBack(this);
        } else {
            throw new IllegalStateException("Drawble object not currently displayed on canvas");
        }
    }
}

