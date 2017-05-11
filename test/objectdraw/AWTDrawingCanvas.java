/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import objectdraw.CanvasManager;
import objectdraw.DrawableIterator;
import objectdraw.DrawingCanvas;

public class AWTDrawingCanvas
extends Canvas
implements DrawingCanvas,
ComponentListener {
    public static final Dimension DEFAULT_PREFERRED_SIZE = new Dimension(400, 400);
    public final int REPAINT_DELAY = 50;
    private Dimension preferred_dim;
    private CanvasManager canvasContent;

    public AWTDrawingCanvas() {
        this(DEFAULT_PREFERRED_SIZE);
    }

    public AWTDrawingCanvas(int width, int height) {
        this(new Dimension(width, height));
    }

    public AWTDrawingCanvas(Dimension size) {
        this.canvasContent = new CanvasManager(size, this);
        this.preferred_dim = size;
    }

    public CanvasManager getCanvasContent() {
        return this.canvasContent;
    }

    public Dimension getPreferredSize() {
        return this.preferred_dim;
    }

    public void repaint() {
        super.repaint(50);
    }

    public synchronized void clear() {
        this.canvasContent.clear();
    }

    public synchronized void paint(Graphics g) {
        this.canvasContent.paint(g);
    }

    public synchronized void update(Graphics g) {
        this.paint(g);
    }

    public synchronized void enableAutoRepaint() {
        this.canvasContent.enableAutoRepaint();
    }

    public synchronized void disableAutoRepaint() {
        this.canvasContent.disableAutoRepaint();
    }

    public int getWidth() {
        return this.getSize().width;
    }

    public int getHeight() {
        return this.getSize().height;
    }

    public void componentResized(ComponentEvent e) {
        this.repaint();
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentShown(ComponentEvent e) {
    }

    public void componentHidden(ComponentEvent e) {
    }

    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        this.canvasContent.setStateChanged();
        return super.imageUpdate(img, infoflags, x, y, width, height);
    }

    public synchronized DrawableIterator getDrawableIterator() {
        return this.canvasContent.getDrawableIterator();
    }
}

