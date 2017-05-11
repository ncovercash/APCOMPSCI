/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import objectdraw.AWTController;
import objectdraw.AWTDrawingCanvas;
import objectdraw.AWTInitializer;
import objectdraw.AWTWindowControllerListener;
import objectdraw.ActiveObject;
import objectdraw.DrawingCanvas;
import objectdraw.Location;

public class AWTWindowController
extends AWTController {
    protected DrawingCanvas canvas = new AWTDrawingCanvas();

    public void init() {
        super.setup();
        this.add((Component)((Object)this.canvas), "Center");
        this.canvas.repaint();
        ActiveObject.initializeEventList();
        this.validate();
        new objectdraw.AWTInitializer(this);
    }

    synchronized boolean callBegin() {
        boolean beginWasCalled = false;
        if (this.canvas.getGraphics() != null && this.canvas.getWidth() > 0 && this.canvas.getHeight() > 0) {
            beginWasCalled = super.callBegin();
            AWTWindowControllerListener myListener = new AWTWindowControllerListener(this, this.canvas);
            this.canvas.addMouseListener(myListener);
            this.canvas.addMouseMotionListener(myListener);
        }
        return beginWasCalled;
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

