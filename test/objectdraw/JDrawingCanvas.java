/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JComponent;
import objectdraw.CanvasManager;
import objectdraw.DrawableIterator;
import objectdraw.DrawingCanvas;

public class JDrawingCanvas
extends JComponent
implements DrawingCanvas,
ComponentListener {
    public static final Dimension DEFAULT_PREFERRED_SIZE = new Dimension(400, 400);
    public final int REPAINT_DELAY = 50;
    private CanvasManager canvasContent;
    private Dimension preferred_dim;

    public JDrawingCanvas() {
        this(DEFAULT_PREFERRED_SIZE);
    }

    public JDrawingCanvas(int width, int height) {
        this(new Dimension(width, height));
    }

    public JDrawingCanvas(Dimension size) {
        this.canvasContent = new CanvasManager(size, this);
        this.preferred_dim = size;
        this.setRequestFocusEnabled(true);
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

    public void clear() {
        this.canvasContent.clear();
    }

    public void paint(Graphics g) {
        this.canvasContent.paint(g);
    }

    public void update(Graphics g) {
        this.paint(g);
    }

    public void enableAutoRepaint() {
        this.canvasContent.enableAutoRepaint();
    }

    public void disableAutoRepaint() {
        this.canvasContent.disableAutoRepaint();
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

