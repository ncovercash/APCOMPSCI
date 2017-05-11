/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import objectdraw.Controller;
import objectdraw.DrawingCanvas;
import objectdraw.JDrawingCanvas;
import objectdraw.Location;
import objectdraw.WindowControllerListener;

public class WindowController
extends Controller {
    protected DrawingCanvas canvas = new JDrawingCanvas();

    public void init() {
        super.setup();
        this.getContentPane().add((Component)((Object)this.canvas), "Center");
        this.validate();
        this.canvas.repaint();
        while (this.canvas.getWidth() == 0) {
            Thread.yield();
        }
        this.begin();
        this.validate();
        WindowControllerListener listener = new WindowControllerListener(this, this.canvas);
        this.canvas.addMouseListener(listener);
        this.canvas.addMouseMotionListener(listener);
    }

    public void begin() {
    }

    public void onMouseClick(Location point) {
    }

    public void onMousePress(Location point) {
    }

    public void onMouseRelease(Location point) {
    }

    public void onMouseEnter(Location point) {
    }

    public void onMouseExit(Location point) {
    }

    public void onMouseDrag(Location point) {
    }

    public void onMouseMove(Location point) {
    }
}

