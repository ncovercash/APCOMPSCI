/*
 * Decompiled with CFR 0_121.
 */
package objectdraw;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentListener;
import java.awt.image.ImageObserver;
import java.util.Vector;
import objectdraw.Drawable;
import objectdraw.DrawableInterface;
import objectdraw.DrawableIterator;
import objectdraw.DrawingCanvas;

class CanvasManager {
    public static final Dimension DEFAULT_PREFERRED_SIZE = new Dimension(400, 400);
    private DrawingCanvas canvas;
    private Dimension preferred_dim;
    private Vector objects = new Vector();
    private Image buffer;
    private Dimension bufSize;
    private Graphics buffer_gc;
    private boolean stateChanged = false;
    private boolean enableRepaint = true;
    private boolean repaintPending = false;

    public CanvasManager(Dimension size, DrawingCanvas canvas) {
        this.preferred_dim = size;
        this.canvas = canvas;
        ((Component)((Object)canvas)).setForeground(Color.black);
        ((Component)((Object)canvas)).setBackground(Color.white);
        canvas.addComponentListener(canvas);
    }

    protected DrawingCanvas getCanvas() {
        return this.canvas;
    }

    synchronized void addToCanvas(Drawable d) {
        d.setCanvasContent(this);
        this.objects.addElement(d);
        this.setStateChanged();
    }

    synchronized void removeFromCanvas(Drawable d) {
        if (!this.objects.removeElement(d)) {
            throw new IllegalStateException("Drawble object not currently displayed on canvas");
        }
        d.clearCanvasContent();
        this.setStateChanged();
    }

    synchronized void clear() {
        int i = 0;
        while (i < this.objects.size()) {
            ((Drawable)this.objects.elementAt(i)).clearCanvasContent();
            ++i;
        }
        this.objects.removeAllElements();
        this.setStateChanged();
    }

    void setStateChanged() {
        this.stateChanged = true;
        if (this.enableRepaint && !this.repaintPending) {
            this.repaintPending = true;
            this.canvas.repaint();
        }
    }

    synchronized void paint(Graphics g) {
        this.repaintPending = false;
        if (this.buffer == null || !this.bufSize.equals(this.canvas.getSize())) {
            this.stateChanged = true;
            this.createBuffer();
        }
        if (this.stateChanged) {
            this.redraw();
        }
        g.drawImage(this.buffer, 0, 0, this.canvas);
    }

    private synchronized void redraw() {
        this.stateChanged = false;
        this.buffer_gc.setColor(((Component)((Object)this.canvas)).getBackground());
        this.buffer_gc.fillRect(0, 0, this.canvas.getSize().width, this.canvas.getSize().height);
        int i = 0;
        while (i < this.objects.size()) {
            Drawable nextDrawable = (Drawable)this.objects.elementAt(i);
            if (!nextDrawable.isHidden()) {
                nextDrawable.draw(this.buffer_gc);
            }
            ++i;
        }
    }

    public synchronized void enableAutoRepaint() {
        this.enableRepaint = true;
        if (this.stateChanged) {
            this.setStateChanged();
        }
    }

    public synchronized void disableAutoRepaint() {
        this.enableRepaint = false;
    }

    synchronized void sendObjectToFront(Drawable object) {
        if (!this.objects.removeElement(object)) {
            throw new IllegalStateException("Drawble object not currently displayed on canvas");
        }
        this.objects.addElement(object);
        this.setStateChanged();
    }

    synchronized void sendObjectToBack(Drawable object) {
        if (!this.objects.removeElement(object)) {
            throw new IllegalStateException("Drawble object not currently displayed on canvas");
        }
        this.objects.insertElementAt(object, 0);
        this.setStateChanged();
    }

    synchronized void sendObjectForward(Drawable object) {
        int index = this.objects.indexOf(object);
        if (index >= 0) {
            if (index < this.objects.size() - 1) {
                DrawableInterface next = (DrawableInterface)this.objects.elementAt(index + 1);
                this.objects.setElementAt(next, index);
                this.objects.setElementAt(object, index + 1);
                this.setStateChanged();
            }
        } else {
            throw new IllegalStateException("Drawble object not currently displayed on canvas");
        }
    }

    synchronized void sendObjectBackward(Drawable object) {
        int index = this.objects.indexOf(object);
        if (index > 0) {
            DrawableInterface previous = (DrawableInterface)this.objects.elementAt(index - 1);
            this.objects.setElementAt(previous, index);
            this.objects.setElementAt(object, index - 1);
            this.setStateChanged();
        } else if (index < 0) {
            throw new IllegalStateException("Drawble object not currently displayed on canvas");
        }
    }

    public synchronized DrawableIterator getDrawableIterator() {
        return new DrawableIterator(this.objects);
    }

    private synchronized void createBuffer() {
        Image tempbuffer = null;
        this.bufSize = this.canvas.getSize();
        if (this.bufSize.width > 0 && this.bufSize.height > 0) {
            tempbuffer = this.canvas.createImage(this.canvas.getWidth(), this.canvas.getHeight());
        }
        this.buffer = tempbuffer;
        if (this.buffer != null) {
            this.buffer_gc = this.buffer.getGraphics();
        }
    }
}

